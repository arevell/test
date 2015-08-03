package com.ttc.ch2.audit;

public interface Appender {

    void storePre(Struct struct);
    
    void storePost(Struct struct);
    
    void storeFail(Struct struct); 
}
