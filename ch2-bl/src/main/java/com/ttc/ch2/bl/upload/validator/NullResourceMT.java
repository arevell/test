package com.ttc.ch2.bl.upload.validator;

import java.util.Arrays;
import java.util.List;

import com.ttc.util.messages.Severity;
import com.ttc.util.messages.subject.MessageSubjectName;
import com.ttc.util.messages.type.FixedSubjectMessageType;

public enum NullResourceMT implements FixedSubjectMessageType {
    
    
    
    REQUEST("ERR_REQ_NULL", "The request is null", Severity.ERROR, SubjectNames.REQ),
    INPUTSTREAM("ERR_INPUT_STREAM", "The InputStream parameter is null", SubjectNames.IS);
    

    private final String code;
    private final String description;
    private final Severity severity;
    private final List<MessageSubjectName> subjectNames;

    private NullResourceMT(String code, String description, Severity severity, MessageSubjectName... subjectNames) {
        this.code = code;
        this.description = description;
        this.severity = severity;
        this.subjectNames = Arrays.asList(subjectNames);
    }

    private NullResourceMT(String code, String description, MessageSubjectName... subjectNames) {
        this(code, description, Severity.ERROR, subjectNames);
    }

    private NullResourceMT(String code, String description) {
        this(code, description, Severity.ERROR);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    @Override
    public List<MessageSubjectName> getSubjectNames() {
        return subjectNames;
    }

    private static class SubjectNames {
        private static final MessageSubjectName REQ = new MessageSubjectName("Request");
        private static final MessageSubjectName IS = new MessageSubjectName("InputStream");
    }

}
