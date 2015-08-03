package com.ttc.ch2.scheduler.service;

public interface SchedulerForSimpleJobsService {
    String SIMPLE_GROUP = "simple-group";
    
    String AUDIT_PURGER = "AuditPurgeJob";
    
    String AUDIT_PRIMARY_AGGREGATOR = "AuditPrimaryAggregatorJob";

    void init() throws SchedulerServiceException;
}
