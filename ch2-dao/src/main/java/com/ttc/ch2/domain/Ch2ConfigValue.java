package com.ttc.ch2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ttc.ch2.domain.common.EntityBase;


@Entity
@Table(name="CONFIG_VALUES")
public class Ch2ConfigValue extends EntityBase{
	
	public static enum PropName{SystemDirection}
	

	private static final long serialVersionUID = -124774390441289351L;
		
	@Column(name="PROP_NAME", length=50, nullable=false)
	private String propertyName;
	
	@Column(name="PROP_VALUE", length=200, nullable=false)
	private String propertyValue;
	

	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}	
}
