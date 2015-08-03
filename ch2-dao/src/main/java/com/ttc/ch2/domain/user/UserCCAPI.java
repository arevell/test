package com.ttc.ch2.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.ttc.ch2.domain.auth.CCAPIAuthority;

@NamedQueries({
	@NamedQuery( name = "UserCCAPI.findByToken", query = "select u from UserCCAPI u where u.token=:token")
//	@NamedQuery( name = "UserCCAPI.findByToken", query = "select u from UserCCAPI u where u.token=:token",cacheable=true,cacheRegion="UserRegion")
})
@Entity
@DiscriminatorValue("CCAPI")
public class UserCCAPI extends User {
	
	private static final long serialVersionUID = 5024293929390917415L;


	@Column(name="TOKEN", length=40,unique=true)
	private String token;
	
	
	@Column(name="ADDRESS", length=200)
	private String address;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="userCcapi")
	private Set<CCAPIAuthority> ccapiAuthorities;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<CCAPIAuthority> getCcapiAuthorities() {
		if(ccapiAuthorities==null)
				ccapiAuthorities=new HashSet<CCAPIAuthority>();
		return ccapiAuthorities;
	}
	public void setCcapiAuthorities(Set<CCAPIAuthority> ccapiAuthtorities) {
		this.ccapiAuthorities = ccapiAuthtorities;
	}	
}
