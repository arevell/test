package com.ttc.util.ext;

import com.ttc.util.messages.Severity;
import com.ttc.util.messages.type.OpenSubjectMessageType;

public enum V3MT implements OpenSubjectMessageType {
    NULL("ERRNULL", "The structure is null"),
    NONAME("ERRNAME", "The file name is empty"),
    SYSTEM_ERROR("ERREXCEPTION", "system error occurred: {0}", Severity.ERROR),
    WRONG_TOKEN("ERR_TOKEN", "Invalid securityKey permission denied: {0}"),
    NO_TOUR("ERR_NOTOUR", "Cannot find tour details for the given parameters."),
    INVALID_NUMBER("ERR_WRONG_NUMBER", "Invalid number for {0} parameter, must {1} "),
    INVALID_NUMBER_RANGE("ERR_BAD_RANGE", "invalid number range for {0} parameter, must be between 1 and 100"),
    UPLOAD_TOUR_INFO("ERROR_UPLOAD", "Error during message"),
    BROCHURE("ERROR_UPLOAD", "Error during message"),
    OK("OK", "It's OK", Severity.INFO);
    
    private final String code;
    private final String description;
    private final Severity severity;

    private V3MT(String code, String description, Severity severity) {
        this.code = code;
        this.description = description;
        this.severity = severity;
    }

    private V3MT(String code, String description) {
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
