package com.ttc.ch2.audit;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.support.TransactionTemplate;

import com.ttc.ch2.common.SpringContext;

public class AsynchronousAppender implements Appender {


    private static Logger logger = LoggerFactory.getLogger(AsynchronousAppender.class);
    
    private ThreadPoolExecutor executor;
    private ScheduledThreadPoolExecutor sexecutor;

    private TransactionTemplate transactionTemplate;
    
    private DataSource dataSource;
    
    private int threadsCount;

    private Queue<Struct> queue = new ConcurrentLinkedQueue<Struct>();

    private ThreadGroup threadGroup;

    private List<ScheduledFuture<?>> futures = new ArrayList<>();
    
    private AtomicLong rejectedCounter =  new AtomicLong(0L);
    private AtomicLong structCounter =  new AtomicLong(0L);

    
    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    
    @PostConstruct
    public void initializeThreads () {
//        initializeByThreadGroups();
//        initializeByExecutors();
        initializeByScheduledExecutors();
    }
    
    @PreDestroy
    public void destroy() {
        if (executor != null) {
            executor.shutdown();
        }
        
        if (threadGroup != null) {
            Thread[] threads = new Thread[threadGroup.activeCount()];
            threadGroup.enumerate(threads);
            for (Thread thread : threads) {
                thread.interrupt();
            }
            
            threadGroup.stop();
        }
    }

    private void initializeByThreadGroups() {
        int n = Math.max(1, threadsCount);
        
        threadGroup = new ThreadGroup("Audits");
        
        AuditManager auditManager = null;
        try {
            auditManager = SpringContext.getApplicationContext().getBean(AuditManager.class);
        } catch (BeansException e) {
        }
        
        Thread[] threads = new Thread[n];
       
        InnerDbAsynchronousAppender appender = new InnerDbAsynchronousAppender();
        appender.setTransactionTemplate(transactionTemplate);
        appender.setDataSource(dataSource);
        appender.setQueue(queue);
        appender.setAuditManager(auditManager);
        
        AppenderThread ru = new AppenderThread();
        
        ru.setAppender(appender);
        ru.setQueue(queue);
        ru.setMinPack(1);
        ru.setMaxPack(1000_000);
        ru.setNapTime(5_000L);
        
        threads[0] = new Thread(threadGroup, ru, "Audit-0");
        
        for (int i = 1; i < threadsCount; i++) {
            appender = new InnerDbAsynchronousAppender();
            appender.setTransactionTemplate(transactionTemplate);
            appender.setDataSource(dataSource);
            appender.setQueue(queue);
            appender.setAuditManager(auditManager);
            
            ru = new AppenderThread();
            ru.setAppender(appender);
            ru.setQueue(queue);
            ru.setMinPack(1000);
            ru.setMaxPack(0);
            ru.setNapTime(60_000L);
            threads[i] = new Thread(threadGroup, ru, "Audit-" + i);
        }
        
        for (Thread t : threads) {
            t.start();
        }
        
        logger.info("started {} audit appender threads in the group {}", n, threadGroup.getName());
    }
    
    private void initializeByExecutors() {
        int n = Math.max(1, threadsCount);
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        
        
//        ThreadGroup group = new ThreadGroup("Audits");
        
        AuditManager auditManager = null;
        try {
            auditManager = SpringContext.getApplicationContext().getBean(AuditManager.class);
        } catch (BeansException e) {
        };
        
        Runnable[] runs = new Runnable[n];
        
        InnerDbAsynchronousAppender appender = new InnerDbAsynchronousAppender();
        appender.setTransactionTemplate(transactionTemplate);
        appender.setDataSource(dataSource);
        appender.setQueue(queue);
        appender.setAuditManager(auditManager);
        
        AppenderThread ru = new AppenderThread();
        
        ru.setAppender(appender);
        ru.setQueue(queue);
        ru.setMinPack(1);
        ru.setMaxPack(1000_000);
        ru.setNapTime(5_000L);
        runs[0] = ru;
        
        for (int i = 1; i < threadsCount; i++) {
            appender = new InnerDbAsynchronousAppender();
            appender.setTransactionTemplate(transactionTemplate);
            appender.setDataSource(dataSource);
            appender.setQueue(queue);
            appender.setAuditManager(auditManager);
            
            ru = new AppenderThread();
            ru.setAppender(appender);
            ru.setQueue(queue);
            ru.setMinPack(1000);
            ru.setMaxPack(0);
            ru.setNapTime(60_000L);
            
            runs[i] = ru;
        }
        
        for (Runnable runnable : runs) {
            executor.execute(runnable);
        }
                
        logger.info("started {} audit appender threads by the executor {}", n);
    }
    
    private void initializeByScheduledExecutors() {
        int n = Math.max(1, threadsCount);
        sexecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);
        
        AuditManager auditManager = null;
        try {
            auditManager = SpringContext.getApplicationContext().getBean(AuditManager.class);
        } catch (BeansException e) {
        };
        
        AppenderCallable[] runs = new AppenderCallable[n];
        
        InnerDbAsynchronousAppender appender = new InnerDbAsynchronousAppender();
        appender.setTransactionTemplate(transactionTemplate);
        appender.setDataSource(dataSource);
        appender.setQueue(queue);
        appender.setAuditManager(auditManager);
        
        AppenderCallable ru = new AppenderCallable();
        
        ru.setAppender(appender);
        ru.setQueue(queue);
        ru.setMinPack(1);
        ru.setMaxPack(1_000_000);
//        ru.setNapTime(5_000L);
        runs[0] = ru;
        
        for (int i = 1; i < threadsCount; i++) {
            appender = new InnerDbAsynchronousAppender();
            appender.setTransactionTemplate(transactionTemplate);
            appender.setDataSource(dataSource);
            appender.setQueue(queue);
            appender.setAuditManager(auditManager);
            
            ru = new AppenderCallable();
            ru.setAppender(appender);
            ru.setQueue(queue);
            ru.setMinPack(1000);
            ru.setMaxPack(0);
//            ru.setNapTime(60_000L);
            
            runs[i] = ru;
        }
        
        for (int i = 0; i < runs.length; i++) {
            ScheduledFuture<?> future = sexecutor.scheduleAtFixedRate(runs[i], 1 + i*2L, 20L, TimeUnit.SECONDS);
            futures.add(future);
//            sexecutor.schedule
            
        }
        
        logger.info("started {} audit appender threads by the scheduled executor {}", n);
    }

    @Scheduled(fixedRate = 60_000)
    public void controlExecutedTasks() {
        if (sexecutor == null) {
            return;
        }
        
        int activeCount = sexecutor.getActiveCount();
        
        int dones = 0;
        int cancelled = 0;
        for (ScheduledFuture<?> future : futures) {
            if (future.isDone()) {
                dones++;
            }
            if (future.isCancelled()) {
                cancelled++;
            }
        }
        logger.debug("there are {} threads in the executor, {} done, {} cancelled, {}/{}/{} structs added/rejected/queued", activeCount, dones, cancelled, structCounter.get(), rejectedCounter.get(), queue.size());
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int getThreadsCount() {
        return threadsCount;
    }

    public void setThreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
    }
    
    @Override
    public void storePre(Struct struct) {   
        structCounter.incrementAndGet();
        if (!queue.offer(struct)){
            rejectedCounter.incrementAndGet();
        };
    }
    
    @Override
    public void storePost(Struct struct) {
        structCounter.incrementAndGet();
        if (!queue.offer(struct)) {            
            rejectedCounter.incrementAndGet();
        }
    }
    
    @Override
    public void storeFail(Struct struct) {
        structCounter.incrementAndGet();
        if (!queue.offer(struct)) {            
            rejectedCounter.incrementAndGet();
        }
    }

}
