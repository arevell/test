package com.ttc.ch2.bl.download.ch1download;

import java.util.Map;

public interface Ch1DownloadService {

	public Map<String, String> downloadTIFromCH1(String brandCode) throws Ch1DownloadServiceException;
	
	public void downloadTIFromCH1WithSaveToDB(String brandCode) throws Ch1DownloadServiceException;
	
	public boolean testCH1Download(String brandCode);
}
