package com.ttc.ch2.bl.departure.common;

import java.text.MessageFormat;

public enum TropicSynchronizeMessages {

	// errors
	UNEXPECTED_EXCEPTION("ERR-1001", "Unexpected problem occurred"),
	GET_TOURS_FROM_TROPIC_EXCEPTION("ERR-1002", "A problem has occurred for brand code: [{0}] whilst calling the Web Service for tours list. Error message: {1}"),
	READ_BRANDS_FROM_DB_EXCEPTION("ERR-1003", "A problem with received brands from DB"),	
	OUTGOING_ARCHIVE_EXCEPTION("ERR-1015E", "Creating File Collect for brand: [{0}] - failed."),
	TOURS_WITHOUT_BRAND("ERR-1016E","After save/update data [temporary message] -  tours without brand were found:{0}"),

	// warnings
	NO_BRANDS("WRN-2000", "List of brands is empty, nothing to do"), 
	TD_CHECK_SUM_EXIST_IN_CR("INF-2001", "The content repository imported tour departure from tropics in previous synchronization: tour code [{0}]"), 
	CANCEL_PROCESS("WRN-2004", "The process was cancelled: {0}"),
	INACTIVE_PROCESS("WRN-2005", "The process was inactive: {0}"),
	INDEXING_TOURN_OFF("WRN-2006", "Elastic search indexing is disabled by the property 'elastic.search.indexing' in its configuration"),
	SCHEMA_VALIDATION_MARK("WRN-2007", "A validation error with generating main content tour departure using JAXB. Tour code: [{0}]"),
	SCHEMA_VALIDATION_WARNING("WRN-2008","A validation error with generating main content tour departure using JAXB. Tour code: [{0}] / file name - [{1}]"),
	OPERATION_FOR_PRODUCT_IMPORT_WARNING("WRN-2009", "Operation for product code: [{0}] being: [{1}/{2}] - failed - cannot access the Web Service."),
	OPERATION_FOR_PRODUCT_MAPPING_WARNING("WRN-2010", "Operation for product code: [{0}] being: [{1}/{2}] - failed - cannot map the Tour Departure data."),
	TOUR_INFO_CH1_DOWNLOAD_DISABLED_CFG("WRN-2011", "Tour Info CH1 download for brand: [{0}] - disabled by the property 'ch1.tidownload.enabled' in its configuration"),
	
	// info
	SYNCHRONIZE_START("INF-4001", "Synchronizing - started"),
	SYNCHRONIZE_END("INF-4002", "Synchronizing - finished - time: [{0}]"),
	SYNCHRONIZE_STATUS("INF-4003", "Synchronization - start: [{0}] end: [{1}] duration: [{2}]"),
	OPERATION_FOR_BRAND_START("INF-4004", "Operation for Brand: [{0}] code: [{1}] - started"),
	OPERATION_FOR_BRAND_END("INF-4005", "Operation for Brand: [{0}] code: [{1}] - finished - time: [{2}]"),
	OPERATION_FOR_PRODUCT_START("INF-4006", "Operation for product code: [{0}] being: [{1}/{2}] - started"),
	OPERATION_FOR_PRODUCT_END("INF-4007", "Operation for product code: [{0}] being: [{1}/{2}] - finished - time: [{3}]"),
	START_GENERATING_FILES_FOR_TOUR("INF-4010", "Generating files for product code: [{0}] - started"),
	START_MAPPING_VERSION3("INF-4011", "Start mapping for product code: [{0}] selling companies: {1} version 3"),
	START_MAPPING_VERSION1("INF-4012", "Start mapping for product code: [{0}] selling companies: {1} version 1"),
	END_GENERATING_FILES_FOR_TOUR("INF-4013", "Generating files for product code: [{0}] - finished - time: [{1}]"),
	IMPORT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE("INF-4014", "Data imported for product code: [{0}] selling company: [{1}]"),
	REJECT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE("INF-4015", "Data rejected for product code: [{0}] selling company: [{1}] reason: [{2}]"),
	SAVE_CR_FOR_TOUR_CODE("INF-4017", "The new 'ContentRepository' for product code: [{0}] was saved"),
	UPDATE_CR_FOR_TOUR_CODE("INF-4018", "The 'ContentRepository' for product code: [{0}] was updated"),
	OUTGOING_ARCHIVE_FOR_BRAND("INF-4019", "Creating File Collect for brand: [{0}] - started"),
	OUTGOING_ARCHIVE_FOR_BRAND_END("INF-4020", "Creating File Collect for brand: [{0}] - finished - time: [{1}]"),
	BRAND_STATUS("INF-4029", "Status operation for Brand: [{0}] - start: [{1}] end: [{2}] time elapsed: [{3}]"),
	DELETE_CR_START("INF-4030", "Deleting unnecessary content repository data for brand: [{0}] - started"),
	DELETE_CR_END("INF-4042", "Deleting unnecessary content repository data for brand: [{0}] - finished - time: [{1}]"),
	INDEX_SUCCESS("INF-4031", "TourDeparture indexing was successful"),
	RECONCILIATION_GEN("INF-4032", "The reconciliation report: [{0}] - started"),	
	TOUR_INFO_CH1_DOWNLOAD_DISABLED("INF-4033", "Tour Info CH1 download for brand: [{0}] - disabled"),
	TOUR_INFO_CH1_DOWNLOAD_START("INF-4034", "Tour Info CH1 download for brand: [{0}] - started"),
	TOUR_INFO_CH1_DOWNLOAD_END("INF-4035", "Tour Info CH1 download for brand: [{0}] - finished - time: [{1}]"),
	TOUR_INFO_CH1_UPLOAD_TO_CH2_START("INF-4036", "Tour Info CH1 uploading to CH2 for brand: [{0}] - started"),
	TOUR_INFO_CH1_UPLOAD_TO_CH2_END("INF-4037", "Tour Info CH1 uploading to CH2 for brand: [{0}] - finished - time: [{1}]"),
	CLEAN_CR_START("INF-4038","Cleaning tour departure  for brand: [{0}] - started"),
	CLEAN_CR_END("INF-4039","Cleaning tour departure for brand: [{0}] - finished - time: [{1}]"),
	INDEXING_START("INF-4040","Indexing for brand: [{0}] - started"),
	INDEXING_END("INF-4041","Indexing for brand: [{0}] - finished - time: [{1}]"),
	SHORT_INDEX_SUCCESS("INF-4042","Short TourDeparture indexing was successful for [{0}] tours"),
	SHORT_INDEX_IGNORED("INF-4043","Short TourDeparture indexing ignored due to 0 modified tours"),	
	RECONCILIATION_GEN_END("INF-4044", "The reconciliation report: [{0}] - finished - time : [{1}]"),
	EXTENDED_STATUS("INF-4045", "Extended operation - start: [{0}] end: [{1}] duration: [{2}]"),
	COMMUNICATION_TIME("INF-4046", "Communication duration: [{0}]"),
	CLEANING_ES_START("INF-4047","ElasticSearch started cleaning index"),
	CLEANING_ES_STOP("INF-4048","ElasticSearch ended cleaning index"),
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
