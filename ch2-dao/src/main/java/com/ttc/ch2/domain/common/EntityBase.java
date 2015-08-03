package com.ttc.ch2.domain.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@MappedSuperclass
public class EntityBase implements Serializable {

	private static final long serialVersionUID = -7445991081389480488L;

	@Id
	@Column(name="ID")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ch2_seq_gen")
	@SequenceGenerator(name="ch2_seq_gen", sequenceName="CH2_GLOBAL_SEQ")
    private Long id;
	
	@Version
	@Column(name="version")
	private Long version;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	

	@Override
	public String toString() {			
		return getClass().getName()+" ID:"+id;
	}
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityBase other = (EntityBase) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	*/
	
	
}
