package com.ttc.ch2.ui.moduls.tour.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ttc.ch2.api.ccapi.v3.GetBrochureRequest;
import com.ttc.ch2.brox.service.BrochureService;
import com.ttc.ch2.brox.service.BrochureServiceException;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.PermissionException;

@Service
public class GetBrochureWeaveService {

	@Inject
	private BrochureService brochureService;
	public void getBrochureExample(HttpServletRequest request,
								   HttpServletResponse response,
								   String tour,
								   String brandCode) {

		checkAuthenticated();
		GetBrochureRequest params = new GetBrochureRequest();

		params.setTitle("eBrochure example");
		params.setBrandCode(brandCode);
		params.setSellingCompanyCode(BrochureService.ANY_CODE);
		params.setAgentText(null);
		params.setAgentImage(null);
		params.getTour().addAll(Arrays.asList(tour));

		byte[] resultData = null;

		try {
			resultData = brochureService.getBrochureData(params);
		} catch (BrochureServiceException e) {
			throw new CH2Exception(e.getMessage(), e);
		}

		response.setContentType("application/pdf");
		response.setContentLength(resultData.length);

		try {
			response.getOutputStream().write(resultData);
			response.getOutputStream().flush();
		} catch (IOException e) {
			throw new CH2Exception(e.getMessage(), e);
		}
	}

	public void getBrochure(HttpServletRequest request,
							HttpServletResponse response,
							String token,
							String title,
							String brandCode,
							String sellingCompanyCode,
							String agentText,
							String agentImage,
							List<String> tours) {

		checkAuthenticated();
		GetBrochureRequest params = new GetBrochureRequest();

		params.setTitle(title);
		params.setBrandCode(brandCode);
		params.setSellingCompanyCode(sellingCompanyCode);
		params.setAgentText(agentText);
		params.setAgentImage(agentImage);
		params.getTour().addAll(tours);

		byte[] resultData = null;

		try {
			resultData = brochureService.getBrochureData(params);
		} catch (BrochureServiceException e) {
			throw new CH2Exception("", e);
		}

		response.setHeader("Content-disposition", "attachment; filename = brochure.pdf");
		response.setContentType("application/pdf");
		response.setContentLength(resultData.length);

		try {
			response.getOutputStream().write(resultData);
			response.getOutputStream().flush();
		} catch (IOException e) {
			throw new CH2Exception(e.getMessage(), e);
		}
	}
	
	protected void checkAuthenticated(){		
		if(SecurityHelper.isAuthenticated()==false){
			throw new PermissionException("Authenticated not exist");
		}
		else{
			if(SecurityHelper.hasAuthority(Role.ADMINISTRATOR)==true)
				throw new PermissionException("Access denied");
		}
	}
}
