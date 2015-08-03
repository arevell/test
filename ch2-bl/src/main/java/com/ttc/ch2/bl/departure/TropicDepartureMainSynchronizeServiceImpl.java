package com.ttc.ch2.bl.departure;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Throwables;
import com.ttc.ch2.bl.departure.common.LogOperationHelper;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.bl.departure.habs.HabsDepartureSynchronizeService;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.DateHelper.CalculateTimePattern;
import com.ttc.ch2.domain.comment.QHComment;

/**
 * If you need support transacion on this level change Propagation level below and be sure that TropicDepartureSynchronizeService.departureSynchronize will be invoke with  
 * transaction Transactional.REQUIRED_NEW
 * */

@Service
@Transactional(readOnly=false,propagation=Propagation.NEVER)
public class TropicDepartureMainSynchronizeServiceImpl implements TropicDepartureMainSynchronizeService {

	private static final Logger logger = LoggerFactory.getLogger(TropicDepartureMainSynchronizeServiceImpl.class);

	@Inject
	@Named("TropicDepartureSynchronizeServiceImpl")
	private TropicDepartureSynchronizeService tropicDepartureSynchronizeService;
	
	@Inject
	@Named("HabsDepartureSynchronizeServiceImpl")
	private HabsDepartureSynchronizeService habsDepartureSynchronizeService;

	@Inject
	private TourDepartureHistoryService tourDepartureHistoryService;
	
//	@Inject
//	private ContentRepositoryService contentRepositoryService;

	
	@Override
	public OperationStatus departureSynchronizeOperation(OperationStatus opStatus) throws TropicSynchronizeServiceException {				
		
		try {
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.SYNCHRONIZE_START);			
			initialTourDepartureHistory(opStatus);		// new tx
			
//			tropicDepartureSynchronizeService.departureSynchronize(opStatus);
			habsDepartureSynchronizeService.departureSynchronize(opStatus);
		}
		catch (TropicSynchronizeServiceException e) {
			if(opStatus.isCancelOrInactiveProcess()==false)
				throw e;
		}
		finally {					
			opStatus.finish();			
			LogOperationHelper.logDefaultMessage(logger, opStatus, TropicSynchronizeMessages.SYNCHRONIZE_END, DateHelper.calculateTime(opStatus.getStartOperation(), opStatus.getEndOperation(),CalculateTimePattern.HMS));
			tourDepartureHistoryService.saveTourDepartureHistory(opStatus.getTourDepartureHistory(),opStatus.getCrSavedOrUpdateForBrand()); // new tx
			
//			Date startDelete=new Date();
//			logger.debug(TropicSynchronizeMessages.DELETE_CR_START.createMessage(opStatus.getCurrentBrand()));
//			int deleteCount = contentRepositoryService.deleteEmptyContentRepository(opStatus.getCurrentBrand());
//			logger.debug(TropicSynchronizeMessages.DELETE_CR_END.createMessage(opStatus.getCurrentBrand(),DateHelper.calculateTime(startDelete, new Date(), CalculateTimePattern.HMS)));
//			logger.debug("Deleted "+deleteCount+"rows from content repository for brand:"+opStatus.getCurrentBrand() );
		}
		return opStatus;
	}

	private void initialTourDepartureHistory(OperationStatus opStatus){
		try{
			tourDepartureHistoryService.saveInitialTourDepartureHistory(opStatus.getTourDepartureHistory()); // new tx
		}
		catch(Exception e){
			logger.error("",e);
			QHComment comment=new QHComment();
			comment.setMessage("Unexpected problem occured");
			comment.setMessageCode("ERR-1000");
			comment.setModifiedTime(new Date());
			comment.setQuartzJobHistory(opStatus.getQuartzJobHistory());
			comment.setModifiedBy(opStatus.getUser());
			comment.setStackTrace(Throwables.getStackTraceAsString(e));
			opStatus.getQuartzJobHistory().getComments().add(comment);
			throw e;
		}
	}
}
