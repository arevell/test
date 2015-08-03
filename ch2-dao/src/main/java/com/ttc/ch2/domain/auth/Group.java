package com.ttc.ch2.domain.auth;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name="GROUPS")
public class Group extends EntityBase {
	
	private static final long serialVersionUID = -6694893169077801024L;

	@Column(name="GROUP_NAME", length=40, unique=true, nullable=false)
	private String groupName;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="group")
	private Set<GroupAuthority> groupAuthorities;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Set<GroupAuthority> getGroupAuthorities() {
		if(groupAuthorities == null)
			groupAuthorities = new HashSet<GroupAuthority>();
		return groupAuthorities;
	}
	public void setGroupAuthorities(Set<GroupAuthority> groupAuthorities) {
		this.groupAuthorities = groupAuthorities;
	}
}
