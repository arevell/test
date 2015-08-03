package com.ttc.ch2.scheduler.service;


public interface SchedulerForSimpleJobsService {
    String SIMPLE_GROUP = "simple-group";
    
    String AUDIT_PRIMARY_AGGREGATOR = "AuditPrimaryAggregatorJob";
    
    public static String JOB_DESC="Audit Purge Job";

    void init() throws SchedulerServiceException;
}
