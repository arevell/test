package com.ttc.ch2.ui.common.zkcontrolers;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

import com.ttc.common.params.ParamsUtils;

public abstract class AbstractEncryptedParamHandelComp {
	
	public enum ParamNames{msgBox}
	
	protected Map<String,String> params;
	protected boolean isParamsExist=false; 
		
	@Init
	public void initBase()
	{
		String param=Executions.getCurrent().getParameter("param");
		if (StringUtils.isNotBlank(param)) {
			params = ParamsUtils.getInstance().decodeAllParam(param);
			isParamsExist=params.size()>0;
		}
		else {
			params = Collections.<String, String> emptyMap();
		}		
		showMessage();
	}
	
	
	protected void showMessage(){		
		if(isParamsExist && params.containsKey(ParamNames.msgBox.toString())){
		Messagebox.show(Labels.getLabel("users.validation.user_not_exist"), Labels.getLabel("users.operator_ccapi_wiz.validation.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
		}
	}
}
