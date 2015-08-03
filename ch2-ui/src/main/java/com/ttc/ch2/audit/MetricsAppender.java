package com.ttc.ch2.audit;

import org.springframework.beans.BeansException;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ttc.ch2.common.SpringContext;

public class MetricsAppender extends SimpleJdbcDaoSupport {
    private static final String SQL = "insert into rt_metrics(host, timestamp, threadcount, uptime, process_cpu_load, system_cpu_load, memphysicalfree, memphysicaltotal, memphysicalusedperc, memvirtualcommitted, memvirtualcommitedusedperc, swapspacefree, swapspacetotal, swapusedperc, heapmemory)\n" +
            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private AuditJmxBean auditJmxBean;

    public boolean isOn() {
        
        if (auditJmxBean == null) {
            try {
                auditJmxBean = SpringContext.getApplicationContext().getBean(AuditJmxBean.class);
            } catch (BeansException ignored) {
            }            
        }
            
        if (auditJmxBean == null) {
            return false;
        }
        
        return auditJmxBean.isMetrics();
    }

    private TransactionTemplate transactionTemplate;

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void store(final Metric m) {
        if (!isOn()) {
            return;
        }
        
        this.getTransactionTemplate().execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getSimpleJdbcTemplate().update(SQL,
                        m.hostAddr,
                        m.timestamp,
                        m.threadCount,
                        m.uptime,
                        m.processCpuLoad,
                        m.systemCpuLoad,
                        m.memPhysicalFree,
                        m.memPhysicalTotal,
                        m.memPhysicalUsedPerc,
                        m.memVirtualCommitted,
                        m.memVirtualCommitedUsedPerc,
                        m.swapSpaceFree,
                        m.swapSpaceTotal,
                        m.swapUsedPerc,
                        m.heapMemory);
            }
        });
    }

}
