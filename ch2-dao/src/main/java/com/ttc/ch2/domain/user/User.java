package com.ttc.ch2.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ttc.ch2.domain.auth.Authority;
import com.ttc.ch2.domain.auth.Group;
import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name="USERS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends EntityBase {
	
	private static final long serialVersionUID = -1516998998557232567L;

	@Column(name="USERNAME", length=40, nullable=false, unique=true)
	private String username;
	
	@Column(name="ENABLED", nullable=false)
	private Boolean enabled;
	
	@Column(name="EMAIL",length=200)
	private String email;
	
	@Column(name="DELFLAG", nullable=false)
	private Boolean delFlag; 
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="user")
	private Set <Authority> authorities;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "GROUP_MEMBERS", joinColumns = { 
			@JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "GROUP_ID",nullable = false, updatable = false) })
	private Set<Group> groups;
	
	
	public User() {
		this.delFlag = false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Authority> getAuthorities() {
		if(authorities == null)
			authorities = new HashSet<Authority>();
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public Set<Group> getGroups() {
		if(groups==null)
			groups = new HashSet<Group>();
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

}
