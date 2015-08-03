package com.ttc.ch2.audit;

import static org.apache.commons.lang.StringUtils.defaultIfBlank;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

public abstract class AbstractTwoPhaseDbAppender extends AbstractDbAppender {
    
    
    public static final String  SQL_INSERT_1 = "insert into rt_audit_action(id, timestamp, app_name, user_name, action, client_ip, server_ip, resource_obj, aud_object, token, session_id, thread) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    public static final String SQL_INSERT_2 = "insert into rt_audit_execution(id, timestamp, app_name, user_name, result, execution_time)"
            + "values(?, ?, ?, ?, ?, ?)";

    protected void store(final Struct struct) {
        this.getTransactionTemplate().execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                store(getSimpleJdbcTemplate(), struct);
            }
        });
    }
    
    protected  void storeEx(final Struct struct) {
        this.getTransactionTemplate().execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                storeEx(getSimpleJdbcTemplate(), struct);
            }
        });
    }
    
    protected void store(SimpleJdbcTemplate jdbcTemplate, Struct ... structs) {
        if (structs == null || structs.length == 0) {
            return;
        }
        
        List<Object[]> batchArgs = new ArrayList<>();        
        for (Struct struct : structs) {
            Object[] args = new Object[] {        
                struct.id,
                struct.timestamp,
                defaultIfBlank(struct.application, "<unknown>"),
                struct.user,
                struct.action,
                struct.client,
                struct.host,
                struct.resource,
                struct.object,
                struct.token, 
                struct.session,
                struct.thread};
            batchArgs.add(args);
        }
        jdbcTemplate.batchUpdate(SQL_INSERT_1, batchArgs);
    }
    
    protected void storeEx(SimpleJdbcTemplate jdbcTemplate, Struct ... structs) {
        if (structs == null || structs.length == 0) {
            return;
        }
        
        List<Object[]> batchArgs = new ArrayList<>();        
        for (Struct struct : structs) {
            Object[] args = new Object[] {
                struct.id,
                struct.timestamp,
                defaultIfBlank(struct.application, "<unknown>"),
                struct.user,
                struct.result,
                struct.execution};
            batchArgs.add(args);
        }
        jdbcTemplate.batchUpdate(SQL_INSERT_2, batchArgs);
    }
}
