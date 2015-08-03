package com.ttc.ch2.scheduler.jobs.audit;

public interface AuditPurger {
    void purge(int auditMaturityDays, int metricsMaturityDays, int auditRecordsLimit, int metricsRecordsLimit);
}
