package com.ttc.ch2.ui.moduls.base;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;

import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.ch2.ui.common.zkcontrolers.AbstractEncryptedParamHandelComp;
import com.ttc.ch2.ui.mvc.ExceptionHandlingCtrl;
import com.ttc.ch2.ui.mvc.ExceptionHandlingCtrl.Param;

@org.springframework.stereotype.Component("ErrorCtrl")
@Scope("request")
public class ErrorCtrl extends AbstractEncryptedParamHandelComp{
		
	private static final Logger logger = LoggerFactory.getLogger(ErrorCtrl.class);
	
	
	private boolean showPbMain;
	private Integer statusCode;
	private String exceptionMessage;
	private boolean showPbReload; 
	private String reloadPath;
	
	@Init
	public void init(){
		try{
			super.initBase();
			if(isParamsExist)
			{
				String sessionExist=params.get(ExceptionHandlingCtrl.Param.READ_FROM_SESSION.toString());
				if(StringUtils.isNotBlank(sessionExist) && BooleanUtils.toBoolean(sessionExist)==true){					
				// log exception
					Throwable throwable=(Throwable) (SessionHelper.getSession().getAttribute(UserContextStaticName.EXCEPTION));					
					if (throwable != null) {
						org.zkoss.util.logging.Log.lookup("Fatal").error(throwable);							
					}
										
				// setup message exception	
				String msgException=(String) SessionHelper.getSession().getAttribute(UserContextStaticName.EXCEPTION_MSG);
					if(StringUtils.isNotEmpty(msgException)){
						this.exceptionMessage=StringEscapeUtils.escapeHtml(msgException);	
					}
					else{
						this.exceptionMessage="No message exception in session "+Labels.getLabel("base.errorCtrl.commonmsg"); 
					}
//					this.exceptionMessage=(String) SessionHelper.getSession().getAttribute(UserContextStaticName.EXCEPTION_MSG);
					this.reloadPath=(String) SessionHelper.getSession().getAttribute(UserContextStaticName.RELOAD_PAGE);
					this.showPbReload=StringUtils.isNotEmpty(reloadPath);
				}
				else{					
					String msgException=(String)params.get(ExceptionHandlingCtrl.Param.EXCEPTION_MSG.toString());
					if(StringUtils.isNotEmpty(msgException)){
						this.exceptionMessage=StringEscapeUtils.escapeHtml(msgException);	
					}
					else{
						this.exceptionMessage="No exist message exception "+Labels.getLabel("base.errorCtrl.commonmsg"); 
					}					
				}				

		    	if(params.get(ExceptionHandlingCtrl.Param.CODE.toString())!=null){    	
		    		this.statusCode=Integer.parseInt(params.get(ExceptionHandlingCtrl.Param.CODE.toString()));
		    	}
			}
			else{
				this.exceptionMessage=Labels.getLabel("base.errorCtrl.commonmsg");
				this.statusCode=500;	
			}
			this.showPbMain=com.ttc.ch2.common.SecurityHelper.isAuthenticatedSilent();
		}
		catch(Exception e){
			logger.error("",e);
			this.statusCode=500;
			this.showPbMain=false;	
			this.showPbReload=false;
			this.exceptionMessage=Labels.getLabel("base.errorCtrl.errorpagemsg");			
		}
	}
	
	

	public boolean isShowPbMain() {
		return showPbMain;
	}

	public void setShowPbMain(boolean showPbMain) {
		this.showPbMain = showPbMain;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}



	public String getReloadPath() {
		return reloadPath;
	}



	public void setReloadPath(String reloadPath) {
		this.reloadPath = reloadPath;
	}



	public boolean isShowPbReload() {
		return showPbReload;
	}



	public void setShowPbReload(boolean showPbReload) {
		this.showPbReload = showPbReload;
	}

}
