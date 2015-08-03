package com.ttc.ch2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.common.EntityBase;


@Entity
@Table(name="BRAND_LOCK")
public class BrandLock extends EntityBase{
	
	@Column(name="BRAND_CODE",unique=true,length=2,nullable=false)
	private String brandCode;
	
	@Enumerated(EnumType.STRING)
	@Column(name="PROCCESS_NAME",length=50, nullable=false)
	private ProcessName proccessName;

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public ProcessName getProccessName() {
		return proccessName;
	}

	public void setProccessName(ProcessName proccessName) {
		this.proccessName = proccessName;
	}	
}
