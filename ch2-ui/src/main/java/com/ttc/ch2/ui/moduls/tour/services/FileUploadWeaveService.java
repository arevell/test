package com.ttc.ch2.ui.moduls.tour.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ttc.ch2.bl.upload.UploadServiceException;
import com.ttc.ch2.bl.upload.UploadTourInfoService;
import com.ttc.ch2.bl.upload.common.OperationStatus;
import com.ttc.ch2.bl.upload.common.UploadMessage;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext;

@Service
public class FileUploadWeaveService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadWeaveService.class);
	
	@Inject
	private UploadTourInfoService uploadTourInfoService;
	
	
    public void handleFileUpload(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="file",required = false) MultipartFile file) throws ServletException, IOException{     	
    	String orginalName=file.getOriginalFilename();    	    	
    	Boolean uploadFalg=(Boolean) SessionHelper.getAttributeFromUserContext(request, UserContext.UserContextStaticName.UPLOADED_BY_USER_FLAG);
    	SessionHelper.putAttributeToUserContext(request, UserContext.UserContextStaticName.UPLOADED_FILE_STATUS_MSG, null);
    	String url=forwardUrl(orginalName,uploadFalg==null ? false : uploadFalg);    	
        if (!file.isEmpty() && (uploadFalg==null ||  uploadFalg==false)) {            	
            try {            	
            	SessionHelper.putAttributeToUserContext(request, UserContext.UserContextStaticName.UPLOADED_BY_USER_FLAG, true);
                byte[] bytes = file.getBytes();
        			UploadTourInfoFile u = new UploadTourInfoFile();
        			u.setName(orginalName);
        			InputStream stream=null;
        			try {
        				stream=new ByteArrayInputStream(bytes);        				
        				OperationStatus op=uploadTourInfoService.addManualUploadTourInfoFile(u ,stream,SecurityHelper.getUserGuiPrincipal().getUserDb());
        				SessionHelper.putAttributeToUserContext(request, UserContext.UserContextStaticName.UPLOADED_FILE_STATUS_MSG,  new UploadMessage("The operation completed successfully for file:"+orginalName,TypeMsg.INF));
        				url=forwardUrl("",false);
        			} 
        			finally {
        				if(stream!=null)
        					IOUtils.closeQuietly(stream);
        			}
            } 
            catch (UploadServiceException e) {
            	logger.error("",e);        		
				SessionHelper.putAttributeToUserContext(request, UserContext.UserContextStaticName.UPLOADED_FILE_STATUS_MSG, new UploadMessage(e.getMessage(),TypeMsg.ERR));
				url=forwardUrl(orginalName,false);
			}
            catch (Exception e) {            	
            	logger.error("",e);
            	SessionHelper.putAttributeToUserContext(request, UserContext.UserContextStaticName.UPLOADED_FILE_STATUS_MSG, new UploadMessage(e.getMessage(),TypeMsg.ERR));
            	url=forwardUrl(orginalName,false);
            }
            finally{            	
            	SessionHelper.putAttributeToUserContext(request, UserContext.UserContextStaticName.UPLOADED_BY_USER_FLAG, null);
            }            
        }
    	RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
    } 
    
    
    private String forwardUrl(String fileName,boolean disabled){    	
    	StringBuilder sb=new StringBuilder("uploadFrame.zul");
    	sb.append("?").append("file_name=").append(StringEscapeUtils.escapeHtml(fileName));
    	if(disabled)
    		sb.append("&").append("disabled=").append("disabled");  
    	return sb.toString();
    }
}