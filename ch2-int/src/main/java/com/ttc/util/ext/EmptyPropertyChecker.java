package com.ttc.util.ext;

import java.util.Collection;

import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;

public class EmptyPropertyChecker extends NullPropertyChecker {
    @Override
    protected void check(Collection<Message> messages, Object property) {
        if (property == null) {
            Message message = MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject(getSubjectName()).build();
            messages.add(message);
        
        } else if (property instanceof Object[]) {
            Object[] array = (Object[]) property;
            if (array.length == 0) {
                Message message = MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject(getSubjectName()).build();
                messages.add(message);
            }
        
        } else if (property instanceof Collection) {
            @SuppressWarnings("rawtypes")
            Collection collection = (Collection) property;
            if (collection.isEmpty()) {
                Message message = MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject(getSubjectName()).build();
                messages.add(message);    
            }
        }
    }

}
