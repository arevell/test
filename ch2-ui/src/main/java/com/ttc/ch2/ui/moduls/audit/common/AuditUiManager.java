package com.ttc.ch2.ui.moduls.audit.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;

import com.ttc.ch2.ui.moduls.audit.Period;

public class AuditUiManager extends SimpleJdbcDaoSupport {
    
    public static final String SQL_METRICS =
    "    round(avg(threadcount)) as threadcount,                                                                                                           \n" +
    "    max(uptime) as uptime,                                                                                                                            \n" +
    "    avg(process_cpu_load) as process_cpu_load,                                                                                                        \n" +
    "    avg(system_cpu_load) as system_cpu_load,                                                                                                          \n" +
    "    round(avg(memphysicalfree)) as memphysicalfree,                                                                                                   \n" +
    "    round(avg(memphysicaltotal)) as memphysicaltotal,                                                                                                 \n" +
    "    round(avg(memphysicalusedperc), 2) as memphysicalusedperc,                                                                                        \n" +
    "    round(avg(memvirtualcommitted)) as memvirtualcommitted,                                                                                           \n" +
    "    round(avg(memvirtualcommitedusedperc), 2) as memvirtualcommitedusedperc,                                                                          \n" +
    "    round(avg(swapspacefree)) as swapspacefree,                                                                                                       \n" +
    "    round(avg(swapspacetotal)) as swapspacetotal,                                                                                                     \n" +
    "    round(avg(swapusedperc), 2) as swapusedperc                                                                                                       \n";
    
    public static final String SQL_24H =   
    "select                                                                                                                                                \n" +
    "    trunc(m.timestamp, 'DDD') d, trunc(m.timestamp, 'HH24') dt, m.host, count(*) as count,                                                            \n" +
    "    min(m.timestamp) mint, max(m.timestamp) maxt,                                                                                                     \n" +
    SQL_METRICS +
    "from rt_metrics m                                                                                                                                     \n" +
    "where timestamp >= trunc(sysdate-1, 'HH24')                                                                                                           \n" +
    "group by trunc(m.timestamp, 'DDD'), trunc(m.timestamp, 'HH24'), m.host                                                                                \n" +
    "order by trunc(m.timestamp, 'DDD') desc, trunc(m.timestamp, 'HH24') desc, m.host                                                                      \n";
    
    public static final String SQL_7D =   
    "select                                                                                                                                                \n" +
    "    trunc(m.timestamp, 'DDD') d, trunc(m.timestamp, 'DDD') + ( floor(to_number(to_char(m.timestamp, 'HH24'))/6)*6 / 24) dt, m.host, count(*) as count,\n" +
    "    min(m.timestamp) mint, max(m.timestamp) maxt,                                                                                                     \n" +
    SQL_METRICS +
    "from rt_metrics m                                                                                                                                     \n" +
    "where timestamp >= sysdate-7                                                                                                                          \n" +
    "group by trunc(m.timestamp, 'DDD'), floor(to_number(to_char(m.timestamp, 'HH24'))/6)*6, m.host                                                        \n" +
    "order by trunc(m.timestamp, 'DDD') desc, floor(to_number(to_char(m.timestamp, 'HH24'))/6)*6 desc, m.host                                              \n";
    
    public static final String SQL_1M =
    "select                                                                                                                                                \n" +
    "  trunc(m.timestamp, 'DDD') d, trunc(m.timestamp, 'DDD') dt, m.host, count(*) as count,                                                               \n" +
    "    min(m.timestamp) mint, max(m.timestamp) maxt,                                                                                                     \n" +
    SQL_METRICS +
    "from rt_metrics m                                                                                                                                     \n" +
    "where timestamp >= trunc( add_months(sysdate, -1))                                                                                                    \n" +
    "group by trunc(m.timestamp, 'DDD'), m.host                                                                                                            \n" +
    "order by trunc(m.timestamp, 'DDD') desc, m.host                                                                                                       \n";
    
    
    public static final String EVENTS_SQL = 
        "select *                                                                                                                     \n" +
        "from (                                                                                                                       \n" +
        "  select                                                                                                                     \n" +
        "     coalesce(a.id, e.id) as id, coalesce(a.timestamp, e.timestamp) as timestamp,                                            \n" +
        "     a.action, a.client_ip, aud_object, a.user_name, a.token, e.execution_time, a.server_ip                                  \n" +
        "  from RT_AUDIT_ACTION a                                                                                                     \n" +
        "  left outer join RT_AUDIT_EXECUTION e                                                                                       \n" +
        "      on a.id = e.id                                                                                                         \n" +
        "  where a.aud_object not like 'SOAP:%%' and a.aud_object not like 'REST:%%'  and a.aud_object <> 'Logout Explicit'           \n" +
        "  and a.timestamp >= ?                                                                                                       \n" +
        "  %1$s                                                                                                                       \n" +
        "  union all                                                                                                                  \n" +
        "  select                                                                                                                     \n" +
        "     a.id, a.timestamp,                                                                                                      \n" +
        "     a.action, a.client_ip, a.aud_object, al.user_name, a.token, e.execution_time, a.server_ip                               \n" +
        "  from RT_AUDIT_ACTION a                                                                                                     \n" +
        "  left outer join RT_AUDIT_EXECUTION e                                                                                       \n" +
        "      on a.id = e.id                                                                                                         \n" +
        "  left join RT_AUDIT_ACTION al                                                                                               \n" +
        "      on a.session_id = al.session_id                                                                                        \n" +
        "         and al.aud_object = 'Login'                                                                                         \n" +
        "  where a.aud_object = 'Logout Explicit'                                                                                     \n" +
        "  and a.timestamp >= ?                                                                                                       \n" +
        "  %2$s                                                                                                                       \n" +
        "  order by 2 desc                                                                                                            \n" +
        ")                                                                                                                            \n" +
        "where rownum <= 1000                                                                                                         \n";
    
    public static final String EVENTS_FILTER_SQL =
        "select distinct %3$s  from ( \n" +
                EVENTS_SQL +
        ")                            \n" +
        "%4$s                         \n";

                
            
    
    public static final String FUNCTIONS_SQL = 
            "select a.user_name, a.aud_object, count(*) count, a.action, a.resource_obj,       \n" +
            "       count(distinct a.client_ip) as ip_count,                                   \n" +
            "       round(avg(e.execution_time)) as avg_e, max(e.execution_time) as max_e      \n" +
            "from rt_audit_action a                                                            \n" +
            "left join rt_audit_execution e                                                    \n" +
            "    on a.id = e.id                                                                \n" +
            "where a.user_name is not null and a.timestamp >= ?                                \n" +
            "and a.token is not null                                                           \n" +
            " %1$s                                                                             \n" +
            "group by a.user_name, a.action, a.aud_object, a.resource_obj                      \n" +
            "order by count(*) desc                                                            \n";    
            
    public static final String FUNCTIONS_HOSTS_SQL =
            "select distinct server_ip                          \n" +
            "from rt_audit_action a                             \n" +                                               
            "where a.user_name is not null and a.timestamp >= ? \n" +                                                
            "and a.token is not null                            \n" +                       
            "order by server_ip                                 \n";                                             
            
            
    public static final String FUNCTION_FILTER_SQL =
            "select distinct %2$s  from ( \n" +
            FUNCTIONS_SQL +
            ")                            \n" +
            "%3$s                \n";

    public static final String SOAP_V3_CALLS_SQL = 
            "select count(*) as count from rt_audit_action a                           \n" +
            "where a.timestamp >= (sysdate - 1/24) and a.aud_object like 'SOAP:V3%'    \n";
    
    
    private TransactionTemplate transactionTemplate;

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    
    public List<AuditEvent> readFunctions(Period period, String user, String action, String function, String host) {
        Date date = fromDate(period);

        String filter1 = filterClauseEvents1(user, action, function, host);
        String filter2 = filterClauseEvents1(user, action, function, host);
        
        
        String sql = String.format(FUNCTIONS_SQL, filter1, filter2);
        
        List<Map<String, Object>> rows = getSimpleJdbcTemplate().queryForList(sql, date);
        List<AuditEvent> result =  new ArrayList<>();
        for (Map<String, Object> map : rows) {
            AuditEvent ae = new AuditEvent();
            
            ae.setAction(stringValue(map, "action"));
            ae.setObject(stringValue(map, "aud_object"));
            ae.setUser(stringValue(map, "user_name"));
            ae.setSc(stringValue(map, "resource_obj"));
            ae.setIpCount(intValue(map, "ip_count"));
            ae.setCount(intValue(map, "count")); 
            ae.setMaxEt(intValue(map, "max_e"));
            ae.setAvgEt(intValue(map, "avg_e"));
            
            result.add(ae);
        }
        
        return result ;        
    }

    private String filterClauseEvents1(String user, String action, String function, String host) {
        String filter = "";
        
        if (StringUtils.isNotBlank(user)) {
            filter += "and a.user_name = '" + StringEscapeUtils.escapeSql(user) + '\'';    
        }
        
        if (StringUtils.isNotBlank(action)) {
            filter += "and a.action = '" + StringEscapeUtils.escapeSql(action) + '\'';    
        }
        
        if (StringUtils.isNotBlank(function)) {
            filter += "and a.aud_object = '" + StringEscapeUtils.escapeSql(function) + '\'';    
        }
        
        if (StringUtils.isNotBlank(host)) {
            filter += "and a.server_ip = '" + StringEscapeUtils.escapeSql(host) + '\'';    
        }
        return filter;
    }
    
    private String filterClauseEvents2(String user, String action, String function, String host) {
        String filter = "";
        
        if (StringUtils.isNotBlank(user)) {
            filter += "and a.user_name = '" + StringEscapeUtils.escapeSql(user) + '\'';    
        }
        
        if (StringUtils.isNotBlank(action)) {
            filter += "and a.action = '" + StringEscapeUtils.escapeSql(action) + '\'';    
        }
        
        if (StringUtils.isNotBlank(function)) {
            filter += "and a.aud_object = '" + StringEscapeUtils.escapeSql(function) + '\'';    
        }
        
        if (StringUtils.isNotBlank(host)) {
            filter += "and al.server_ip = '" + StringEscapeUtils.escapeSql(host) + '\'';    
        }
        return filter;
    }

    public int readEventsSize(Period period, String user, String action, String function, String host) {
        Date date = fromDate(period);
        String filter1 = filterClauseEvents1(user, action, function, host);
        String filter2 = filterClauseEvents2(user, action, function, host);
        
        String sql = String.format(EVENTS_FILTER_SQL, filter1, filter2, "count(*) as count", "");
        
        int count = getSimpleJdbcTemplate().queryForInt(sql, date, date);
        
        return count;
    }
    
    public List<AuditEvent> readEvents(Period period, String user, String action, String function, String host) {
        Date date = fromDate(period);
        String filter1 = filterClauseEvents1(user, action, function, host);
        String filter2 = filterClauseEvents2(user, action, function, host);
        
        String sql = String.format(EVENTS_SQL, filter1, filter2);
        
        List<Map<String, Object>> rows = getSimpleJdbcTemplate().queryForList(sql, date, date);
        List<AuditEvent> result =  new ArrayList<>();
        for (Map<String, Object> map : rows) {
            AuditEvent ae = new AuditEvent();
            ae.setId(stringValue(map, "id"));
            ae.setTimestamp(dateValue(map, "timestamp"));
            ae.setAction(stringValue(map, "action"));
            ae.setClient(stringValue(map, "client_ip"));
            ae.setObject(stringValue(map, "aud_object"));
            ae.setUser(stringValue(map, "user_name"));
            ae.setToken(stringValue(map, "token"));
            ae.setExecutionTime(longValue(map, "execution_time"));
            ae.setHost(stringValue(map, "server_ip"));
            
            result.add(ae);
        }
        
        return result ;        
    }
    
    private Date fromDate(Period period) {
        if (period == null) {
            period = Period.H24;
        }
        
        @SuppressWarnings("deprecation")
        Date date = DateUtils.add(new Date(), period.added(), -period.amountAdded());
        return date;
    }
    
    String stringValue(Map<String, Object> map, String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Object object = map.get(key);
        if (object instanceof String) {
            return (String) object;
        }
        
        return ObjectUtils.toString(object);
    }
    
    Date dateValue(Map<String, Object> map, String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Object object = map.get(key);
        if (object instanceof Date) {
            return (Date) object;
        }
        
        if (object instanceof Calendar) {
            Calendar c = (Calendar) object;
            return c.getTime();
            
        }
        
        return null;
    }
    
    Long longValue(Map<String, Object> map, String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Object object = map.get(key);
        if (object instanceof Long) {
            return (Long) object;
        }
        
        if (object instanceof Number) {
            Number number = (Number) object;
            return number.longValue();
        }
        
        return null;
    }
    
    Integer intValue(Map<String, Object> map, String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Object object = map.get(key);
        if (object instanceof Integer) {
            return (Integer) object;
        }
        
        if (object instanceof Number) {
            Number number = (Number) object;
            return number.intValue();
        }
        
        return null;
    }
    
    public List<MetricsAggregate> readMetrics(Period period) {
        String sql;
        switch (period) {
            case H24:
            default:
                sql = SQL_24H;
                break;
                
            case D7:
                sql = SQL_7D;
                break;
                
            case M1:
                sql = SQL_1M;
                break;
        }

        List<Map<String, Object>> queryForList = getSimpleJdbcTemplate().queryForList(sql);
        
        Map<String, MetricsAggregate> aggs = new TreeMap<>();
        
        for (Map<String, Object> map : queryForList) {
            String host = ObjectUtils.toString(map.get("HOST"));
            if (StringUtils.isBlank(host)) {
                continue;
            }
            
            MetricsAggregate agg = aggs.get(host);
            if (agg == null) {
                agg = new MetricsAggregate();
                
                agg.setHost(host);
                aggs.put(host, agg);
            }
            
            Date date = dateValue(map, "dt");
            if (date != null) {
                agg.data.put(date, map);     
            }
        }
        
        return new ArrayList<MetricsAggregate>(aggs.values());
    }
    
    
    public long readSoapV3Calls() {
        long calls = getSimpleJdbcTemplate().queryForLong(SOAP_V3_CALLS_SQL);
        return calls;
    }
}
