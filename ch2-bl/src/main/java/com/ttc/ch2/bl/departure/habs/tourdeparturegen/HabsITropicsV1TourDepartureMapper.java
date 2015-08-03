package com.ttc.ch2.bl.departure.habs.tourdeparturegen;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConstants;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Iterables;
import com.ttc.ch2.bl.departure.TropicSynchronizeServiceException;
import com.ttc.ch2.bl.departure.common.LogOperationHelper;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.ArrayOfDeparture;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.ArrayOfDeparturePricing;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.ArrayOfMandatoryProduct;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.ArrayOfMarketVariationPricing;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.ArrayOfSurcharge;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.Departure;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.DeparturePricing;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MandatoryProduct;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationDepartureInfo;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.MarketVariationPricing;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.ObjectFactory;
import com.ttsl.marketvariationdepartureinfo._2010._09._1.Surcharge;
import com.wsout.habs.itropicsbuildws.WsCombinedIncludedChargesVO;
import com.wsout.habs.itropicsbuildws.WsDepartureVO;
import com.wsout.habs.itropicsbuildws.WsMandatoryMiscellaneousProductsVO;
import com.wsout.habs.itropicsbuildws.WsMiscellaneousProductVO;
import com.wsout.habs.itropicsbuildws.WsPriceVO;
import com.wsout.habs.itropicsbuildws.WsSurchargesVO;
import com.wsout.habs.itropicsbuildws.WsTourRulesRoomVO;


public class HabsITropicsV1TourDepartureMapper {

	private static final Logger logger = LoggerFactory.getLogger(HabsITropicsV1TourDepartureMapper.class);

	private static String SURCHARGE_LABEL = "Surcharge";
	private static BigDecimal VALUE_ZERO = new BigDecimal(0).setScale(2);

	public static MarketVariationDepartureInfo map(OperationStatus opStatus, HabsTourDepartureAndSC tourDepartureAndSC) throws TropicSynchronizeServiceException {

		if (tourDepartureAndSC == null) {
			return null;
		}

		List<String> sellingCompaniesCodes = new ArrayList<String>();

		for (HabsTourDepartureAndSC tourDepartureItem : Iterables.concat(Arrays.asList(tourDepartureAndSC), tourDepartureAndSC.getOtherReferences())) {
			sellingCompaniesCodes.add(tourDepartureItem.getSellingCompany().getCode());
		}

		LogOperationHelper.logMessageForProduct(logger, opStatus,
				tourDepartureAndSC.getProduct().getCode(),
				TropicSynchronizeMessages.START_MAPPING_VERSION1,
				tourDepartureAndSC.getProduct().getCode(),
				sellingCompaniesCodes.toString());

		MarketVariationDepartureInfo tourDepartureDst = new MarketVariationDepartureInfo();

		copyProductData(tourDepartureAndSC, tourDepartureDst);
		copyProductPricingsData(tourDepartureAndSC, tourDepartureDst);
		copyDeparturesData(tourDepartureAndSC, tourDepartureDst);
		copyDeparturesPricingsData(tourDepartureAndSC, tourDepartureDst);

		return tourDepartureDst;
	}

	private static void copyProductData(HabsTourDepartureAndSC tourDepartureSrc, MarketVariationDepartureInfo tourDepartureDst) {

		tourDepartureDst.setBookableOnline(tourDepartureSrc.getProduct().isOnlineBookable());
		tourDepartureDst.setBrochureCode(tourDepartureSrc.getProduct().getBrochureCode());
		tourDepartureDst.setMarketVariationCode(tourDepartureSrc.getProduct().getCode());
		tourDepartureDst.setOperatingProductCode(tourDepartureSrc.getProduct().getOperatingProduct().getCode());	
	}

	private static void copyProductPricingsData(HabsTourDepartureAndSC tourDepartureSrc, MarketVariationDepartureInfo tourDepartureDst) {

		ArrayOfMarketVariationPricing pricingArray = new ArrayOfMarketVariationPricing();
		List<MarketVariationPricing> pricingList = pricingArray.getMarketVariationPricing();

		for (HabsTourDepartureAndSC tourDepartureItem : Iterables.concat(Arrays.asList(tourDepartureSrc), tourDepartureSrc.getOtherReferences())) {

			WsDepartureVO departure = tourDepartureItem.getDepartures().getDeparture().get(0);
			WsCombinedIncludedChargesVO charges = departure.getTourRules().getCombinedIncludedCharges();

			MarketVariationPricing pricing = new MarketVariationPricing();

			pricing.setAdultPortTax(charges.getPortTax() != null && charges.getPortTax().getPrice() != null && charges.getPortTax().getPrice().getAdult() != null ? charges.getPortTax().getPrice().getAdult() : VALUE_ZERO);
			pricing.setAirPriceIncluded(false);
			pricing.setChildPortTax(charges.getPortTax() != null && charges.getPortTax().getPrice() != null && charges.getPortTax().getPrice().getChild() != null ? charges.getPortTax().getPrice().getChild() : VALUE_ZERO);
			pricing.setCurrency(departure.getCurrencyCode());
			pricing.setFoodFundPrice(charges.getFoodFund() != null && charges.getFoodFund().getPrice() != null && charges.getFoodFund().getPrice().getAdult() != null ? charges.getFoodFund().getPrice().getAdult() : VALUE_ZERO);
			pricing.setPriceIsIndicative(departure.isPriceIsIndicative());
			pricing.setSellingCompanyCode(tourDepartureItem.getSellingCompany().getCode());

			pricingList.add(pricing);
		}

		tourDepartureDst.setMarketVariationPricings(pricingArray);
	}

	private static void copyDeparturesData(HabsTourDepartureAndSC tourDepartureSrc, MarketVariationDepartureInfo tourDepartureDst) {

		ArrayOfDeparture departureArray = new ArrayOfDeparture();
		List<Departure> departureList = departureArray.getDeparture();

		for (HabsTourDepartureAndSC tourDepartureItem : Iterables.concat(Arrays.asList(tourDepartureSrc), tourDepartureSrc.getOtherReferences())) {

			for (final WsDepartureVO departureSrc : tourDepartureItem.getDepartures().getDeparture()) {

				Object departure = CollectionUtils.find(departureList, new Predicate() {

					public boolean evaluate(Object object) {
						return departureSrc.getDepartureCode().equals(((Departure) object).getDepartureCode());
					}
				});

				if (departure != null) {
					continue;
				}

				Departure departureDst = new Departure();

				departureDst.setAvailabilityStatus(departureSrc.getAvailabilityStatus());
				departureDst.setDefiniteDeparture(departureSrc.isDefiniteDeparture());
				departureDst.setDepartureCode(departureSrc.getDepartureCode());
				departureDst.setDeparturePricings(new ArrayOfDeparturePricing());
				departureDst.setStartDate(departureSrc.getStartDateTime());
				departureDst.setEndDate(departureSrc.getEndDateTime());
				departureDst.getStartDate().setTimezone(DatatypeConstants.FIELD_UNDEFINED);
				departureDst.getEndDate().setTimezone(DatatypeConstants.FIELD_UNDEFINED);

				departureList.add(departureDst);
			}
		}

		tourDepartureDst.setDepartures(new ObjectFactory().createMarketVariationDepartureInfoDepartures(departureArray));
	}

	private static void copyDeparturesPricingsData(HabsTourDepartureAndSC tourDepartureSrc, MarketVariationDepartureInfo tourDepartureDst) {

		Map<String, List<DeparturePricing>> pricingMap = new HashMap<String, List<DeparturePricing>>();

		for (Departure departure : tourDepartureDst.getDepartures().getValue().getDeparture()) {
			pricingMap.put(departure.getDepartureCode(), departure.getDeparturePricings().getDeparturePricing());
		}

		for (HabsTourDepartureAndSC tourDepartureItem : Iterables.concat(Arrays.asList(tourDepartureSrc), tourDepartureSrc.getOtherReferences())) {

			for (WsDepartureVO departureSrc : tourDepartureItem.getDepartures().getDeparture()) {

				DeparturePricing pricing = new DeparturePricing();

				pricing.setCurrency(departureSrc.getCurrencyCode());
				pricing.setSellingCompanyCode(tourDepartureItem.getSellingCompany().getCode());
				pricing.setLandOnlyReduction(VALUE_ZERO);
				pricing.setTeenagerDiscount(VALUE_ZERO);

				BigDecimal childPrice = new BigDecimal(Integer.MAX_VALUE);

				if (departureSrc.getTourRules().getRooms() != null && departureSrc.getTourRules().getRooms().getRoom() != null) {

					for (WsTourRulesRoomVO room : departureSrc.getTourRules().getRooms().getRoom()) {

						BigDecimal priceBase = room.getPrice().getAdult().getBase();
						BigDecimal priceCombined = room.getPrice().getAdult().getCombined();

						if (childPrice.compareTo(room.getPrice().getChild().getCombined()) > 0) {
							childPrice = room.getPrice().getChild().getCombined();
						}

						switch (room.getType()) {

							case SINGLE:
								pricing.setAdultSingleRoomPrice(priceBase);
								pricing.setAdultSingleRoomPriceCombined(priceCombined);
								break;

							case TWIN:
								pricing.setAdultTwinRoomPrice(priceBase);
								pricing.setAdultTwinRoomPriceCombined(priceCombined);
								break;

							case TRIPLE:
								pricing.setAdultTripleRoomPrice(priceBase);
								pricing.setAdultTripleRoomPriceCombined(priceCombined);
								break;

							case QUAD:
								pricing.setAdultQuadRoomPrice(priceBase);
								pricing.setAdultQuadRoomPriceCombined(priceCombined);
								break;

							default:
								break;
						}
					}

				} else {

					pricing.setAdultSingleRoomPrice(VALUE_ZERO);
					pricing.setAdultSingleRoomPriceCombined(VALUE_ZERO);
					pricing.setAdultTwinRoomPrice(VALUE_ZERO);
					pricing.setAdultTwinRoomPriceCombined(VALUE_ZERO);
					pricing.setAdultTripleRoomPrice(VALUE_ZERO);
					pricing.setAdultTripleRoomPriceCombined(VALUE_ZERO);
					pricing.setAdultQuadRoomPrice(VALUE_ZERO);
					pricing.setAdultQuadRoomPriceCombined(VALUE_ZERO);

					childPrice = VALUE_ZERO;
				}

				pricing.setChildPrice(childPrice);

				copyDeparturesPricingsMandatoryProducts(departureSrc, pricing);
				copyDeparturesPricingsSurcharges(departureSrc, pricing);

				pricingMap.get(departureSrc.getDepartureCode()).add(pricing);
			}
		}
	}

	private static void copyDeparturesPricingsMandatoryProducts(WsDepartureVO departureSrc, DeparturePricing pricing) {

		ArrayOfMandatoryProduct mandatoryProductArray = new ArrayOfMandatoryProduct();
		List<MandatoryProduct> mandatoryProductList = mandatoryProductArray.getMandatoryProduct();

		WsMandatoryMiscellaneousProductsVO mandatoryProductSrc = departureSrc.getTourRules().getCombinedIncludedCharges().getMandatoryMiscellaneousProducts();

		if (mandatoryProductSrc != null && mandatoryProductSrc.getProduct() != null) {

			for (WsMiscellaneousProductVO product : mandatoryProductSrc.getProduct()) {

				MandatoryProduct mandatoryProductDst = new MandatoryProduct();

				mandatoryProductDst.setAdultPrice(product.getPrice().getAdult());
				mandatoryProductDst.setChildPrice(product.getPrice().getChild());
				mandatoryProductDst.setDescription(product.getName());
				mandatoryProductDst.setProductId(product.getProductId());
				mandatoryProductDst.setProductType(product.getCategory());

				mandatoryProductList.add(mandatoryProductDst);
			}
		}

		pricing.setMandatoryProducts(new ObjectFactory().createDeparturePricingMandatoryProducts(mandatoryProductArray));
	}

	private static void copyDeparturesPricingsSurcharges(WsDepartureVO departureSrc, DeparturePricing pricing) {

		ArrayOfSurcharge surchargeArray = new ArrayOfSurcharge();
		List<Surcharge> surchargeList = surchargeArray.getSurcharge();

		WsSurchargesVO surchargeSrc = departureSrc.getTourRules().getCombinedIncludedCharges().getSurcharges();

		if (surchargeSrc != null && surchargeSrc.getPrice() != null) {

			for (WsPriceVO price : surchargeSrc.getPrice()) {

				Surcharge surchargeDst = new Surcharge();

				surchargeDst.setAmountIsAbsolute(true);
				surchargeDst.setSurchargeAmount(price.getAdult());
				surchargeDst.setSurchargeDescription(SURCHARGE_LABEL);
				surchargeDst.setSurchargeType(SURCHARGE_LABEL);

				surchargeList.add(surchargeDst);
			}
		}

		pricing.setSurcharges(new ObjectFactory().createDeparturePricingSurcharges(surchargeArray));
	}
}
