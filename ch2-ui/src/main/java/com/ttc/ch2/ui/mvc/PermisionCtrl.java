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


/**
 * Component used in rest pages used token
 * */
@Controller
public class PermisionCtrl 
{	
	private static final Logger logger = LoggerFactory.getLogger(PermisionCtrl.class);
		
	@RequestMapping(value = "/permision",  method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView  handleAccessDenied(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		ModelAndView mav=new ModelAndView(JspCh2URIs.PERMISSION.getName());
		return mav;
	}
}