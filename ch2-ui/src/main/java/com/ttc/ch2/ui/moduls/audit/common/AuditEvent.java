package com.ttc.ch2.ui.moduls.audit.common;

import java.io.Serializable;
import java.util.Date;

public class AuditEvent implements Serializable {
    private static final long serialVersionUID = 659411478139983474L;

    private String id;
    private Date timestamp;
    private String action;
    private String client;
    private String object;
    private String user;
    private String token;
    private Long executionTime;
    private Integer count;
    private Integer ipCount;
    private Integer maxEt;
    private Integer avgEt;
    private String host;
    private String sc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getIpCount() {
        return ipCount;
    }

    public void setIpCount(Integer ipCount) {
        this.ipCount = ipCount;
    }

    public Integer getMaxEt() {
        return maxEt;
    }

    public void setMaxEt(Integer maxEt) {
        this.maxEt = maxEt;
    }

    public Integer getAvgEt() {
        return avgEt;
    }

    public void setAvgEt(Integer avgEt) {
        this.avgEt = avgEt;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }


}
