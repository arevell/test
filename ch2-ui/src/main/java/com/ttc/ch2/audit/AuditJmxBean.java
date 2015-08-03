package com.ttc.ch2.audit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;

import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.ui.moduls.audit.common.AuditUiManager;

public class AuditJmxBean {
    
    private static Logger logger = LoggerFactory.getLogger(AuditJmxBean.class);
    
    @Value("${audit.on}")
    private boolean auditDefault;

    @Value("${audit.verbose}")
    private boolean verboseDefault;
    
    @Value("${audit.metrics}")
    private boolean metricsDefault;

    private boolean audit;
    private boolean verbose;
    private boolean metrics;
    
    private AuditUiManager getAuditManager() {
        try {
            return SpringContext.getApplicationContext().getBean(AuditUiManager.class);
        } catch (BeansException e) {
        }
        return null;
    }

    @PostConstruct
    public void copyDefaults() {
        audit = auditDefault;
        metrics = metricsDefault;
        verbose = verboseDefault;
    }

    public long getCcapiV3Calls() {
        AuditUiManager auditManager = getAuditManager();
        if (auditManager == null) {
            return -1L;
        }
        return auditManager.readSoapV3Calls();
    }

    public boolean isAuditDefault() {
        return auditDefault;
    }

    public boolean isVerboseDefault() {
        return verboseDefault;
    }

    public boolean isMetricsDefault() {
        return metricsDefault;
    }

    public boolean isAudit() {
        return audit;
    }

    public void setAudit(boolean audit) {
        this.audit = audit;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean isMetrics() {
        return metrics;
    }

    public void setMetrics(boolean metrics) {
        this.metrics = metrics;
    }
    
    public String getAuditState() {
        try {
             AuditManager bean = SpringContext.getApplicationContext().getBean(AuditManager.class);
             if (bean != null) {
                return "The AuditManager bean is present";
            }
        } catch (BeansException ignored) {
        }
        return "The AuditManager bean is NOT present";
    }
    
    public String getMetricsState() {
        try {
            MetricsManager bean = SpringContext.getApplicationContext().getBean(MetricsManager.class);
            if (bean != null) {
                return "The MetricsManager bean is present";
            }
        } catch (BeansException ignored) {
        }
        return "The MetricsManager bean is NOT present";
    }
    
    
}
