package com.ttc.ch2.scheduler.service;

import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.common.collect.Lists;
import org.springframework.stereotype.Service;

import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.dao.departure.ImportStatusDAO;
import com.ttc.ch2.dao.upload.UploadStatusDAO;
import com.ttc.ch2.domain.departure.ImportStatus;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;
import com.ttc.ch2.domain.upload.UploadStatus;



@Service
public class StatusOperationServiceImpl implements StatusOperationService {

	@Inject
	private ImportStatusDAO importStatusDAO;
	
	@Inject
	private UploadStatusDAO uploadStatusDAO;
	
	
	public List<StatusOperationVO> getStausOfProcess(){
	
		List<StatusOperationVO> result=Lists.newArrayList();
		
		for (ImportStatus iStatus : importStatusDAO.findAll()) {
			
			if(TypeMsg.HDN==iStatus.getTypeMsg())
				continue;
			
			StatusOperationVO v=new StatusOperationVO();
			v.setBrandCode(iStatus.getBrandCode());
			v.setDateUpdate(iStatus.getDateUpdate());
			v.setMsg(iStatus.getMessages());
			
			double level=iStatus.getProcessLevel().getLevel();
			double countLevel=ProcessLevel.values().length;
			int progress=new Double((100d/countLevel)*level).intValue();			
			v.setProgress(String.valueOf(progress)+" % ");
			result.add(v);	
		}
		
		for (UploadStatus uStatus : uploadStatusDAO.findAll()) {
			
			if(TypeMsg.HDN==uStatus.getTypeMsg())
				continue;
			
			StatusOperationVO v=new StatusOperationVO();
			v.setBrandCode(uStatus.getBrandCode());
			v.setDateUpdate(uStatus.getDateUpdate());
			v.setMsg(uStatus.getMessages());			
			v.setProgress(calculateProgress(uStatus));
			
			result.add(v);	
		}
		
		return result;
		
	}
	
	public static String calculateProgress(UploadStatus uStatus){		
		int progressValue= uStatus.getValue()*100/(UploadStatus.ProcessLevel.FINISH.getLevel());		
		return progressValue+" %";
	}
	
}
