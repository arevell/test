package com.ttc.ch2.domain.comment;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ttc.ch2.domain.jobs.QuartzJobHistory;

@Entity
@DiscriminatorValue("QHComment")
public class QHComment extends Comment {
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="QJHISTORY_ID")
	private QuartzJobHistory quartzJobHistory;

	public QuartzJobHistory getQuartzJobHistory() {
		return quartzJobHistory;
	}

	public void setQuartzJobHistory(QuartzJobHistory quartzJobHistory) {
		this.quartzJobHistory = quartzJobHistory;
	} 
}
