SET DEFINE OFF;
Insert into QRTZ_LOCKS
   (LOCK_NAME)
 Values
   ('CALENDAR_ACCESS');
Insert into QRTZ_LOCKS
   (LOCK_NAME)
 Values
   ('JOB_ACCESS');
Insert into QRTZ_LOCKS
   (LOCK_NAME)
 Values
   ('MISFIRE_ACCESS');
Insert into QRTZ_LOCKS
   (LOCK_NAME)
 Values
   ('SCHEDULER_OPERATION');
Insert into QRTZ_LOCKS
   (LOCK_NAME)
 Values
   ('STATE_ACCESS');
Insert into QRTZ_LOCKS
   (LOCK_NAME)
 Values
   ('TRIGGER_ACCESS');
COMMIT;
