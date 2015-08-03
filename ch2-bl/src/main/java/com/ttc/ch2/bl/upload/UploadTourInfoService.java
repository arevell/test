package com.ttc.ch2.bl.upload;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ttc.ch2.api.ccapi.v3.UploadTourInfoRequest;
import com.ttc.ch2.api.ccapi.v3.UploadTourInfoResponse;
import com.ttc.ch2.bl.upload.common.OperationStatus;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.user.UserGui;

public interface UploadTourInfoService {
		
	public OperationStatus addManualUploadTourInfoFile(UploadTourInfoFile uploadTourInfoFile, InputStream inputStream,UserGui user) throws UploadServiceException;	
	public UploadTourInfoResponse addApiUploadTourInfoFile(UploadTourInfoRequest request) throws UploadServiceException;
	
	public boolean addTourInfoV1OnSynchronizationTDLevel(String brandCode,String tourCode,String tourInfoV1Xml,Set<Long> toursSetAdded,List<String> toursListNotAdded,Brand brand,Map<String, SellingCompany> sellingCompaniesMap, int index, int number) throws UploadServiceException;

}
