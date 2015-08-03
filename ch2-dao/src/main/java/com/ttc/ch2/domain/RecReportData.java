package com.ttc.ch2.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ttc.ch2.domain.common.EntityBase;



@Entity
@Table(name="RECONCILIATION_REPORT_DATA")
public class RecReportData extends EntityBase{

	private static final long serialVersionUID = -124774390441289351L;
	
	public static enum Type{FileCollectVO,IndexSearchingVO};
	
	@Column(name="BRAND_CODE")
	private String brandCode;
	
	@Column(name="INSERT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date insertDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	private Type type;
		
	@Lob
	@Column(name="SER_DATA")
	private String content;

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
