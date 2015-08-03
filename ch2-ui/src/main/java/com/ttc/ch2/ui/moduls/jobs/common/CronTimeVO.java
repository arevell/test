package com.ttc.ch2.ui.moduls.jobs.common;

import java.util.List;

import com.google.common.collect.Lists;
import com.ttc.ch2.common.enums.CronExpresion;

public class CronTimeVO{
	private String id;		
	private String desc;
	private CronExpresion expresion;
	
	public CronTimeVO(String id, String desc, CronExpresion expresion) {
		super();
		this.id = id;
		this.desc = desc;
		this.expresion = expresion;
	}
	
	public static List<CronTimeVO> getList(){
		
		List<CronTimeVO> result=Lists.newArrayList();
		result.add(new CronTimeVO("1", "6h", CronExpresion.TDI_HOUR06));
		result.add(new CronTimeVO("2", "12h", CronExpresion.TDI_HOUR12));
		result.add(new CronTimeVO("3", "24h", CronExpresion.TDI_HOUR24));
		return result;			
	}

	public String getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public CronExpresion getExpresion() {
		return expresion;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setExpresion(CronExpresion expresion) {
		this.expresion = expresion;
	}
}