package com.ttc.ch2.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name="SELLING_COMPANY")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class SellingCompany extends EntityBase {

	private static final long serialVersionUID = -4576000141319805477L;

	@Column(name="NAME", length=60, nullable=false)
	private String name;

	@Column(name="CODE",unique=true, length=10, nullable=false)
	private String code;
			
	@ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="BRAND_ID")
	@Cache(usage=CacheConcurrencyStrategy.READ_ONLY,region="NoModifiedDataRegion")
	private Brand brand;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
