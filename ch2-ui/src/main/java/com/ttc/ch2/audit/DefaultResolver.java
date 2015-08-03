package com.ttc.ch2.audit;

import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttc.ch2.audit.annotations.AuditInfo;

public class DefaultResolver implements Resolver {
    private static Logger logger = LoggerFactory.getLogger(Resolver.class);
    
    private AtomicLong counter = new AtomicLong();
    
    private String appNode = null;
    
    @Inject
    private AuditManager auditManager;

    protected boolean isVerbose() {
        if (auditManager == null) {
            return false;
        }
        
        return auditManager.isVerbose();   
    }

    private String host = ""; 
    
    private String getHost() {
        if (StringUtils.isNotBlank(appNode)) {
            return appNode;            
        }
        
        if (StringUtils.isBlank(host)) {
            try {
               this.host = InetAddress.getLocalHost().getHostName();
            } catch (Throwable th) {
                if (isVerbose()) {
                    logger.warn("", th);
                }
            }
        }
        return host;
    }

    public Struct resolve(JoinPoint joinPoint, AuditInfo ai) {
        Struct struct = new Struct();
        
        struct.id = id();

        struct.phase = Phase.PRE;
        struct.action = action(joinPoint, ai);
        struct.timestamp = new Date();
        struct.thread = thread();
        struct.host = getHost();
        
        if (ai != null) {
            struct.resource = ai.getResource();
            struct.object = ai.getObject();
        }
        
        return struct;
    }

    public Struct resolve(Struct pstruct, JoinPoint joinPoint, AuditInfo ai, Object result) {
        Struct struct = (Struct) (pstruct != null ? pstruct.copy() : new Struct());

        if (StringUtils.isBlank(struct.id)) {
            struct.id = id();
        }
        
        struct.phase = Phase.POST;

        if (struct.timestamp == null) {
            struct.timestamp = new Date();
        } else {
            struct.execution = System.currentTimeMillis() - struct.timestamp.getTime();
        }
        return struct;
    }

    public Struct resolve(Struct pstruct, JoinPoint joinPoint, AuditInfo ai, Throwable th) {
        Struct struct = (Struct) (pstruct != null ? pstruct.copy() : new Struct());
        
        if (StringUtils.isBlank(struct.id)) {
            struct.id = id();
        }

        struct.phase = Phase.FAIL;

        if (struct.timestamp == null) {
            struct.timestamp = new Date();
        } else {
            struct.execution = System.currentTimeMillis() - struct.timestamp.getTime();
        }

        if (th != null) {
            struct.result = th.getClass().getName() + ": " + StringUtils.defaultString(th.getMessage());            
        }
        return struct;
    }

    protected String action(JoinPoint joinPoint, AuditInfo ai) {
        if (ai != null && StringUtils.isNotBlank(ai.getAction())) {
            return ai.getAction();
        }

        if (joinPoint == null) {
            return null;
        }
        
        Object target = joinPoint.getTarget();
        Signature signature = joinPoint.getSignature();

        String type;
        if (target != null) {
            type = target.getClass().getName();
        } else {
            type = signature.getDeclaringTypeName();
        }
        String method = signature.getName();
        String fullName = type + '.' + method;

        return fullName;
    }
    
    protected String id () {
//        long number = (System.currentTimeMillis() << 16) + (++counter % 0xFFFF); 
        long number = System.currentTimeMillis(); 
        number <<= 16;
        number +=  (counter.incrementAndGet() % 0xFFFF);
        return new StringBuilder(getHost()).append('_').append(number).toString();
    }
    
    protected String thread() {
        try {
            return Thread.currentThread().getName();
        } catch (Throwable th) {
            if (isVerbose()) {
                logger.warn("", th);
            }
        }
        return null;
    }

    public String getAppNode() {
        return appNode;
    }

    public void setAppNode(String appNode) {
        this.appNode = appNode;
    }
}
