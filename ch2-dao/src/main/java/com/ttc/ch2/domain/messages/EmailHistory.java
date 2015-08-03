package com.ttc.ch2.domain.messages;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name="EMAIL_HISTORY")
public class EmailHistory extends EntityBase {
	public enum EmailStatus {
		Success,
		ServerFail
	};
		
	@Enumerated(EnumType.STRING)
	@Column(name="PROCCESS_NAME",length=50, nullable=false)
	private ProcessName proccessName;
	

	@Column(name="EMAIL_FROM",length=200, nullable=false)
	private String from;
	
	@Column(name="EMAIL_TO",length=1000, nullable=false)
	private String to;
	
	@Column(name="SUBJECT",length=255, nullable=false)
	private String subject;
	
	@Lob
	@Column(name="MESSAGE", nullable=false)
	private String message;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", nullable=false)
	private EmailStatus status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MESSAGE_DELIVERY_TIME")
	private Date messageDeliveryTime;
		
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="BRAND_ID")	
	private Brand brand;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) { 
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public EmailStatus getStatus() {
		return status;
	}
	public void setStatus(EmailStatus status) {
		this.status = status;
	}
	public Date getMessageDeliveryTime() {
		return messageDeliveryTime;
	}
	public void setMessageDeliveryTime(Date messageDeliveryTime) {
		this.messageDeliveryTime = messageDeliveryTime;
	}
	public ProcessName getProccessName() {
		return proccessName;
	}
	public void setProccessName(ProcessName proccessName) {
		this.proccessName = proccessName;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
