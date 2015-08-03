package com.ttc.ch2.ui.moduls.tour.mvc;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.TourValidatorException;
import com.ttc.ch2.ui.common.validators.TourValidator;
import com.ttc.ch2.ui.moduls.tour.services.GetBrochureWeaveService;
import com.ttc.ch2.ui.mvc.ExceptionXmlConverter;
import com.ttc.common.utils.HttpResponseHelper;


@Controller
public class GetBrochureController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(GetBrochureController.class);
	@Inject
	private GetBrochureWeaveService service;
	

	@RequestMapping(value = "/modules/brochure/view", params = { "tour" }, method = RequestMethod.GET)
	@ResponseBody
	public void getBrochureExample(HttpServletRequest request,
								   HttpServletResponse response,
								   @RequestParam(value = "tour") String tour,
								   @RequestParam(value = "brandCode") String brandCode) {
	    
		TourValidator.validate(tour);
	    service.getBrochureExample(request, response, tour, brandCode);
	}

	@RequestMapping(value = "/brochure_engine/brochure", params = { "token", "title", "brandCode", "sellingCompanyCode", "agent_text", "agent_image", "tours" }, method = RequestMethod.GET)
	@ResponseBody
	public void getBrochure(HttpServletRequest request,
							HttpServletResponse response,
							@RequestParam(value = "token") String token,
							@RequestParam(value = "title", required = false) String title,
							@RequestParam(value = "brandCode") String brandCode,
							@RequestParam(value = "sellingCompanyCode") String sellingCompanyCode,
							@RequestParam(value = "agent_text", required = false) String agentText,
							@RequestParam(value = "agent_image", required = false) String agentImage,
							@RequestParam(value = "tours") List<String> tours) {
		for(String tour: tours)
			TourValidator.validate(tour);
	    service.getBrochure(request, response, token, title, brandCode, sellingCompanyCode, agentText, agentImage, tours);
	}
	
	
	@ExceptionHandler({Exception.class,CH2Exception.class})
	public String unhandledException(HttpServletRequest request,HttpServletResponse response,Exception e) {	
		try {		
			logger.error("",e);						
			if(request.getParameterMap().containsKey("zk") && BooleanUtils.toBoolean(request.getParameter("zk"))){
				throw e;
			}else{			
				String content=new ExceptionXmlConverter().convertToXmlByJaxb(e);
				writeOutput(response,content,HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} catch (IOException e1) {
				throw new CH2Exception(e);
		} catch (Exception e1) {
			throw new CH2Exception(e);
		}
		return null;
	}
	
	
		
	protected void writeOutput(HttpServletResponse response,String content,int statusCode) throws IOException{
		HttpResponseHelper.writeOutput(statusCode,response, content,"text/xml");
	}
	
}
