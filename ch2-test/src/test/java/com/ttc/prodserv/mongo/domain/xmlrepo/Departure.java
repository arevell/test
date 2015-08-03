package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "departure", propOrder = {
	"code",
	"status",
	"startDate",
	"endDate",
	"seasonStartDate",
	"seasonEndDate",
	"note",
	"guaranteedStatus",
	"priceIndicative",
	"eligibleForFrequentTravellerDiscount",
	"foodFundPrice",
	"childPortTax",
	"adultPortTax",
	"childPrice",
	"childSingleRoomPrice",
	"childTwinRoomPrice",
	"childTripleRoomPrice",
	"childQuadRoomPrice",
	"combinedChildSingleRoomPrice",
	"combinedChildTwinRoomPrice",
	"combinedChildTripleRoomPrice",
	"combinedChildQuadRoomPrice",
	"adultSingleRoomPrice",
	"adultTwinRoomPrice",
	"adultTripleRoomPrice",
	"adultQuadRoomPrice",
	"combinedAdultSingleRoomPrice",
	"combinedAdultTwinRoomPrice",
	"combinedAdultTripleRoomPrice",
	"combinedAdultQuadRoomPrice",
	"surcharges",
	"discounts",
	"accommodationProducts",
	"miscellaneousProducts",
	"insuranceProducts"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Departure {

	protected String code;
	protected String status;
	@XmlSchemaType(name = "dateTime")
	protected Date startDate;
	@XmlSchemaType(name = "dateTime")
	protected Date endDate;
	@XmlSchemaType(name = "dateTime")
	protected Date seasonStartDate;
	@XmlSchemaType(name = "dateTime")
	protected Date seasonEndDate;
	protected String note;
	protected String guaranteedStatus;
	protected Boolean priceIndicative;
	protected Boolean eligibleForFrequentTravellerDiscount;

	protected BigDecimal foodFundPrice;
	protected BigDecimal childPortTax;
	protected BigDecimal adultPortTax;

	protected BigDecimal childPrice;
	protected BigDecimal childSingleRoomPrice;
	protected BigDecimal childTwinRoomPrice;
	protected BigDecimal childTripleRoomPrice;
	protected BigDecimal childQuadRoomPrice;
	protected BigDecimal combinedChildSingleRoomPrice;
	protected BigDecimal combinedChildTwinRoomPrice;
	protected BigDecimal combinedChildTripleRoomPrice;
	protected BigDecimal combinedChildQuadRoomPrice;

	protected BigDecimal adultSingleRoomPrice;
	protected BigDecimal adultTwinRoomPrice;
	protected BigDecimal adultTripleRoomPrice;
	protected BigDecimal adultQuadRoomPrice;
	protected BigDecimal combinedAdultSingleRoomPrice;
	protected BigDecimal combinedAdultTwinRoomPrice;
	protected BigDecimal combinedAdultTripleRoomPrice;
	protected BigDecimal combinedAdultQuadRoomPrice;

	protected Surcharges surcharges;
	protected Discounts discounts;

	protected AccommodationProducts accommodationProducts;
	protected MiscellaneousProducts miscellaneousProducts;
	protected InsuranceProducts insuranceProducts;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(Date seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public Date getSeasonEndDate() {
		return seasonEndDate;
	}

	public void setSeasonEndDate(Date seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getGuaranteedStatus() {
		return guaranteedStatus;
	}

	public void setGuaranteedStatus(String guaranteedStatus) {
		this.guaranteedStatus = guaranteedStatus;
	}

	public Boolean getPriceIndicative() {
		return priceIndicative;
	}

	public void setPriceIndicative(Boolean priceIndicative) {
		this.priceIndicative = priceIndicative;
	}

	public Boolean getEligibleForFrequentTravellerDiscount() {
		return eligibleForFrequentTravellerDiscount;
	}

	public void setEligibleForFrequentTravellerDiscount(Boolean eligibleForFrequentTravellerDiscount) {
		this.eligibleForFrequentTravellerDiscount = eligibleForFrequentTravellerDiscount;
	}

	public BigDecimal getFoodFundPrice() {
		return foodFundPrice;
	}

	public void setFoodFundPrice(BigDecimal foodFundPrice) {
		this.foodFundPrice = foodFundPrice;
	}

	public BigDecimal getChildPortTax() {
		return childPortTax;
	}

	public void setChildPortTax(BigDecimal childPortTax) {
		this.childPortTax = childPortTax;
	}

	public BigDecimal getAdultPortTax() {
		return adultPortTax;
	}

	public void setAdultPortTax(BigDecimal adultPortTax) {
		this.adultPortTax = adultPortTax;
	}

	public BigDecimal getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(BigDecimal childPrice) {
		this.childPrice = childPrice;
	}

	public BigDecimal getChildSingleRoomPrice() {
		return childSingleRoomPrice;
	}

	public void setChildSingleRoomPrice(BigDecimal childSingleRoomPrice) {
		this.childSingleRoomPrice = childSingleRoomPrice;
	}

	public BigDecimal getChildTwinRoomPrice() {
		return childTwinRoomPrice;
	}

	public void setChildTwinRoomPrice(BigDecimal childTwinRoomPrice) {
		this.childTwinRoomPrice = childTwinRoomPrice;
	}

	public BigDecimal getChildTripleRoomPrice() {
		return childTripleRoomPrice;
	}

	public void setChildTripleRoomPrice(BigDecimal childTripleRoomPrice) {
		this.childTripleRoomPrice = childTripleRoomPrice;
	}

	public BigDecimal getChildQuadRoomPrice() {
		return childQuadRoomPrice;
	}

	public void setChildQuadRoomPrice(BigDecimal childQuadRoomPrice) {
		this.childQuadRoomPrice = childQuadRoomPrice;
	}

	public BigDecimal getCombinedChildSingleRoomPrice() {
		return combinedChildSingleRoomPrice;
	}

	public void setCombinedChildSingleRoomPrice(BigDecimal combinedChildSingleRoomPrice) {
		this.combinedChildSingleRoomPrice = combinedChildSingleRoomPrice;
	}

	public BigDecimal getCombinedChildTwinRoomPrice() {
		return combinedChildTwinRoomPrice;
	}

	public void setCombinedChildTwinRoomPrice(BigDecimal combinedChildTwinRoomPrice) {
		this.combinedChildTwinRoomPrice = combinedChildTwinRoomPrice;
	}

	public BigDecimal getCombinedChildTripleRoomPrice() {
		return combinedChildTripleRoomPrice;
	}

	public void setCombinedChildTripleRoomPrice(BigDecimal combinedChildTripleRoomPrice) {
		this.combinedChildTripleRoomPrice = combinedChildTripleRoomPrice;
	}

	public BigDecimal getCombinedChildQuadRoomPrice() {
		return combinedChildQuadRoomPrice;
	}

	public void setCombinedChildQuadRoomPrice(BigDecimal combinedChildQuadRoomPrice) {
		this.combinedChildQuadRoomPrice = combinedChildQuadRoomPrice;
	}

	public BigDecimal getAdultSingleRoomPrice() {
		return adultSingleRoomPrice;
	}

	public void setAdultSingleRoomPrice(BigDecimal adultSingleRoomPrice) {
		this.adultSingleRoomPrice = adultSingleRoomPrice;
	}

	public BigDecimal getAdultTwinRoomPrice() {
		return adultTwinRoomPrice;
	}

	public void setAdultTwinRoomPrice(BigDecimal adultTwinRoomPrice) {
		this.adultTwinRoomPrice = adultTwinRoomPrice;
	}

	public BigDecimal getAdultTripleRoomPrice() {
		return adultTripleRoomPrice;
	}

	public void setAdultTripleRoomPrice(BigDecimal adultTripleRoomPrice) {
		this.adultTripleRoomPrice = adultTripleRoomPrice;
	}

	public BigDecimal getAdultQuadRoomPrice() {
		return adultQuadRoomPrice;
	}

	public void setAdultQuadRoomPrice(BigDecimal adultQuadRoomPrice) {
		this.adultQuadRoomPrice = adultQuadRoomPrice;
	}

	public BigDecimal getCombinedAdultSingleRoomPrice() {
		return combinedAdultSingleRoomPrice;
	}

	public void setCombinedAdultSingleRoomPrice(BigDecimal combinedAdultSingleRoomPrice) {
		this.combinedAdultSingleRoomPrice = combinedAdultSingleRoomPrice;
	}

	public BigDecimal getCombinedAdultTwinRoomPrice() {
		return combinedAdultTwinRoomPrice;
	}

	public void setCombinedAdultTwinRoomPrice(BigDecimal combinedAdultTwinRoomPrice) {
		this.combinedAdultTwinRoomPrice = combinedAdultTwinRoomPrice;
	}

	public BigDecimal getCombinedAdultTripleRoomPrice() {
		return combinedAdultTripleRoomPrice;
	}

	public void setCombinedAdultTripleRoomPrice(BigDecimal combinedAdultTripleRoomPrice) {
		this.combinedAdultTripleRoomPrice = combinedAdultTripleRoomPrice;
	}

	public BigDecimal getCombinedAdultQuadRoomPrice() {
		return combinedAdultQuadRoomPrice;
	}

	public void setCombinedAdultQuadRoomPrice(BigDecimal combinedAdultQuadRoomPrice) {
		this.combinedAdultQuadRoomPrice = combinedAdultQuadRoomPrice;
	}

	public Surcharges getSurcharges() {
		return surcharges;
	}

	public void setSurcharges(Surcharges surcharges) {
		this.surcharges = surcharges;
	}

	public Discounts getDiscounts() {
		return discounts;
	}

	public void setDiscounts(Discounts discounts) {
		this.discounts = discounts;
	}

	public AccommodationProducts getAccommodationProducts() {
		return accommodationProducts;
	}

	public void setAccommodationProducts(AccommodationProducts accommodationProducts) {
		this.accommodationProducts = accommodationProducts;
	}

	public MiscellaneousProducts getMiscellaneousProducts() {
		return miscellaneousProducts;
	}

	public void setMiscellaneousProducts(MiscellaneousProducts miscellaneousProducts) {
		this.miscellaneousProducts = miscellaneousProducts;
	}

	public InsuranceProducts getInsuranceProducts() {
		return insuranceProducts;
	}

	public void setInsuranceProducts(InsuranceProducts insuranceProducts) {
		this.insuranceProducts = insuranceProducts;
	}
}
