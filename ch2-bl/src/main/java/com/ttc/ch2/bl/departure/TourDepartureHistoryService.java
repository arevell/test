package com.ttc.ch2.bl.departure;

import java.util.Set;

import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.departure.TourDepartureHistory;

public interface TourDepartureHistoryService {

	public void saveInitialTourDepartureHistory(TourDepartureHistory tdh);
	
	public void saveTourDepartureComments(TDComment comments);
	
	public void saveTourDepartureHistory(TourDepartureHistory tdh,Set<Long> idsCr);
	
	
		
}
