package com.ttc.ch2.ui.moduls.tour.common;

import java.util.List;

import com.ttc.ch2.domain.upload.UploadStatus;

public class ProgressUploadStatus {
		
	private List<UploadStatus> uploadStatusServiceVOs;
	private boolean hasUploadProccesses;
	private String height="65px";

	public List<UploadStatus> getUploadStatusServiceVOs() {
		return uploadStatusServiceVOs;
	}


	public void setUploadStatusServiceVOs(
			List<UploadStatus> uploadStatusServiceVOs) {
		this.uploadStatusServiceVOs = uploadStatusServiceVOs;
		hasUploadProccesses=uploadStatusServiceVOs.size()>0;
		if(hasUploadProccesses){
			height=""+(uploadStatusServiceVOs.size()*65)+"px";
		}
		else
			height="65px";
	}


	public boolean isHasUploadProccesses() {
		return hasUploadProccesses;
	}


	public void setHasUploadProccesses(boolean hasUploadProccesses) {
		this.hasUploadProccesses = hasUploadProccesses;
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}
}
