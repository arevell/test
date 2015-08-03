package com.ttc.ch2.bl.upload.validator;

import com.ttc.util.messages.Severity;
import com.ttc.util.messages.type.OpenSubjectMessageType;

public enum BLMT implements OpenSubjectMessageType {
    NULL("ERRNULL", "The structure is null"),
    NONAME("ERRNAME", "The file name is empty"),
    SYSTEM_ERROR("ERREXCEPTION", "system error occurred: {0}", Severity.ERROR),
    UPLOAD_TOUR_INFO("ERROR_UPLOAD", "Error during message upload"),
    BROCHURE("ERROR_UPLOAD", "Error during message"),
    
    STIU_INCORRECT_FILENAME("WRNNAME_STIU", "File {0} was not found", Severity.WARNING);
    
    
    private final String code;
    private final String description;
    private final Severity severity;

    private BLMT(String code, String description, Severity severity) {
        this.code = code;
        this.description = description;
        this.severity = severity;
    }

    private BLMT(String code, String description) {
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
