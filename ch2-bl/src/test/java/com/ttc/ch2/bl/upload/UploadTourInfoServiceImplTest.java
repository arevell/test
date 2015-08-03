package com.ttc.ch2.bl.upload;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.InputStream;
import java.util.Collection;

import javax.activation.DataHandler;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.ttc.ch2.api.ccapi.v3.UploadTourInfoRequest;
import com.ttc.ch2.api.ccapi.v3.UploadTourInfoResponse;
import com.ttc.ch2.bl.upload.common.OperationStatus;
import com.ttc.ch2.bl.upload.common.SellingPermissionChecker;
import com.ttc.ch2.bl.upload.validator.BLMT;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;
import com.ttc.util.ext.PropertyMT;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.messages.formatter.DefaultMessageFormatter;
import com.ttc.util.messages.formatter.MessageFormatter;


public class UploadTourInfoServiceImplTest extends MessageTest {
    static Logger logger = LoggerFactory.getLogger(UploadTourInfoServiceImplTest.class);
    
    @Test
    public void test_validation_null_request() throws Throwable {
        
        UploadTourInfoServiceImpl service = new UploadTourInfoServiceImpl();
        
        Collection<Message> messages = service.validate(null);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request parameter").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("file parameter").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("input stream parameter").build());
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("file name").build());
        
    }
    
    @Test
    public void test_validation_request_null() throws Throwable {
        UploadTourInfoServiceImpl service = new UploadTourInfoServiceImpl();
        UploadTourInfoRequest request = mock(UploadTourInfoRequest.class);
        
        Collection<Message> messages = service.validate(request);
        
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);

        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request parameter").build());
        
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("input stream parameter").build());
        
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("file name").build());
    }
        
    @Test
    public void test_validation_request_stream() throws Throwable {
        UploadTourInfoServiceImpl service = new UploadTourInfoServiceImpl();
        
        UploadTourInfoRequest request = mock(UploadTourInfoRequest.class);
        
        DataHandler fileData = mock(DataHandler.class);
        when(request.getFileData()).thenReturn(fileData);
        
        when(fileData.getInputStream()).thenReturn(mock(InputStream.class));
        
        Collection<Message> messages = service.validate(request);
        
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request parameter").build());
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("input stream parameter").build());
        
        assertContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("file name").build());
    }
    
    @Test
    public void test_validation_request_file() throws Throwable {
        UploadTourInfoServiceImpl service = new UploadTourInfoServiceImpl();
        
        UploadTourInfoRequest request = mock(UploadTourInfoRequest.class);
        
        when(request.getFileName()).thenReturn("file");
        
        Collection<Message> messages = service.validate(request);
        
        MessageFormatter mf = new DefaultMessageFormatter();
        logger.info("");
        for (Message message : messages) {
            logger.info("{}", mf.format(message));
        }
        
        assertNotNull(messages);
        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject("request parameter").build());        
        assertNotContainsUtil(messages, MessageBuilder.newMessage(PropertyMT.EMPTY).withNameOnlySubject("file name").build());
    }
    
    @Test
    public void test_addApiUploadTourInfoFile_UploadServiceException() throws Throwable {

        UploadTourInfoServiceImpl service = mock(UploadTourInfoServiceImpl.class);
        when(service.addApiUploadTourInfoFile(any(UploadTourInfoRequest.class))).thenCallRealMethod();
        
        UploadTourInfoRequest request = mock(UploadTourInfoRequest.class);
        when(request.getFileName()).thenReturn("file.xml");
        
        DataHandler dataHandler = mock(DataHandler.class);
        when(dataHandler.getInputStream()).thenReturn(mock(InputStream.class));
        when(request.getFileData()).thenReturn(dataHandler);
        when(request.getSecurityKey()).thenReturn("token-xxx");
        
        
        ApplicationContext ctx = mock(ApplicationContext.class);
        when(ctx.getBean(any(Class.class))).thenReturn(new SellingPermissionChecker());
        Whitebox.setInternalState(service, "ctx", ctx);
        OperationStatus os = mock(OperationStatus.class);
        when(service.addUploadTourInfoFile(any(OperationStatus.class), any(UploadTourInfoFile.class), any(InputStream.class))).thenThrow(new UploadServiceException("UploadServiceException:test content"));
        
        
        
        whenNew(OperationStatus.class).withAnyArguments().thenReturn(os );
        
        UserCCAPIDAO userCCAPIDAO = mock(UserCCAPIDAO.class);
        when(userCCAPIDAO.findByToken(any(String.class))).thenReturn(null);
        Whitebox.setInternalState(service, "userCCAPIDAO", userCCAPIDAO);
        
        UploadTourInfoResponse response = service.addApiUploadTourInfoFile(request );
        
        Collection<com.ttc.ch2.api.ccapi.v3.Message> messages = response.getMessageContext().getMessage();
        
        Message is = MessageBuilder.newMessage(BLMT.SYSTEM_ERROR).withSubject("Exception", "UploadServiceException:test content").build();
        assertContains(messages, is);
        
        assertFalse(response.isSuccessful());
    }
    
    
    // flow was changed this situation never happen 
    @Ignore
    @Test
    public void test_addApiUploadTourInfoFile_OperationStatus() throws Throwable {
        
        UploadTourInfoServiceImpl service = mock(UploadTourInfoServiceImpl.class);
        when(service.addApiUploadTourInfoFile(any(UploadTourInfoRequest.class))).thenCallRealMethod();
        
        UploadTourInfoRequest request = mock(UploadTourInfoRequest.class);
        when(request.getFileName()).thenReturn("file.xml");
        
        DataHandler dataHandler = mock(DataHandler.class);
        when(dataHandler.getInputStream()).thenReturn(mock(InputStream.class));
        when(request.getFileData()).thenReturn(dataHandler);
        when(request.getSecurityKey()).thenReturn("token-xxx");
        
        ApplicationContext ctx = mock(ApplicationContext.class);        
        when(ctx.getBean(any(Class.class))).thenReturn(new SellingPermissionChecker());
        Whitebox.setInternalState(service, "ctx", ctx);
                
        
        OperationStatus os = mock(OperationStatus.class);
        when(os.getStatus()).thenReturn(UploadTourInfoFileStatus.FAIL);
        when(os.getLastErrorMessage()).thenReturn("LastErrorMessage: test content");
        
        when(service.addUploadTourInfoFile(any(OperationStatus.class), any(UploadTourInfoFile.class), any(InputStream.class))).thenReturn(os);
        
        whenNew(OperationStatus.class).withAnyArguments().thenReturn(os );
        
        UserCCAPIDAO userCCAPIDAO = mock(UserCCAPIDAO.class);
        when(userCCAPIDAO.findByToken(any(String.class))).thenReturn(null);
        Whitebox.setInternalState(service, "userCCAPIDAO", userCCAPIDAO);
        
        UploadTourInfoResponse response = service.addApiUploadTourInfoFile(request );
        
        Collection<com.ttc.ch2.api.ccapi.v3.Message> messages = response.getMessageContext().getMessage();
        
        Message is = MessageBuilder.newMessage(BLMT. UPLOAD_TOUR_INFO).withSubject("Exception", "LastErrorMessage: test content").build();
        assertContains(messages, is);
        
        assertFalse(response.isSuccessful());
    }

}
