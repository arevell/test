package com.ttc.ch2.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ttc.ch2.domain.Brand;

@Entity
@DiscriminatorValue("GUI")
public class UserGui  extends User {

	private static final long serialVersionUID = 5024293929390917415L;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_BRAND", joinColumns = { 
			@JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "BRAND_ID",nullable = false, updatable = false) })
	private Set <Brand> brands;
	
	@Column(name="PASSWORD", length=32)
	private String password;
	
	@Column(name="CNT_LOG_ERR")
	private Integer countInvalidLog;
	
	@Column(name="FIRST_LOG")
	private Boolean firstLog;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Brand> getBrands() {
		if(brands == null)
			brands = new HashSet<Brand>();
		return brands;
	}

	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}

	public Integer getCountInvalidLog() {
		return countInvalidLog;
	}

	public void setCountInvalidLog(Integer countInvalidLog) {
		this.countInvalidLog = countInvalidLog;
	}

	public Boolean getFirstLog() {
		return firstLog;
	}

	public void setFirstLog(Boolean firstLog) {
		this.firstLog = firstLog;
	}

	
	public boolean isLdapAccount(){
		return StringUtils.isEmpty(password);
	}
	
	
	
}
