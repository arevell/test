package com.ttc.ch2.bl.upload;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.ttc.ch2.api.ccapi.v3.CurrentBrandStatusType;
import com.ttc.ch2.api.ccapi.v3.SnapshotType;
import com.ttc.ch2.api.ccapi.v3.GetTourDataUploadStatusRequest;
import com.ttc.ch2.api.ccapi.v3.GetTourDataUploadStatusResponse;
import com.ttc.ch2.api.ccapi.v3.UploadFileStatusType;
import com.ttc.ch2.bl.lock.LockBrandService;
import com.ttc.ch2.bl.upload.validator.BLMT;
import com.ttc.ch2.bl.upload.validator.TiZipStreamValidator;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.BrandLock;
import com.ttc.ch2.domain.upload.UploadStatus;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.messages.Severity;
import com.ttc.util.ws.MessagesUtil;


@Service
public class SnapshotUploadServiceImpl implements SnapshotUploadService {
	
	private static final Logger logger = LoggerFactory.getLogger(SnapshotUploadServiceImpl.class);
	
	@Inject
	private LockBrandService lockBrandService;
	
	@Inject
	private UploadStatusService uploadStatusService;
	
	@Inject
	private UploadService uploadService;
	
	
	@Override
	public GetTourDataUploadStatusResponse getSnapshotUploadTour(GetTourDataUploadStatusRequest request) {
		logger.trace("SnapshotUploadServiceImpl:getSnapshotUploadTour-start");
		GetTourDataUploadStatusResponse response=new GetTourDataUploadStatusResponse();
		try{
			
		extraSecureCheck(request);	
			
		setupStatusFileIfExist(request,response);
		setupBrandStatus(request,response);
		response.setSuccessful(true);
		}
		catch(Exception e){
		    logger.error(" ", e);
	        Message message = MessageBuilder.newMessage(BLMT. SYSTEM_ERROR).withSubject("Exception", e.getMessage()).build();
	        MessagesUtil.assignContext(response, message);
			response.setSuccessful(false);
		}
		
		logger.trace("SnapshotUploadServiceImpl:getSnapshotUploadTour-end");
		return response;
	}
	
	
	private void setupBrandStatus(GetTourDataUploadStatusRequest request, GetTourDataUploadStatusResponse response){
		
		CurrentBrandStatusType currentBrandStatus=new CurrentBrandStatusType();
				
		BrandLock brandLock=lockBrandService.findBrandLockByCode(request.getBrandCode());
		if(brandLock==null ){
			currentBrandStatus.setBrandStatus(Brand.Status.InActive.toString());	
		}else{
			currentBrandStatus.setBrandStatus(brandLock.getProccessName()==ProcessName.UPLOAD ? Brand.Status.UploadTourInfo.toString() : Brand.Status.ImportTourDeparture.toString());
		}
		
		List<UploadStatus> listOfUPloadStatus= uploadStatusService.getListOfProccess();
		for (UploadStatus uploadStatus : listOfUPloadStatus) {
			if(uploadStatus.getBrandCode().equals(request.getBrandCode())){				
				SnapshotType snapshot=new SnapshotType();
				snapshot.setBrandCode(uploadStatus.getBrandCode());				
				snapshot.setCountFile((uploadStatus.getCount() >0 ? new BigInteger(String.valueOf(uploadStatus.getCount())) : null) );
				snapshot.setNoProcessingFile((uploadStatus.getValue() >0 ? new BigInteger(String.valueOf(uploadStatus.getValue())) : null) );
				snapshot.setDateUpdate(DateHelper.dateToXMLGregorianCalendar(uploadStatus.getDateUpdate()));
				snapshot.setFileNameDesc(uploadStatus.getFileNameDesc());
				snapshot.setMessage(uploadStatus.getMessages());
				snapshot.setTypeMsg(uploadStatus.getTypeMsg()!=null ? uploadStatus.getTypeMsg().toString() :  null);
				currentBrandStatus.setSnapshot(snapshot);
				break;
			}
		}
		
		response.setCurrentBrandStatus(currentBrandStatus);
	}
	
	private void setupStatusFileIfExist(GetTourDataUploadStatusRequest request,GetTourDataUploadStatusResponse response){
		
		if(StringUtils.isBlank(request.getFileName()))
			return;
		
		UploadTourInfoFile uploadTourInfoFile=uploadService.getUploadTourInfoFileByName(request.getFileName());
		if(uploadTourInfoFile==null){
			Message message = MessageBuilder.newMessage(BLMT.STIU_INCORRECT_FILENAME).withSubject("name",request.getFileName()).build();
			com.ttc.util.ws.MessagesUtil.assignContext(response, message);
			return; 
		}
		UploadFileStatusType statusFile=new UploadFileStatusType(); 
		statusFile.setFileStatus(uploadTourInfoFile.getStatus().toString());
		statusFile.setFileName(request.getFileName());
		response.setUploadFileStatus(statusFile);
	}
	
	private void extraSecureCheck(GetTourDataUploadStatusRequest request){		
		UserCCAPI user=getLogedUser();			
		Brand brand=AuthorityHelper.getBrandFromUserCcapiByBrandCode(user, request.getBrandCode(), FunctionType.UPLOAD_TOUR_INFO);
		if(brand==null){
			throw new PermissionDeniedException("Permission denied for brand:"+request.getBrandCode());
		}
		if(StringUtils.isNotEmpty(request.getFileName())){
			
			if(request.getFileName().matches(TiZipStreamValidator.UPLOAD_FILENAME_PATTERN)==false){
					throw new SnapshotUploadServiceException(String.format("Incorrect file name:%s not according to an agreed naming convention ‘[BrandCode]-[ISO DateTime Format].zip’",request.getFileName()));
			}
			
			String brandFormFile=Splitter.on("-").split(request.getFileName()).iterator().next();	;
			if(brand.getCode().equals(brandFormFile)==false){
				throw new SnapshotUploadServiceException("Brand of request is different from the file name:"+brandFormFile);
			}
		}	
	}
	
	private UserCCAPI getLogedUser() {
		UserCCAPI user=SecurityHelper.getUserCCAPIPrincipal().getUserDb();		
		return user;				
	}
}
