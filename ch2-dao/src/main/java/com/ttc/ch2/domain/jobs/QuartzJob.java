package com.ttc.ch2.domain.jobs;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ttc.ch2.domain.common.EntityBase;
import com.ttc.ch2.domain.user.User;

@Entity
@Table(name="QUARTZ_JOB")
public class QuartzJob extends EntityBase {
	public enum JobStatus {
		Active,
		Inactive,
		Cancelled,
	};
	
	public static enum JobName{
		DepartureSynchronizeJob,UploadTourInfoJob,DepartureExtendedSynchronizeJob,AuditPurgeJob
	}
		
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,CascadeType.REFRESH })
	@JoinColumn(name = "USER_ID", nullable = true)
	private User user;
	
	@Column(name="JOB_NAME", length=60, nullable=false)
	private String jobName;
	
	@Column(name="UI_NAME", length=60)
	private String uiName;

	@Column(name="GROUP_NAME", length=60)
	private String groupName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="JOB_STATUS", nullable=false)
	private JobStatus jobStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="NEXT_FIRING_TIME")
	private Date nextFiringTime;
	
	@Column(name="CRON_EXPRESION")
	private String cronExpresion;
	
	@Column(name="BRAND_CODE")
	private String brandCode;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public JobStatus getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}
	public Date getNextFiringTime() {
		return nextFiringTime;
	}
	public void setNextFiringTime(Date nextFiringTime) {
		this.nextFiringTime = nextFiringTime;
	}
	public String getCronExpresion() {
		return cronExpresion;
	}
	public void setCronExpresion(String cronExpresion) {
		this.cronExpresion = cronExpresion;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUiName() {
		return uiName;
	}
	public void setUiName(String uiName) {
		this.uiName = uiName;
	}
}
