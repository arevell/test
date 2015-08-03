package com.ttc.ch2.bl.departure;

import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.ttc.ch2.dao.comment.TDCommentDAO;
import com.ttc.ch2.dao.departure.TourDepartureHistoryDAO;
import com.ttc.ch2.dao.jobs.QuartzJobHistoryDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.departure.TourDepartureHistory;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.tour.ContentRepository;

@Service
public class TourDepartureHistoryServiceImpl implements TourDepartureHistoryService {

	private static final Logger logger = LoggerFactory.getLogger(TourDepartureHistoryServiceImpl.class);
	
	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	@Inject
	private TourDepartureHistoryDAO tourDepartureHistoryDAO;
	
	@Inject
	private QuartzJobHistoryDAO quartzJobHistoryDAO;
	
	@Inject
	private TDCommentDAO tdCommentDAO;
	
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void saveTourDepartureHistory(TourDepartureHistory tdh,Set<Long> idsCr)
	{			
		logger.trace("TourDepartureHistoryServiceImpl:saveTourDepartureHistory-start");
		Preconditions.checkArgument(tdh.getId()!=null, "TourDepartureHistory was not  persist befor invoke this method:saveTourDepartureHistory");
		
		TourDepartureHistory localTdh=tourDepartureHistoryDAO.find(tdh.getId());		
		Preconditions.checkState(localTdh!=null, "TourDepartureHistory should exist in DB method:saveTourDepartureHistory");
		
		localTdh.setStatus(tdh.getStatus());
		localTdh.setOperationEnd(tdh.getOperationEnd());
		localTdh.setUpdatedCount(tdh.getUpdatedCount());
		localTdh.setImportedCount(tdh.getImportedCount());
		localTdh.setModifiedBy(tdh.getModifiedBy());
				
		for (Long id : idsCr) {													
				ContentRepository cr=contentRepositoryDAO.find(id);
				Preconditions.checkState(cr!=null, "ContentRepository should exist in DB for id"+id);					
				localTdh.getContentRepositories().add(cr);
				cr.getTourDepartureHistory().add(localTdh);
		}

		for (TDComment comment : tdh.getComments()) {
			if(comment.getId()==null){
				comment.setTourDepartureHistory(localTdh);
				tdCommentDAO.save(comment);
				tdCommentDAO.flush();
			}
		}
		
		tourDepartureHistoryDAO.save(localTdh);
		tourDepartureHistoryDAO.flush();		
		logger.trace("TourDepartureHistoryServiceImpl:saveTourDepartureHistory-end");
	}
	
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void saveInitialTourDepartureHistory(TourDepartureHistory tdh) {
		logger.trace("TourDepartureHistoryServiceImpl:saveInitialTourDepartureHistory-start");	
			Preconditions.checkState(tdh.getContentRepositories().size()==0, "Initial state of TourDepartureHistory should have empty ContentRepositories list");
			Preconditions.checkState(tdh.getComments().size()==0, "Initial state of TourDepartureHistory should have empty Comments list");
			
			if (tdh.getQuartzJobHistory() != null && tdh.getQuartzJobHistory().getId() != null) {
				QuartzJobHistory qhistory=quartzJobHistoryDAO.find(tdh.getQuartzJobHistory().getId());
				tdh.setQuartzJobHistory(qhistory);
				tdh.setBrand(qhistory.getBrand());
				qhistory.setTourDepartureHistory(tdh);
			}				
			
			tourDepartureHistoryDAO.save(tdh);
			tourDepartureHistoryDAO.flush();
			if(tdh.getQuartzJobHistory()!=null){
				QuartzJobHistory qhistory=tdh.getQuartzJobHistory();
				quartzJobHistoryDAO.save(qhistory);
				quartzJobHistoryDAO.flush();
			}		
		logger.trace("TourDepartureHistoryServiceImpl:saveInitialTourDepartureHistory-end");
	}


	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void saveTourDepartureComments(TDComment comment) {
		logger.trace("TourDepartureHistoryServiceImpl:saveTourDepartureComments-start");
		tdCommentDAO.save(comment);
		tdCommentDAO.flush();
		logger.trace("TourDepartureHistoryServiceImpl:saveTourDepartureComments-end");
	}
}
