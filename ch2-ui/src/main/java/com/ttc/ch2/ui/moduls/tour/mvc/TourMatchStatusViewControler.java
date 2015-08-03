package com.ttc.ch2.ui.moduls.tour.mvc;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.config.JspCh2URIs;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.PermissionException;
import com.ttc.ch2.ui.common.exceptions.SessionIncorrectException;
import com.ttc.ch2.ui.moduls.tour.services.TourMatchStatusWeaveService;


@Controller
public class TourMatchStatusViewControler {
	
	@Inject 
	private TourMatchStatusWeaveService service;
	
	@RequestMapping(value = "/modules/tour/tour_match_status_view",params = {"id","brandCode"}, method = RequestMethod.GET)
	@ResponseBody
    public  String generateView(HttpServletRequest request,@RequestParam(value = "id") String id,@RequestParam(value = "brandCode") String brandCode ) {	
	    return service.generateView(request, id, brandCode);
    } 
	
	@ExceptionHandler({PermissionException.class})
    public String permissionError(HttpServletRequest req,Exception e) {						
		return "redirect:"+JspCh2URIs.ACCESS_DENIED.getPath()+"?msg="+e.getMessage();
	}
	
	@ExceptionHandler({SessionIncorrectException.class})
	public String sessionIncorrectError() {	
		return "redirect:"+JspCh2URIs.SESSION_INVALIDATE.getPath();
	}
	@ExceptionHandler({CH2Exception.class})
	public String ch2GeneralError(Exception e) {	
		throw ((CH2Exception)e);
	}
}
