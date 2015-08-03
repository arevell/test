-- show content repositories and C
SELECT TOUR_CODE,S.CODE FROM CONTENT_REPOSITORY C
join SELLINGCOMPANY_CONTENT SC on SC.CONTENT_ID=C.ID
join SELLING_COMPANY S on SC.SELLING_COMPANY_ID=S.ID
where C.IS_TOURDEPARTURE_AVAILABLE=1 and C.IS_TOURINFO_AVAILABLE=1;


-- setup default params
update QUARTZ_JOB_HISTORY set JOB_HISTORY_STATUS = 'Fail' where JOB_HISTORY_STATUS='Processing';
update TOURDEPARTURE_HISTORY set TOURDEPARTURE_STATUS= 'ERROR_OPERATION_END' where TOURDEPARTURE_STATUS='OPERATION_IN_PROGESS';
update TOURINFO_HISTORY set file_status='FAIL' where file_status='PROCESSING';

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



delete from BRAND_LOCK;
delete from upload_status;
delete from IMPORT_STATUS;


--clear import comments
--TD import
delete from COMMENTS where TDHISTORY_ID IS NOT NULL;
delete from COMMENTS where QJHISTORY_ID IS NOT NULL;
delete from TOURDEPARTURE_CONTENT;
delete from TOURDEPARTURE_HISTORY;
delete from QUARTZ_JOB_HISTORY;

delete from QUARTZ_JOB;

-- ti upload
delete from COMMENTS where TIHISTORY_ID IS NOT NULL;
delete from TOURINFO_CONTENT;
delete from TOURINFO_HISTORY;
delete from TIBLOB_DATA;


-- Cr brand
 delete from XML_CONTENT_REPOSITORY where CONTENT_REPOSITORY_ID in (select content_id from brand_content where brand_id=36);
 delete from BRAND_CONTENT where (CONTENT_ID) in ( select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=36) );                             
 delete from SELLINGCOMPANY_CONTENT where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=36));        
 delete from TOURDEPARTURE_CONTENT  where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=36));                   
 delete from TOURINFO_CONTENT where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=36));    
 delete from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=36);

 delete from XML_CONTENT_REPOSITORY where CONTENT_REPOSITORY_ID in (select content_id from brand_content where brand_id=26);
 delete from BRAND_CONTENT where (CONTENT_ID) in ( select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=26) );                             
 delete from SELLINGCOMPANY_CONTENT where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=26));        
 delete from TOURDEPARTURE_CONTENT  where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=26));                   
 delete from TOURINFO_CONTENT where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=26));    
 delete from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=26);

 delete from XML_CONTENT_REPOSITORY where CONTENT_REPOSITORY_ID in (select content_id from brand_content where brand_id=24);
 delete from BRAND_CONTENT where (CONTENT_ID) in ( select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=24) );                             
 delete from SELLINGCOMPANY_CONTENT where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=24));        
 delete from TOURDEPARTURE_CONTENT  where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=24));                   
 delete from TOURINFO_CONTENT where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=24));    
 delete from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=24);

 delete from XML_CONTENT_REPOSITORY where CONTENT_REPOSITORY_ID in (select content_id from brand_content where brand_id=15);
 delete from BRAND_CONTENT where (CONTENT_ID) in ( select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=15) );                             
 delete from SELLINGCOMPANY_CONTENT where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=15));        
 delete from TOURDEPARTURE_CONTENT  where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=15));                   
 delete from TOURINFO_CONTENT where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=15));    
 delete from CONTENT_REPOSITORY where ID in ( select content_id from brand_content where brand_id=15);
 
 delete from XML_CONTENT_REPOSITORY where CONTENT_REPOSITORY_ID in (SELECT id from CONTENT_REPOSITORY where REPOSITORY_STATUS='Empty');
 delete from BRAND_CONTENT where (CONTENT_ID) in ( select ID from CONTENT_REPOSITORY where ID in ( SELECT id from CONTENT_REPOSITORY where REPOSITORY_STATUS='Empty') );                             
 delete from SELLINGCOMPANY_CONTENT where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( SELECT id from CONTENT_REPOSITORY where REPOSITORY_STATUS='Empty'));        
 delete from TOURDEPARTURE_CONTENT  where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( SELECT id from CONTENT_REPOSITORY where REPOSITORY_STATUS='Empty'));                   
 delete from TOURINFO_CONTENT where (CONTENT_ID) in (select ID from CONTENT_REPOSITORY where ID in ( SELECT id from CONTENT_REPOSITORY where REPOSITORY_STATUS='Empty'));    
 delete from CONTENT_REPOSITORY where ID in (SELECT id from CONTENT_REPOSITORY where REPOSITORY_STATUS='Empty');

            

 -- clear CR
 delete from XML_CONTENT_REPOSITORY; 
 delete from BRAND_CONTENT ; 
 delete from SELLINGCOMPANY_CONTENT;  
 delete from TOURDEPARTURE_CONTENT ;                 
 delete from TOURINFO_CONTENT; 
 delete from CONTENT_REPOSITORY;
 
  
 -- clear users
delete from GROUP_AUTHORITIES;
delete from CCAPI_AUTHORITY;
delete from GROUPS;

delete from AUTHORITY;
delete from CCAPI_AUTHORITY;
delete from GROUP_MEMBERS;
delete from USER_BRAND;
delete from users;

