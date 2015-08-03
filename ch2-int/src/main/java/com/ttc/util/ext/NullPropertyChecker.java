package com.ttc.util.ext;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.validation.checker.PrerequisiteAwareChecker;

public class NullPropertyChecker extends PrerequisiteAwareChecker<Object> {
    private String propertyName;
    private String subjectName;

    @Override
    public Collection<Message> check(Object item) {        
        Collection<Message> messages = new ArrayList<>();
        
        Object property = propertyAccess(item, propertyName);

        check(messages, property);

        return messages;
    }

    protected void check(Collection<Message> messages, Object property) {
        if (property == null) {
            Message message = MessageBuilder.newMessage(PropertyMT.NULL).withNameOnlySubject(subjectName).build();
            messages.add(message);
        }
    }

    protected Object propertyAccess(Object bean, String name) {

        if (StringUtils.isBlank(name) || bean == null) {
            return bean;
        }

        try {
            try {
                Object property = PropertyUtils.getProperty(bean, name);
                return property;
                
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            }
        } catch (Throwable th) {
        }
        return null;
    }
    

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

}
