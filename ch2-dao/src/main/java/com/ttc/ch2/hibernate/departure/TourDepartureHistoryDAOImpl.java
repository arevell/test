package com.ttc.ch2.hibernate.departure;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.departure.TourDepartureHistoryDAO;
import com.ttc.ch2.domain.departure.TourDepartureHistory;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class TourDepartureHistoryDAOImpl extends BaseDao<TourDepartureHistory, Long> implements  TourDepartureHistoryDAO {
	
	public List<TourDepartureHistory> getTourDepartureHistoryByExemple(TourDepartureHistory tourDepartureHistory){
		Search search = new Search();
		if(tourDepartureHistory!=null){
			if(tourDepartureHistory.getStatus()!=null)
				search.addFilterEqual("status", tourDepartureHistory.getStatus());
			if(tourDepartureHistory.getBrand()!=null)
				search.addFilterEqual("brand", tourDepartureHistory.getBrand());
		}
		return search(search);
	}
	
}
