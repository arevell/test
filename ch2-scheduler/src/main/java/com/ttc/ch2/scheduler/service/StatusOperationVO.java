package com.ttc.ch2.scheduler.service;

import java.util.Date;

public class StatusOperationVO {

	
	private String msg;
	private String progress;
	private String brandCode;
	private Date dateUpdate;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
}
