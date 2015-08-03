package com.ttc.ch2.bl.upload;

import java.util.List;
import java.util.Map;

import com.ttc.ch2.bl.upload.common.JobExecutor;

public interface UploadTourInfoBatchService {

	void invokeProcess(String brandCode,JobExecutor extraOperationFromScheduler);
	
	
	/**Method used in synchronize departure process
	 * */
	public List<String> uploadTourInfo(String brandCode, Map<String, String> tourInfoV1CodeXmlMap) throws UploadServiceException;

}