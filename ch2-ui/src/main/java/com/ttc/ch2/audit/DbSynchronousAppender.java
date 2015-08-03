package com.ttc.ch2.audit;

import static org.apache.commons.lang.StringUtils.defaultIfBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;


public class DbSynchronousAppender extends AbstractDbAppender {
    
    private static Logger logger = LoggerFactory.getLogger(DbSynchronousAppender.class);

    private static final String INSERT_SQL_TEMPLATE = "INSERT INTO %s " +
            "(AUD_USER, AUD_CLIENT_IP, AUD_SERVER_IP, AUD_RESOURCE, AUD_ACTION, APPLIC_CD, AUD_DATE, AUD_OBJECT, AUD_TOKEN, AUD_SESSION_ID) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    

    private String tableName = "COM_AUDIT_TRAIL_CH";
    
    @Override
    public void storePre(Struct struct) {
    }

    @Override
    public void storePost(Struct struct) {
        store(struct);
    }

    @Override
    public void storeFail(Struct struct) {
        store(struct);
    }

    protected void store(final Struct struct) {
        this.getTransactionTemplate().execute(new TransactionCallbackWithoutResult() {
            
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getSimpleJdbcTemplate().update(
                        String.format(INSERT_SQL_TEMPLATE, tableName),
                        
                        defaultIfBlank(struct.user, "<unknown>"),
                        defaultIfBlank(struct.client, "<unknown>"),
                        defaultIfBlank(struct.host,"<unknown>"),
                        defaultIfBlank(struct.resource, "<unknown>"),
                        defaultIfBlank(struct.action, "<unknown>"),
                        defaultIfBlank(struct.application, "<unknown>"),
                        struct.timestamp,
                        struct.result, 
                        struct.token, 
                        struct.session);
            }
        });
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
