package com.ttc.ch2.ui.moduls.processmgr.common;

import org.zkoss.util.resource.Labels;

import com.ttc.ch2.domain.Brand;

public class BrandStatus{
		
	private ComboValue cbxValue;
	private Brand brand;
	private String message;
	private boolean showMsg;
	
	public BrandStatus(Brand brand, ComboValue cbxValue) {
		super();
		this.brand = brand;
		this.cbxValue = cbxValue;
		this.message=Labels.getLabel("processmgr.warrning");
	}
	
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	public ComboValue getCbxValue() {
		return cbxValue;
	}


	public void setCbxValue(ComboValue cbxValue) {
		this.cbxValue = cbxValue;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public boolean isShowMsg() {
		return showMsg;
	}


	public void setShowMsg(boolean showMsg) {
		this.showMsg = showMsg;
	}


	
}
