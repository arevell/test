package com.ttc.ch2.audit.annotations;

public class AuditInfo {    
    String applicationCode;
    String action;
    boolean twoPhase;

    String resource;

    String object;


    public AuditInfo() {
    }
    
    public AuditInfo(Audit audit) {
        if (audit == null) {
            return;
        }
        
        this.applicationCode = audit.applicationCode();
        this.action = audit.action();
        this.twoPhase = audit.twoPhase();
        this.resource = audit.resource();
        this.object = audit.object();        
    }
    
    
    
    public String getApplicationCode() {
        return applicationCode;
    }
    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public boolean isTwoPhase() {
        return twoPhase;
    }
    public void setTwoPhase(boolean twoPhase) {
        this.twoPhase = twoPhase;
    }
    
    public String getResource() {
        return resource;
    }
    
    public void setResource(String resource) {
        this.resource = resource;
    }
    
    public String getObject() {
        return object;
    }
    
    public void setObject(String object) {
        this.object = object;
    }
}
