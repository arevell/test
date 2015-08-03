package com.ttc.ch2.search.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.elasticsearch.node.Node;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesResponse;
import com.ttc.util.ext.PropertyMT;
import com.ttc.util.ext.V3MT;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.messages.formatter.DefaultMessageFormatter;
import com.ttc.util.messages.formatter.MessageFormatter;

public class SearchServiceImpl_getTourCategories_Test extends MessageTest {

    private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl_getTourCategories_Test.class);
    
    @Test
    public void test_validation_null_sc() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        Collection<Message> messages = service.validate((GetTourCategoriesRequest)null);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanyName").build());
    }
    
    @Test
    public void test_validation_sc() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        
        GetTourCategoriesRequest request = new GetTourCategoriesRequest();
        request.setSellingCompany(null);
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanyName").build());
    }
    
    @Test
    public void test_validation_getTourCategories() throws Throwable {
        
        SearchServiceImpl service = mock(SearchServiceImpl.class);
        
        when(service.getTourCategories(any (GetTourCategoriesRequest.class))).thenCallRealMethod();
        Node node = mock(Node.class);
        when(node.client()).thenThrow(new RuntimeException("test context"));
        Whitebox.setInternalState(service, "node", node);
        
        GetTourCategoriesRequest request = mock(GetTourCategoriesRequest.class);
        
        
        GetTourCategoriesResponse response = service.getTourCategories(request );
        
        Collection<com.ttc.ch2.api.ccapi.v3.Message> messages = response.getMessageContext().getMessage();
        logger.info("");
        for (com.ttc.ch2.api.ccapi.v3.Message message : messages) {
            logger.info("{}", message.getMessageType());
        }
        
        assertNotNull(messages);
        
        Message message = MessageBuilder.newMessage(V3MT. SYSTEM_ERROR).withSubject("Exception", "test context").build();


        assertContains(messages, message);
    }
}
