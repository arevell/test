package com.ttc.util.ext;

import java.util.Collection;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;

public class NotBlankPropertyChecker extends NullPropertyChecker {
    @Override
    protected void check(Collection<Message> messages, Object property) {
        String string = ObjectUtils.toString(property, null);
        
        if (StringUtils.isBlank(string)) {
            Message message = MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject(getSubjectName()).build();
            messages.add(message);
        }
    }
}
