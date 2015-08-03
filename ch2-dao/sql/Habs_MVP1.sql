delete from qrtz_job_listeners;
delete from qrtz_trigger_listeners;
delete from qrtz_fired_triggers;
delete from qrtz_simple_triggers;
delete from qrtz_cron_triggers;
delete from qrtz_blob_triggers;
delete from qrtz_triggers;
delete from qrtz_job_details;
delete from qrtz_calendars;
delete from qrtz_paused_trigger_grps;
delete from qrtz_scheduler_state;

alter table Quartz_JOB add UI_NAME VARCHAR2(60);

update Quartz_JOB set UI_NAME='Departure Import Job ' || BRAND_CODE where JOB_NAME='DepartureSynchronizeJob';
update Quartz_JOB set UI_NAME='Upload Tour Info Job ' || BRAND_CODE where JOB_NAME='UploadTourInfoJob';
update Quartz_JOB set UI_NAME='Departure Extended Job ' || BRAND_CODE where JOB_NAME='QuickDepartureSynchronizeJob';
update Quartz_JOB set UI_NAME='Audit Purge Job' where JOB_NAME='AuditPurgeJob';

update Quartz_JOB set UI_NAME='Upload Tour Info Job Listener ' || BRAND_CODE where JOB_NAME='UploadTourInfoJob';


update Quartz_JOB set GROUP_NAME='import-extended-job-group-ch2'  where GROUP_NAME='quick-synchronize-job-group-ch2';

update Quartz_JOB set JOB_NAME='DepartureExtendedSynchronizeJob'  where JOB_NAME='QuickDepartureSynchronizeJob';

PROMPT
PROMPT Creating table CONFIG_VALUES
PROMPT ====================
PROMPT

CREATE TABLE CONFIG_VALUES
(
   "ID"            NUMBER (19) NOT NULL,
   "VERSION"       NUMBER (19),
   "PROP_NAME"     VARCHAR2 (50 CHAR),
   "PROP_VALUE"    VARCHAR2 (200 CHAR) 
);

ALTER TABLE CONFIG_VALUES ADD PRIMARY KEY (ID) USING INDEX;


CREATE TABLE RECONCILIATION_REPORT_DATA
(
   "ID"            NUMBER (19) NOT NULL,
   "VERSION"       NUMBER (19),
   "BRAND_CODE"    VARCHAR2 (5 CHAR),
   "INSERT_DATE"   TIMESTAMP,
   "TYPE"          VARCHAR2 (25 CHAR),
   "SER_DATA"      CLOB NOT NULL
);

ALTER TABLE RECONCILIATION_REPORT_DATA ADD PRIMARY KEY (ID) USING INDEX;

CREATE INDEX RRD_IDX_BRAND_CODE ON RECONCILIATION_REPORT_DATA (BRAND_CODE);
CREATE INDEX RRD_IDX_TYPE ON RECONCILIATION_REPORT_DATA ("TYPE");
CREATE INDEX RRD_IDX_INSERT_DATE ON RECONCILIATION_REPORT_DATA (INSERT_DATE);