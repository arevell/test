package com.ttc.ch2.ui.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Ch2Properties 
{
	@Value("${app.name}")
	private String appName;
	
	@Value("${app.node}")
	private String appNode;
	
	
	@Value("${common.version}")
	private String version;
	
	@Value("${common.enviroment}")
	private String enviroment;
	
	@Value("${common.deploy.time}")
	private String deployTime;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppNode() {
		return appNode;
	}

	public String getVersion() {
		return version;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public String getDeployTime() {
		return deployTime;
	}
	
}