package com.ttc.util.ext;

import com.ttc.util.messages.Severity;
import com.ttc.util.messages.type.OpenSubjectMessageType;

public enum PropertyMT implements OpenSubjectMessageType {
    NULL("ERR_NULL", "The {0} property is null"),
    EMPTY("ERR_EMPTY", "The {0} property is empty"),
    NONAME("ERRNAME", "The file name is empty"),
    SYSTEM_ERROR("ERREXCEPTION", "System error occurred: {0}", Severity.ERROR),
    UPLOAD_TOUR_INFO("ERROR_UPLOAD", "Error during message");
    
    
    private final String code;
    private final String description;
    private final Severity severity;

    private PropertyMT(String code, String description, Severity severity) {
        this.code = code;
        this.description = description;
        this.severity = severity;
    }

    private PropertyMT(String code, String description) {
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

}