package com.ttc.ch2.bl.upload;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.common.predicates.FindBrandByCodePredicate;
import com.ttc.ch2.dao.upload.UploadStatusDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.upload.UploadStatus;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
public class UploadStatusServiceImpl implements UploadStatusService, UploadStatusServicePartial {
	
	
	@Inject
	private UploadStatusDAO uploadStatusDAO;
	
	
	
	
	@Override
	public void initalizeNewProccess(String brandCode){		
		Preconditions.checkArgument(StringUtils.isNotEmpty(brandCode),"UploadStatusServiceImpl->initalizeNewProccess brandCode is empty");
		
		UploadStatus uploadStatus= uploadStatusDAO.getUploadStatusByBrandCode(brandCode);
		uploadStatusDAO.remove(uploadStatus);		
		UploadStatus newUploadStatus=new UploadStatus();
		newUploadStatus.setBrandCode(brandCode);
		newUploadStatus.setDateUpdate(new Date());
		uploadStatusDAO.save(newUploadStatus);
		
	}
	
	@Override
	public void updateCountProccess(int count,String brandCode){		
		Preconditions.checkArgument(StringUtils.isNotEmpty(brandCode),"UploadStatusServiceImpl->initalizeNewProccess brandCode is empty");
		
		UploadStatus uploadStatus= uploadStatusDAO.getUploadStatusByBrandCode(brandCode);
		uploadStatus.setCount(count);
		uploadStatus.setDateUpdate(new Date());
		uploadStatusDAO.save(uploadStatus);
		
	}
		

	@Override
	public void proccesingDescription(String brandCode,String msg,boolean updateValue){
		
		Preconditions.checkArgument(StringUtils.isNotEmpty(brandCode),"UploadStatusServiceImpl->proccesOnFile brandCode is empty");
		Preconditions.checkArgument(StringUtils.isNotEmpty(msg),"UploadStatusServiceImpl->proccesOnFile fileName is empty");
				
		UploadStatus uploadStatus= uploadStatusDAO.getUploadStatusByBrandCode(brandCode);
		Preconditions.checkState(uploadStatus!=null, "UploadStatusServiceImpl->proccesOnFile cant find UploadStatusServiceVO for brandCode:"+brandCode);	
		if(updateValue)
			uploadStatus.setValue(uploadStatus.getValue()+1);		
		uploadStatus.setFileNameDesc(msg);
		uploadStatus.setDateUpdate(new Date());
		uploadStatusDAO.save(uploadStatus);
	}
	
	public void proccesingDescription(String brandCode,String msg,Integer value){
		
		Preconditions.checkArgument(StringUtils.isNotEmpty(brandCode),"UploadStatusServiceImpl->proccesOnFile brandCode is empty");
		Preconditions.checkArgument(StringUtils.isNotEmpty(msg),"UploadStatusServiceImpl->proccesOnFile fileName is empty");
		
		UploadStatus uploadStatus= uploadStatusDAO.getUploadStatusByBrandCode(brandCode);
		Preconditions.checkState(uploadStatus!=null, "UploadStatusServiceImpl->proccesOnFile cant find UploadStatusServiceVO for brandCode:"+brandCode);	
		if(value!=null)
			uploadStatus.setValue(value);		
		uploadStatus.setFileNameDesc(msg);
		uploadStatus.setDateUpdate(new Date());
		uploadStatusDAO.save(uploadStatus);
	}
	
	@Override
	public void setupMessage(String brandCode,String message,TypeMsg typeMessage){		

		Preconditions.checkArgument(StringUtils.isNotEmpty(message),"UploadStatusServiceImpl->setupMessage messages is empty");		

		UploadStatus uploadStatus= uploadStatusDAO.getUploadStatusByBrandCode(brandCode);
		if(StringUtils.isNotBlank(brandCode) && uploadStatus!=null){			
			uploadStatus.setMessages(message);
			uploadStatus.setTypeMsg(typeMessage);
			uploadStatus.setDateUpdate(new Date());
			uploadStatusDAO.save(uploadStatus);
		}
	}
	
		
	@Override
	public void clearProccess(String brandCode){
		UploadStatus uploadStatus= uploadStatusDAO.getUploadStatusByBrandCode(brandCode);
		if(StringUtils.isNotBlank(brandCode) && uploadStatus!=null){
			uploadStatusDAO.remove(uploadStatus);
		}
	}
	
	@Override
	public List<UploadStatus> getListOfProccess(){
		
		List<UploadStatus> result=Lists.newArrayList();
		List<Brand> authBrands=Lists.newArrayList();
		
		if(SecurityHelper.isUserGui())
			authBrands.addAll(SecurityHelper.getUserGuiPrincipal().getUserDb().getBrands());
		else if(SecurityHelper.isUserCcapi()){
			authBrands.addAll(AuthorityHelper.getBrandsFromUserCcapi(SecurityHelper.getUserCCAPIPrincipal().getUserDb(), FunctionType.UPLOAD_TOUR_INFO));
		}
		
		List<UploadStatus> allUploadStatus=uploadStatusDAO.findAll();
		for (UploadStatus uploadStatus : allUploadStatus) {
			
			Optional<Brand> findedBrand=Iterables.tryFind(authBrands, new FindBrandByCodePredicate(uploadStatus.getBrandCode()));
			if(findedBrand.isPresent())
				result.add(uploadStatus);
		}	
		return result; 		
	}
}
