package com.ttc.ch2.scheduler.jobs.audit;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.BeansException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ttc.ch2.common.SpringContext;

public abstract class AuditJob extends QuartzJobBean {
    
    protected int toInt(Map<?, ?> map, String key) {
        if (map == null) {
            return 0;
        }
        return NumberUtils.toInt(ObjectUtils.toString(map.get(key)), 0);
    }

    protected String toString(Map<?, ?> map, String key) {
        if (map == null) {
            return null;
        }
        return ObjectUtils.toString(map.get(key));
    }
    

    protected <T> T getBean (Class<T> clazz) {
        try {
            return SpringContext.getApplicationContext().getBean(clazz);
        } catch (BeansException e) {
        }
        return null;
    }
}
