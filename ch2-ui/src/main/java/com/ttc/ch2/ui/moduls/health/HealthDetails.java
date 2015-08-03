package com.ttc.ch2.ui.moduls.health;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;

import com.ttc.ch2.bl.diag.Diagnostic;
import com.ttc.ch2.ui.common.config.Ch2URIs;

@Component("HealthDetails")
@Scope("request")
public class HealthDetails implements Serializable{
	
	@Inject
	private Diagnostic diagnostic;
		
	
	private boolean isElasticSearchOK;
	private boolean isTropicsConnectivityOK;
	private boolean isCH1UploadConnectionOK;
	private boolean isCH1DownloadConnectionOK;
	private boolean isTempDirOK;
	private boolean isDBCodingSetOK;
	private boolean isClusterStateOK;
	private long matchingTIandTD;
	

	
	@Init
	public void init() {
		isElasticSearchOK = diagnostic.isElasticSearchOK();
		isTropicsConnectivityOK = diagnostic.isTropicsConnectivityOK();
		isCH1UploadConnectionOK = diagnostic.isCH1UploadConnectionOK();
		isCH1DownloadConnectionOK = diagnostic.isCH1DownloadConnectionOK();
		isTempDirOK = diagnostic.isTempDirOK();
		isDBCodingSetOK = diagnostic.isDBCodingSetOK();
		isClusterStateOK = diagnostic.isClusterStateOK();
		matchingTIandTD = diagnostic.getMatchingTIandTD();
	
	}

	public boolean isElasticSearchOK() {
		return isElasticSearchOK;
	}

	public boolean isTropicsConnectivityOK() {
		return isTropicsConnectivityOK;
	}

	public boolean isCH1UploadConnectionOK() {
		return isCH1UploadConnectionOK;
	}

	public boolean isCH1DownloadConnectionOK() {
		return isCH1DownloadConnectionOK;
	}

	public boolean isTempDirOK() {
		return isTempDirOK;
	}

	public boolean isDBCodingSetOK() {
		return isDBCodingSetOK;
	}

	public boolean isClusterStateOK() {
		return isClusterStateOK;
	}

	public long getMatchingTIandTD() {
		return matchingTIandTD;
	}
	
	@Command
	public void refreshStatus(){
		diagnostic.refreshStatus();
		Executions.sendRedirect(Ch2URIs.HEALTH_CHECKER.getPath());
	}


}
