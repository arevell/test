package com.ttc.ch2.common.enums;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;

public enum CronExpresion {
	
	TDE_HOUR02("TDExtend","0 {0} 0/2 * * ?"),
	
	TDI_HOUR06("TDImport","0 {0} 0/6 * * ?"),
	TDI_HOUR12("TDImport","0 {0} 0/12 * * ?"),
	TDI_HOUR24("TDImport","0 {0} 0 * * ?"),
	
	TI_MINUTE03("TIUpload","0 0/3 * * * ?"),
	AUDIT_PURGE("Audit_PURGE","0 23 2 * * ?");
	
	private String name;
	private String expresion;
	
	private CronExpresion(String name, String expresion) {
		this.name = name;
		this.expresion = expresion;
	}

	public String getName() {
		return name;
	}

	public String getExpresion() {
		return expresion;
	}
	
	public String getExpresion(int minute) {
		Preconditions.checkArgument((minute>=0 && minute<=59),"CronExpresion->findByExpresion arg minute is incorrect");			
		String expresion=MessageFormat.format(this.expresion, minute);		
		return expresion;
	}
	
	public static CronExpresion findByExpresion(String name,String expresion){
		
		Preconditions.checkArgument(StringUtils.isNotBlank(name),"CronExpresion->findByExpresion arg name is empty");
		Preconditions.checkArgument(StringUtils.isNotBlank(expresion),"CronExpresion->findByExpresion arg expresion is empty");
		
		for (int i = 0; i < values().length; i++) {
			CronExpresion ce=values()[i];
			
			if(name.equals(ce.getName()) && ce.getExpresion().contains(expresion)){
				return ce;
			}
		}
		
		throw new IllegalStateException(String.format("CronExpresion->findByExpresion incorrect params name: %s, expresion: %s",name,expresion));
	}
	
}
