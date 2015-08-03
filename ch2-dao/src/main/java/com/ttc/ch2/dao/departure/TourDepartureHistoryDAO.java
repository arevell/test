package com.ttc.ch2.dao.departure;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.departure.TourDepartureHistory;

public interface TourDepartureHistoryDAO extends GenericDAO<TourDepartureHistory, Long> {

	public List<TourDepartureHistory> getTourDepartureHistoryByExemple(TourDepartureHistory tourDepartureHistory);
}
