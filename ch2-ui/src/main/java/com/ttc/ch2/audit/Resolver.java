package com.ttc.ch2.audit;

import org.aspectj.lang.JoinPoint;

import com.ttc.ch2.audit.annotations.AuditInfo;

public interface Resolver {
    
    Struct resolve(JoinPoint joinPoint, AuditInfo ai);
    
    Struct resolve(Struct pstruct, JoinPoint joinPoint, AuditInfo ai, Object result);
    
    Struct resolve(Struct pstruct, JoinPoint joinPoint, AuditInfo ai, Throwable th);
}
