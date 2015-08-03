package com.ttc.ch2.ui.moduls.processmgr.common;

public enum Status {

	CH2_CH1_UPLOAD("A. CH2.0 v3 upload and CH1.0 v1 push (during Tour Info Import of v3)"),
	CH1_CH2_DOWNLOAD("B. CH1.0 v1 download to CH2.0, no v3 support (during Tour Departure Import)");

	private String desc;

	private Status(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
