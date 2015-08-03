package com.ttc.ch2.ui.moduls.tour.mvc;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.NoFoundResourceException;
import com.ttc.ch2.ui.common.rest.BaseRest;
import com.ttc.ch2.ui.moduls.tour.services.GetOutgoingArchivesWeaveService;
import com.ttc.ch2.ui.mvc.ExceptionXmlConverter;
import com.ttc.util.messages.Severity;

@Controller
public class GetOutgoingArchivesController extends BaseRest {	
    
	@Inject
	private GetOutgoingArchivesWeaveService service;

	@RequestMapping(value = "/outgoing_archives/{VERSION}/{SELLING_COMPANY}", params = { "token" }, method = RequestMethod.GET)
	@ResponseBody
	public void getOutgoingArchivesV3(HttpServletRequest request,
									  HttpServletResponse response,
									  @PathVariable("VERSION") String version,
									  @PathVariable("SELLING_COMPANY") String sellingCompanyCode,
									  @RequestParam(value = "token") String token) {
	    service.getOutgoingArchivesV3(request, response, version, sellingCompanyCode, token);
	}

	@RequestMapping(value = "/outgoing_archives/{SELLING_COMPANY}", params = { "token" }, method = RequestMethod.GET)
	@ResponseBody
	public void getOutgoingArchivesV1(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("SELLING_COMPANY") String sellingCompanyCode,
			@RequestParam(value = "token") String token) {
	    service.getOutgoingArchivesV1(request, response, sellingCompanyCode, token);
	}

	
	@ExceptionHandler({NoFoundResourceException.class})
	public void sourceNoExist(HttpServletResponse response,Exception e) {	
		try {			
			String content=new ExceptionXmlConverter().convertToXmlByJaxb(HttpServletResponse.SC_NOT_FOUND,e.getMessage(),Severity.ERROR);
			writeOutput(response,content);		
		} catch (Exception e1) {
			throw new CH2Exception(e);
		}
	}
		
	
}
