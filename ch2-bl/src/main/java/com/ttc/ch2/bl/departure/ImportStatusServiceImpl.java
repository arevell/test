package com.ttc.ch2.bl.departure;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.dao.departure.ImportStatusDAO;
import com.ttc.ch2.domain.departure.ImportStatus;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
public class ImportStatusServiceImpl implements ImportStatusService, ImportStatusServicePartial {

	private static final Logger logger = LoggerFactory.getLogger(ImportStatusServiceImpl.class);
	
	
	@Inject
	private ImportStatusDAO importStatusDAO;
	

	@Override
	public void setupMessage(String message,String brandCode,TypeMsg typeMsg,ProcessLevel processLevel){
		logger.trace("ImportStatusServiceImpl:setupMessage-start");
		try{			
			ImportStatus findedStatus=importStatusDAO.getImportStatusByBrandCode(brandCode);
			if(findedStatus==null){
				ImportStatus importStatus=new ImportStatus();
				importStatus.setMessages(message);
				importStatus.setBrandCode(brandCode);
				importStatus.setTypeMsg(typeMsg);
				importStatus.setDateUpdate(new Date());
				importStatus.setProcessLevel(processLevel);
				importStatusDAO.save(importStatus);
			}
			else{
				findedStatus.setMessages(message);
				findedStatus.setBrandCode(brandCode);
				findedStatus.setTypeMsg(typeMsg);			
				findedStatus.setDateUpdate(new Date());
				findedStatus.setProcessLevel(processLevel);
				importStatusDAO.save(findedStatus);
			}
			importStatusDAO.flush();
		}
		catch(Exception e){
			logger.error("",e);
		}
		logger.trace("ImportStatusServiceImpl:setupMessage-end");		
	}

	@Override
	public void setupMessage(String message, String brandCode, String typeMsg, String processLevel) {
		setupMessage(message, brandCode, TypeMsg.valueOf(typeMsg), ProcessLevel.valueOf(processLevel));
	}

	@Override
	public void clearStatus(){
		
		logger.trace("ImportStatusServiceImpl:clearStatus-start");
		try{
		for (ImportStatus row: importStatusDAO.findAll()) {
			importStatusDAO.remove(row);			
		}
		importStatusDAO.flush();
		}
		catch(Exception e){
			logger.error("",e);
		}
		logger.trace("ImportStatusServiceImpl:clearStatus-end");
	}
		
	@Override
	public void clearStatus(String brandCode){
		logger.trace("ImportStatusServiceImpl:clearStatus-start");
		
		try{
			ImportStatus findedStatus=importStatusDAO.getImportStatusByBrandCode(brandCode);
			if(findedStatus!=null){
			importStatusDAO.remove(findedStatus);
			importStatusDAO.flush();
			}	
		}
		catch (Exception e) {
			logger.error("", e);
		}		
		logger.trace("ImportStatusServiceImpl:clearStatus-end");
	}
}
