package com.ttc.ch2.ui.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttc.ch2.ui.common.config.JspCh2URIs;

@Controller
public class InvalidSessionCtrl 
{	
	private static final Logger logger = LoggerFactory.getLogger(InvalidSessionCtrl.class);
		
	@RequestMapping(value = "/invalid_session",  method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView  handleAccessDenied(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		ModelAndView mav=new ModelAndView(JspCh2URIs.SESSION_INVALIDATE.getName());
		return mav;
	}
}