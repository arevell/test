package com.ttc.ch2.bl.departure.common;

import java.text.MessageFormat;

public enum TropicSynchronizeMessages {

	// errors
	UNEXPECTED_EXCEPTION("ERR-1001", "Unexpected problem occured"),
	GET_TOURS_FROM_TROPIC_EXCEPTION("ERR-1002", "Some problem has occured for brand code: [{0}] whilst calling the Web Service for tours list. Error message: {1}"),
	READ_BRANDS_FROM_DB_EXCEPTION("ERR-1003", "Problem with recive brands from DB"),
	@Deprecated
	GENERATE_CHECK_SUM_EXCEPTION("ERR-1004", "System has problem with generate checksum for xml:{0}"),
	@Deprecated
	XSL_FILE_NOT_FOUND("ERR-1005", "XSL File not found bean TropicDepartureSynchronizService is not correctly initialized:{0}"),
	@Deprecated
	XSL_FILE_TRANSFORM_CONFIGURATION("ERR-1006", "Problem with transform xsl configuration"),
	@Deprecated
	XSL_FILE_TRANSFORM_EXCEPTION("ERR-1007", "Problem with transform xsl with tourdeparture :{0} - file: {1}"),
	@Deprecated
	GENERATE_TOUR_DEPARTURE_EXCEPTION_V3("ERR-1008", "Some problem has occured for tour code: [{0}] whilst generating tour departure main content version 3."),
	@Deprecated
	GENERATE_TOUR_DEPARTURE_EXCEPTION_V1("ERR-1009", "Some problem has occured for tour code: [{0}] whilst generating tour departure main content version 1."),
	@Deprecated
	GET_DATES_AND_RATE_FROM_TROPIC_EXCEPTION("ERR-1012", "Problem with recive GetDatesAndRate for SellingCompany:{0} product code: {1}"),
	OUTGOING_ARCHIVE_EXCEPTION("ERR-1015E", "Creating Outgoing Archives for brand: [{0}] - failed."),

	// warnings
	NO_BRANDS("WRN-2000", "List of brands is empty nothing to do"), 
	TD_CHECK_SUM_EXIST_IN_CR("INF-2001", "Content repository has tour departure imported from tropics in previous synchronization: tour code [{0}]"), 
	@Deprecated
	TOUR_DAPARTURE_DATA_LIST_NOT_EXIST_BRAND("WRN-2002", "Tour departure data list not exist for brand code:{0} probably all elements was reject"),
	@Deprecated
	ADD_MOCK_ELEMENT("WRN-2003","Mapping add mock element {0} for tour code {1} , selling company: {2} version {3}"),
	CANCEL_PROCESS("WRN-2004", "Process was cancelled: {0}"),
	INACTIVE_PROCESS("WRN-2005", "Process was inactived: {0}"),
	INDEXING_TOURN_OFF("WRN-2006", "Elastic search indexing is disabled"),
	SCHEMA_VALIDATION_MARK("WRN-2007", "Validation error with generate main content tour departure using JAXB. Tour code: [{0}]"),
	SCHEMA_VALIDATION_WARNING("WRN-2008"," Validation error with generate main content tour departure using JAXB. Tour code: [{0}] / file name - [{1}]"),
	OPERATION_FOR_PRODUCT_IMPORT_WARNING("WRN-2009", "Operation for product code: [{0}] being: [{1}/{2}] - failed - cannot access to the Web Service."),
	OPERATION_FOR_PRODUCT_MAPPING_WARNING("WRN-2010", "Operation for product code: [{0}] being: [{1}/{2}] - failed - cannot map the Tour Departure data."),

	// info
	SYNCHRONIZE_START("INF-4001", "Synchronizing - started"),
	SYNCHRONIZE_END("INF-4002", "Synchronizing - finished - time: [{0}]"),
	SYNCHRONIZE_STATUS("INF-4003", "Synchronization - start: [{0}] end: [{1}] duration: [{2}]"),
	OPERATION_FOR_BRAND_START("INF-4004", "Operation for Brand: [{0}] code: [{1}] - started"),
	OPERATION_FOR_BRAND_END("INF-4005", "Operation for Brand: [{0}] code: [{1}] - finished - time: [{2}]"),
	OPERATION_FOR_PRODUCT_START("INF-4006", "Operation for product code: [{0}] being: [{1}/{2}] - started"),
	OPERATION_FOR_PRODUCT_END("INF-4007", "Operation for product code: [{0}] being: [{1}/{2}] - finished - time: [{3}]"),
	@Deprecated
	IMPORT_DATA_FOR_BRAND_START("INF-4008", "Importing data for Brand: [{0}] - started"),  // to remove
	@Deprecated
	IMPORT_DATA_FOR_BRAND_END("INF-4009", "Importing data for Brand: [{0}] - finished - time: [{1}] size: [{2}]"), // to remove
	START_GENERATING_FILES_FOR_TOUR("INF-4010", "Generating files for product code: [{0}] - started"),
	START_MAPPING_VERSION3("INF-4011", "Start mapping for product code: [{0}] selling companies: {1} version 3"),
	START_MAPPING_VERSION1("INF-4012", "Start mapping for product code: [{0}] selling companies: {1} version 1"),
	END_GENERATING_FILES_FOR_TOUR("INF-4013", "Generating files for product code: [{0}] - finished - time: [{1}]"),
	IMPORT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE("INF-4014", "Data imported for product code: [{0}] selling company: [{1}]"),
	REJECT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE("INF-4015", "Data rejected for product code: [{0}] selling company: [{1}] reason: [{2}]"),
	@Deprecated
	GENERATE_FILE_FOR_PRODUCT("INF-4016", "Generate file for tour code:{0} / file: {1} / version:{2} "),
	SAVE_CR_FOR_TOUR_CODE("INF-4017", "Save new 'ContentRepository' for product code: [{0}]"),
	UPDATE_CR_FOR_TOUR_CODE("INF-4018", "Update 'ContentRepository' for product code: [{0}]"),
	OUTGOING_ARCHIVE_FOR_BRAND("INF-4019", "Creating Outgoing Archives for brand: [{0}] - started"),
	OUTGOING_ARCHIVE_FOR_BRAND_END("INF-4020", "Creating Outgoing Archives for brand: [{0}] - finished - time: [{1}]"),
	@Deprecated
	MERGE_FOR_TOUR_CODE("INF-4021", "Merge tour departure for tour code:{0} "),
	@Deprecated
	RECON_FOR_TOUR_CODE("INF-4022", "Reconciliation tour departure for tour code:{0} "),
	BRAND_STATUS("INF-4029", "Status operation for Brand: [{0}] - start: [{1}] end: [{2}] time elapsed: [{3}]"),
	DELETE_CR_START("INF-4030", "Deleting unnecessary content repository data for brand: [{0}] - started"),
	DELETE_CR_END("INF-4042", "Deleting unnecessary content repository data for brand: [{0}] - finished - time: [{1}]"),
	INDEX_SUCCESS("INF-4031", "TourDeparture indexing was successful"),
	RECONCILIATION_GEN("INF-4032", "Reconciliation raport: [{0}] - started"),
	TOUR_INFO_CH1_DOWNLOAD_DISABLED("INF-4033", "Tour Info CH1 download for brand: [{0}] - disabled"),
	TOUR_INFO_CH1_DOWNLOAD_START("INF-4034", "Tour Info CH1 download for brand: [{0}] - started"),
	TOUR_INFO_CH1_DOWNLOAD_END("INF-4035", "Tour Info CH1 download for brand: [{0}] - finished - time: [{1}]"),
	TOUR_INFO_CH1_UPLOAD_TO_CH2_START("INF-4036", "Tour Info CH1 uploading to CH2 for brand: [{0}] - started"),
	TOUR_INFO_CH1_UPLOAD_TO_CH2_END("INF-4037", "Tour Info CH1 uploading to CH2 for brand: [{0}] - finished - time: [{1}]"),
	CLEAN_CR_START("INF-4038","Clean tour departure  for brand: [{0}] - started"),
	CLEAN_CR_END("INF-4039","Clean tour departure for brand: [{0}] - finished - time: [{1}]"),
	INDEXING_START("INF-4040","Indexing for brand: [{0}] - started"),
	INDEXING_END("INF-4041","Indexing for brand: [{0}] - finished - time: [{1}]"),
	;

	private String code;
	private String message;

	private TropicSynchronizeMessages(String code, String msg) {
		this.code = code;
		this.message = msg;
	}

	public static TropicSynchronizeMessages getByCode(String code) {
		TropicSynchronizeMessages tab[] = values();
		for (int i = 0; i < tab.length; i++) {
			if (tab[i].getCode().equals(code))
				return tab[i];
		}
		throw new IllegalArgumentException("Enum 'TropicSynchronizeErrorCodes' don't exist for code:" + code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String createMessage(Object... params) {
		return MessageFormat.format(this.getMessage(), params);
	}

	public static String getMessage(TropicSynchronizeMessages error, Object... params) {
		return MessageFormat.format(error.getMessage(), params);
	}

	public static String getMessage(String code, Object... params) {
		return getMessage(TropicSynchronizeMessages.getByCode(code), params);
	}
}
