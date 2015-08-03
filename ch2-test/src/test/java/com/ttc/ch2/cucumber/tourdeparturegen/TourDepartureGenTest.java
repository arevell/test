package com.ttc.ch2.cucumber.tourdeparturegen;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.ttc.ch2.bl.departure.common.tourdeparturegen.ITropicsV1TourDepartureMapper;
import com.ttc.ch2.bl.departure.common.tourdeparturegen.StringWriterDepartureDataConsumer;
import com.ttc.ch2.bl.departure.common.tourdeparturegen.TourDepartureAndSC;
import com.ttc.ch2.common.SchemaParam;
import com.ttc.ch2.cucumber.CcapiCucumberHelper;
import com.ttc.ch2.domain.SellingCompany;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationDepartureInfo;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import facade.itropics.webservice.tropics.com.itropicsbuildws.ITropicsBuild;
import facade.itropics.webservice.tropics.com.itropicsbuildws.ITropicsBuildWS;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsDeparturesVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsInGetTourDatesAndRatesVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsInGetToursVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsProductVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsToursVO;

public class TourDepartureGenTest {

	
	private static String webServiceEndpoint=null;
	static
	{
		webServiceEndpoint = CcapiCucumberHelper.tropicEndpoint;
	}
	
	private int numberOfTours;	
	private List<String> tourCodeList;
	private List<String> sellingCompaniesCodes;
	private ITropicsBuildWS tropicsBuildWS;
	private Map<String, WsToursVO> responseToursMap;
	private List<TourDepartureAndSC> tourDeparturesAndSCList;
	private List<MarketVariationDepartureInfo> mappingResult;

	@Given("^selling companies with codes (.*)")
	public void set_selling_companies_codes(String sellingCompaniesCodes) {

		this.sellingCompaniesCodes = new ArrayList<String>();

		for (String sellingCompanyCode : sellingCompaniesCodes.split(",")) {
			this.sellingCompaniesCodes.add(sellingCompanyCode.trim());
		}
	}

	@And("^number of tours equal (.*)")
	public void set_number_of_tours(String numberOfTours) {
		this.numberOfTours = Integer.parseInt(numberOfTours);
	}

	@When("^call the web service for tours data")
	public void call_the_web_service_for_tours_data() throws Exception {

		ITropicsBuild service = new ITropicsBuild(new URL(webServiceEndpoint + "?wsdl"),
												  new QName("http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd", "ITropicsBuild"));

		tropicsBuildWS = service.getITropicsBuildWSPort();
		responseToursMap = new HashMap<String, WsToursVO>();

		for (String sellingCompanyCode : sellingCompaniesCodes) {

			WsInGetToursVO requestTours = new WsInGetToursVO();
			requestTours.setSellingCompanyCode(sellingCompanyCode);

			responseToursMap.put(sellingCompanyCode, tropicsBuildWS.getTours(requestTours));
		}
	}

	@Then("^valid tours data are returned")
	public void test_if_valid_tour_data_are_returned() {

		tourCodeList = new ArrayList<String>();

		for (WsProductVO product : responseToursMap.get(sellingCompaniesCodes.get(0)).getProduct()) {

			boolean found = sellingCompaniesCodes.size() == 1 ? true : false;

			for (int i = 1; i < sellingCompaniesCodes.size(); i++) {

				if (found = getProduct(sellingCompaniesCodes.get(i), product.getCode()) != null) {
					break;
				}
			}

			if (found) {

				tourCodeList.add(product.getCode());

				if (tourCodeList.size() == numberOfTours) {
					break;
				}
			}
		}

		assertTrue("Cannot find enough tours data.", tourCodeList.size() == numberOfTours);
	}

	@When("^call the web service for departures data")
	public void call_the_web_service_for_departures_data() throws Exception {

		tourDeparturesAndSCList = new ArrayList<TourDepartureAndSC>();

		for (String tourCode : tourCodeList) {

			for (String sellingCompanyCode : sellingCompaniesCodes) {

				WsInGetTourDatesAndRatesVO requestDepartures = new WsInGetTourDatesAndRatesVO();
				requestDepartures.setSellingCompanyCode(sellingCompanyCode);
				requestDepartures.setTourCode(tourCode);
				WsDeparturesVO responseDepartures = tropicsBuildWS.getTourDatesAndRates(requestDepartures);

				SellingCompany sellingCompany = new SellingCompany();
				sellingCompany.setCode(sellingCompanyCode);

				TourDepartureAndSC tourDepartureAndSC = new TourDepartureAndSC();
				tourDepartureAndSC.setSellingCompany(sellingCompany);
				tourDepartureAndSC.setProduct(getProduct(sellingCompanyCode, tourCode));
				tourDepartureAndSC.setDepartures(responseDepartures);

				tourDeparturesAndSCList.add(tourDepartureAndSC);
			}
		}

		for (int i = 0; i < tourDeparturesAndSCList.size(); i++) {

			for (int j = 0; j < tourDeparturesAndSCList.size(); j++) {

				if (i != j && tourDeparturesAndSCList.get(i).getProduct().getCode().equals(tourDeparturesAndSCList.get(j).getProduct().getCode())) {

					List<TourDepartureAndSC> references = tourDeparturesAndSCList.get(i).getOtherReferences();

					if (references == null) {
						references = new ArrayList<TourDepartureAndSC>();
						tourDeparturesAndSCList.get(i).setOtherReferences(references);
					}

					references.add(tourDeparturesAndSCList.get(j));
				}
			}
		}
	}

	@Then("^valid departures data are returned")
	public void test_if_valid_departures_data_are_returned() {

		for (TourDepartureAndSC tourDepartureSrc : tourDeparturesAndSCList) {

			assertTrue("Cannot find departures data for a given tour.", tourDepartureSrc.getDepartures() != null &&
																		tourDepartureSrc.getDepartures().getDeparture() != null &&
																		tourDepartureSrc.getDepartures().getDeparture().size() > 0);
		}
	}

	@And("^backwards Mapping of Tour Departures succeed")
	public void test_if_mapping_succeed() throws Exception {

		mappingResult = new ArrayList<MarketVariationDepartureInfo>();

		for (TourDepartureAndSC tourDepartureSrc : tourDeparturesAndSCList) {

			if (tourDepartureSrc.getIsUsed() != null && tourDepartureSrc.getIsUsed()) {
				continue;
			}

			MarketVariationDepartureInfo tourDepartureDst = new MarketVariationDepartureInfo();

			Method method; 

			method = ITropicsV1TourDepartureMapper.class.getDeclaredMethod("copyProductData", tourDepartureSrc.getClass(), tourDepartureDst.getClass());
			method.setAccessible(true);
			method.invoke(new ITropicsV1TourDepartureMapper(), tourDepartureSrc, tourDepartureDst);

			method = ITropicsV1TourDepartureMapper.class.getDeclaredMethod("copyProductPricingsData", tourDepartureSrc.getClass(), tourDepartureDst.getClass());
			method.setAccessible(true);
			method.invoke(new ITropicsV1TourDepartureMapper(), tourDepartureSrc, tourDepartureDst);

			method = ITropicsV1TourDepartureMapper.class.getDeclaredMethod("copyDeparturesData", tourDepartureSrc.getClass(), tourDepartureDst.getClass());
			method.setAccessible(true);
			method.invoke(new ITropicsV1TourDepartureMapper(), tourDepartureSrc, tourDepartureDst);

			method = ITropicsV1TourDepartureMapper.class.getDeclaredMethod("copyDeparturesPricingsData", tourDepartureSrc.getClass(), tourDepartureDst.getClass());
			method.setAccessible(true);
			method.invoke(new ITropicsV1TourDepartureMapper(), tourDepartureSrc, tourDepartureDst);

			mappingResult.add(tourDepartureDst);
		}

		assertTrue("error", mappingResult != null && mappingResult.size() > 0);
	}

	@And("^save to file succeed for a given (.*)")
	public void test_if_save_succeed(String path) throws Exception {

		StringWriter writer = new StringWriter();

		JAXBContext myJAXBContext = JAXBContext.newInstance(SchemaParam.TD_1_1_0.getJaxbContextPath());
		Schema schema = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(StringWriterDepartureDataConsumer.class.getResource(SchemaParam.TD_1_1_0.getSchemaPath()));

		Marshaller marshaller = myJAXBContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setSchema(schema);

		for (MarketVariationDepartureInfo mappingItem : mappingResult) {

			marshaller.marshal(new JAXBElement<MarketVariationDepartureInfo>(new QName("http://www.ttsl.com/MarketVariationDepartureInfo/2010/09/1.1", "MarketVariationDepartureInfo"),
					MarketVariationDepartureInfo.class, mappingItem), writer);

			String xmlResult = writer.getBuffer().toString();

			File file = new File(path + mappingItem.getMarketVariationCode() + ".xml");

			if (!file.exists()) {
				file.createNewFile();
			}

			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));

			fileWriter.write(xmlResult);
			fileWriter.close();
		}
	}

	private WsProductVO getProduct(String sellingCompanyCode, String tourCode) {

		for (WsProductVO productInt : responseToursMap.get(sellingCompanyCode).getProduct()) {

			if (tourCode.equals(productInt.getCode())) {
				return productInt;
			}
		}

		return null;
	}
}
