package com.ttc.ch2.cucumber.systemcompare.ch2vshabs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ttc.ch2.bl.departure.habs.tourdeparturegen.HabsTourDepartureAndSC;
import com.ttc.ch2.cucumber.systemcompare.HabsDataVO;
import com.ttc.prodserv.mongo.domain.xmlrepo.AccommodationProduct;
import com.ttc.prodserv.mongo.domain.xmlrepo.Departure;
import com.ttc.prodserv.mongo.domain.xmlrepo.Discount;
import com.ttc.prodserv.mongo.domain.xmlrepo.InsuranceProduct;
import com.ttc.prodserv.mongo.domain.xmlrepo.MiscellaneousProduct;
import com.ttc.prodserv.mongo.domain.xmlrepo.OperatingProduct;
import com.ttc.prodserv.mongo.domain.xmlrepo.PayingAdult;
import com.ttc.prodserv.mongo.domain.xmlrepo.Product;
import com.ttc.prodserv.mongo.domain.xmlrepo.SellingCompany;
import com.ttc.prodserv.mongo.domain.xmlrepo.Surcharge;
import com.ttc.prodserv.mongo.domain.xmlrepo.Tour;
import com.ttc.prodserv.mongo.domain.xmlrepo.Tour.TourType;
import com.wsout.habs.itropicsbuildws.ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO;
import com.wsout.habs.itropicsbuildws.PassengerType;
import com.wsout.habs.itropicsbuildws.ProductType;
import com.wsout.habs.itropicsbuildws.RoomType;
import com.wsout.habs.itropicsbuildws.WsAccommodationProductPassengerVO;
import com.wsout.habs.itropicsbuildws.WsAccommodationProductPassengersVO;
import com.wsout.habs.itropicsbuildws.WsAccommodationProductRoomVO;
import com.wsout.habs.itropicsbuildws.WsAccommodationProductRoomsVO;
import com.wsout.habs.itropicsbuildws.WsAccommodationProductVO;
import com.wsout.habs.itropicsbuildws.WsAccommodationVO;
import com.wsout.habs.itropicsbuildws.WsAddressVO;
import com.wsout.habs.itropicsbuildws.WsAdultChildPriceVO;
import com.wsout.habs.itropicsbuildws.WsAssociatedProductsVO;
import com.wsout.habs.itropicsbuildws.WsCombinedIncludedChargesVO;
import com.wsout.habs.itropicsbuildws.WsDepartureVO;
import com.wsout.habs.itropicsbuildws.WsDeparturesVO;
import com.wsout.habs.itropicsbuildws.WsFoodFundPriceVO;
import com.wsout.habs.itropicsbuildws.WsMandatoryMiscellaneousProductsVO;
import com.wsout.habs.itropicsbuildws.WsMiscellaneousProductVO;
import com.wsout.habs.itropicsbuildws.WsMiscellaneousVO;
import com.wsout.habs.itropicsbuildws.WsOccupancyRuleVO;
import com.wsout.habs.itropicsbuildws.WsPortTaxPriceVO;
import com.wsout.habs.itropicsbuildws.WsPriceVO;
import com.wsout.habs.itropicsbuildws.WsProdDepartureEPDDetails;
import com.wsout.habs.itropicsbuildws.WsRoomPriceVO;
import com.wsout.habs.itropicsbuildws.WsSurchargesVO;
import com.wsout.habs.itropicsbuildws.WsTourRulesPassengerVO;
import com.wsout.habs.itropicsbuildws.WsTourRulesPassengersVO;
import com.wsout.habs.itropicsbuildws.WsTourRulesRoomVO;
import com.wsout.habs.itropicsbuildws.WsTourRulesRoomsVO;
import com.wsout.habs.itropicsbuildws.WsTourRulesVO;
import com.wsout.habs.itropicsbuildws.WsiProductNoteVO;

public class HabsTourDepartureMapper {

	private static final int SCALE = 2;

	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

	private static final String GUARANTEED_STATUS_LABEL_DEF = "DEF";
	private static final String GUARANTEED_STATUS_LABEL_GTT = "GTT";

	private static final String CONTIKI_PREFIX = "CH";

	private static final String TOUR_TYPE_LABEL_CAMPING = "CAMPING";
	private static final String TOUR_TYPE_LABEL_CONCEPT = "CONCEPT";

	private static final String ROOM_TYPE_LABEL_SINGLE = "SIG";
	private static final String ROOM_TYPE_LABEL_TWIN = "TWN";
	private static final String ROOM_TYPE_LABEL_TWIN_SHARE = "TWS";
	private static final String ROOM_TYPE_LABEL_TRIPLE = "TRP";
	private static final String ROOM_TYPE_LABEL_TRIPLE_SHARE = "TRS";
	private static final String ROOM_TYPE_LABEL_QUAD = "QAD";
	private static final String ROOM_TYPE_LABEL_QUAD_SHARE = "QDS";

	private enum DepartureStatus {

		AVAILABLE("AV", "Available"), CLOSED("CL", "Closed"), ONREQUEST("RQ", "OnRequest"), CANCELLED("XX", "Cancelled");

		private final String code;
		private final String name;

		private DepartureStatus(String code, String name) {

			this.code = code;
			this.name = name;
		}

		public static String getFullName(String code) {

			for (DepartureStatus status : values()) {

				if (status.code.equals(code)) {
					return status.getName();
				}
			}

			return null;
		}

		public String getName() {
			return name;
		}
	}

	public static HabsTourDepartureAndSC convert(HabsDataVO habsDataVO) throws DatatypeConfigurationException {

		SellingCompany sellingCompanyVO = habsDataVO.sellingCompany;
		OperatingProduct operatingProductVO = habsDataVO.operatingProduct;
		Tour tourVO = habsDataVO.tour;
		Product productVO = habsDataVO.product;

		WsDeparturesVO departuresVO = new WsDeparturesVO();

		for (Departure srcDepartureVO : productVO.getDepartures().getDeparture()) {

			WsDepartureVO dstDepartureVO = new WsDepartureVO();

			fillDepartureDetails(srcDepartureVO, dstDepartureVO, sellingCompanyVO, tourVO);
			fillNotesDetails(srcDepartureVO, dstDepartureVO, sellingCompanyVO, tourVO);
			fillTourRulesDetails(srcDepartureVO, dstDepartureVO, operatingProductVO, tourVO);
			fillAssociatedProductsDetails(srcDepartureVO, dstDepartureVO);
			fillDiscountsDetails(srcDepartureVO, dstDepartureVO);

			departuresVO.getDeparture().add(dstDepartureVO);
		}

		HabsTourDepartureAndSC habsTourDepartureAndSC = new HabsTourDepartureAndSC();
		habsTourDepartureAndSC.setDepartures(departuresVO);

		return habsTourDepartureAndSC;
	}

	private static void fillDepartureDetails(Departure srcDepartureVO, WsDepartureVO dstDepartureVO, SellingCompany sellingCompanyVO, Tour tourVO) throws DatatypeConfigurationException {

		dstDepartureVO.setDepartureCode(srcDepartureVO.getCode());
		dstDepartureVO.setCurrencyCode(sellingCompanyVO.getCurrencyCode());
		dstDepartureVO.setAvailabilityStatus(DepartureStatus.getFullName(srcDepartureVO.getStatus()));
		dstDepartureVO.setStartDateTime(dateToXMLGregorianCalendar(srcDepartureVO.getStartDate()));
		dstDepartureVO.setEndDateTime(dateToXMLGregorianCalendar(srcDepartureVO.getEndDate()));
		dstDepartureVO.setDateRangeIncludesTravelTime(tourVO.isAirPriceIncluded());
		dstDepartureVO.setPriceIsIndicative(tourVO.getTypeAsEnum() == TourType.TP ? true : srcDepartureVO.getPriceIndicative());
		dstDepartureVO.setOnlineBookable(tourVO.isITropicsSellable());
		dstDepartureVO.setDefiniteDeparture(GUARANTEED_STATUS_LABEL_DEF.equals(srcDepartureVO.getGuaranteedStatus()) || GUARANTEED_STATUS_LABEL_GTT.equals(srcDepartureVO.getGuaranteedStatus()));
		dstDepartureVO.setIsEligibleForFrequentTravellerDiscount(srcDepartureVO.getEligibleForFrequentTravellerDiscount());
		dstDepartureVO.setCanSearchForFlights(tourVO.isAirPriceIncluded());		
		dstDepartureVO.setStartCity(tourVO.getStartLocationCity());
		dstDepartureVO.setEndCity(tourVO.getEndLocationCity());
	}

	private static void fillNotesDetails(Departure srcDepartureVO, WsDepartureVO dstDepartureVO, SellingCompany sellingCompanyVO, Tour tourVO) {

		WsiProductNoteVO noteVO = new WsiProductNoteVO();

		noteVO.setSellingCompany(sellingCompanyVO.getNote());
		noteVO.setTour(tourVO.getNote());
		noteVO.setDeparture(srcDepartureVO.getNote());

		dstDepartureVO.setNotes(noteVO);
	}

	private static void fillTourRulesDetails(Departure srcDepartureVO, WsDepartureVO dstDepartureVO, OperatingProduct operatingProductVO, Tour tourVO) {

		dstDepartureVO.setTourRules(new WsTourRulesVO());

		fillCombinedIncludedChargesDetails(srcDepartureVO, dstDepartureVO.getTourRules());
		fillPassengersDetails(tourVO, dstDepartureVO.getTourRules());
		fillRoomsDetails(srcDepartureVO, operatingProductVO, tourVO, dstDepartureVO.getTourRules());
	}

	private static void fillCombinedIncludedChargesDetails(Departure srcDepartureVO, WsTourRulesVO tourRulesVO) {

		WsCombinedIncludedChargesVO combinedIncludedChargesVO = new WsCombinedIncludedChargesVO();

		if ((srcDepartureVO.getChildPortTax() != null && BigDecimal.ZERO.compareTo(srcDepartureVO.getChildPortTax()) != 0) ||
			(srcDepartureVO.getAdultPortTax() != null && BigDecimal.ZERO.compareTo(srcDepartureVO.getAdultPortTax()) != 0)) {

			WsPriceVO priceVO = new WsPriceVO();
			priceVO.setChild(srcDepartureVO.getChildPortTax() != null && BigDecimal.ZERO.compareTo(srcDepartureVO.getChildPortTax()) != 0 ? srcDepartureVO.getChildPortTax().setScale(SCALE, ROUNDING_MODE) : null);
			priceVO.setAdult(srcDepartureVO.getAdultPortTax() != null && BigDecimal.ZERO.compareTo(srcDepartureVO.getAdultPortTax()) != 0 ? srcDepartureVO.getAdultPortTax().setScale(SCALE, ROUNDING_MODE) : null);

			WsPortTaxPriceVO portTaxPriceVO = new WsPortTaxPriceVO();
			portTaxPriceVO.setPrice(priceVO);

			combinedIncludedChargesVO.setPortTax(portTaxPriceVO);
		}

		if (srcDepartureVO.getFoodFundPrice() != null && BigDecimal.ZERO.compareTo(srcDepartureVO.getFoodFundPrice()) != 0) {

			WsPriceVO priceVO = new WsPriceVO();
			priceVO.setChild(srcDepartureVO.getFoodFundPrice().setScale(SCALE, ROUNDING_MODE));
			priceVO.setAdult(srcDepartureVO.getFoodFundPrice().setScale(SCALE, ROUNDING_MODE));

			WsFoodFundPriceVO foodFundPriceVO = new WsFoodFundPriceVO();
			foodFundPriceVO.setPrice(priceVO);

			combinedIncludedChargesVO.setFoodFund(foodFundPriceVO);
		}

		fillMandatoryMiscellaneousProductsDetails(srcDepartureVO, combinedIncludedChargesVO);
		fillSurchargesDetails(srcDepartureVO, combinedIncludedChargesVO);

		tourRulesVO.setCombinedIncludedCharges(combinedIncludedChargesVO);
	}

	private static void fillMandatoryMiscellaneousProductsDetails(Departure srcDepartureVO, WsCombinedIncludedChargesVO combinedIncludedChargesVO) {

		if (srcDepartureVO.getMiscellaneousProducts() == null) {

			if (srcDepartureVO.getInsuranceProducts() != null) {
				combinedIncludedChargesVO.setMandatoryMiscellaneousProducts(new WsMandatoryMiscellaneousProductsVO());
			}

			return;
		}

		combinedIncludedChargesVO.setMandatoryMiscellaneousProducts(new WsMandatoryMiscellaneousProductsVO());

		for (MiscellaneousProduct srcMiscellaneousProductVO : srcDepartureVO.getMiscellaneousProducts().getMiscellaneousProduct()) {

			if (!srcMiscellaneousProductVO.isMandatory()) {
				continue;
			}

			WsMiscellaneousProductVO dstMiscellaneousProductVO = new WsMiscellaneousProductVO();

			fillMiscellaneousProductDetails(srcMiscellaneousProductVO, dstMiscellaneousProductVO);

			combinedIncludedChargesVO.getMandatoryMiscellaneousProducts().getProduct().add(dstMiscellaneousProductVO);
		}
	}

	private static void fillMiscellaneousProductDetails(MiscellaneousProduct srcMiscellaneousProductVO, WsMiscellaneousProductVO dstMiscellaneousProductVO) {

		WsPriceVO priceVO = new WsPriceVO();
		priceVO.setAdult(srcMiscellaneousProductVO.getAdultPrice() != null ? srcMiscellaneousProductVO.getAdultPrice().setScale(SCALE, ROUNDING_MODE) : null);
		priceVO.setChild(srcMiscellaneousProductVO.getChildPrice() != null ? srcMiscellaneousProductVO.getChildPrice().setScale(SCALE, ROUNDING_MODE) : null);

		dstMiscellaneousProductVO.setProductId(srcMiscellaneousProductVO.getProductId());
		dstMiscellaneousProductVO.setCategory(srcMiscellaneousProductVO.getCategory());
		dstMiscellaneousProductVO.setCode(srcMiscellaneousProductVO.getCode());
		dstMiscellaneousProductVO.setName(srcMiscellaneousProductVO.getName());
		dstMiscellaneousProductVO.setPrice(priceVO);
	}

	private static void fillSurchargesDetails(Departure srcDepartureVO, WsCombinedIncludedChargesVO combinedIncludedChargesVO) {

		combinedIncludedChargesVO.setSurcharges(new WsSurchargesVO());

		if (srcDepartureVO.getSurcharges() == null) {
			return;
		}

		for (Surcharge surchargeVO : srcDepartureVO.getSurcharges().getSurcharge()) {

			WsPriceVO priceVO = new WsPriceVO();

			if (surchargeVO.getAmount() != null) {

				priceVO.setChild(surchargeVO.getAmount().setScale(SCALE, ROUNDING_MODE));
				priceVO.setAdult(surchargeVO.getAmount().setScale(SCALE, ROUNDING_MODE));
			}

			combinedIncludedChargesVO.getSurcharges().getPrice().add(priceVO);
		}
	}

	private static void fillPassengersDetails(Tour tourVO, WsTourRulesVO tourRulesVO) {

		tourRulesVO.setPassengers(new WsTourRulesPassengersVO());

		if (tourVO.isAdultSellableFlag()) {

			WsTourRulesPassengerVO adultPassengerVO = new WsTourRulesPassengerVO();

			adultPassengerVO.setType(PassengerType.ADULT);
			adultPassengerVO.setAgeFrom(tourVO.getAdultAgeFrom());
			adultPassengerVO.setAgeTo(tourVO.getAdultAgeTo());

			tourRulesVO.getPassengers().getPassenger().add(adultPassengerVO);
		}

		if (tourVO.isChildSellableFlag()) {

			WsTourRulesPassengerVO childPassengerVO = new WsTourRulesPassengerVO();

			childPassengerVO.setType(PassengerType.CHILD);
			childPassengerVO.setAgeFrom(tourVO.getChildAgeFrom());
			childPassengerVO.setAgeTo(tourVO.getChildAgeTo());

			tourRulesVO.getPassengers().getPassenger().add(childPassengerVO);
		}
	}

	private static void fillRoomsDetails(Departure srcDepartureVO, OperatingProduct operatingProductVO, Tour tourVO, WsTourRulesVO tourRulesVO) {

		WsTourRulesRoomsVO tourRulesRoomsVO = new WsTourRulesRoomsVO();

		Map<RoomType, Boolean> roomsAvailabilityMap = getRoomsAvailabilityMap(tourVO);
		Map<RoomType, Integer> roomsPayingAdultsMap = getRoomsPayingAdultsMap(operatingProductVO);

		for (RoomType roomType : RoomType.values()) {

			if (roomsAvailabilityMap.get(roomType)) {

				WsOccupancyRuleVO occupancyRuleVO = new WsOccupancyRuleVO();

				WsAdultChildPriceVO childPriceVO = new WsAdultChildPriceVO();
				WsAdultChildPriceVO adultPriceVO = new WsAdultChildPriceVO();

				switch (roomType) {

					case SINGLE:

						occupancyRuleVO.setMaximumAdults(operatingProductVO.getRoomOccuMaxAdultSingle());
						occupancyRuleVO.setMaximumPassengers(operatingProductVO.getRoomOccuMaxPaxSingle());
						occupancyRuleVO.setMinimumAdults(operatingProductVO.getRoomOccuMinAdultSingle());
						occupancyRuleVO.setMinimumPassengers(operatingProductVO.getRoomOccuMinPaxSingle());

						childPriceVO.setBase(srcDepartureVO.getChildSingleRoomPrice());
						childPriceVO.setCombined(srcDepartureVO.getCombinedChildSingleRoomPrice());

						adultPriceVO.setBase(srcDepartureVO.getAdultSingleRoomPrice());
						adultPriceVO.setCombined(srcDepartureVO.getCombinedAdultSingleRoomPrice());

						break;

					case TWIN:

						occupancyRuleVO.setMaximumAdults(operatingProductVO.getRoomOccuMaxAdultTwin() != null && operatingProductVO.getRoomOccuMaxAdultTwin() > 2 ? 2 : operatingProductVO.getRoomOccuMaxAdultTwin());
						occupancyRuleVO.setMaximumPassengers(operatingProductVO.getRoomOccuMaxPaxTwin() != null && operatingProductVO.getRoomOccuMaxPaxTwin() > 2 ? 2 : operatingProductVO.getRoomOccuMaxPaxTwin());
						occupancyRuleVO.setMinimumAdults(operatingProductVO.getRoomOccuMinAdultTwin());
						occupancyRuleVO.setMinimumPassengers(operatingProductVO.getRoomOccuMinPaxTwin());

						childPriceVO.setBase(srcDepartureVO.getChildTwinRoomPrice());
						childPriceVO.setCombined(srcDepartureVO.getCombinedChildTwinRoomPrice());

						adultPriceVO.setBase(srcDepartureVO.getAdultTwinRoomPrice());
						adultPriceVO.setCombined(srcDepartureVO.getCombinedAdultTwinRoomPrice());

						break;

					case TWIN_SHARE:

						occupancyRuleVO.setMaximumAdults(operatingProductVO.getRoomOccuMaxAdultTwinShare() != null && operatingProductVO.getRoomOccuMaxAdultTwinShare() > 2 ? 2 : operatingProductVO.getRoomOccuMaxAdultTwinShare());
						occupancyRuleVO.setMaximumPassengers(operatingProductVO.getRoomOccuMaxPaxTwinShare() != null && operatingProductVO.getRoomOccuMaxPaxTwinShare() > 2 ? 2 : operatingProductVO.getRoomOccuMaxPaxTwinShare());
						occupancyRuleVO.setMinimumAdults(operatingProductVO.getRoomOccuMinAdultTwinShare());
						occupancyRuleVO.setMinimumPassengers(operatingProductVO.getRoomOccuMinPaxTwinShare());

						childPriceVO.setBase(srcDepartureVO.getChildTwinRoomPrice());
						childPriceVO.setCombined(srcDepartureVO.getCombinedChildTwinRoomPrice());

						adultPriceVO.setBase(srcDepartureVO.getAdultTwinRoomPrice());
						adultPriceVO.setCombined(srcDepartureVO.getCombinedAdultTwinRoomPrice());

						break;

					case TRIPLE:

						occupancyRuleVO.setMaximumAdults(operatingProductVO.getRoomOccuMaxAdultTriple());
						occupancyRuleVO.setMaximumPassengers(operatingProductVO.getRoomOccuMaxPaxTriple() != null && operatingProductVO.getRoomOccuMaxPaxTriple() > 3 ? 3 : operatingProductVO.getRoomOccuMaxPaxTriple());
						occupancyRuleVO.setMinimumAdults(operatingProductVO.getRoomOccuMinAdultTriple());
						occupancyRuleVO.setMinimumPassengers(operatingProductVO.getRoomOccuMinPaxTriple());

						childPriceVO.setBase(srcDepartureVO.getChildTripleRoomPrice());
						childPriceVO.setCombined(srcDepartureVO.getCombinedChildTripleRoomPrice());

						adultPriceVO.setBase(srcDepartureVO.getAdultTripleRoomPrice());
						adultPriceVO.setCombined(srcDepartureVO.getCombinedAdultTripleRoomPrice());

						break;

					case TRIPLE_SHARE:

						occupancyRuleVO.setMaximumAdults(operatingProductVO.getRoomOccuMaxAdultTripleShare());
						occupancyRuleVO.setMaximumPassengers(operatingProductVO.getRoomOccuMaxPaxTripleShare() != null && operatingProductVO.getRoomOccuMaxPaxTripleShare() > 3 ? 3 : operatingProductVO.getRoomOccuMaxPaxTripleShare());
						occupancyRuleVO.setMinimumAdults(operatingProductVO.getRoomOccuMinAdultTripleShare());
						occupancyRuleVO.setMinimumPassengers(operatingProductVO.getRoomOccuMinPaxTripleShare());

						childPriceVO.setBase(srcDepartureVO.getChildTripleRoomPrice());
						childPriceVO.setCombined(srcDepartureVO.getCombinedChildTripleRoomPrice());

						adultPriceVO.setBase(srcDepartureVO.getAdultTripleRoomPrice());
						adultPriceVO.setCombined(srcDepartureVO.getCombinedAdultTripleRoomPrice());

						break;

					case QUAD:

						occupancyRuleVO.setMaximumAdults(operatingProductVO.getRoomOccuMaxAdultQuad());
						occupancyRuleVO.setMaximumPassengers(operatingProductVO.getRoomOccuMaxPaxQuad());
						occupancyRuleVO.setMinimumAdults(operatingProductVO.getRoomOccuMinAdultQuad());
						occupancyRuleVO.setMinimumPassengers(operatingProductVO.getRoomOccuMinPaxQuad());

						childPriceVO.setBase(srcDepartureVO.getChildQuadRoomPrice());
						childPriceVO.setCombined(srcDepartureVO.getCombinedChildQuadRoomPrice());

						adultPriceVO.setBase(srcDepartureVO.getAdultQuadRoomPrice());
						adultPriceVO.setCombined(srcDepartureVO.getCombinedAdultQuadRoomPrice());

						break;

					case QUAD_SHARE:

						occupancyRuleVO.setMaximumAdults(operatingProductVO.getRoomOccuMaxAdultQuadShare());
						occupancyRuleVO.setMaximumPassengers(operatingProductVO.getRoomOccuMaxPaxQuadShare());
						occupancyRuleVO.setMinimumAdults(operatingProductVO.getRoomOccuMinAdultQuadShare());
						occupancyRuleVO.setMinimumPassengers(operatingProductVO.getRoomOccuMinPaxQuadShare());

						childPriceVO.setBase(srcDepartureVO.getChildQuadRoomPrice());
						childPriceVO.setCombined(srcDepartureVO.getCombinedChildQuadRoomPrice());

						adultPriceVO.setBase(srcDepartureVO.getAdultQuadRoomPrice());
						adultPriceVO.setCombined(srcDepartureVO.getCombinedAdultQuadRoomPrice());

						break;

					default:
						break;

				}

				occupancyRuleVO.setMinimumPayingAdults(roomsPayingAdultsMap.containsKey(roomType) ? roomsPayingAdultsMap.get(roomType) : occupancyRuleVO.getMinimumAdults());

				if (childPriceVO.getBase() == null) { childPriceVO.setBase(BigDecimal.ZERO); }
				if (childPriceVO.getCombined() == null) { childPriceVO.setCombined(BigDecimal.ZERO); }
				if (adultPriceVO.getBase() == null) { adultPriceVO.setBase(BigDecimal.ZERO); }
				if (adultPriceVO.getCombined() == null) { adultPriceVO.setCombined(BigDecimal.ZERO); }

				childPriceVO.setBase(childPriceVO.getBase().setScale(SCALE, ROUNDING_MODE));
				childPriceVO.setCombined(childPriceVO.getCombined().setScale(SCALE, ROUNDING_MODE));
				adultPriceVO.setBase(adultPriceVO.getBase().setScale(SCALE, ROUNDING_MODE));
				adultPriceVO.setCombined(adultPriceVO.getCombined().setScale(SCALE, ROUNDING_MODE));

				WsRoomPriceVO roomPriceVO = new WsRoomPriceVO();
				roomPriceVO.setChild(childPriceVO);
				roomPriceVO.setAdult(adultPriceVO);

				WsTourRulesRoomVO tourRulesRoomVO = new WsTourRulesRoomVO();
				tourRulesRoomVO.setOccupancyRule(occupancyRuleVO);
				tourRulesRoomVO.setPrice(roomPriceVO);
				tourRulesRoomVO.setType(roomType);

				tourRulesRoomsVO.getRoom().add(tourRulesRoomVO);
			}
		}

		tourRulesVO.setRooms(tourRulesRoomsVO);
	}

	private static void fillAssociatedProductsDetails(Departure srcDepartureVO, WsDepartureVO dstDepartureVO) {

		if (srcDepartureVO.getAccommodationProducts() == null && srcDepartureVO.getMiscellaneousProducts() == null && srcDepartureVO.getInsuranceProducts() == null) {
			return;
		}

		dstDepartureVO.setAssociatedProducts(new WsAssociatedProductsVO());

		fillAccommodationProductsDetails(srcDepartureVO, dstDepartureVO);
		fillMiscellaneousProductsDetails(srcDepartureVO, dstDepartureVO);
		fillInsuranceProductsDetails(srcDepartureVO, dstDepartureVO);
	}

	private static void fillAccommodationProductsDetails(Departure srcDepartureVO, WsDepartureVO dstDepartureVO) {

		if (srcDepartureVO.getAccommodationProducts() == null) {
			return;
		}

		dstDepartureVO.getAssociatedProducts().setAccommodation(new WsAccommodationVO());

		for (AccommodationProduct srcAccommodationProductVO : srcDepartureVO.getAccommodationProducts().getAccommodationProduct()) {

			WsAccommodationProductRoomsVO roomsVO = new WsAccommodationProductRoomsVO();

			if (srcAccommodationProductVO.isSingleRoomAvailable()) { fillAccommodationProductRoomDetails(roomsVO, RoomType.SINGLE, srcAccommodationProductVO.getSingleRoomPrice()); }
			if (srcAccommodationProductVO.isTwinRoomAvailable()) { fillAccommodationProductRoomDetails(roomsVO, RoomType.TWIN, srcAccommodationProductVO.getTwinRoomPrice()); }
			if (srcAccommodationProductVO.isTwinShareRoomAvailable()) { fillAccommodationProductRoomDetails(roomsVO, RoomType.TWIN_SHARE, srcAccommodationProductVO.getTwinRoomPrice()); }
			if (srcAccommodationProductVO.isTripleRoomAvailable()) { fillAccommodationProductRoomDetails(roomsVO, RoomType.TRIPLE, srcAccommodationProductVO.getTripleRoomPrice()); }
			if (srcAccommodationProductVO.isTripleShareRoomAvailable()) { fillAccommodationProductRoomDetails(roomsVO, RoomType.TRIPLE_SHARE, srcAccommodationProductVO.getTripleRoomPrice()); }
			if (srcAccommodationProductVO.isQuadRoomAvailable()) { fillAccommodationProductRoomDetails(roomsVO, RoomType.QUAD, srcAccommodationProductVO.getQuadRoomPrice()); }
			if (srcAccommodationProductVO.isQuadShareRoomAvailable()) { fillAccommodationProductRoomDetails(roomsVO, RoomType.QUAD_SHARE, srcAccommodationProductVO.getQuadRoomPrice()); }

			WsAddressVO addressVO = new WsAddressVO();
			addressVO.setLine1(srcAccommodationProductVO.getAddress().getAddressline1());
			addressVO.setLine2(srcAccommodationProductVO.getAddress().getAddressline2());
			addressVO.setLine3(srcAccommodationProductVO.getAddress().getAddressline3());
			addressVO.setCity(srcAccommodationProductVO.getAddress().getCityName());
			addressVO.setRegion(srcAccommodationProductVO.getAddress().getRegionName());
			addressVO.setPostCode(srcAccommodationProductVO.getAddress().getZipCode());
			addressVO.setCountry(srcAccommodationProductVO.getAddress().getCountryName());

			WsAccommodationProductVO dstAccommodationProductVO = new WsAccommodationProductVO();
			dstAccommodationProductVO.setType(srcAccommodationProductVO.isPreAccommodation() ? ProductType.PRE_ACCOMMODATION_PRODUCT : ProductType.POST_ACCOMMODATION_PRODUCT);
			dstAccommodationProductVO.setCode(srcAccommodationProductVO.getCode());
			dstAccommodationProductVO.setName(srcAccommodationProductVO.getName());
			dstAccommodationProductVO.setAddress(addressVO);
			dstAccommodationProductVO.setRooms(roomsVO);

			dstDepartureVO.getAssociatedProducts().getAccommodation().getAccommodationProduct().add(dstAccommodationProductVO);
		}
	}

	private static void fillAccommodationProductRoomDetails(WsAccommodationProductRoomsVO roomsVO, RoomType roomType, BigDecimal roomPrice) {

		if (roomPrice != null) {
			roomPrice = roomPrice.setScale(SCALE, ROUNDING_MODE);
		}

		WsAccommodationProductPassengerVO adultPassengerVO = new WsAccommodationProductPassengerVO();
		adultPassengerVO.setType(PassengerType.ADULT);
		adultPassengerVO.setPrice(roomPrice);

		WsAccommodationProductPassengerVO childPassengerVO = new WsAccommodationProductPassengerVO();
		childPassengerVO.setType(PassengerType.CHILD);
		childPassengerVO.setPrice(roomPrice);

		WsAccommodationProductPassengersVO passengersVO = new WsAccommodationProductPassengersVO();
		passengersVO.getPassenger().add(adultPassengerVO);
		passengersVO.getPassenger().add(childPassengerVO);

		WsAccommodationProductRoomVO roomVO = new WsAccommodationProductRoomVO();
		roomVO.setType(roomType);
		roomVO.setPassengers(passengersVO);

		roomsVO.getRoom().add(roomVO);
	}

	private static void fillMiscellaneousProductsDetails(Departure srcDepartureVO, WsDepartureVO dstDepartureVO) {

		if (srcDepartureVO.getMiscellaneousProducts() == null) {
			return;
		}

		dstDepartureVO.getAssociatedProducts().setMiscellaneous(new WsMiscellaneousVO());

		for (MiscellaneousProduct srcMiscellaneousProductVO : srcDepartureVO.getMiscellaneousProducts().getMiscellaneousProduct()) {

			if (srcMiscellaneousProductVO.isMandatory() || checkForAAABenefit(srcMiscellaneousProductVO)) {
				continue;
			}

			WsMiscellaneousProductVO dstMiscellaneousProductVO = new WsMiscellaneousProductVO();

			fillMiscellaneousProductDetails(srcMiscellaneousProductVO, dstMiscellaneousProductVO);

			dstDepartureVO.getAssociatedProducts().getMiscellaneous().getMiscellaneousProduct().add(dstMiscellaneousProductVO);
		}
	}

	private static void fillInsuranceProductsDetails(Departure srcDepartureVO, WsDepartureVO dstDepartureVO) {

		if (srcDepartureVO.getInsuranceProducts() == null) {
			return;
		}

		if (dstDepartureVO.getAssociatedProducts().getMiscellaneous() == null) {
			dstDepartureVO.getAssociatedProducts().setMiscellaneous(new WsMiscellaneousVO());
		}

		for (InsuranceProduct srcInsuranceProductVO : srcDepartureVO.getInsuranceProducts().getInsuranceProduct()) {

			WsPriceVO priceVO = new WsPriceVO();

			if (srcInsuranceProductVO.getPrice() != null) {

				priceVO.setChild(srcInsuranceProductVO.getPrice().setScale(SCALE, ROUNDING_MODE));
				priceVO.setAdult(srcInsuranceProductVO.getPrice().setScale(SCALE, ROUNDING_MODE));
			}

			WsMiscellaneousProductVO dstInsuranceProductVO = new WsMiscellaneousProductVO();

			dstInsuranceProductVO.setCode(srcInsuranceProductVO.getCode());
			dstInsuranceProductVO.setName(srcInsuranceProductVO.getName());
			dstInsuranceProductVO.setCategory(srcInsuranceProductVO.getCategory());
			dstInsuranceProductVO.setPrice(priceVO);

			dstDepartureVO.getAssociatedProducts().getMiscellaneous().getMiscellaneousProduct().add(dstInsuranceProductVO);
		}
	}

	private static void fillDiscountsDetails(Departure srcDepartureVO, WsDepartureVO dstDepartureVO) throws DatatypeConfigurationException {

		if (srcDepartureVO.getDiscounts() == null) {
			return;
		}

		dstDepartureVO.setDiscounts(new WsProdDepartureEPDDetails());

		for (Discount srcDiscountVO : srcDepartureVO.getDiscounts().getDiscountsList()) {

			ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO dstDiscountVO = new ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO();

			dstDiscountVO.setDiscountId(srcDiscountVO.getDiscountId());
			dstDiscountVO.setPaymentDate(dateToXMLGregorianCalendar(srcDiscountVO.getPaymentDate()));
			dstDiscountVO.setAmount(srcDiscountVO.getAmount().floatValue());
			dstDiscountVO.setPercentage(srcDiscountVO.isPercentage());

			dstDepartureVO.getDiscounts().getWsEPDDetailsVOArray().add(dstDiscountVO);
		}
	}

	private static Map<RoomType, Boolean> getRoomsAvailabilityMap(Tour tourVO) {

		Map<RoomType, Boolean> roomsAvailabilityMap = new HashMap<RoomType, Boolean>();

		if (tourVO.getSellingCompanyCode().startsWith(CONTIKI_PREFIX) && TOUR_TYPE_LABEL_CAMPING.equals(tourVO.getStyle())) {

			roomsAvailabilityMap.put(RoomType.SINGLE, tourVO.isSellableSingleRoomFlag());
			roomsAvailabilityMap.put(RoomType.TWIN, tourVO.isSellableTwinRoomFlag());
			roomsAvailabilityMap.put(RoomType.TWIN_SHARE, tourVO.isSellableTwinShareRoomFlag());
			roomsAvailabilityMap.put(RoomType.TRIPLE, false);
			roomsAvailabilityMap.put(RoomType.TRIPLE_SHARE, false);
			roomsAvailabilityMap.put(RoomType.QUAD, false);
			roomsAvailabilityMap.put(RoomType.QUAD_SHARE, false);

		} else if (tourVO.getSellingCompanyCode().startsWith(CONTIKI_PREFIX) && TOUR_TYPE_LABEL_CONCEPT.equals(tourVO.getStyle())) {

			roomsAvailabilityMap.put(RoomType.SINGLE, false);
			roomsAvailabilityMap.put(RoomType.TWIN, false);
			roomsAvailabilityMap.put(RoomType.TWIN_SHARE, false);
			roomsAvailabilityMap.put(RoomType.TRIPLE, false);
			roomsAvailabilityMap.put(RoomType.TRIPLE_SHARE, false);
			roomsAvailabilityMap.put(RoomType.QUAD, false);
			roomsAvailabilityMap.put(RoomType.QUAD_SHARE, tourVO.isSellableQuadShareRoomFlag());

		} else {

			roomsAvailabilityMap.put(RoomType.SINGLE, tourVO.isSellableSingleRoomFlag());
			roomsAvailabilityMap.put(RoomType.TWIN, tourVO.isSellableTwinRoomFlag());
			roomsAvailabilityMap.put(RoomType.TWIN_SHARE, tourVO.isSellableTwinShareRoomFlag());
			roomsAvailabilityMap.put(RoomType.TRIPLE, tourVO.isSellableTripleRoomFlag());
			roomsAvailabilityMap.put(RoomType.TRIPLE_SHARE, tourVO.isSellableTripleShareRoomFlag());
			roomsAvailabilityMap.put(RoomType.QUAD, tourVO.isSellableQuadRoomFlag());
			roomsAvailabilityMap.put(RoomType.QUAD_SHARE, tourVO.isSellableQuadShareRoomFlag());
		}

		return roomsAvailabilityMap;
	}

	private static Map<RoomType, Integer> getRoomsPayingAdultsMap(OperatingProduct operatingProductVO) {

		Map<RoomType, Integer> roomsPayingAdultsMap = new HashMap<RoomType, Integer>();

		if (operatingProductVO.getPayingAdults() != null) {

			for (PayingAdult payingAdult : operatingProductVO.getPayingAdults().getPayingAdult()) {

				switch (payingAdult.getRoomTypeCode()) {

					case ROOM_TYPE_LABEL_SINGLE:

						roomsPayingAdultsMap.put(RoomType.SINGLE, payingAdult.getMinNoPayingAdults());
						break;

					case ROOM_TYPE_LABEL_TWIN:

						roomsPayingAdultsMap.put(RoomType.TWIN, payingAdult.getMinNoPayingAdults());
						break;

					case ROOM_TYPE_LABEL_TWIN_SHARE:

						roomsPayingAdultsMap.put(RoomType.TWIN_SHARE, payingAdult.getMinNoPayingAdults());
						break;

					case ROOM_TYPE_LABEL_TRIPLE:

						roomsPayingAdultsMap.put(RoomType.TRIPLE, payingAdult.getMinNoPayingAdults());
						break;

					case ROOM_TYPE_LABEL_TRIPLE_SHARE:

						roomsPayingAdultsMap.put(RoomType.TRIPLE_SHARE, payingAdult.getMinNoPayingAdults());
						break;

					case ROOM_TYPE_LABEL_QUAD:

						roomsPayingAdultsMap.put(RoomType.QUAD, payingAdult.getMinNoPayingAdults());
						break;

					case ROOM_TYPE_LABEL_QUAD_SHARE:
	
						roomsPayingAdultsMap.put(RoomType.QUAD_SHARE, payingAdult.getMinNoPayingAdults());
						break;
				}
			}
		}

		return roomsPayingAdultsMap;
	}

	private static boolean checkForAAABenefit(MiscellaneousProduct miscellaneousProductVO) {
		return miscellaneousProductVO.isBenefit() && miscellaneousProductVO.getCategory() != null && miscellaneousProductVO.getCategory().contains("AAA");
	}

	private static XMLGregorianCalendar dateToXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);

		return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
	}
}
