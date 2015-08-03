package com.ttc.ch2.brox.service;

import static org.junit.Assert.assertFalse;
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

import com.ttc.ch2.api.ccapi.v3.GetBrochureRequest;
import com.ttc.ch2.api.ccapi.v3.GetBrochureResponse;
import com.ttc.ch2.bl.upload.validator.BLMT;
import com.ttc.util.ext.PropertyMT;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.messages.formatter.DefaultMessageFormatter;
import com.ttc.util.messages.formatter.MessageFormatter;

public class BrochureServiceImplTest_validation extends MessageTest {
    private static Logger logger = LoggerFactory.getLogger(BrochureServiceImplTest_validation.class);

    @Test
    public void test_validation_null_request() throws Throwable {
        
        BrochureServiceImpl service = new BrochureServiceImpl();
        
        Collection<Message> messages = service.validate(null);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request parameter").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("tour").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("brandCode").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanyCode").build());
    }
        
    @Test
    public void test_validation_request() throws Throwable {
        
        BrochureServiceImpl service = new BrochureServiceImpl();
        
        GetBrochureRequest request =mock(GetBrochureRequest.class);
        

        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request parameter").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("tour").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("brandCode").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanyCode").build());        
    }
    
    @Test
    public void test_validation_tour_empty() throws Throwable {
        
        BrochureServiceImpl service = new BrochureServiceImpl();
        
        GetBrochureRequest request =mock(GetBrochureRequest.class);
        when(request.getTour()).thenReturn(new ArrayList<String>());
        
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request parameter").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("tour").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("brandCode").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanyCode").build());        
    }
    
    @Test
    public void test_validation_tour() throws Throwable {
        
        BrochureServiceImpl service = new BrochureServiceImpl();
        
        GetBrochureRequest request =mock(GetBrochureRequest.class);
        when(request.getTour()).thenReturn(Collections.singletonList("AAAAAA"));
        
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request parameter").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("tour").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("brandCode").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanyCode").build());        
    }
    
    @Test
    public void test_validation_BrandCode() throws Throwable {
        
        BrochureServiceImpl service = new BrochureServiceImpl();
        
        GetBrochureRequest request = mock(GetBrochureRequest.class);
        when(request.getBrandCode()).thenReturn("AA");
        
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request parameter").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("tour").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("brandCode").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanyCode").build());        
    }
    
    @Test
    public void test_validation_sc() throws Throwable {
        
        BrochureServiceImpl service = new BrochureServiceImpl();
        
        GetBrochureRequest request = mock(GetBrochureRequest.class);
        when(request.getSellingCompanyCode()).thenReturn("AABBCC");
        
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request parameter").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("tour").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("brandCode").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanyCode").build());        
    }
    

    @Test
    public void test_getBrochure() throws Throwable {
        BrochureServiceImpl service = mock(BrochureServiceImpl.class);
        GetBrochureRequest request = mock(GetBrochureRequest.class);
        
        when(service.getBrochure(any(GetBrochureRequest.class))).thenCallRealMethod();
        
        GetBrochureResponse response = service.getBrochure(request);
        
        
        Collection<com.ttc.ch2.api.ccapi.v3.Message> messages = response.getMessageContext().getMessage();
        logger.info("");
        for (com.ttc.ch2.api.ccapi.v3.Message message : messages) {
            logger.info("", message.getMessageType().getDescription());
        }
        
        Message message = MessageBuilder.newMessage(BLMT. SYSTEM_ERROR).withSubject("Exception", "The data \"null\" is not legal for a JDOM attribute: A null is not a legal XML value.").build();
        
        assertContains(messages, message);
//        WsMessage expected = new WsMessage(message);
//        logger.info(expected.getDetails());
//        
//        assertContainsUtil(messages, message));
        
        assertFalse(response.isSuccessful());
    }

}

