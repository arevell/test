package com.ttc.ch2.bl.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.inject.Inject;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.DOMOutputter;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
//import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.tour.ContentRepository;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
public class TourMatchingViewServiceImpl implements TourMatchingViewService {

	private static final Logger logger=LoggerFactory.getLogger(TourMatchingViewServiceImpl.class);

	@Value("${app.name}")
	private String appName;
	
	
	@Value("${tour.view.xsl.file}")
	private String xslFile;
	
	@Value("${tour.view.xsl.file_new}")
	private String xslFileNew;
	
	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	public String getTransformateContentView(String tourCode,String brandCode) throws TourMatchingViewException
	{
		Preconditions.checkArgument(StringUtils.isNotEmpty(tourCode),"TourCode is empty");
		Preconditions.checkArgument(StringUtils.isNotEmpty(brandCode),"BrandCode is empty");
		
		ContentRepository cr=contentRepositoryDAO.findByTourCode(tourCode,brandCode);
		if(cr==null)
			throw new TourMatchingViewException("ContentRepository not found for tour code:"+tourCode);
	
		return 	generateNew(cr);
	}
	
	private String generateNew(ContentRepository cr) throws TourMatchingViewException
	{
		try {					
			Source source = generateSourceDocument(cr.getXmlContentRepository().get(0).getTourInfoXML(), cr.getXmlContentRepository().get(0).getTourDepartureXML());
			InputStreamReader fileXsl=new InputStreamReader(getClass().getResourceAsStream(xslFileNew)); 			
			Transformer	transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(fileXsl));
			transformer.setParameter("tourInfoDataSource", ContentRepository.DataSource.CH1.equals(cr.getTourInfoSource()) ? "Content Hub 1.0" : "Content Hub 2.0");
			if(StringUtils.isNotEmpty(appName))
				transformer.setParameter("appName","/"+appName);
			StringWriter output=new StringWriter();
			StreamResult rs = new StreamResult(output);
			transformer.transform(source, rs);
			String html = rs.getWriter().toString();
			// The replaceAll method below is for security reasons
			return html.replaceAll("<([^ ]*) [^>]*(http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4|http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0)[^>]*", "<$1");
		} catch (TransformerConfigurationException e) {					
			throw new TourMatchingViewException(e);		
		} catch (TransformerFactoryConfigurationError e) {
			throw new TourMatchingViewException(e.getException());		
		} catch (Exception e) {			
			throw new TourMatchingViewException(e);		
		}
	}
	
	
	private String generate(ContentRepository cr) throws TourMatchingViewException
	{
		try {					
			Source source = generateSourceDocument(cr.getXmlContentRepository().get(0).getOldTourInfoXML(), cr.getXmlContentRepository().get(0).getOldTourDepartureXML());
			InputStreamReader fileXsl=new InputStreamReader(getClass().getResourceAsStream(xslFile)); 			
			Transformer	transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(fileXsl));
			if(StringUtils.isNotEmpty(appName))
				transformer.setParameter("appName","/"+appName);
			StringWriter output=new StringWriter();
			StreamResult rs = new StreamResult(output);
			transformer.transform(source, rs);
			return rs.getWriter().toString();
		} catch (TransformerConfigurationException e) {					
			throw new TourMatchingViewException(e);		
		} catch (TransformerFactoryConfigurationError e) {
			throw new TourMatchingViewException(e.getException());		
		} catch (Exception e) {			
			throw new TourMatchingViewException(e);		
		}		 	
	}
	
	
	private Source generateSourceDocument(String tourInfoXml,String tourDepartureXml) throws JDOMException, IOException 
	{		
		SAXBuilder builder = new SAXBuilder();

		Document tourDataDocument = new Document(new Element("TourData"));
		if(StringUtils.isNotEmpty(tourInfoXml))
		{
			Document tourInfoFileDocument = builder.build( new StringReader(tourInfoXml));
			tourDataDocument.getRootElement().addContent (tourInfoFileDocument.getRootElement().detach());
		}
		if(StringUtils.isNotEmpty(tourDepartureXml))
		{
			Document  tourDepartureDocument = builder.build( new StringReader(tourDepartureXml));
			tourDataDocument.getRootElement().addContent (tourDepartureDocument.getRootElement().detach());
		}
		
		DOMOutputter outputter = new DOMOutputter();		
		org.w3c.dom.Document domDocument = outputter.output(tourDataDocument);
		Source xmlSource = new DOMSource(domDocument);
		return xmlSource;
	}
}
	

