package com.ttc.ch2.audit;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import com.ttc.ch2.audit.annotations.Audit;
import com.ttc.ch2.audit.annotations.AuditInfo;
import com.ttc.ch2.common.SpringContext;


@Aspect
public class AuditManager {
        
    private static Logger logger = LoggerFactory.getLogger(AuditManager.class);
    
    private String applicationCode;
    
    private List<Appender> appenders;
    
    private Resolver resolver = new DefaultResolver();
    
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
        
        return auditJmxBean.isAudit();
    }
    
    
    public boolean isVerbose() {
        if (auditJmxBean == null) {
            return false;
        }
        
        return auditJmxBean.isVerbose();   
    }

    @Around(value = "@annotation(audit)", argNames = "audit")
    public Object handleAudit(JoinPoint joinPoint, Audit audit) throws Throwable {
        return handleAudit(joinPoint, new AuditInfo(audit));
    }
    
    public void handleAudit(JoinPoint joinPoint, AuditInfo ai, Throwable th) {
        
        if (!isOn()) {            
            return;
        }
        
        try {
            Struct struct = pre(joinPoint, ai);
            thrown(struct, joinPoint, ai, th);
            
        } catch (Throwable ignored) {
            if (isVerbose()) {
                logger.warn("", ignored);
            }
        }        
    }
        
    public Object handleAudit(JoinPoint joinPoint, AuditInfo ai) throws Throwable {
        
        if (!isOn()) {
            if (joinPoint instanceof ProceedingJoinPoint) {
                ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) joinPoint;
                return proceedingJoinPoint.proceed();
            }
            
            return null;
        }

        Struct struct = null;
        try {
            // pre
            struct = pre(joinPoint, ai);
            
            Object result =  null;
            if (joinPoint instanceof ProceedingJoinPoint) {
                ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) joinPoint;
                result = proceedingJoinPoint.proceed();    
            }
                        
            // post
            post(struct, joinPoint, ai, result);
            return result;
            
        } catch (Throwable th) {
            thrown(struct, joinPoint, ai, th);
            throw th;
        }        
    }
    
    
    protected Struct pre(JoinPoint joinPoint, AuditInfo ai) {
        Struct struct = null;
        
        try {
            struct = struct(joinPoint, ai);
            storePre(struct);
        } catch (Throwable ignored) {
            if (isVerbose()) {
                logger.warn("", ignored);
            }
        }
        
        return struct;
    }
    
    protected void post(Struct struct, JoinPoint joinPoint, AuditInfo ai, Object result) {
        // post
        try {
            struct = struct(struct, joinPoint, ai, result);
            storePost(struct);
        } catch (Throwable ignored) {
            if (isVerbose()) {
                logger.warn("", ignored);
            }
        }
    }
    
    protected void thrown(Struct struct, JoinPoint joinPoint, AuditInfo ai, Throwable th) {
        try {
            struct = struct(struct, joinPoint, ai, th);
            storeFail(struct);
        } catch (Throwable ignored) {
            if (isVerbose()) {
                logger.warn("", ignored);
            }
        }
    }

    protected void storePre(Struct struct) {
        boolean succesfull = false;
        if (appenders != null && !appenders.isEmpty()) {
            for (Appender appender : appenders  ) {
                try {
                    appender.storePre(struct);
                    succesfull = true;
                } catch (Throwable th) {
                    if (isVerbose()) {
                        logger.warn("", th);
                    }
                }
            }            
        }
        
        if (!succesfull) {
            logger.trace("pre  log {}", struct.action);
        }
    }
    
    protected void storePost(Struct struct) {

        boolean succesfull = false;
        if (appenders != null && !appenders.isEmpty()) {
            for (Appender appender : appenders  ) {
                try {
                    appender.storePost(struct);
                    succesfull = true;
                } catch (Throwable th) {
                    if (isVerbose()) {
                        logger.warn("", th);
                    }
                }
            }            
        }
        
        if (!succesfull) {
            logger.trace("post log {}: {} ms", struct.action, struct.execution);
        }
    }
    
    protected void storeFail(Struct struct) {
        boolean succesfull = false;
        if (appenders != null && !appenders.isEmpty()) {
            for (Appender appender : appenders  ) {
                try {
                    appender.storeFail(struct);
                    succesfull = true;
                } catch (Throwable th) {
                    if (isVerbose()) {
                        logger.warn("", th);
                    }
                }
            }            
        }
        
        if (!succesfull) {
            logger.trace("fail log {}: {} ms", struct.action, struct.execution);
        }
    }


    protected Struct struct(JoinPoint joinPoint, AuditInfo ai) {
        Struct struct = resolver.resolve(joinPoint, ai);
        String application = StringUtils.defaultIfBlank(ai==null? null: ai.getApplicationCode(), applicationCode);
        struct.application = application;
        return struct;
    }
    
    protected Struct struct(Struct pstruct, JoinPoint joinPoint, AuditInfo ai, Object result) {
        Struct struct = resolver.resolve(pstruct, joinPoint, ai, result);
        return struct;
    }
    
    protected Struct struct(Struct pstruct, JoinPoint joinPoint, AuditInfo ai, Throwable th) {
       Struct resolved = resolver.resolve(pstruct, joinPoint, ai, th);
       return resolved;
    }

    public List<Appender> getAppenders() {
        return appenders;
    }

    public void setAppenders(List<Appender> appenders) {
        this.appenders = appenders;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    public Resolver getResolver() {
        return resolver;
    }

    public void setResolver(Resolver resolver) {
        this.resolver = resolver;
    }
}
