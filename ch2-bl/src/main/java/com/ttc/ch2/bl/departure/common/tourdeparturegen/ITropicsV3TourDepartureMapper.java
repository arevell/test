package com.ttc.ch2.bl.departure.common.tourdeparturegen;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Iterables;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.AccommodationProductType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.AccommodationProductTypeCodeType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.AccommodationProductsType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.AddressType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.AssociatedProductsType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.AvailabilityStatusType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.CombinedIncludedChargesType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.DepartureType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.DeparturesType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.DiscountType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.DiscountsType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.FoodFundType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.MandatoryMiscellaneousProductsType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.MiscellaneousProductType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.MiscellaneousProductsType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.Notes;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.ObjectFactory;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.OccupancyRuleType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.PassengerType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.PassengerTypeType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.PassengersType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.PortTaxType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.PriceType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.ProductRoomType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.ProductRoomsType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.RoomPriceType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.RoomPriceType.Adult;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.RoomPriceType.Child;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.RoomType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.RoomTypeCodeType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.RoomsType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.SellingCompaniesType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.SellingCompanyType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.SurchargeType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.SurchargesType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourRulesType;
import com.ttc.ch2.bl.departure.common.LogOperationHelper;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;

import facade.itropics.webservice.tropics.com.itropicsbuildws.ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.ProductType;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsAccommodationProductPassengerVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsAccommodationProductRoomVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsAccommodationProductVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsAssociatedProductsVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsCombinedIncludedChargesVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsDepartureVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsDeparturesVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsMiscellaneousProductVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsPriceVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsProdDepartureEPDDetails;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsTourRulesPassengerVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsTourRulesPassengersVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsTourRulesRoomVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsTourRulesRoomsVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsTourRulesVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsiProductNoteVO;

public class ITropicsV3TourDepartureMapper {

	private static final Logger logger = LoggerFactory.getLogger(ITropicsV3TourDepartureMapper.class);

	private static String NEW_LINE = "\r\n";
	private static String TEMPLATE_DELIMITER = "((?<=%1$s)|(?=%1$s))";

	private static BigDecimal VALUE_ZERO = new BigDecimal(0).setScale(2);

	private static ObjectFactory objectFactory = new ObjectFactory();

	public static TourDeparturesType map(OperationStatus opStatus, TourDepartureAndSC tourDepartureAndSC) {

		if (tourDepartureAndSC == null) {
			return null;
		}

		List<String> sellingCompaniesCodes = new ArrayList<String>();

		for (TourDepartureAndSC tourDepartureItem : Iterables.concat(Arrays.asList(tourDepartureAndSC), tourDepartureAndSC.getOtherReferences())) {
			sellingCompaniesCodes.add(tourDepartureItem.getSellingCompany().getCode());
		}

		LogOperationHelper.logMessageForProduct(logger, opStatus,
				tourDepartureAndSC.getProduct().getCode(),
				TropicSynchronizeMessages.START_MAPPING_VERSION3,
				tourDepartureAndSC.getProduct().getCode(),
				sellingCompaniesCodes.toString());

		TourDeparturesType tourDepartureDst = new TourDeparturesType();

		copyProductData(tourDepartureAndSC, tourDepartureDst);
		copyProductSellingCompaniesData(tourDepartureAndSC, tourDepartureDst);

		return tourDepartureDst;
	}

	private static void copyProductData(TourDepartureAndSC tourDepartureSrc, TourDeparturesType tourDepartureDst) {

		tourDepartureDst.setOnlineBookable(BooleanUtils.toBoolean(tourDepartureSrc.getProduct().isOnlineBookable()));
		tourDepartureDst.setOperatingProductCode(tourDepartureSrc.getProduct().getOperatingProduct().getCode());
		tourDepartureDst.setTourCode(tourDepartureSrc.getProduct().getCode());
	}

	private static void copyProductSellingCompaniesData(TourDepartureAndSC tourDepartureSrc, TourDeparturesType tourDepartureDst) {

		SellingCompaniesType sellingCompaniesArray = new SellingCompaniesType();
		List<SellingCompanyType> sellingCompaniesList = sellingCompaniesArray.getSellingCompany();

		for (TourDepartureAndSC tourDepartureItem : Iterables.concat(Arrays.asList(tourDepartureSrc), tourDepartureSrc.getOtherReferences())) {

			SellingCompanyType sellingCompany = new SellingCompanyType();

			sellingCompany.setCode(tourDepartureItem.getSellingCompany().getCode());
			sellingCompany.setDepartures(new DeparturesType());
			sellingCompany.setInventoryBrochureCode(tourDepartureItem.getProduct().getBrochureCode());
			sellingCompany.setCurrencyCode(tourDepartureItem.getDepartures() != null && tourDepartureItem.getDepartures().getDeparture() != null && tourDepartureItem.getDepartures().getDeparture().size() > 0
					? tourDepartureItem.getDepartures().getDeparture().get(0).getCurrencyCode() : StringUtils.EMPTY);

			copyDeparturesData(tourDepartureItem.getDepartures(), sellingCompany.getDepartures());

			sellingCompaniesList.add(sellingCompany);
		}

		tourDepartureDst.setSellingCompanies(sellingCompaniesArray);
	}

	private static void copyDeparturesData(WsDeparturesVO departuresSrc, DeparturesType departuresDst) {

		if (departuresSrc == null || departuresSrc.getDeparture() == null || departuresSrc.getDeparture().size() == 0) {
			return;
		}

		for (WsDepartureVO departureSrc : departuresSrc.getDeparture()) {

			DepartureType departureDst = new DepartureType();

			departureDst.setAssociatedProducts(new AssociatedProductsType());
			departureDst.setAvailabilityStatus(AvailabilityStatusType.fromValue(departureSrc.getAvailabilityStatus()));
			departureDst.setDateRangeIncludesTravelTime(BooleanUtils.toBoolean(departureSrc.isDateRangeIncludesTravelTime()));
			departureDst.setDefiniteDeparture(BooleanUtils.toBoolean(departureSrc.isDefiniteDeparture()));
			departureDst.setDepartureCode(departureSrc.getDepartureCode());
			departureDst.setStartDateTime(departureSrc.getStartDateTime());
			departureDst.setTourRules(new TourRulesType());
			departureDst.setEndDateTime(departureSrc.getEndDateTime());

			copyAssociatedAccommodationProductsData(departureSrc.getAssociatedProducts(), departureDst.getAssociatedProducts());
			copyAssociatedMiscellaneousProductsData(departureSrc.getAssociatedProducts(), departureDst.getAssociatedProducts());
			copyNotesData(departureSrc.getNotes(), departureDst);
			copyTourRulesData(departureSrc.getTourRules(), departureDst.getTourRules(), departureSrc);

			departuresDst.getDeparture().add(departureDst);
		}
	}

	private static void copyAssociatedAccommodationProductsData(WsAssociatedProductsVO associatedProductsSrc, AssociatedProductsType associatedProductsDst) {

		if (associatedProductsSrc == null ||
			associatedProductsSrc.getAccommodation() == null ||
			associatedProductsSrc.getAccommodation().getAccommodationProduct() == null ||
			associatedProductsSrc.getAccommodation().getAccommodationProduct().size() == 0) {

			return;
		}

		associatedProductsDst.setAccommodationProducts(new AccommodationProductsType());

		for (WsAccommodationProductVO accommodationProductSrc : associatedProductsSrc.getAccommodation().getAccommodationProduct()) {

			AddressType address = new AddressType();

			if (accommodationProductSrc.getAddress() != null) {

				address.setLine1(accommodationProductSrc.getAddress().getLine1());
				address.setLine2(StringUtils.defaultIfBlank(accommodationProductSrc.getAddress().getLine2(), null));
				address.setLine3(StringUtils.defaultIfBlank(accommodationProductSrc.getAddress().getLine3(), null));
				address.setCity(accommodationProductSrc.getAddress().getCity());
				address.setCountry(accommodationProductSrc.getAddress().getCountry());
				address.setPostcode(accommodationProductSrc.getAddress().getPostCode());
				address.setRegion(accommodationProductSrc.getAddress().getRegion());
			}

			ProductRoomsType rooms = new ProductRoomsType();

			if (accommodationProductSrc.getRooms() != null && accommodationProductSrc.getRooms().getRoom() != null && accommodationProductSrc.getRooms().getRoom().size() > 0) {

				for (WsAccommodationProductRoomVO roomSrc : accommodationProductSrc.getRooms().getRoom()) {

					PriceType price = new PriceType();

					if (roomSrc.getPassengers() != null && roomSrc.getPassengers().getPassenger() != null) {

						WsAccommodationProductPassengerVO passenger1 = roomSrc.getPassengers().getPassenger().size() > 0 ? roomSrc.getPassengers().getPassenger().get(0) : null;
						WsAccommodationProductPassengerVO passenger2 = roomSrc.getPassengers().getPassenger().size() > 1 ? roomSrc.getPassengers().getPassenger().get(1) : null;

						if (passenger1 != null) {

							if (facade.itropics.webservice.tropics.com.itropicsbuildws.PassengerType.ADULT.equals(passenger1.getType())) {
								price.setAdult(passenger1.getPrice());
							} else if (facade.itropics.webservice.tropics.com.itropicsbuildws.PassengerType.CHILD.equals(passenger1.getType())) {
								price.setChild(passenger1.getPrice());
							}
						}

						if (passenger2 != null) {

							if (facade.itropics.webservice.tropics.com.itropicsbuildws.PassengerType.ADULT.equals(passenger2.getType())) {
								price.setAdult(passenger2.getPrice());
							} else if (facade.itropics.webservice.tropics.com.itropicsbuildws.PassengerType.CHILD.equals(passenger2.getType())) {
								price.setChild(passenger2.getPrice());
							}
						}

						if (price.getAdult() == null) {
							price.setAdult(VALUE_ZERO);
						}

						if (price.getChild() == null) {
							price.setChild(VALUE_ZERO);
						}
					}

					ProductRoomType roomDst = new ProductRoomType();

					roomDst.setPrice(price);
					roomDst.setType(RoomTypeCodeType.fromValue(roomSrc.getType().value()));

					rooms.getRoom().add(roomDst);
				}
			}

			AccommodationProductType accommodationProductDst = new AccommodationProductType();

			accommodationProductDst.setAddress(address);
			accommodationProductDst.setCode(accommodationProductSrc.getCode());
			accommodationProductDst.setName(accommodationProductSrc.getName());
			accommodationProductDst.setRooms(rooms);
			accommodationProductDst.setType(ProductType.PRE_ACCOMMODATION_PRODUCT.equals(accommodationProductSrc.getType())
					? AccommodationProductTypeCodeType.PRE_ACCOMMODATION : AccommodationProductTypeCodeType.POST_ACCOMMODATION);

			associatedProductsDst.getAccommodationProducts().getAccommodationProduct().add(accommodationProductDst);
		}
	}

	private static void copyAssociatedMiscellaneousProductsData(WsAssociatedProductsVO associatedProductsSrc, AssociatedProductsType associatedProductsDst) {

		if (associatedProductsSrc == null ||
			associatedProductsSrc.getMiscellaneous() == null ||
			associatedProductsSrc.getMiscellaneous().getMiscellaneousProduct() == null ||
			associatedProductsSrc.getMiscellaneous().getMiscellaneousProduct().size() == 0) {

			return;
		}

		associatedProductsDst.setMiscellaneousProducts(new MiscellaneousProductsType());

		for (WsMiscellaneousProductVO miscellaneousProductSrc : associatedProductsSrc.getMiscellaneous().getMiscellaneousProduct()) {

			PriceType price = new PriceType();

			price.setAdult(miscellaneousProductSrc.getPrice() != null ? miscellaneousProductSrc.getPrice().getAdult() : VALUE_ZERO);
			price.setChild(miscellaneousProductSrc.getPrice() != null ? miscellaneousProductSrc.getPrice().getChild() : VALUE_ZERO);

			MiscellaneousProductType miscellaneousProductDst = new MiscellaneousProductType();

			miscellaneousProductDst.setCategory(miscellaneousProductSrc.getCategory());
			miscellaneousProductDst.setCode(miscellaneousProductSrc.getCode());
			miscellaneousProductDst.setName(miscellaneousProductSrc.getName());
			miscellaneousProductDst.setPrice(price);

			associatedProductsDst.getMiscellaneousProducts().getMiscellaneousProduct().add(miscellaneousProductDst);
		}
	}

	private static void copyNotesData(WsiProductNoteVO notesSrc, DepartureType departureDst) {

		if (notesSrc == null) {
			return;
		}

		List<String> notesList = new ArrayList<String>();

		if (StringUtils.isNotBlank(notesSrc.getSellingCompany())) {
			notesList.add(notesSrc.getSellingCompany());
		}

		if (StringUtils.isNotBlank(notesSrc.getTour())) {
			notesList.add(notesSrc.getTour());
		}

		if (StringUtils.isNotBlank(notesSrc.getDeparture())) {
			notesList.add(notesSrc.getDeparture());
		}

		if (notesList.isEmpty()) {
			return;
		}

		String notesText = StringUtils.join(notesList, NEW_LINE);

		Notes notes = new Notes();

		for (String note : notesText.split(String.format(TEMPLATE_DELIMITER, NEW_LINE))) {
			notes.getContent().add(NEW_LINE.equals(note) ? objectFactory.createNotesBr(new Notes.Br()) : note);
		}

		departureDst.setNotes(notes);
	}

	private static void copyTourRulesData(WsTourRulesVO tourRulesSrc, TourRulesType tourRulesDst, WsDepartureVO departureSrc) {

		if (tourRulesSrc == null) {
			return;
		}

		tourRulesDst.setCanSearchForFlights(BooleanUtils.toBoolean(departureSrc.isCanSearchForFlights()));
		tourRulesDst.setCombinedIncludedCharges(new CombinedIncludedChargesType());
		tourRulesDst.setDiscounts(new DiscountsType());
		tourRulesDst.setIsEligibleForFrequentTravellerDiscount(BooleanUtils.toBoolean(departureSrc.isIsEligibleForFrequentTravellerDiscount()));
		tourRulesDst.setPassengers(new PassengersType());
		tourRulesDst.setPriceIsIndicative(BooleanUtils.toBoolean(departureSrc.isPriceIsIndicative()));
		tourRulesDst.setRooms(new RoomsType());

		copyTourRulesCombinedIncludedChargesData(tourRulesSrc.getCombinedIncludedCharges(), tourRulesDst.getCombinedIncludedCharges(), tourRulesDst);
		copyTourRulesDiscountsData(departureSrc.getDiscounts(), tourRulesDst.getDiscounts(), tourRulesDst);
		copyTourRulesPassengersData(tourRulesSrc.getPassengers(), tourRulesDst.getPassengers());
		copyTourRulesRoomsData(tourRulesSrc.getRooms(), tourRulesDst.getRooms());
	}

	private static void copyTourRulesCombinedIncludedChargesData(WsCombinedIncludedChargesVO chargesSrc, CombinedIncludedChargesType chargesDst, TourRulesType tourRulesDst) {

		if (chargesSrc == null) {

			tourRulesDst.setCombinedIncludedCharges(null);
			return;
		}

		FoodFundType foodFund = null;

		if (chargesSrc.getFoodFund() != null && chargesSrc.getFoodFund().getPrice() != null) {

			PriceType foodFundPrice = new PriceType();

			foodFundPrice.setAdult(chargesSrc.getFoodFund().getPrice().getAdult() != null ? chargesSrc.getFoodFund().getPrice().getAdult() : VALUE_ZERO);
			foodFundPrice.setChild(chargesSrc.getFoodFund().getPrice().getChild() != null ? chargesSrc.getFoodFund().getPrice().getChild() : VALUE_ZERO);

			foodFund = new FoodFundType();
			foodFund.setPrice(foodFundPrice);
		}

		PortTaxType portTax = null;

		if (chargesSrc.getPortTax() != null && chargesSrc.getPortTax().getPrice() != null) {

			PriceType portTaxPrice = new PriceType();

			portTaxPrice.setAdult(chargesSrc.getPortTax().getPrice().getAdult() != null ? chargesSrc.getPortTax().getPrice().getAdult() : VALUE_ZERO);
			portTaxPrice.setChild(chargesSrc.getPortTax().getPrice().getChild() != null ? chargesSrc.getPortTax().getPrice().getChild() : VALUE_ZERO);

			portTax = new PortTaxType();
			portTax.setPrice(portTaxPrice);
		}

		MandatoryMiscellaneousProductsType miscellaneousProducts = null;

		if (chargesSrc.getMandatoryMiscellaneousProducts() != null && chargesSrc.getMandatoryMiscellaneousProducts().getProduct() != null && chargesSrc.getMandatoryMiscellaneousProducts().getProduct().size() > 0) {

			miscellaneousProducts = new MandatoryMiscellaneousProductsType();

			for (WsMiscellaneousProductVO miscellaneousProductSrc : chargesSrc.getMandatoryMiscellaneousProducts().getProduct()) {

				PriceType price = new PriceType();

				price.setAdult(miscellaneousProductSrc.getPrice() != null ? miscellaneousProductSrc.getPrice().getAdult() : VALUE_ZERO);
				price.setChild(miscellaneousProductSrc.getPrice() != null ? miscellaneousProductSrc.getPrice().getChild() : VALUE_ZERO);

				MiscellaneousProductType miscellaneousProductDst = new MiscellaneousProductType();

				miscellaneousProductDst.setCategory(miscellaneousProductSrc.getCategory());
				miscellaneousProductDst.setCode(miscellaneousProductSrc.getCode());
				miscellaneousProductDst.setName(miscellaneousProductSrc.getName());
				miscellaneousProductDst.setPrice(price);

				miscellaneousProducts.getMiscellaneousProduct().add(miscellaneousProductDst);
			}
		}

		SurchargesType surcharges = null;

		if (chargesSrc.getSurcharges() != null && chargesSrc.getSurcharges().getPrice() != null && chargesSrc.getSurcharges().getPrice().size() > 0) {

			surcharges = new SurchargesType();

			for (WsPriceVO priceSrc : chargesSrc.getSurcharges().getPrice()) {

				PriceType priceDst = new PriceType();

				priceDst.setAdult(priceSrc.getAdult());
				priceDst.setChild(priceSrc.getChild());

				SurchargeType surchargeDst = new SurchargeType();

				surchargeDst.setPrice(priceDst);

				surcharges.getSurcharge().add(surchargeDst);
			}
		}

		chargesDst.setFoodFund(foodFund);
		chargesDst.setPortTax(portTax);
		chargesDst.setMandatoryMiscellaneousProducts(miscellaneousProducts);
		chargesDst.setSurcharges(surcharges);
	}

	private static void copyTourRulesDiscountsData(WsProdDepartureEPDDetails discountsSrc, DiscountsType discountsDst, TourRulesType tourRulesDst) {

		if (discountsSrc == null || discountsSrc.getWsEPDDetailsVOArray() == null || discountsSrc.getWsEPDDetailsVOArray().size() == 0) {

			tourRulesDst.setDiscounts(null);
			return;
		}

		for (ComTropicsWebserviceITropicsVoCurrentEpdTourWSEPDisountDetailsVO discountSrc : discountsSrc.getWsEPDDetailsVOArray()) {

			DiscountType discountDst = new DiscountType();

			discountDst.setAmount(new BigDecimal(discountSrc.getAmount()));
			discountDst.setBasis("The discount is Per Passenger against the passenger's Room Price for the Tour Departure, and dependent on committing to pay for the booking in full by the Early Payment Due Date indicated");
			discountDst.setCode(String.valueOf(discountSrc.getDiscountId()));
			discountDst.setIsPercentage(discountSrc.isPercentage());
			discountDst.setPaymentDueDate(discountSrc.getPaymentDate());
			discountDst.setType("Early Payment Discount");

			discountsDst.getDiscount().add(discountDst);
		}
	}

	private static void copyTourRulesPassengersData(WsTourRulesPassengersVO passengersSrc, PassengersType passengersDst) {

		if (passengersSrc == null || passengersSrc.getPassenger() == null || passengersSrc.getPassenger().size() == 0) {
			return;
		}

		for (WsTourRulesPassengerVO passengerSrc : passengersSrc.getPassenger()) {

			PassengerType passengerDst = new PassengerType();

			passengerDst.setType(PassengerTypeType.fromValue(passengerSrc.getType().value()));
			passengerDst.setAgeTo(passengerSrc.getAgeTo() != null ? passengerSrc.getAgeTo().byteValue() : 0);
			passengerDst.setAgeFrom(passengerSrc.getAgeFrom() != null ? passengerSrc.getAgeFrom().byteValue() : 0);

			passengersDst.getPassenger().add(passengerDst);
		}
	}

	private static void copyTourRulesRoomsData(WsTourRulesRoomsVO roomsSrc, RoomsType roomsDst) {

		if (roomsSrc == null || roomsSrc.getRoom() == null || roomsSrc.getRoom().size() == 0) {
			return;
		}

		for (WsTourRulesRoomVO roomSrc : roomsSrc.getRoom()) {

			OccupancyRuleType rules = new OccupancyRuleType();

			if (roomSrc.getOccupancyRule() != null) {

				rules.setMaximumAdults(roomSrc.getOccupancyRule().getMaximumAdults() != null ? roomSrc.getOccupancyRule().getMaximumAdults().shortValue() : 0);
				rules.setMaximumPassengers(roomSrc.getOccupancyRule().getMaximumPassengers() != null ? roomSrc.getOccupancyRule().getMaximumPassengers().shortValue() : 0);
				rules.setMinimumAdults(roomSrc.getOccupancyRule().getMinimumAdults() != null ? roomSrc.getOccupancyRule().getMinimumAdults().shortValue() : 0);
				rules.setMinimumPassengers(roomSrc.getOccupancyRule().getMinimumPassengers() != null ? roomSrc.getOccupancyRule().getMinimumPassengers().shortValue() : 0);
				rules.setMinimumPayingAdults(roomSrc.getOccupancyRule().getMinimumPayingAdults() != null ? roomSrc.getOccupancyRule().getMinimumPayingAdults().shortValue() : 0);
			}

			RoomPriceType price = new RoomPriceType();

			if (roomSrc.getPrice() != null) {

				Adult adult = new Adult();

				adult.setBase(roomSrc.getPrice().getAdult() != null && roomSrc.getPrice().getAdult().getBase() != null ? roomSrc.getPrice().getAdult().getBase() : VALUE_ZERO);
				adult.setCombined(roomSrc.getPrice().getAdult() != null && roomSrc.getPrice().getAdult().getCombined() != null ? roomSrc.getPrice().getAdult().getCombined() : VALUE_ZERO);

				Child child = new Child();

				child.setBase(roomSrc.getPrice().getChild() != null && roomSrc.getPrice().getChild().getBase() != null ? roomSrc.getPrice().getChild().getBase() : VALUE_ZERO);
				child.setCombined(roomSrc.getPrice().getChild() != null && roomSrc.getPrice().getChild().getCombined() != null ? roomSrc.getPrice().getChild().getCombined() : VALUE_ZERO);

				price.setAdult(adult);
				price.setChild(child);
			}

			RoomType roomDst = new RoomType();

			roomDst.setOccupancyRule(rules);
			roomDst.setPrice(price);
			roomDst.setType(RoomTypeCodeType.fromValue(roomSrc.getType().value()));

			roomsDst.getRoom().add(roomDst);
		}
	}
}
