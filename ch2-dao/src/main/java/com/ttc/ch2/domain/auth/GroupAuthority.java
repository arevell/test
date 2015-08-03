package com.ttc.ch2.domain.auth;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name="GROUP_AUTHORITIES")
public class GroupAuthority extends EntityBase {
	
	private static final long serialVersionUID = -1392634710039349913L;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="GROUP_ID")
	private Group group;
	
	@Column(name="AUTHORITY", nullable=false)
	private String authority;
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
}
