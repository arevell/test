package com.ttc.ch2.bl.upload.common;

import java.text.MessageFormat;

public enum TourInfoMessages 
{		
	//errors
	UNEXPECTED_EXCEPTION("ERR-1000","Unexpected problem occured"),
	TI_FILENAME_CHECK("ERR-1001","Tourcode: {0} is not equal to filename:{1}"),
	BRAND_DONT_EXIST("ERR-1002","BRAND code:{0} was not found in the system Brand table (Zip file name:{1})"),
	SELLING_COMPANIES_DONT_EXIST("ERR-1003","SellingCompanies were not found in the system SellingCompany table for tour code:{0}"),
	SCHEMA_VALIDATE_ON_FILE("ERR-1004","Schema validation error in file:{0}, xsd message:{1} "),
	INCORRECT_ZIP("ERR-1005E", "Incorrect Zip file {0} or Zip file is empty!"),	
	PROCESSING_UPLOAD_FOR_BRAND("ERR-1006","Brand {0} - Other file is being currently uploaded or brand is being processed by Tour Departure import. Please wait till end of processing and try again."),
	BRAND_CODE_NOT_EXIST_IN_ZIP("ERR-1007","System can't find brand code"),
	DIFFERENT_BRAND("ERR-1008","Process has registred BRAND code:{0} (brand code from first correct file) and found file with diffrent brand Code:{1} for tour code:{2}"),
	INCORRECT_FILE_NAME("ERR-1009","Incorrect file name:{0} ,reason : {1}"),
	DUPLICATE_FILE_NAME("ERR-1010","File upload problem file:{0} – a file already exists with name:{0}"),
	INCORRECT_ZIP_FILE("ERR-1011","Incorrect zip file name:{0}"),
	PERMISSION_DENIED_BRAND("ERR-1021","Permission denied for brand:{0} [file name:{1}]"),
	PERMISSION_DENIED_INVALID_BRANDIN_ZIP_FILE("ERR-1013","Permission denied for brand:{0} [zip file name:{1}]"),
	PERMISSION_DENIED_SELLING_COPANIES("ERR-1014","Permission denied for Selling companies:{0} [file name:{1}]"),
	UPLOAD_STATUS_ERROR("ERR-1015","Found problems while uploading tour info xml with nested files"),
	INCORRECT_FILE_NAME_LENGHT("ERR-1016","File upload problem – file: {0} – File name {0}  is not correct length - 27 characters expected"),
	SELLING_COMPANIES_DONT_EXIST_IN_BRAND("ERR-1017","Following SellingCompanies ({0}) were not found in selected Brand:{1} "),
	ZIP_ENTRY_IS_DIRECTORY("ERR-1018","Zip has a direcotry"),
	CH1_UPLOAD_ERROR("ERR-1019", "Error during automatic upload to CH1.0 machine"),

	//warninng
	CH1_UPLOAD_TOURN_OFF("WRN-2002","Ch1 upload is disabled"), 
	INDEXING_UPLOAD_TOURN_OFF("WRN-2003","Elastic search indexing is disabled"), 
	ITINERARY_SEGMENT_VALIDATION_WARNING("WRN-2004", "Files with invalid ItinerarySegment(s) StartDay/Duration values:\n{0}"),
	CANCEL_PROCESS("WRN-2005","Process was cancelled:{0}"),
	INACTIVE_PROCESS("WRN-2006","Process was inactived:{0}"),
	
	//info
	PRE_UPLOAD_START("INF-4001","Pre processing started"),
	PRE_UPLOAD_END("INF-4002","Pre processing ended - time {0}"),
	UPLOAD_START("INF-4003","Upload started"),
	UPLOAD_END("INF-4004","Upload ended - time {0}"),
	UPLOAD_STATUS("INF-4005","Upload started {0} , end {1}  time elapsed {2}"),
	OPERATION_ON_FILE("INF-4006","Operation on file: {0}, size:{1}, file date:{2}"),
	
//	STATUS_ON_FILE("INF-4004","Log operation for file: {0}"),
	LOCK_UPLOAD_FOR_BRAND("INF-4006","Locked upload for brand:{0}"),
	UPLOAD_STATUS_INFO("INF-4007","Upload file generated additional messages"),
	START_PERSISTS("INF-4008","Upload tour info started persisting files [count persists/update:{0}]"),
	END_PERSISTS("INF-4009","Upload files finished persisting files, execution time:{0}"),
	CREATE_PRODUCTS_START("INF-4010","Started validate xml files included in zip"),
	CREATE_PRODUCTS_END("INF-4011","Finished validate xml files included in zip, execution time:{0}"),
	VALIDATION_FILE("INF-4012","Validating file:{0}"),
	MAPPING_FILE("INF-4013","Mapping file:{0}"),
	CREATE_OLD_TOUR_INFO("INF-4014","Creating tour info file version 1:{0}"),
	INSERT_TOUR_INFO("INF-4015","Selected to insert tour info, tour code:{0}"),
	UPDATE_TOUR_INFO("INF-4016","Selected to update tour info, tour code:{0}"),
	SUCCESS_INFO("INF-4017","File:{0} successfully processed. Number of files in Zip:{1}"),
	FAIL_INFO("INF-4018","Processing failure in file:{0}; Number of files in Zip:{1}"),
	CH1_UPLOAD_SUCCESS("INF-4019", "TourInfo upload to CH1 was successful"),
	INDEX_UPLOAD_SUCCESS("INF-4020", "TourInfo indexing was successful"),
	TI_CHECK_SUM_EXIST_IN_CR("INF-4021","MD5 for TourInfo_XML is the same, ignoring tour code:{0}"),
	CH1_UPLOAD_DISABLED("INF-4022", "Tour Info CH1 upload for brand: [{0}] - disabled"),
	ZIP_MAX_FILES_EXCEEDED("INF-4023","To many XML files in ZIP content, max value is {0} files"),
	ZIP_XML_SIZE_EXCEEDED("INF-4024","Maximum size of XML file in ZIP is exeeded, maximum size is {0} bytes"),
	MAX_ZIPS_EXCEEDED("INF-4025","Maximum ZIPs in REJECTED state for brand is equal {0}. Please upload ZIP tomorrow!");
	
	private String code;
	private String message;
	
	private TourInfoMessages(String code,String msg)
	{
		this.code=code;
		this.message=msg;
	}
		
	public static TourInfoMessages getByCode(String code)
	{
		TourInfoMessages tab[]=values();
		for (int i = 0; i < tab.length; i++) {
			if(tab[i].getCode().equals(code))
				return tab[i];
		}		
		throw new IllegalArgumentException("Enum 'TourInfoMessagesErrorCodes' don't exist for code:"+code);
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
	
	public static String getMessage(TourInfoMessages error,Object ... params)
	{
		return MessageFormat.format(error.getMessage(), params);
	}
	public static String getMessage(String code,Object ... params)
	{
		return getMessage(TourInfoMessages.getByCode(code), params);
	}
}
