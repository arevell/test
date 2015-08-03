package com.ttc.ch2.domain.comment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name="COMMENTS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public class Comment extends EntityBase {
	
	@Column(name="code", length=15, nullable=true)
	private String messageCode;
	
	@Column(name="MESSAGE", length=4000, nullable=false)
	private String message;
	
	@Lob
	@Column(name="STACK_TRACE")
	private String stackTrace;
	
	@Lob
	@Column(name="CONTENT")
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFIED_TIME", nullable=false)
	private Date modifiedTime;
	
	@Column(name="MODIFIED_BY",length=40, nullable=false)
	private String modifiedBy;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	
}
