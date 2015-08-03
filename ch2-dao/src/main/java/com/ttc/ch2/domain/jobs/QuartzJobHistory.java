package com.ttc.ch2.domain.jobs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.common.EntityBase;
import com.ttc.ch2.domain.departure.TourDepartureHistory;

@Entity
@Table(name="QUARTZ_JOB_HISTORY")
public class QuartzJobHistory extends EntityBase implements Serializable{

	private static final long serialVersionUID = 8068547500272881836L;
	
	public enum JobHistoryStatus {
		Processing,
		Success,
		Fail,
		Warning,
		Cancelled,
	};
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="QUARTZ_JOB_ID")
	private QuartzJob quartzJob;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DATE", nullable=false)
	private Date startDate;
	
	@Column(name="EXECUTION_TIME")
	private Long executionTime;
	
	@Column(name="EXECUTED_BY", length=40, nullable=false)
	private String executedBy;
	
	@Enumerated(EnumType.STRING)
	@Column(name="JOB_HISTORY_STATUS", nullable=false)
	private  JobHistoryStatus status;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "quartzJobHistory")
	@OrderBy("id")
//	@LazyCollection(LazyCollectionOption.FALSE)
	private List<QHComment> comments;
		
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST}, mappedBy="quartzJobHistory")
	private TourDepartureHistory tourDepartureHistory;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="BRAND_ID")
	private Brand brand; 
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Long getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Long executionTime) {
		this.executionTime = executionTime;
	}
	public String getExecutedBy() {
		return executedBy;
	}
	public void setExecutedBy(String executedBy) {
		this.executedBy = executedBy;
	}
	public JobHistoryStatus getStatus() {
		return status;
	}
	public void setStatus(JobHistoryStatus status) {
		this.status = status;
	}
	public QuartzJob getQuartzJob() {
		return quartzJob;
	}
	public void setQuartzJob(QuartzJob quartzJob) {
		this.quartzJob = quartzJob;
	}
	public List<QHComment> getComments() {
		if(comments == null)
			comments = new ArrayList<QHComment>();
		return comments;
	}
	public void setComments(List<QHComment> comments) {
		this.comments = comments;
	}
	public TourDepartureHistory getTourDepartureHistory() {
		return tourDepartureHistory;
	}
	public void setTourDepartureHistory(TourDepartureHistory tourDepartureHistory) {
		this.tourDepartureHistory = tourDepartureHistory;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
