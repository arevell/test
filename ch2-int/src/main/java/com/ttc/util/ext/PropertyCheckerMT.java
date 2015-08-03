//package com.ttc.util.ext;
//
//import java.util.Arrays;
//import java.util.List;
//
//import com.ttc.util.messages.Severity;
//import com.ttc.util.messages.subject.MessageSubjectName;
//import com.ttc.util.messages.type.FixedSubjectMessageType;
//
// enum _PropertyCheckerMT implements FixedSubjectMessageType {
//    NULL("ERRNULL", "The structure is null"),
//    NONAME("ERRNAME", "The file name is empty"),
//    SYSTEM_ERROR("ERREXCEPTION", "system error occurred: {0}", Severity.ERROR),
//    INVALID_NUMBER("ERR_BAD_RANGE", "invalid number for {0} parameter, must {1} "),
//    INVALID_NUMBER_RANGE("ERR_BAD_RANGE", "invalid value for {0} parameter ({1}), must be between {2} and {3}"),
//    UPLOAD_TOUR_INFO("ERROR_UPLOAD", "Error during message"),
//    BROCHURE("ERROR_UPLOAD", "Error during message");
//    
//    private final String code;
//    private final String description;
//    private final Severity severity;
//    private final List<MessageSubjectName> subjectNames;
//
//    private _PropertyCheckerMT(String code, String description, Severity severity, MessageSubjectName ... subjectNames) {
//        this.code = code;
//        this.description = description;
//        this.severity = severity;
//        this.subjectNames = Arrays.asList(subjectNames);
//    }
//
//    private _PropertyCheckerMT(String code, String description) {
//        this(code, description, Severity.ERROR);
//    }
//
//    @Override
//    public String getCode() {
//        return code;
//    }
//
//    @Override
//    public String getDescription() {
//        return description;
//    }
//
//    @Override
//    public Severity getSeverity() {
//        return severity;
//    }
//
//    @Override
//    public List<MessageSubjectName> getSubjectNames() {
//        return subjectNames;
//    }
//
//}
