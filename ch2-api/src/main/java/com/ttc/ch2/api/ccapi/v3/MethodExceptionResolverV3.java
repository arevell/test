package com.ttc.ch2.api.ccapi.v3;

import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.context.MessageContext;

import com.ttc.ch2.api.ccapi.TokenValidationException;
import com.ttc.util.ext.V3MT;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.soap_messages.type.SoapResponse;
import com.ttc.util.ws.MessagesUtil;

public class MethodExceptionResolverV3 {

	private static final Logger logger = LoggerFactory.getLogger(MethodExceptionResolverV3.class);

	private static final String CONTEXT_PATH = "com.ttc.ch2.api.ccapi.v3";
	private static final String METHOD_SET_RESULT_FLAG = "setSuccessful";
	private static final String METHOD_SET_RESULT_MESSAGE = "setErrorMessage";
	private static final String ERROR_MESSAGE = "Error whilst creating exception response";

	public boolean resolveExceptionInternal(MessageContext messageContext, Object endpoint, Class<?> clazzResponse, Exception exception) {

		try {

			Object classResponse = clazzResponse.newInstance();
			if (classResponse instanceof SoapResponse) {
                SoapResponse response = (SoapResponse) classResponse;
                response.setMessageCollection(Collections.singletonList(MessageBuilder.newMessage(V3MT.SYSTEM_ERROR).withSubject("exception", exception.getMessage()).build()));
			} else if (assignSoapMessageContext(classResponse, exception)) {
                
			    
            } else {
                classResponse.getClass().getDeclaredMethod(METHOD_SET_RESULT_MESSAGE, String.class).invoke(classResponse, exception.getMessage());                
            }
			classResponse.getClass().getDeclaredMethod(METHOD_SET_RESULT_FLAG, boolean.class).invoke(classResponse, false);

			Marshaller marshaller = JAXBContext.newInstance(CONTEXT_PATH, clazzResponse.getClassLoader()).createMarshaller();
			marshaller.marshal(classResponse, messageContext.getResponse().getPayloadResult());

		} catch (Exception e) {

			logger.error(ERROR_MESSAGE, e);
			return false;
		}

		return true;
	}
	
	private boolean assignSoapMessageContext(Object bean, Exception ex) {
	    Message message;
	    if (ex instanceof TokenValidationException) {
	        message = MessageBuilder.newMessage(V3MT.WRONG_TOKEN).withSubject("exception", ex.getMessage()).build();
            
        } else {
            message = MessageBuilder.newMessage(V3MT.SYSTEM_ERROR).withSubject("exception", ex.getMessage()).build();
        }
	    
	    return MessagesUtil.assignSoapMessageContext(bean, message);
	}
}
