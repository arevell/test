package com.ttc.ch2.audit;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Struct implements Cloneable {
    private static Logger logger = LoggerFactory.getLogger(Struct.class);
    
    public String id;
    
    public Phase phase;
    
    public String application;
    
    public Date timestamp;
    
    public long execution;
    
    public String action;
    
    public String user;
    
    public String host;
    
    public String client;

    public String resource;

    public String token;

    public String session;

    public String result;
    
    public String object;
    
    public String thread;
    
    public Struct copy() {
        Struct clone = null;
        
        try {
            clone = (Struct) clone();
        } catch (Throwable ignored) {
//            if (AuditManager.ondevelop) {
//                logger.error("", ignored);
//            }
        }
        
        if (clone == null) {
            clone = new Struct();
        }
        
        return clone;
    }

}
