package com.ttc.ch2.audit;

import javax.inject.Inject;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;

public abstract class AbstractDbAppender extends SimpleJdbcDaoSupport implements Appender {
    
    @Inject
    private AuditManager auditManager;

    protected boolean isVerbose() {
        if (auditManager == null) {
            return false;
        }
        
        return auditManager.isVerbose();   
    }

    private TransactionTemplate transactionTemplate;

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public AuditManager getAuditManager() {
        return auditManager;
    }

    public void setAuditManager(AuditManager auditManager) {
        this.auditManager = auditManager;
    }

}