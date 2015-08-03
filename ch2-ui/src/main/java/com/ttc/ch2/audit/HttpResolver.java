package com.ttc.ch2.audit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ttc.ch2.audit.annotations.AuditInfo;

public class HttpResolver extends DefaultResolver {
    private static Logger logger = LoggerFactory.getLogger(HttpResolver.class);

    @Override
    public Struct resolve(JoinPoint joinPoint, AuditInfo ai) {
        Struct struct = super.resolve(joinPoint, ai);

        resolveHttp(struct);

        return struct;
    }

    protected void resolveHttp(Struct struct) {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (!(requestAttributes instanceof ServletRequestAttributes)) {
                return;
            }

            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = servletRequestAttributes.getRequest();
            if (request == null) {
                return;
            }

            if (StringUtils.isBlank(struct.host)) {
                struct.host = request.getLocalAddr();                
            }
            struct.client = request.getRemoteAddr();

            HttpSession session = request.getSession(false);
            if (session == null) {
                return;
            }

            struct.session = session.getId();
        } catch (Throwable ignored) {
            if (isVerbose()) {
                logger.warn("", ignored);
            }
        }
    }

}