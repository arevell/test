package com.ttc.ch2.ui.moduls.tour.mvc;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ttc.ch2.bl.upload.common.UploadMessage;
import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext;
import com.ttc.ch2.ui.moduls.tour.ProgressUploadCntr;
import com.ttc.ch2.ui.moduls.tour.services.FileUploadWeaveService;

@Controller
public class FileUploadController {
	private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	@Inject
	private FileUploadWeaveService service;
	
	
    @RequestMapping(value="/modules/tour/upload.htm", method=RequestMethod.POST)
    public void handleFileUpload(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="file",required = false) MultipartFile file) throws ServletException, IOException{
        
        String attribute = (String) request.getSession().getAttribute(ProgressUploadCntr.TOKEN_ATTRIBUTE);
        String token = request.getParameter("pToken");
        
        if (StringUtils.isBlank(token) || !StringUtils.equals(attribute, token)) {
            logger.warn("Invalid token:{} found in manual file upload, correct is:{}", token, attribute);
            SessionHelper.putAttributeToUserContext(request, UserContext.UserContextStaticName.UPLOADED_FILE_STATUS_MSG, new UploadMessage("Server is temporarily out of service", TypeMsg.ERR));
            SessionHelper.putAttributeToUserContext(request, UserContext.UserContextStaticName.UPLOADED_BY_USER_FLAG, null);       
            RequestDispatcher dispatcher = request.getRequestDispatcher("uploadFrame.zul?file_name=");
            
            dispatcher.forward(request, response);            
            return;
        }
        
        service.handleFileUpload(request, response, file);  
    } 
}