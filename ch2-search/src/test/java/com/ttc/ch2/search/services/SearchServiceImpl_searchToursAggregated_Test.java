package com.ttc.ch2.search.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedRequest;
import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedResponse;
import com.ttc.util.ext.PropertyMT;
import com.ttc.util.ext.V3MT;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.messages.formatter.DefaultMessageFormatter;
import com.ttc.util.messages.formatter.MessageFormatter;

public class SearchServiceImpl_searchToursAggregated_Test extends MessageTest {

    private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl_searchToursAggregated_Test.class);
    
    @Test
    public void test_validation_null_request() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        Collection<Message> messages = service.validate((SearchToursAggregatedRequest)null);
        
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
    public void test_validation_request() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        SearchToursAggregatedRequest request = new SearchToursAggregatedRequest();
        request.setFirstRecordNumber(BigInteger.ZERO);
        request.setNumberOfRecords(BigInteger.ZERO);
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanies").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER_RANGE).withNameOnlySubject("numberOfRecords").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER).withNameOnlySubject("first record number").withNameOnlySubject(" >= 1").build());
    }
    
    @Test
    public void test_validation_numberOfRecords_in_range() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        SearchToursAggregatedRequest request = new SearchToursAggregatedRequest();
        request.setFirstRecordNumber(BigInteger.ZERO);
        request.setNumberOfRecords(BigInteger.TEN);
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanies").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER_RANGE).withNameOnlySubject("numberOfRecords").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER).withNameOnlySubject("first record number").withNameOnlySubject(" >= 1").build());
    }
    
    @Test
    public void test_validation_numberOfRecords_above_range() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        SearchToursAggregatedRequest request = new SearchToursAggregatedRequest();
        request.setFirstRecordNumber(BigInteger.ZERO);
        request.setNumberOfRecords(BigInteger.valueOf(120L));
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanies").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER_RANGE).withNameOnlySubject("numberOfRecords").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER).withNameOnlySubject("first record number").withNameOnlySubject(" >= 1").build());
    }

    
    @Test
    public void test_validation_valid_first_recordNumber() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        SearchToursAggregatedRequest request = new SearchToursAggregatedRequest();
        request.setFirstRecordNumber(BigInteger.TEN);
        request.setNumberOfRecords(BigInteger.TEN);
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanies").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER_RANGE).withNameOnlySubject("numberOfRecords").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER).withNameOnlySubject("first record number").withNameOnlySubject(" >= 1").build());
    }
    
    @Test
    public void test_validation_sc() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        SearchToursAggregatedRequest request = new SearchToursAggregatedRequest();
        request.setFirstRecordNumber(BigInteger.ZERO);
        request.setNumberOfRecords(BigInteger.ZERO);
        
        Whitebox.setInternalState(request, "sellingCompanies", Collections.singletonList("ABCDEF"));
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanyName").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER_RANGE).withNameOnlySubject("numberOfRecords").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER).withNameOnlySubject("first record number").withNameOnlySubject(" >= 1").build());
    }
    
    @Test
    public void test_validation_sc_empty() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        SearchToursAggregatedRequest request = new SearchToursAggregatedRequest();
        request.setFirstRecordNumber(BigInteger.ZERO);
        request.setNumberOfRecords(BigInteger.ZERO);
        
        Whitebox.setInternalState(request, "sellingCompanies", Collections.emptyList());
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanies").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER_RANGE).withNameOnlySubject("numberOfRecords").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER).withNameOnlySubject("first record number").withNameOnlySubject(" >= 1").build());
    }
    
    @Test
    public void test_validation_sc_null() throws Throwable {
        
        SearchServiceImpl service = new SearchServiceImpl();
        
        SearchToursAggregatedRequest request = new SearchToursAggregatedRequest();
        request.setFirstRecordNumber(BigInteger.ZERO);
        request.setNumberOfRecords(BigInteger.ZERO);
        
        Whitebox.setInternalState(request, "sellingCompanies", null);
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("sellingCompanies").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER_RANGE).withNameOnlySubject("numberOfRecords").build());
        assertContainsUtil(messages, MessageBuilder.newMessage(V3MT.INVALID_NUMBER).withNameOnlySubject("first record number").withNameOnlySubject(" >= 1").build());
    }
    
    @Test
    public void test_request_null() throws Throwable {
        
        SearchServiceImpl service = mock(SearchServiceImpl.class);
        when(service.searchToursAggregated(any(SearchToursAggregatedRequest.class))).thenCallRealMethod();
        
        SearchToursAggregatedResponse response = service.searchToursAggregated(null);
        
        Collection<com.ttc.ch2.api.ccapi.v3.Message> messages = response.getMessageContext().getMessage();
        logger.info("");
        for (com.ttc.ch2.api.ccapi.v3.Message message : messages) {
            logger.info(message.getMessageType().getDescription());
        }
        
        Message message = MessageBuilder.newMessage(V3MT. SYSTEM_ERROR).withSubject("Exception", null).build();
        assertContains(messages, message);
        
        assertFalse(response.isSuccessful());
    }
    
    @Test
    public void test_request() throws Throwable {
        
        SearchServiceImpl service = mock(SearchServiceImpl.class);
        when(service.searchToursAggregated(any(SearchToursAggregatedRequest.class))).thenCallRealMethod();
        
        SearchToursAggregatedRequest request = mock(SearchToursAggregatedRequest.class);
        doThrow(new RuntimeException("test_request()")).doNothing().when(request).setPreferedRoomType(any(String.class));
//        when(request.setPreferedRoomType(any(String.class))).thenThrow(new RuntimeException("test_request()"));
//        when
        
        SearchToursAggregatedResponse response = service.searchToursAggregated(request);
        
        Collection<com.ttc.ch2.api.ccapi.v3.Message> messages = response.getMessageContext().getMessage();
        logger.info("");
        for (com.ttc.ch2.api.ccapi.v3.Message message : messages) {
            logger.info(message.getMessageType().getDescription());
        }
        
        Message message = MessageBuilder.newMessage(V3MT. SYSTEM_ERROR).withSubject("Exception", "test_request()").build();

        assertContains(messages, message);
        
        assertFalse(response.isSuccessful());
    }
}
