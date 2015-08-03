package com.ttc.ch2.domain.departure;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.common.EntityBase;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.tour.ContentRepository;

@Entity
@Table(name="TOURDEPARTURE_HISTORY")
public class TourDepartureHistory extends EntityBase {
	public static enum TourDepartureStatus {ERROR_OPERATION_END,WARNING_OPERATION_END,SUCCESS_OPERATION_END,OPERATION_IN_PROGESS};
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="tourDepartureHistory")
	Set<ContentRepository> contentRepositories;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TOURDEPARTURE_STATUS", nullable=false)
	private TourDepartureStatus status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPERATION_START")
	private Date operationStart;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPERATION_END")
	private Date operationEnd;
	
	@Column(name="IMPORTED_COUNT")
	private Integer importedCount;
	
	@Column(name="UPDATED_COUNT")
	private Integer updatedCount;
	
	@Column(name="MODIFIED_BY", length=40, nullable=false)
	private String modifiedBy;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="BRAND_ID")
	private Brand brand; 
	
	@OneToOne
	@JoinColumn(name="QJHISTORY_ID")
	private QuartzJobHistory quartzJobHistory; 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="tourDepartureHistory")
	@OrderBy("id")
	private List<TDComment> comments;
	
	public Set<ContentRepository> getContentRepositories() {
		if(contentRepositories == null)
			contentRepositories = new HashSet<ContentRepository>();
		return contentRepositories;
	}
	public void setContentRepositories(Set<ContentRepository> contentRepositories) {
		this.contentRepositories = contentRepositories;
	}
	
	public List<TDComment> getComments() {
		if(comments == null)
			comments = new ArrayList<TDComment>();
		return comments;
	}
	public void setComments(List<TDComment> comments) {
		this.comments = comments;
	}
	public TourDepartureStatus getStatus() {
		return status;
	}
	public void setStatus(TourDepartureStatus status) {
		this.status = status;
	}
	public Integer getImportedCount() {
		return importedCount;
	}
	public void setImportedCount(Integer importedCount) {
		this.importedCount = importedCount;
	}
	public Integer getUpdatedCount() {
		return updatedCount;
	}
	public void setUpdatedCount(Integer updatedCount) {
		this.updatedCount = updatedCount;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getOperationStart() {
		return operationStart;
	}
	public void setOperationStart(Date operationStart) {
		this.operationStart = operationStart;
	}
	public Date getOperationEnd() {
		return operationEnd;
	}
	public void setOperationEnd(Date operationEnd) {
		this.operationEnd = operationEnd;
	}
	public QuartzJobHistory getQuartzJobHistory() {
		return quartzJobHistory;
	}
	public void setQuartzJobHistory(QuartzJobHistory quartzJobHistory) {
		this.quartzJobHistory = quartzJobHistory;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
}
