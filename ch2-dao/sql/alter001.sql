ALTER TABLE USERS add delflag NUMBER (1) default 0 not null; 

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

