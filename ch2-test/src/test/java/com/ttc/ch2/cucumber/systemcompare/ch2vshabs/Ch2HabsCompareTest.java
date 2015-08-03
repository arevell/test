package com.ttc.ch2.cucumber.systemcompare.ch2vshabs;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.google.common.base.Joiner;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.cucumber.SpringHelper;
import com.ttc.ch2.cucumber.systemcompare.BaseCompareTest;
import com.ttc.ch2.cucumber.systemcompare.HabsDataVO;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.prodserv.mongo.domain.UniqueIdHelper;
import com.ttc.prodserv.mongo.domain.xmlrepo.OperatingProduct;
import com.ttc.prodserv.mongo.domain.xmlrepo.Product;
import com.ttc.prodserv.mongo.domain.xmlrepo.Tour;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Ch2HabsCompareTest extends BaseCompareTest {

	private static final Logger logger = LoggerFactory.getLogger(Ch2HabsCompareTest.class);

	static {
		SpringHelper.applicationContext.getBean(SellingCompanyDAO.class);
	}

	@Given("^selling companies:(.*)")
	public void setSellingCompanyCode(String scCode) {
		setupSellingCompany(scCode);
	}

	@Given("^percent for data:(.*)")
	public void setPercent(String percent) {
		setupPercent(percent);
	}
	
	@And("^setting property (.*) as binding for field (.*)")
	public void set_binding_property_for_field(String propertyName, String fieldName) {
		bindingMap.put(fieldName, propertyName);
	}

	@Given("^appropriately configured test environment$")
	public void prepare() {
		prepareDataBasedOnCh2();
	}

	@When("^the compare process is invoked$")
	public void execute() throws IOException, InterruptedException, JAXBException {

		ContentRepositoryService crService = SpringHelper.applicationContext.getBean(ContentRepositoryService.class);
		ContentRepositoryDAO crDAO = SpringHelper.applicationContext.getBean(ContentRepositoryDAO.class);
		MongoTemplate mongoOperation = SpringHelper.applicationContext.getBean(MongoTemplate.class);
		JAXBContext jaxbContextV3 = JAXBContext.newInstance(TourDeparturesType.class);

		for (String code : choosedTour) {

			TourDeparturesType ch2Data = findChData(code, crDAO,crService, jaxbContextV3);
			HabsDataVO habsDataVO = findHabsData(code, mongoOperation);
			
			if (habsDataVO != null) {
				compare(ch2Data, habsDataVO);
			}
		}
	}

	// check conditions
	@Then("^the result is checked and possible differences are presented$")
	public void checkResults() throws Throwable {
		Assert.assertFalse("Found differences\n" + Joiner.on("\n").join(compareResults), compareResults.size() > 0);
	}

	private HabsDataVO findHabsData(String tourCode, MongoTemplate mongoOperation) {

		HabsDataVO result = new HabsDataVO();

		Product product = mongoOperation.findById(UniqueIdHelper.getDatesAndRatesId(tourCode, sellingCompanyCode), Product.class);

		if (product == null) {
			compareResults.add(String.format("Domain data:DatesAndRates not found in habs - tourCode %s, selling comapany %s", tourCode, sellingCompanyCode));
			return null;
		}

		result.product = product;

		Tour tour = mongoOperation.findById(UniqueIdHelper.getTourId(tourCode, sellingCompanyCode), Tour.class);

		if (tour == null) {
			compareResults.add(String.format("Domain data:Tour not found in habs - tourCode %s, selling comapany %s", tourCode, sellingCompanyCode));
			return null;
		}

		result.tour = tour;
		result.operatingProduct = mongoOperation.findById(String.valueOf(tour.getOperatingProductId()), OperatingProduct.class);
		result.sellingCompany = mongoOperation.findById(String.valueOf(tour.getSellingCompanyId()), com.ttc.prodserv.mongo.domain.xmlrepo.SellingCompany.class);

		if (result.operatingProduct == null) {
			compareResults.add(String.format("Domain data:OperatingProduct not found in habs - tourCode %s, selling comapany %s, opId %s", tourCode, sellingCompanyCode, tour.getOperatingProductId()));
			return null;
		}

		if (result.sellingCompany == null) {
			compareResults.add(String.format("Domain data:SellingCompany not found in habs - tourCode %s, selling comapany %s, scId %s", tourCode, sellingCompanyCode, tour.getSellingCompanyId()));
			return null;
		}

		return result;
	}

	private TourDeparturesType findChData(String tourCode, ContentRepositoryDAO crDao, ContentRepositoryService crService, JAXBContext jaxbContextV3) {

		ContentRepository cr=null;
		try{
			cr = crService.findByTourCodeWithXML(tourCode, brandCode);
	
			Assert.assertNotNull("ContentRepository not exist", cr);
			Assert.assertNotNull("Xml Data not exist", cr.getXmlContentRepository());
			Assert.assertNotNull("Xml Data not exist", cr.getXmlContentRepository().get(0));
			Assert.assertTrue("Xml Data not exist", StringUtils.isNotEmpty(cr.getXmlContentRepository().get(0).getTourDepartureXML()));
	
			TourDeparturesType tourData = getTourDeparturesTypeV3(cr.getTourCode(), cr.getXmlContentRepository().get(0).getTourDepartureXML(), jaxbContextV3);
			
			if (tourData.getSellingCompanies() != null) {
				for (int i = 0; i < tourData.getSellingCompanies().getSellingCompany().size(); i++) {
					if (!this.sellingCompanyCode.equals(tourData.getSellingCompanies().getSellingCompany().get(i).getCode())) {
						tourData.getSellingCompanies().getSellingCompany().remove(i);
						i--;
					}
				}
			}
			
			return tourData;
		}
		finally{
			if(cr!=null){
				crDao.evictEntity(cr);
			}
		}
	}

}