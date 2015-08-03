package com.ttc.ch2.search.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedRequest;
import com.ttc.ch2.api.ccapi.v3.GetContinentsAndCountriesVisitedResponse;
import com.ttc.util.ext.PropertyMT;
import com.ttc.util.ext.V3MT;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.messages.formatter.DefaultMessageFormatter;
import com.ttc.util.messages.formatter.MessageFormatter;

public class SearchServiceImpl_getContinentsAndCountriesVisited_Test extends MessageTest {

    private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl_getContinentsAndCountriesVisited_Test.class);
    
    @Test
    public void test_validation_null_request() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        Collection<Message> messages = service.validate((GetContinentsAndCountriesVisitedRequest)null);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
    }
    
    @Test
    public void test_validation_request_empty_sc() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        GetContinentsAndCountriesVisitedRequest request = mock(GetContinentsAndCountriesVisitedRequest.class);
        when(request.getSellingCompanies()).thenReturn(new ArrayList<String>());
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);

        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanies").build());
    }
    
    @Test
    public void test_validation_request_null_sc() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        GetContinentsAndCountriesVisitedRequest request = mock(GetContinentsAndCountriesVisitedRequest.class);
        when(request.getSellingCompanies()).thenReturn(null);
        
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanies").build());
    }
    
    @Test
    public void test_validation_request_sc() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        GetContinentsAndCountriesVisitedRequest request = mock(GetContinentsAndCountriesVisitedRequest.class);
        when(request.getSellingCompanies()).thenReturn(Collections.singletonList("ABCDEF"));
        
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanies").build());
    }
    
    @Test
    public void test_validation_getTourCategories() throws Throwable {
        
        SearchServiceImpl service = mock(SearchServiceImpl.class);
        
        when(service.getContinentsAndCountriesVisited(any (GetContinentsAndCountriesVisitedRequest.class))).thenCallRealMethod();
        
        GetContinentsAndCountriesVisitedRequest request = mock(GetContinentsAndCountriesVisitedRequest.class);
        
        
        GetContinentsAndCountriesVisitedResponse response = service.getContinentsAndCountriesVisited(request );
        
        Collection<com.ttc.ch2.api.ccapi.v3.Message> messages = response.getMessageContext().getMessage();
        logger.info("");
        for (com.ttc.ch2.api.ccapi.v3.Message message : messages) {
            logger.info("{}", message.getMessageType().getDescription());
        }
        
        assertNotNull(messages);
        
        Message message = MessageBuilder.newMessage(V3MT. SYSTEM_ERROR).withSubject("Exception", "test context").build();
        
        assertContains(messages, message);
    }
}
