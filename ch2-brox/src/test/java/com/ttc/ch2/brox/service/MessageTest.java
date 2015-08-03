package com.ttc.ch2.brox.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.ttc.util.messages.Message;
import com.ttc.util.ws.MessageComparator;
import com.ttc.util.ws.MessagesUtil;

public abstract class MessageTest {

    protected void assertContains(Collection<com.ttc.ch2.api.ccapi.v3.Message> messages, Message message) {
        com.ttc.ch2.api.ccapi.v3.Message copy = MessagesUtil.copy(message);        
        
        Set<com.ttc.ch2.api.ccapi.v3.Message> set = new TreeSet<>(new MessageComparator());
        set.addAll(messages);
        
        assertTrue(set.contains(copy));
    }

    protected void assertContainsUtil(Collection<Message> messages, Message message) {
        Set<Message> set = new HashSet<>(messages);
        assertTrue(set.contains(message));
    }
    
    protected void assertNotContainsUtil(Collection<Message> messages, Message message) {
        Set<Message> set = new HashSet<>(messages);
        assertFalse(set.contains(message));
    }
}