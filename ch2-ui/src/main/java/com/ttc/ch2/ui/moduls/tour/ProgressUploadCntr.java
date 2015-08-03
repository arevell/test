package com.ttc.ch2.ui.moduls.tour;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import com.ttc.ch2.bl.upload.UploadStatusService;
import com.ttc.ch2.bl.upload.common.UploadMessage;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.domain.upload.UploadStatus;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.moduls.tour.common.ProgressUploadStatus;

@org.springframework.stereotype.Component("ProgressUploadCntr")
@Scope("request")
public class ProgressUploadCntr {

    public static final String TOKEN_ATTRIBUTE = ProgressUploadCntr.class.getName() + ".TOKEN"; 
    

    private static final Random RANDOM = new SecureRandom();
    
    
	private UploadMessage uploadMessage;
	private String uplodMessageStyle;
	private ProgressUploadStatus progressUploadStatus;
	private String token;
	
	@Inject
	private UploadStatusService uploadStatusService;
	
	@Init
	public void init() {
		 progressUploadStatus=new ProgressUploadStatus();
		 uploadMessage=new UploadMessage("", TypeMsg.INF);
		 uplodMessageStyle="hightlight";
		 
		 token = generateToken();
		 
		 HttpSession session = SessionHelper.getSession();
		 session.setAttribute(TOKEN_ATTRIBUTE, token);
	}

    protected String generateToken() {
        // token generation is copied from struts2 framework 
		 return new BigInteger(165, RANDOM).toString(36).toUpperCase();
    }
	
	
	@Command("onUploadCheckStatus")
	@NotifyChange({"progressUploadStatus","uploadMessage"})
	public void onUploadCheckStatus() {			
		List<UploadStatus> vos=uploadStatusService.getListOfProccess();
		progressUploadStatus.setUploadStatusServiceVOs(vos);				
	}


	public UploadMessage getUploadMessage() {
		return uploadMessage;
	}


	public void setUploadMessage(UploadMessage uploadMessage) {
		this.uploadMessage = uploadMessage;
	}


	public String getUplodMessageStyle() {
		return uplodMessageStyle;
	}


	public void setUplodMessageStyle(String uplodMessageStyle) {
		this.uplodMessageStyle = uplodMessageStyle;
	}


	public ProgressUploadStatus getProgressUploadStatus() {
		return progressUploadStatus;
	}


	public void setProgressUploadStatus(ProgressUploadStatus progressUploadStatus) {
		this.progressUploadStatus = progressUploadStatus;
	}
	public void setToken(String token) {
	    this.token = token;
	}
	public String getToken() {
	    return token;
	}
}
