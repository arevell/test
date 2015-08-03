package com.ttc.ch2.audit;

public class TwoPhaseDbAppender extends AbstractTwoPhaseDbAppender {
    
    @Override
    public void storePre(final Struct struct) {      
        store(struct);
    }
    
    @Override
    public void storePost(Struct struct) {
        storeEx(struct);
    }

    @Override
    public void storeFail(Struct struct) {
        storeEx(struct);
    }
}
