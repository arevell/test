package com.ttc.ch2.domain.auth;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ttc.ch2.domain.Function;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.common.EntityBase;
import com.ttc.ch2.domain.user.UserCCAPI;

@Entity
@Table(name="CCAPI_AUTHORITY")
public class CCAPIAuthority extends EntityBase{
	
	private static final long serialVersionUID = -5738751850327592565L;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="FUNCTION_ID")
	private Function function;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="COMPANY_ID")
	private SellingCompany sellingCompany;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="USER_ID")
	private UserCCAPI userCcapi;

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public SellingCompany getSellingCompany() {
		return sellingCompany;
	}

	public void setSellingCompany(SellingCompany sellingCompany) {
		this.sellingCompany = sellingCompany;
	}

	public UserCCAPI getUserCcapi() {
		return userCcapi;
	}

	public void setUserCcapi(UserCCAPI userCcapi) {
		this.userCcapi = userCcapi;
	}
	
	@Override
	public String toString() {	
		return super.toString() +"[function:"+function.getName()+"][comapny:"+sellingCompany.getCode()+"]";
	}

}
