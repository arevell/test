package com.ttc.ch2.ui.moduls.tour.services;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.NoFoundResourceException;
import com.ttc.ch2.ui.common.exceptions.PermissionException;
import com.ttc.ch2.ui.moduls.tour.common.TypeFile;


@Service
public class GetContentRepositoryXmlWeaveService /*extends BaseRest*/ {
		
	
	private static final Logger logger = LoggerFactory.getLogger(GetContentRepositoryXmlWeaveService.class);
    private static final Logger activityLogger = LoggerFactory.getLogger("ch2.activity.GetContentRepositoryXmlControler");
	
	@Inject
	private ContentRepositoryService contentRepositoryService;
//		
//	private enum TypeFile{TOUR_INFO_OLD,
//		                  TOUR_INFO_NEW,
//		                  TOUR_DEPARTURE_OLD,
//		                  TOUR_DEPARTURE_NEW;
//	};
	
    public  void getTourInfoFile(HttpServletRequest request,HttpServletResponse response,
    							String brand,
    							String tourCode,
    							String token ) {
				checkAuthenticated();
        		downloadFile(tourCode,brand,TypeFile.TOUR_INFO_OLD,response);		
    }
	
	public void getTourInfoFileNew(HttpServletRequest request,HttpServletResponse response,
			String brand,
			String tourCode,
			String token ) {
				
		checkAuthenticated();
		downloadFile(tourCode,brand,TypeFile.TOUR_INFO_NEW,response);		
	}
	
	public  void getTourDepartureFile(HttpServletRequest request,	HttpServletResponse response,	
			String brand,
			String tourCode,
			String token ) {

		checkAuthenticated();
        downloadFile(tourCode,brand,TypeFile.TOUR_DEPARTURE_OLD,response);		
	} 
	
	public  void getTourDepartureFileNew(HttpServletRequest request,	HttpServletResponse response,	
			String brand,
			String tourCode,
			String token ) {
		checkAuthenticated();
        downloadFile(tourCode,brand,TypeFile.TOUR_DEPARTURE_NEW,response);	
	} 
	

	private void downloadFile(String tourCode,String brandCode,TypeFile typeFile,HttpServletResponse response) throws CH2Exception
	{		
		logger.trace("GetContentRepositoryXmlControler:downloadFile-start");			
		ContentRepository cr=contentRepositoryService.findByTourCodeWithXML(tourCode,brandCode);
		String txt=null;
		if (cr != null) {
			switch (typeFile) {
			case TOUR_INFO_NEW:
				txt=cr.getXmlContentRepository().get(0).getTourInfoXML();break;
			case TOUR_INFO_OLD:
				txt= cr.getXmlContentRepository().get(0).getOldTourInfoXML();break;
			case TOUR_DEPARTURE_OLD:
				txt=cr.getXmlContentRepository().get(0).getOldTourDepartureXML();break;
			case TOUR_DEPARTURE_NEW:
				txt=cr.getXmlContentRepository().get(0).getTourDepartureXML();break;
			}
		}
		else
		{			
			throw new CH2Exception("Entity 'ContentRepository' not found for tourCode:"+tourCode);
		}
		
		if (StringUtils.isEmpty(txt)) {
			throw new NoFoundResourceException("Source not found for tour code:"+tourCode);			
		}
		else {
			String fileName=tourCode+".xml";
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
	        response.setHeader("Content-disposition","attachment; filename="+fileName);			
			try {
				ServletOutputStream out = response.getOutputStream();
				OutputStreamWriter w=new OutputStreamWriter(out, "UTF-8");
				w.write(txt);	
				w.flush();				
			} catch (IOException e) {
				throw new CH2Exception(e);
			}

            activityLogger.info("USER: {} downloaded file for tourCode:{}", SecurityHelper.getLoginSilent(), tourCode);
		}		
		logger.trace("GetContentRepositoryXmlControler:downloadFile-end");
		return ;
	}
    
    protected void checkAuthenticated(){        
        if(SecurityHelper.isAuthenticated()==false){
            throw new PermissionException("Authenticated not exist");
        }
    }
}
