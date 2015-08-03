package com.ttc.ch2.domain.auth;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.ttc.ch2.domain.common.EntityBase;
import com.ttc.ch2.domain.user.User;

@Entity
@Table(name="AUTHORITY")
//@Table(name="AUTHORITY",indexes = { @Index(name = "IDX_AUTHORITY",  columnList = "authority") })
@org.hibernate.annotations.Table(appliesTo = "AUTHORITY", indexes = { @Index(name = "IDX_AUTHORITY", columnNames = { "authority" }) })
public class Authority extends EntityBase {
	
	private static final long serialVersionUID = -3332393763355620506L;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private User user;
		
	@Column(name="AUTHORITY", length=40, nullable=false)
	private String authority;
	
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
