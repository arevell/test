package com.ttc.ch2.ui.moduls.tour.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.zkoss.zul.Filedownload;

import com.google.common.base.Preconditions;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;

@Service
public class TourRepositoryExtWeaveService {
    
	@Inject
	protected ContentRepositoryService contentRepositoryService;

	protected ContentRepository getContentRepositoryWithXML(String tourCode,String brandCode) {
		return contentRepositoryService.findByTourCodeWithXML(tourCode,brandCode);
	}
	
	protected void downloadOperation( String xmlcontent,String xmlFileName)
	{
		InputStream is=null;
		try {				
			is = new ByteArrayInputStream(xmlcontent.getBytes("UTF-8"));
			if (is != null)
	            Filedownload.save(is, "text/xml", xmlFileName);
		} catch (UnsupportedEncodingException e) {
			throw new CH2Exception(xmlFileName+" encoding problem" );
		}
	}
    
    public void downloadDepartureNewExt(ContentRepository selected)
    {
        //in system Cr should have only one brand
        Brand brand=selected.getBrands().iterator().next(); 
        ContentRepository cr = getContentRepositoryWithXML(selected.getTourCode(),brand.getCode());
        Preconditions.checkState(StringUtils.isNotEmpty(cr.getXmlContentRepository().get(0).getTourDepartureXML()),"ContentRepository don't have NewTourDepartureXML");
        downloadOperation(cr.getXmlContentRepository().get(0).getTourDepartureXML(), selected.getTdFileName());
    }
    
    public void downloadDepartureOldExt(ContentRepository selected)
    {
        //in system Cr should have only one brand
        Brand brand=selected.getBrands().iterator().next(); 
        ContentRepository cr = getContentRepositoryWithXML(selected.getTourCode(),brand.getCode());
        Preconditions.checkState(StringUtils.isNotEmpty(cr.getXmlContentRepository().get(0).getOldTourDepartureXML()),"ContentRepository don't have OldTourDepartureXML");
        downloadOperation(cr.getXmlContentRepository().get(0).getOldTourDepartureXML(), selected.getTdFileName());
    }
    
    public void downloadInfoNewExt(ContentRepository selected) {
        //in system Cr should have only one brand
        Brand brand=selected.getBrands().iterator().next(); 
        ContentRepository cr = getContentRepositoryWithXML(selected.getTourCode(),brand.getCode());
        Preconditions.checkState(StringUtils.isNotEmpty(cr.getXmlContentRepository().get(0).getTourInfoXML()),"ContentRepository don't have NewTourInfoXML");
        downloadOperation(cr.getXmlContentRepository().get(0).getTourInfoXML(), selected.getTiFileName());
    }
    
    public void downloadInfoOldExt(ContentRepository selected)
    {
        //in system Cr should have only one brand
        Brand brand=selected.getBrands().iterator().next(); 
        ContentRepository cr = getContentRepositoryWithXML(selected.getTourCode(),brand.getCode());
        Preconditions.checkState(StringUtils.isNotEmpty(cr.getXmlContentRepository().get(0).getOldTourInfoXML()),"ContentRepository don't have OldTourInfoXML");
        downloadOperation(cr.getXmlContentRepository().get(0).getOldTourInfoXML(), selected.getTiFileName());
    }
    
}
