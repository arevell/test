package com.ttc.ch2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ttc.ch2.domain.common.EntityBase;


@Entity
@Table(name="FUNCTION")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Function extends EntityBase {
	
	private static final long serialVersionUID = 239558918835119044L;

	@Column(name="NAME", length=60, nullable=false)
	private String name;  // type-version-name
	
	@Column(name="info", length=250, nullable=true)
	private String info;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	
}
