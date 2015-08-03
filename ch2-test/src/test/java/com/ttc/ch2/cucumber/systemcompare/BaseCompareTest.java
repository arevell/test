package com.ttc.ch2.cucumber.systemcompare;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.base.Joiner;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.collect.Sets;
import org.junit.Assert;
import org.junit.Assume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.habs.tourdeparturegen.HabsITropicsV3TourDepartureMapper;
import com.ttc.ch2.bl.departure.habs.tourdeparturegen.HabsTourDepartureAndSC;
import com.ttc.ch2.cucumber.SpringHelper;
import com.ttc.ch2.cucumber.systemcompare.ch2vshabs.HabsTourDepartureMapper;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.ArrayOfMarketVariationPricing;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationDepartureInfo;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationPricing;

import facade.itropics.webservice.tropics.com.itropicsbuildws.WsOperatingProductVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsProductVO;


public class BaseCompareTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseCompareTest.class);
	
	protected List<String> choosedTour;
	protected List<String> compareResults;
	
	
	protected String brandCode;
	// params from feature files	
	protected String sellingCompanyCode;
	protected int percent;
	protected Map<String, String> bindingMap = new HashMap<String, String>();
	
	

	protected TourDeparturesType getTourDeparturesTypeV3(String tourCode,String data,JAXBContext jaxbContextV3){
		try{
			Unmarshaller unmarshaller = jaxbContextV3.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<TourDeparturesType> obj=(JAXBElement<TourDeparturesType>) unmarshaller.unmarshal(new StringReader(data));
			TourDeparturesType tourData= obj.getValue();
			return tourData;
		}
		catch(Exception e){
			logger.error("",e);
			Assert.assertTrue(String.format("Problem with unmarshaller brand:%s tour code:%s",brandCode,tourCode),false);
		}
		return null;
	}
	
	protected MarketVariationDepartureInfo getTourDeparturesTypeV1(String tourCode,String data,JAXBContext jaxbContextV1){
		try{
			Unmarshaller unmarshaller = jaxbContextV1.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<MarketVariationDepartureInfo> obj=(JAXBElement<MarketVariationDepartureInfo>) unmarshaller.unmarshal(new StringReader(data));
			MarketVariationDepartureInfo tourData= obj.getValue();
			return tourData;
		}
		catch(Exception e){
			logger.error("",e);
			Assert.assertTrue(String.format("Problem with unmarshaller brand:%s tour code:%s",brandCode,tourCode),false);
		}
		return null;
	}
	
	
	protected void prepareDataBasedOnCh2(){
		
		choosedTour=Lists.newArrayList();
		compareResults=Lists.newArrayList();
					
		// select ids from CH2
		SellingCompanyDAO scDAO=SpringHelper.applicationContext.getBean(SellingCompanyDAO.class);
		SellingCompany sc=scDAO.findBySellingCompanyCode(sellingCompanyCode);		
		
		ContentRepositoryDAO crDao=SpringHelper.applicationContext.getBean(ContentRepositoryDAO.class);		
		ContentRepository filter=new ContentRepository();
		filter.getSellingCompanies().add(sc);	
//		filter.setTourCode("HIO5B10");
		QueryCondition q=new QueryCondition(); 	
				
		List<Long> ids=crDao.getContentRepositoriesIdsList(q, filter,Lists.newArrayList(ContentRepository.RepositoryStatus.TIandTD,ContentRepository.RepositoryStatus.TourDepartureOnly));
		Assume.assumeTrue("No data in Ch2 DB",ids.size()>0);		
		
		selectTour(ids, crDao);
		Assume.assumeTrue("No data in Ch2 DB",choosedTour.size()>0);
		logger.info(this.sellingCompanyCode+"-> Choosed tours:["+Joiner.on(",").join(choosedTour)+"] size:"+choosedTour.size());
	}
	
	protected void setupPercent(String percent){
		Assert.assertTrue("Incorrect selling company code:"+percent,StringUtils.isNotBlank(percent));
		try{
		int value=Integer.parseInt(percent.trim());		
		Assert.assertTrue("Incorrect percent value:"+value,value>=1 && value<=100);
		this.percent=value;
		}
		catch(NumberFormatException e){
			logger.error("",e);
			Assert.assertTrue("Incorrect selling company code:"+percent,false);			
		}
	}
	
	protected void setupSellingCompany(String scCode){
		Assert.assertTrue("Incorrect selling company code:"+scCode,StringUtils.isNotBlank(scCode));
		Assert.assertTrue("Incorrect selling company code:"+scCode,scCode.length()>2);
		this.sellingCompanyCode=scCode.trim();
		this.brandCode=this.sellingCompanyCode.substring(0, 2);
	}
	
	private void selectTour(List<Long> ids,ContentRepositoryDAO crDao){
		
		ContentRepositoryService crService=SpringHelper.applicationContext.getBean(ContentRepositoryService.class);
		
		double r=(((double)ids.size())*(percent*0.01d));		
		int count= r>1 ? (int) r: 1;
		
		Collections.shuffle(ids);	
		
		while(count>0 && ids.isEmpty()==false){			
				Long id=ids.remove(0);
				ContentRepository cr=crDao.find(id);				
				ContentRepository crFull=crService.findByTourCodeWithXML(cr.getTourCode(), this.brandCode);
				if(getSellingCompanyCodes(crFull).contains(this.sellingCompanyCode)){				
				choosedTour.add(cr.getTourCode());
				count--;
				}
				crDao.evictEntity(cr);
				crDao.evictEntity(crFull);			
		}		
	}
	
	
	protected void compare(TourDeparturesType ch2Data, HabsDataVO habsDataVO) {

		String systemNameA="Content Hub 2.0";
		String systemNameB="Habs";
		EnvironmentsCompareHelper compareHelper=new EnvironmentsCompareHelper(systemNameA, systemNameB);
		compareHelper.getDedicatedCondition().add(new AccommondationProductCondition());
		
		Assert.assertNotNull(String.format(EnvironmentsCompareHelper.NO_DATA_RESPONSE,systemNameA, habsDataVO.tour.getCode(), this.sellingCompanyCode), ch2Data);
		Assert.assertNotNull(String.format(EnvironmentsCompareHelper.NO_DATA_RESPONSE,systemNameB, habsDataVO.tour.getCode(), this.sellingCompanyCode), habsDataVO);
		try {

			SellingCompany sellingCompany = new SellingCompany();
			sellingCompany.setCode(this.sellingCompanyCode);

			WsOperatingProductVO operatingProduct = new WsOperatingProductVO();
			operatingProduct.setCode(habsDataVO.operatingProduct.getCode());

			WsProductVO product = new WsProductVO();
			product.setCode(habsDataVO.tour.getCode());
			product.setOnlineBookable(habsDataVO.tour.isITropicsSellable());
			product.setBrochureCode(habsDataVO.tour.getBrochureCode());
			product.setOperatingProduct(operatingProduct);

			HabsTourDepartureAndSC habsTourDepartureAndSC = HabsTourDepartureMapper.convert(habsDataVO);
			habsTourDepartureAndSC.setSellingCompany(sellingCompany);
			habsTourDepartureAndSC.setProduct(product);

			TourDeparturesType habsData = HabsITropicsV3TourDepartureMapper.map(new OperationStatus(), habsTourDepartureAndSC);

			List<String> fields = new ArrayList<String>();

			compareHelper.getFieldsList(fields, TourDeparturesType.class, null);
			Collections.sort(fields);

			Set<String> differencesList = new TreeSet<String>();
//			saveFile("I:/fields.txt", Joiner.on("\n").join(fields));
			for (String fieldPath : fields) {
				compareHelper.compareFields(ch2Data, habsData, fieldPath, fieldPath, fieldPath, String.format(EnvironmentsCompareHelper.NAME_ITEM_TEMPLATE, this.brandCode, this.sellingCompanyCode, habsDataVO.tour.getCode()), new ArrayList<String>(), differencesList, bindingMap);
			}
			
			if(differencesList.size()>0){
				compareResults.add(String.format(EnvironmentsCompareHelper.DIFFERENCES_MESSAGE, StringUtils.join(differencesList, "")));
			}
			
//			Assert.assertTrue(String.format(EnvironmentsComparerUtils.DIFFERENCES_MESSAGE, StringUtils.join(differencesList, "")), differencesList.size() == 0);

		} catch (Exception e) {

			logger.error("", e);
			Assert.assertTrue("" + e.getMessage(), false);
		}
	}
	
	protected void saveFile(String path,String data){
		try{
			FileUtils.write(new File(path), data);
			}
			catch(Exception e){
				logger.error("",e);
			}
	}
	
	
	private static final String ELEMENT_SELLING_COMPANIES_TD = "MarketVariationPricings";
	private static final String ELEMENT_SELLING_COMPANY_TD = "MarketVariationPricing";
	
	private Set<String> getSellingCompanyCodes(ContentRepository contentRepository)  {

		Set<String> result=Sets.newTreeSet();
		if (contentRepository != null &&			
			StringUtils.isNotBlank(contentRepository.getXmlContentRepository().get(0).getOldTourDepartureXML())) {

			try {

				XMLStreamReader streamReaderTD = XMLInputFactory.newFactory().createXMLStreamReader(new ByteArrayInputStream(contentRepository.getXmlContentRepository().get(0).getOldTourDepartureXML().getBytes(StandardCharsets.UTF_8)));

				do {
					streamReaderTD.next();
				} while (streamReaderTD.hasNext() && !(streamReaderTD.isStartElement() && ELEMENT_SELLING_COMPANIES_TD.equals(streamReaderTD.getLocalName())));

				Unmarshaller jaxbTDUnmarshaller = JAXBContext.newInstance(new Class[] { ArrayOfMarketVariationPricing.class }).createUnmarshaller();

				ArrayOfMarketVariationPricing sellingCompaniesTD = ((JAXBElement<ArrayOfMarketVariationPricing>) jaxbTDUnmarshaller.unmarshal(streamReaderTD, ArrayOfMarketVariationPricing.class)).getValue();

				streamReaderTD.close();
				
				for (MarketVariationPricing sellingCompanyTD : sellingCompaniesTD.getMarketVariationPricing()) {					
					String sellingCompanyCode=sellingCompanyTD.getSellingCompanyCode();	
					result.add(sellingCompanyCode);
				}		
			} catch (JAXBException | XMLStreamException | FactoryConfigurationError e) {
				Throwables.propagate(e);
			}
		}
		return result;
	}
}
