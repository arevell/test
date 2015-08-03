package com.ttc.ch2;

import static org.junit.Assert.fail;

import java.text.MessageFormat;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import com.ttc.ch2.api.ccapi.v3.MessageContext;
import com.ttc.util.messages.Message;

public class AssertMessage {
    
    public static void assertContains(String msg, String expected, Collection<Message> collection) {
        if (collection != null) {
            for (Message message : collection) {
                String description = message.getMessageType().getDescription();
                if (StringUtils.equals(expected, description)) {
                    return;
                }
            }
        }
        fail(msg);
    }
    
    public static void assertContains(String expected, Collection<Message> collection) {
        String msg = MessageFormat.format("message ''{0}'' not fount in messages", expected);
        assertContains(msg, expected, collection);
    }

    public static void assertContains(String message, MessageContext messageContext) {
        if (messageContext != null ) {
            for (com.ttc.ch2.api.ccapi.v3.Message msg : messageContext.getMessage()) {
                if (StringUtils.contains(msg.getInterpolatedMessage(), message)) {
                    return;
                }
            }
        }
        
        String messages = "";
        for (com.ttc.ch2.api.ccapi.v3.Message msg : messageContext.getMessage()) {
            messages += msg.getInterpolatedMessage() + '\n';
        }
        fail(String.format("string %1s not found in messages:\n%2s", message, messages));
    }
}
