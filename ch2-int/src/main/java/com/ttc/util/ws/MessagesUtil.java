package com.ttc.util.ws;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ObjectUtils;

import com.ttc.ch2.api.ccapi.v3.GetTourCategoriesResponse;
import com.ttc.ch2.api.ccapi.v3.GetTourDetailsFullResponse;
import com.ttc.ch2.api.ccapi.v3.MessageContext;
import com.ttc.ch2.api.ccapi.v3.SearchToursAggregatedResponse;
import com.ttc.ch2.api.ccapi.v3.SearchToursResponse;
import com.ttc.ch2.api.ccapi.v3.GetTourDataUploadStatusResponse;
import com.ttc.ch2.api.ccapi.v3.Status;
import com.ttc.ch2.api.ccapi.v3.UploadTourInfoResponse;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageType;
import com.ttc.util.messages.Severity;
import com.ttc.util.messages.formatter.DefaultMessageFormatter;
import com.ttc.util.messages.formatter.MessageFormatter;
import com.ttc.util.messages.subject.MessageSubject;
import com.ttc.util.soap_messages.type.SoapResponse;

public class MessagesUtil {

    public static Severity severity(SoapResponse response) {
        Severity severity = severity(response.getMessageCollection());
        return severity;
    }

    public static Severity severity (Collection<Message> messages) {
        Severity result = Severity.INFO;
        if (messages != null) {
            for (Message message : messages) {
                Severity severity = message.getMessageType().getSeverity();
                if (severity != null && result.compareTo(severity) < 0) {
                    result = severity; 
                }
            }
        }

        return result;
    }
    
    public static void addMessage(SoapResponse response, Message msg) {
        if (response == null || msg == null) {
            return;
        }
        
        Collection<Message> collection = response.getMessageCollection();
        if (collection == null) {
            collection = new ArrayList<>();
            response.setMessageCollection(collection);
        }
        
        collection.add(msg);
    }
    
    public static void addMessages(SoapResponse response, Collection<Message> msgs) {
        if (response == null || msgs == null || msgs.isEmpty()) {
            return;
        }
        
        Collection<Message> collection = response.getMessageCollection();
        if (collection == null) {
            collection = new ArrayList<>();
            response.setMessageCollection(collection);
        }
        
        collection.addAll(msgs);
    }
 
    public static boolean append(MessageContext ctx, Collection<Message> msgs) {
        if (msgs == null) {
            return true;
        }
        for (Message msg : msgs) {
            append(ctx, msg);
        }
        
        if (ctx.getStatus() == null) {
            ctx.setStatus(Status.SUCCESS);
        }
        
        return Status.SUCCESS.equals(ctx.getStatus());
    }
    
    
    
    public static boolean append(MessageContext ctx, Message msg) {
        if (ctx == null || msg == null) {
            return true;
        }
        
        com.ttc.ch2.api.ccapi.v3.Message copy = copy(msg);
        ctx.getMessage().add(copy);
        
        for (com.ttc.ch2.api.ccapi.v3.Message message : ctx.getMessage()) {
            if (com.ttc.ch2.api.ccapi.v3.Severity.ERROR.equals(message.getMessageType().getSeverity()) ) {
                ctx.setStatus(Status.FAILURE);
                return false;
            }
        }
        
        ctx.setStatus(Status.SUCCESS);
        return true;
//        
//        List<com.ttc.ch2.api.ccapi.v3.Message> messages = ctx.getMessage();
//        
//        com.ttc.ch2.api.ccapi.v3.Message message = new com.ttc.ch2.api.ccapi.v3.Message();
//        MessageType messageType = message.getMessageType();
//        
////        MessageType type;
//        message.setMessageType(com.ttc.util.messages.MessageType);
    }
    
   public static com.ttc.ch2.api.ccapi.v3.Message copy(Message source) {
       com.ttc.ch2.api.ccapi.v3.Message copy = new com.ttc.ch2.api.ccapi.v3.Message();

       MessageType messageType = source.getMessageType();
        copy.setMessageType(copy(messageType));
       
        MessageFormatter formatter = new DefaultMessageFormatter();
        String interpolatedMessage = formatter.format(source);
        copy.setInterpolatedMessage(interpolatedMessage);
//        source.g
        
        copy(copy, source.getSubjectList());
//        List<MessageSubject> messageSubject = copy.getMessageSubject();
       return copy;
   }
////    
//   protected static MessageType copy(MessageType source) {
//       return null;
//   }
//    

    
    private static com.ttc.ch2.api.ccapi.v3.MessageType copy(MessageType source) {
        com.ttc.ch2.api.ccapi.v3.MessageType copy = new com.ttc.ch2.api.ccapi.v3.MessageType();
        
        copy.setCode(source.getCode());
        copy.setDescription(source.getDescription());
        
        Severity severity = source.getSeverity();
        com.ttc.ch2.api.ccapi.v3.Severity value = copy (severity);
        copy.setSeverity(value );
        
        return copy ;
    }
    
    
    private static com.ttc.ch2.api.ccapi.v3.Severity copy(Severity source) {
        if (source== null) {
            return null;
        }
        
        switch (source) {
            case ERROR:
                return com.ttc.ch2.api.ccapi.v3.Severity.ERROR;
    
            case WARNING:
                return com.ttc.ch2.api.ccapi.v3.Severity.WARNING;
                
            default:
                return com.ttc.ch2.api.ccapi.v3.Severity.INFO;
        }
    }
    
    protected static void copy(com.ttc.ch2.api.ccapi.v3.Message dest, List<MessageSubject> subjects) {
        List<com.ttc.ch2.api.ccapi.v3.MessageSubject> copies = dest.getMessageSubject();
        for (MessageSubject subject : subjects) {
            com.ttc.ch2.api.ccapi.v3.MessageSubject copy = new com.ttc.ch2.api.ccapi.v3.MessageSubject();
            copy.setName(subject.getName());
            copy.setValue(ObjectUtils.toString(subject.getValue()));
            copies.add(copy);
        }
    }
    
    public static boolean assignContext(UploadTourInfoResponse response, Collection<Message> msgs) {
        MessageContext context = response.getMessageContext();
        if (context == null) {
            context = new MessageContext();
            response.setMessageContext(context);
        }
        boolean result = append(context, msgs);
        response.setSuccessful(result);
        return result;
    }
    
    public static boolean assignContext(UploadTourInfoResponse response, Message msg) {
        MessageContext context = response.getMessageContext();
        if (context == null) {
            context = new MessageContext();
            response.setMessageContext(context);
        }
        
        boolean result = append(context, msg);
        response.setSuccessful(result);
        return result;
    }
    
    public static boolean assignContext(GetTourDataUploadStatusResponse response, Message msg) {
    	MessageContext context = response.getMessageContext();
    	if (context == null) {
    		context = new MessageContext();
    		response.setMessageContext(context);
    	}
    	
    	boolean result = append(context, msg);
    	response.setSuccessful(result);
    	return result;
    }
    
    public static boolean assignContext(GetTourCategoriesResponse response, Collection<Message> msgs) {
        MessageContext context = response.getMessageContext();
        if (context == null) {
            context = new MessageContext();
            response.setMessageContext(context);
        }
        boolean result = append(context, msgs);
        response.setSuccessful(result);
        return result;
    }
    
    public static boolean assignContext(GetTourCategoriesResponse response, Message msg) {
        MessageContext context = response.getMessageContext();
        if (context == null) {
            context = new MessageContext();
            response.setMessageContext(context);
        }
        
        boolean result = append(context, msg);
        response.setSuccessful(result);
        return result;
    }
    
    public static boolean assignContext(SearchToursResponse response, Collection<Message> msgs) {
        MessageContext context = response.getMessageContext();
        if (context == null) {
            context = new MessageContext();
            response.setMessageContext(context);
        }
        boolean result = append(context, msgs);
        response.setSuccessful(result);
        return result;
    }
    
    public static boolean assignContext(SearchToursResponse response, Message msg) {
        MessageContext context = response.getMessageContext();
        if (context == null) {
            context = new MessageContext();
            response.setMessageContext(context);
        }
        
        boolean result = append(context, msg);
        response.setSuccessful(result);
        return result;
    }
    
    public static boolean assignContext(SearchToursAggregatedResponse response, Collection<Message> msgs) {
        MessageContext context = response.getMessageContext();
        if (context == null) {
            context = new MessageContext();
            response.setMessageContext(context);
        }
        boolean result = append(context, msgs);
        response.setSuccessful(result);
        return result;
    }
    
    public static boolean assignContext(SearchToursAggregatedResponse response, Message msg) {
        MessageContext context = response.getMessageContext();
        if (context == null) {
            context = new MessageContext();
            response.setMessageContext(context);
        }
        
        boolean result = append(context, msg);
        response.setSuccessful(result);
        return result;
    }
    
    public static boolean assignContext(GetTourDetailsFullResponse response, Collection<Message> msgs) {
        MessageContext context = response.getMessageContext();
        if (context == null) {
            context = new MessageContext();
            response.setMessageContext(context);
        }
        boolean result = append(context, msgs);
        response.setSuccessful(result);
        return result;
    }
    
    public static boolean assignContext(GetTourDetailsFullResponse response, Message msg) {
        MessageContext context = response.getMessageContext();
        if (context == null) {
            context = new MessageContext();
            response.setMessageContext(context);
        }
        
        boolean result = append(context, msg);
        response.setSuccessful(result);
        return result;
    }
    
        public static boolean assignSoapMessageContext(Object bean, Collection<Message> msgs) {
        try {
            PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(bean, "messageContext");
            Method readMethod = descriptor.getReadMethod();
            com.ttc.ch2.api.ccapi.v3.MessageContext ctx = null;
            Object obj = readMethod.invoke(bean);
            
            if (obj instanceof com.ttc.ch2.api.ccapi.v3.MessageContext) {
                ctx = (com.ttc.ch2.api.ccapi.v3.MessageContext) obj;
            }
            
            if (ctx == null) {
                ctx = new com.ttc.ch2.api.ccapi.v3.MessageContext();
                Method writeMethod = descriptor.getWriteMethod();
                writeMethod.invoke(bean, ctx);
            }
            append(ctx, msgs);
            return true;
            
        } catch (Throwable ignored) {
//            logger.warn("", ignored);
        }
        return false;
    }
    public static boolean assignSoapMessageContext(Object bean, Message msg) {
        try {
            PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(bean, "messageContext");
            Method readMethod = descriptor.getReadMethod();
            com.ttc.ch2.api.ccapi.v3.MessageContext ctx = null;
            Object obj = readMethod.invoke(bean);
            
            if (obj instanceof com.ttc.ch2.api.ccapi.v3.MessageContext) {
                ctx = (com.ttc.ch2.api.ccapi.v3.MessageContext) obj;
            }
            
            if (ctx == null) {
                ctx = new com.ttc.ch2.api.ccapi.v3.MessageContext();
                Method writeMethod = descriptor.getWriteMethod();
                writeMethod.invoke(bean, ctx);
            }
            append(ctx, msg);
            return true;
            
        } catch (Throwable ignored) {
//            logger.warn("", ignored);
        }
        return false;
    }
}
