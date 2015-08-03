package com.ttc.ch2.bl.diag;

public interface Diagnostic {

	boolean isElasticSearchOK();
	
	boolean isTropicsConnectivityOK();
	
	boolean isCH1UploadConnectionOK();
	
	boolean isCH1DownloadConnectionOK();
	
	boolean isTempDirOK();
	
	boolean isDBCodingSetOK();
	
	boolean isClusterStateOK();
	
	long getMatchingTIandTD();
	
	void refreshStatus();
	
}
