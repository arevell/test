package com.ttc.ch2.ui.moduls.tour.mvc;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ttc.ch2.bl.filecollect.FileCollectService.FileCollectVersion;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.NoFoundResourceException;
import com.ttc.ch2.ui.common.exceptions.PermissionException;
import com.ttc.ch2.ui.common.rest.BaseRest;
import com.ttc.ch2.ui.moduls.tour.services.GetContentRepositoryXmlWeaveService;
import com.ttc.ch2.ui.mvc.ExceptionXmlConverter;
import com.ttc.util.messages.Severity;


@Controller
public class GetContentRepositoryXmlControler extends BaseRest {
		
	
	private static final Logger logger = LoggerFactory.getLogger(GetContentRepositoryXmlControler.class);
    private static final Logger activityLogger = LoggerFactory.getLogger("ch2.GetContentRepositoryXmlControler");
		
	@Inject
	private GetContentRepositoryXmlWeaveService service;
		
	
	
	@RequestMapping(value = "/tour_info/{BRAND}/{TOUR_CODE}",params = {"token"}, method = RequestMethod.GET)
    public  void getTourInfoFile(HttpServletRequest request,HttpServletResponse response,
    							@PathVariable("BRAND") String brand,
    							@PathVariable("TOUR_CODE") String tourCode,
    							@RequestParam(value = "token") String token ) {
	    		
	    service.getTourInfoFile(request, response, brand, tourCode, token);		
    }
		
	@RequestMapping(value = "/tour_info/{BRAND}/{VERSION}/{TOUR_CODE}",params = {"token"}, method = RequestMethod.GET)
	public void getTourInfoFileWithVersion(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("BRAND") String brand,
			@PathVariable("TOUR_CODE") String tourCode,
			@PathVariable("VERSION") String version,
			@RequestParam(value = "token") String token ) {
				
		if(StringUtils.isNotEmpty(version)){
			if(version.equals("V3")){
				service.getTourInfoFileNew(request, response, brand, tourCode, token);
			}else if(version.equals("V1")){
				service.getTourInfoFile(request, response, brand, tourCode, token);		
			}
			else{
				throw new CH2Exception("Unsupported version:"+version);
			}
		}   		
	}
	
	@RequestMapping(value = "/tour_departure/{BRAND}/{TOUR_CODE}",params = {"token"}, method = RequestMethod.GET)
	public  void getTourDepartureFile(HttpServletRequest request,	HttpServletResponse response,	
			@PathVariable("BRAND") String brand,
			@PathVariable("TOUR_CODE") String tourCode,
			@RequestParam(value = "token") String token ) {
	    
	    service.getTourDepartureFile(request, response, brand, tourCode, token);		
	} 
	
	@RequestMapping(value = "/tour_departure/{BRAND}/{VERSION}/{TOUR_CODE}",params = {"token"}, method = RequestMethod.GET)
	public  void getTourDepartureFileWithVersion(HttpServletRequest request,	HttpServletResponse response,	
			@PathVariable("BRAND") String brand,
			@PathVariable("VERSION") String version,
			@PathVariable("TOUR_CODE") String tourCode,
			@RequestParam(value = "token") String token ) {
	   	    
	    if(StringUtils.isNotEmpty(version)){
			if(version.equals("V3")){
				service.getTourDepartureFileNew(request, response, brand, tourCode, token);
			}else if(version.equals("V1")){
				service.getTourDepartureFile(request, response, brand, tourCode, token);		
			}
			else{
				throw new CH2Exception("Unsupported version:"+version);
			}
		}   	
	    
	    
	} 
		
	@ExceptionHandler({NoFoundResourceException.class})
	public String sourceNoExist(HttpServletResponse response,Exception e) {	
		try {
			
			String content=new ExceptionXmlConverter().convertToXmlByJaxb(HttpServletResponse.SC_NOT_FOUND,e.getMessage(),Severity.ERROR);
			writeOutput(response,content);		
		} catch (Exception e1) {
			throw new CH2Exception(e);
		}
		return null;
	}
}
