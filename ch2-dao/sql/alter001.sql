ALTER TABLE USERS ADD delflag NUMBER (1) default 0 not null; 

CREATE INDEX IDX_USR_DELFLAG ON USERS (delflag);

update TOURINFO_HISTORY set USER_ID=(select id from users where username='gui-usr-all-brand') 
where id in (select t.Id from TOURINFO_HISTORY T
left Join Users U on T.USER_ID=U.id
where U.ID is NULL);

update QUARTZ_JOB set USER_ID=(select id from users where username='gui-usr-adm') 
where id in (select q.id from QUARTZ_JOB Q
left Join Users U on Q.USER_ID=U.id 
where Q.USER_ID is NOT NULL
 and  U.ID is NULL);
 
 -- update constraints on DB
  update TOURINFO_HISTORY T set brand_id = (select id from brand where code = SUBSTR( T.NAME, 0 ,2 )) where brand_id is  null;
 
 alter table SELLING_COMPANY modify brand_id NUMBER(19,0) NOT NULL;
 alter table EMAIL_HISTORY modify brand_id NUMBER(19,0) NOT NULL;
 alter table TOURDEPARTURE_HISTORY modify brand_id NUMBER(19,0) NOT NULL;
 alter table TOURINFO_HISTORY modify brand_id NUMBER(19,0) NOT NULL;
 alter table TOURINFO_HISTORY modify user_id NUMBER(19,0) NOT NULL;
 alter table QUARTZ_JOB_HISTORY modify brand_id NUMBER(19,0) NOT NULL;
 alter table QUARTZ_JOB_HISTORY modify QUARTZ_JOB_ID NUMBER(19,0) NOT NULL;