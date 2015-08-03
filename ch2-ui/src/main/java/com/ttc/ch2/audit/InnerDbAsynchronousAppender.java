package com.ttc.ch2.audit;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.apache.batik.util.RunnableQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

public class InnerDbAsynchronousAppender extends AbstractTwoPhaseDbAppender {
    private static Logger logger = LoggerFactory.getLogger(InnerDbAsynchronousAppender.class);
    
    private static int PACK_SIZE = 1_000;

    private Queue<Struct> queue; 
    
    @PreDestroy
    public void beforeStrop() {
        store();
    }

    @Override
    public void storePre(Struct struct) {   
        queue.add(struct);
    }
    
    @Override
    public void storePost(Struct struct) {
        queue.add(struct);
    }

    @Override
    public void storeFail(Struct struct) {
        queue.add(struct);
    }

//    @Scheduled(fixedRate=20*1000)
    public void store() {
        final ArrayList<Struct> pack = new ArrayList<>();
        
        for (int i = 0; i < PACK_SIZE; i++) {
            Struct struct = queue.poll();
            if (struct == null) {
                break;
            }
            pack.add(struct);
        }
        
        if (pack.isEmpty()) {
            return;
        }
        
        
        long start = System.currentTimeMillis();
        
        this.getTransactionTemplate().execute(new TransactionCallbackWithoutResult() {
            
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                List<Struct> s1 = new ArrayList<>();
                List<Struct> s2 = new ArrayList<>();
                
                for (Struct struct: pack) {
                        Phase phase = struct.phase == null? Phase.PRE: struct.phase;
                        switch (phase) {
                            case PRE:
                            default:
                                s1.add(struct);
                                break;
    
                            case POST:
                            case FAIL:
                                s2.add(struct);
                                break;       
                        }
                }

                if (!s1.isEmpty()) {
                    try {
                        Struct[] structs = s1.toArray(new Struct[s1.size()]);
                        store(getSimpleJdbcTemplate(), structs);
                    } catch (Throwable th) {
                        if (isVerbose()) {
                            logger.error("", th);
                        }
                    }
                }

                if (!s2.isEmpty()) {
                    try {
                        Struct[] structs = s2.toArray(new Struct[s2.size()]);
                        storeEx(getSimpleJdbcTemplate(), structs);
                    } catch (Throwable th) {
                        if (isVerbose()) {
                            logger.error("", th);
                        }
                    }
                }
            }
        });
        
        if (isVerbose()) {
            int left = queue.size(); 
            long time = System.currentTimeMillis() - start;
            logger.debug("saved {} message(s) in {} ms, {} message(s) in queue", pack.size(), time, left);
        }
    }

    public void setQueue(Queue<Struct> queue) {
        this.queue = queue;
    }
    
}

class AppenderCallable implements Runnable  {
    
    private static Logger logger = LoggerFactory.getLogger(AppenderCallable.class);
    
    private int minPack = 100;
    private int maxPack;
    
    private Queue<Struct> queue;
    private InnerDbAsynchronousAppender appender;
    
    
    private long counter = 0L;

    @Override
    public void run() {
        counter++;
        
        for (int i = 0; i < 10; i++) {
            try {
            int n = queue.size();
            if (maxPack > 0 && n > maxPack) {
                int cleared = 0;
                for (; queue.size() >  maxPack; cleared++) {
                    queue.poll();
                }
                logger.warn("Audit queue size ({} messages) exceeded maximal value={}, the queue removed {} elements", n, maxPack, cleared);
            }

            if (n < minPack) {
                return;
            }
            
            appender.store();
            } catch(Throwable th) {
                logger.warn("{}", th.getMessage());
            }
        }
        
        return;
    }

    public void setQueue(Queue<Struct> queue) {
        this.queue = queue;
    }

    public void setAppender(InnerDbAsynchronousAppender appender) {
        this.appender = appender;
    }

    public void setMinPack(int minPack) {
        this.minPack = minPack;
    }

    public void setMaxPack(int maxPack) {
        this.maxPack = maxPack;
    }

}

class AppenderThread extends RunnableQueue {

    private static Logger logger = LoggerFactory.getLogger(AppenderThread.class);

    private int minPack = 100;
    private int maxPack;
    private long napTime = 60*1000L;
    
    private Queue<Struct> queue;
    private InnerDbAsynchronousAppender appender;

    @Override
    public void run() {
        
        while (!Thread.interrupted()) {
            int n = queue.size();
            if (maxPack> 0 && n > maxPack) {
                queue.clear();
                int cleared = 0;
                for (; queue.size() >  maxPack; cleared++) {
                    queue.poll();
                }
                logger.warn("Audit queue size ({} messages) exceeded maximal value={}, the queue removed {} elements", n, maxPack, cleared);
            }

            if (n < minPack) {
                try {
                    TimeUnit.MILLISECONDS.sleep(napTime);
                } catch (InterruptedException ignored) {
                    logger.warn("sleep was interrupted: {}", ignored.getMessage());
                }
                continue;
            }
            
            appender.store();
        }
        
        logger.trace("the run() is interupted");
    }

    public void setQueue(Queue<Struct> queue) {
        this.queue = queue;
    }

    public void setAppender(InnerDbAsynchronousAppender appender) {
        this.appender = appender;
    }

    public void setMinPack(int minPack) {
        this.minPack = minPack;
    }

    public void setMaxPack(int maxPack) {
        this.maxPack = maxPack;
    }

    public void setNapTime(long napTime) {
        this.napTime = napTime;
    }
    
}
