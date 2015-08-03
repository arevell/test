package com.ttc.ch2.bl.upload;

import java.util.List;

import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.domain.upload.UploadStatus;

public interface UploadStatusService {

	void initalizeNewProccess(String brandCode);

	void proccesingDescription(String brandCode, String msg,boolean updateValue);
	
	void proccesingDescription(String brandCode, String msg,Integer value);
	
	public void setupMessage(String brandCode,String message,TypeMsg typeMsg);

	void clearProccess(String brandCode);
	
	List<UploadStatus> getListOfProccess();

	void updateCountProccess(int count, String brandCode);
}