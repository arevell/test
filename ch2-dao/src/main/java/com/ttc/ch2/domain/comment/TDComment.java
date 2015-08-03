package com.ttc.ch2.domain.comment;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ttc.ch2.domain.departure.TourDepartureHistory;

@Entity
@DiscriminatorValue("TDComment")
public class TDComment extends Comment {
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="TDHISTORY_ID")
	private TourDepartureHistory tourDepartureHistory;

	public TourDepartureHistory getTourDepartureHistory() {
		return tourDepartureHistory;
	}

	public void setTourDepartureHistory(TourDepartureHistory tourDepartureHistory) {
		this.tourDepartureHistory = tourDepartureHistory;
	}
	
	
}
