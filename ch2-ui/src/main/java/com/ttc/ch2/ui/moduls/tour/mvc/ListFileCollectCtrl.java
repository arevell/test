package com.ttc.ch2.ui.moduls.tour.mvc;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttc.ch2.bl.filecollect.FileCollectService;
import com.ttc.ch2.bl.filecollect.FileCollectService.FileCollectVersion;
import com.ttc.ch2.bl.filecollect.FileCollectServiceException;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.NoFoundResourceException;
import com.ttc.ch2.ui.common.rest.BaseRest;
import com.ttc.ch2.ui.moduls.tour.common.FcHtmlRenderer;
import com.ttc.ch2.ui.moduls.tour.common.FcXmlRenderer;
import com.ttc.ch2.ui.moduls.tour.common.Format;
import com.ttc.ch2.ui.moduls.tour.common.Renderer;
import com.ttc.ch2.ui.mvc.ExceptionXmlConverter;
import com.ttc.common.utils.HttpResponseHelper;
import com.ttc.util.messages.Severity;


@Controller
public class ListFileCollectCtrl extends BaseRest {
			
	private static final Logger logger = LoggerFactory.getLogger(ListFileCollectCtrl.class);
    				
	@Inject
	protected FileCollectService fileCollectService;
	
	@Inject
	protected BrandDAO brandDao;
	
	
	@RequestMapping(value = "/outgoing_archives/list",params = {"token","format"}, method = RequestMethod.GET)
	@ResponseBody
    public  void getOutgoingArchivesV1(HttpServletRequest request,HttpServletResponse response,    			    							
    							@RequestParam(value = "token") String token, 
								@RequestParam(value = "format") String format) {
	    		
		logger.trace("ListFileCollectCtrl:getOutgoingArchivesV1-start");
		try {
			List<FileCollectVO> listFileCollectVOs = fileCollectService.getList(FileCollectVersion.V1);		
			Format localformat=Format.getFormatByParamName(format);
			Renderer renderer=buildRenderer(localformat, listFileCollectVOs, FileCollectVersion.V1);		
			HttpResponseHelper.writeOutput(response, renderer.generate(), localformat.getContentType());
		} catch (Exception e) {
				logger.trace("ListFileCollectCtrl:getOutgoingArchivesV1-end");
				throw new CH2Exception(e);
		} 
		logger.trace("ListFileCollectCtrl:getOutgoingArchivesV1-end");
    }
	


	@RequestMapping(value = "/outgoing_archives/{VERSION}/list",params = {"token","format"}, method = RequestMethod.GET)
	@ResponseBody
	public void getOutgoingArchivesWithVersion(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("VERSION") String version,
			@RequestParam(value = "token") String token,
			@RequestParam(value = "format",required=true) String format) {
		
		logger.trace("ListFileCollectCtrl:getOutgoingArchivesWithVersion-start");		
		if(StringUtils.isNotEmpty(version)){
			if(version.equals("V3")){
				try {
					List<FileCollectVO> listFileCollectVOs = fileCollectService.getList(FileCollectVersion.V3);		
					Format localformat=Format.getFormatByParamName(format);
					Renderer renderer=buildRenderer(localformat, listFileCollectVOs, FileCollectVersion.V3);		
					HttpResponseHelper.writeOutput(response, renderer.generate(), localformat.getContentType());
				} catch (Exception e) {
						logger.trace("ListFileCollectCtrl:getOutgoingArchivesWithVersion-end");
						throw new CH2Exception(e);
				} 
			}else if(version.equals("V1")){
				getOutgoingArchivesV1(request, response,token,format);		
			}
			else{
				logger.trace("ListFileCollectCtrl:getOutgoingArchivesWithVersion-end");
				throw new CH2Exception("Unsupported version:"+version);
			}
			logger.trace("ListFileCollectCtrl:getOutgoingArchivesWithVersion-end");
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
	
	

	
	private Renderer buildRenderer(Format format,List<FileCollectVO> rowData,FileCollectVersion requestType){
		
		Renderer renderer=null;
		switch (format) {
		case UI_PLAIN:
				renderer=new FcHtmlRenderer(rowData,requestType,SecurityHelper.getUserCCAPIPrincipal().getUserDb().getUsername());
			break;
		case XML:
				renderer=new FcXmlRenderer(rowData,requestType,SecurityHelper.getUserCCAPIPrincipal().getUserDb().getUsername());
			break;			
		default:
			break;
		}
		return renderer;
	}
}
