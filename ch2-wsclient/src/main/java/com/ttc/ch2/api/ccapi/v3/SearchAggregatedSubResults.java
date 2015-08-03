
package com.ttc.ch2.api.ccapi.v3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.AdditionalDefiners;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Assets;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ContinentsVisited;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.CountriesVisited;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Highlights;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.IncludedSubProducts;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.MarketingFlags;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.RoomTypes;


/**
 * <p>Java class for SearchAggregatedSubResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchAggregatedSubResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tourCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tourName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sellingCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="priceFrom" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="priceTo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="brochureCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="brochureName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marketingFlags" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}MarketingFlags" minOccurs="0"/>
 *         &lt;element name="highlights" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}Highlights" minOccurs="0"/>
 *         &lt;element name="assets" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}Assets" minOccurs="0"/>
 *         &lt;element name="continentsVisited" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}ContinentsVisited"/>
 *         &lt;element name="countriesVisited" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}CountriesVisited"/>
 *         &lt;element name="earlyPaymentDiscountAvailable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="definiteDeparturesAvailable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="accommodations" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="startCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="airportsStartCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="airportsEndCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellableRoomTypes" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}RoomTypes"/>
 *         &lt;element name="operatingProductCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contractingSeason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="earliestDepartureStartDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="latestDepartureStartDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="includedSubProducts" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}IncludedSubProducts" minOccurs="0"/>
 *         &lt;element name="includedCruiseCabinType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="additionalDefiners" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}AdditionalDefiners" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchAggregatedSubResults", propOrder = {
    "tourCode",
    "tourName",
    "sellingCompanyCode",
    "priceFrom",
    "priceTo",
    "brochureCode",
    "brochureName",
    "marketingFlags",
    "highlights",
    "assets",
    "continentsVisited",
    "countriesVisited",
    "earlyPaymentDiscountAvailable",
    "definiteDeparturesAvailable",
    "duration",
    "accommodations",
    "startCity",
    "airportsStartCity",
    "endCity",
    "airportsEndCity",
    "sellableRoomTypes",
    "operatingProductCode",
    "contractingSeason",
    "earliestDepartureStartDate",
    "latestDepartureStartDate",
    "includedSubProducts",
    "includedCruiseCabinType",
    "additionalDefiners"
})
public class SearchAggregatedSubResults {

    @XmlElement(required = true)
    protected String tourCode;
    @XmlElement(required = true)
    protected String tourName;
    @XmlElement(required = true)
    protected String sellingCompanyCode;
    protected double priceFrom;
    protected double priceTo;
    @XmlElement(required = true)
    protected String brochureCode;
    @XmlElement(required = true)
    protected String brochureName;
    protected MarketingFlags marketingFlags;
    protected Highlights highlights;
    protected Assets assets;
    @XmlElement(required = true)
    protected ContinentsVisited continentsVisited;
    @XmlElement(required = true)
    protected CountriesVisited countriesVisited;
    protected boolean earlyPaymentDiscountAvailable;
    protected boolean definiteDeparturesAvailable;
    @XmlElement(required = true)
    protected BigInteger duration;
    protected List<String> accommodations;
    @XmlElement(required = true)
    protected String startCity;
    protected String airportsStartCity;
    @XmlElement(required = true)
    protected String endCity;
    protected String airportsEndCity;
    @XmlElement(required = true)
    protected RoomTypes sellableRoomTypes;
    @XmlElement(required = true)
    protected String operatingProductCode;
    @XmlElement(required = true)
    protected String contractingSeason;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar earliestDepartureStartDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar latestDepartureStartDate;
    protected IncludedSubProducts includedSubProducts;
    protected String includedCruiseCabinType;
    protected AdditionalDefiners additionalDefiners;

    /**
     * Gets the value of the tourCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourCode() {
        return tourCode;
    }

    /**
     * Sets the value of the tourCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourCode(String value) {
        this.tourCode = value;
    }

    /**
     * Gets the value of the tourName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourName() {
        return tourName;
    }

    /**
     * Sets the value of the tourName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourName(String value) {
        this.tourName = value;
    }

    /**
     * Gets the value of the sellingCompanyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingCompanyCode() {
        return sellingCompanyCode;
    }

    /**
     * Sets the value of the sellingCompanyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingCompanyCode(String value) {
        this.sellingCompanyCode = value;
    }

    /**
     * Gets the value of the priceFrom property.
     * 
     */
    public double getPriceFrom() {
        return priceFrom;
    }

    /**
     * Sets the value of the priceFrom property.
     * 
     */
    public void setPriceFrom(double value) {
        this.priceFrom = value;
    }

    /**
     * Gets the value of the priceTo property.
     * 
     */
    public double getPriceTo() {
        return priceTo;
    }

    /**
     * Sets the value of the priceTo property.
     * 
     */
    public void setPriceTo(double value) {
        this.priceTo = value;
    }

    /**
     * Gets the value of the brochureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrochureCode() {
        return brochureCode;
    }

    /**
     * Sets the value of the brochureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrochureCode(String value) {
        this.brochureCode = value;
    }

    /**
     * Gets the value of the brochureName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrochureName() {
        return brochureName;
    }

    /**
     * Sets the value of the brochureName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrochureName(String value) {
        this.brochureName = value;
    }

    /**
     * Gets the value of the marketingFlags property.
     * 
     * @return
     *     possible object is
     *     {@link MarketingFlags }
     *     
     */
    public MarketingFlags getMarketingFlags() {
        return marketingFlags;
    }

    /**
     * Sets the value of the marketingFlags property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarketingFlags }
     *     
     */
    public void setMarketingFlags(MarketingFlags value) {
        this.marketingFlags = value;
    }

    /**
     * Gets the value of the highlights property.
     * 
     * @return
     *     possible object is
     *     {@link Highlights }
     *     
     */
    public Highlights getHighlights() {
        return highlights;
    }

    /**
     * Sets the value of the highlights property.
     * 
     * @param value
     *     allowed object is
     *     {@link Highlights }
     *     
     */
    public void setHighlights(Highlights value) {
        this.highlights = value;
    }

    /**
     * Gets the value of the assets property.
     * 
     * @return
     *     possible object is
     *     {@link Assets }
     *     
     */
    public Assets getAssets() {
        return assets;
    }

    /**
     * Sets the value of the assets property.
     * 
     * @param value
     *     allowed object is
     *     {@link Assets }
     *     
     */
    public void setAssets(Assets value) {
        this.assets = value;
    }

    /**
     * Gets the value of the continentsVisited property.
     * 
     * @return
     *     possible object is
     *     {@link ContinentsVisited }
     *     
     */
    public ContinentsVisited getContinentsVisited() {
        return continentsVisited;
    }

    /**
     * Sets the value of the continentsVisited property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContinentsVisited }
     *     
     */
    public void setContinentsVisited(ContinentsVisited value) {
        this.continentsVisited = value;
    }

    /**
     * Gets the value of the countriesVisited property.
     * 
     * @return
     *     possible object is
     *     {@link CountriesVisited }
     *     
     */
    public CountriesVisited getCountriesVisited() {
        return countriesVisited;
    }

    /**
     * Sets the value of the countriesVisited property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountriesVisited }
     *     
     */
    public void setCountriesVisited(CountriesVisited value) {
        this.countriesVisited = value;
    }

    /**
     * Gets the value of the earlyPaymentDiscountAvailable property.
     * 
     */
    public boolean isEarlyPaymentDiscountAvailable() {
        return earlyPaymentDiscountAvailable;
    }

    /**
     * Sets the value of the earlyPaymentDiscountAvailable property.
     * 
     */
    public void setEarlyPaymentDiscountAvailable(boolean value) {
        this.earlyPaymentDiscountAvailable = value;
    }

    /**
     * Gets the value of the definiteDeparturesAvailable property.
     * 
     */
    public boolean isDefiniteDeparturesAvailable() {
        return definiteDeparturesAvailable;
    }

    /**
     * Sets the value of the definiteDeparturesAvailable property.
     * 
     */
    public void setDefiniteDeparturesAvailable(boolean value) {
        this.definiteDeparturesAvailable = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDuration(BigInteger value) {
        this.duration = value;
    }

    /**
     * Gets the value of the accommodations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accommodations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccommodations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAccommodations() {
        if (accommodations == null) {
            accommodations = new ArrayList<String>();
        }
        return this.accommodations;
    }

    /**
     * Gets the value of the startCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCity() {
        return startCity;
    }

    /**
     * Sets the value of the startCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCity(String value) {
        this.startCity = value;
    }

    /**
     * Gets the value of the airportsStartCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirportsStartCity() {
        return airportsStartCity;
    }

    /**
     * Sets the value of the airportsStartCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirportsStartCity(String value) {
        this.airportsStartCity = value;
    }

    /**
     * Gets the value of the endCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCity() {
        return endCity;
    }

    /**
     * Sets the value of the endCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCity(String value) {
        this.endCity = value;
    }

    /**
     * Gets the value of the airportsEndCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirportsEndCity() {
        return airportsEndCity;
    }

    /**
     * Sets the value of the airportsEndCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirportsEndCity(String value) {
        this.airportsEndCity = value;
    }

    /**
     * Gets the value of the sellableRoomTypes property.
     * 
     * @return
     *     possible object is
     *     {@link RoomTypes }
     *     
     */
    public RoomTypes getSellableRoomTypes() {
        return sellableRoomTypes;
    }

    /**
     * Sets the value of the sellableRoomTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomTypes }
     *     
     */
    public void setSellableRoomTypes(RoomTypes value) {
        this.sellableRoomTypes = value;
    }

    /**
     * Gets the value of the operatingProductCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingProductCode() {
        return operatingProductCode;
    }

    /**
     * Sets the value of the operatingProductCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingProductCode(String value) {
        this.operatingProductCode = value;
    }

    /**
     * Gets the value of the contractingSeason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractingSeason() {
        return contractingSeason;
    }

    /**
     * Sets the value of the contractingSeason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractingSeason(String value) {
        this.contractingSeason = value;
    }

    /**
     * Gets the value of the earliestDepartureStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEarliestDepartureStartDate() {
        return earliestDepartureStartDate;
    }

    /**
     * Sets the value of the earliestDepartureStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEarliestDepartureStartDate(XMLGregorianCalendar value) {
        this.earliestDepartureStartDate = value;
    }

    /**
     * Gets the value of the latestDepartureStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLatestDepartureStartDate() {
        return latestDepartureStartDate;
    }

    /**
     * Sets the value of the latestDepartureStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLatestDepartureStartDate(XMLGregorianCalendar value) {
        this.latestDepartureStartDate = value;
    }

    /**
     * Gets the value of the includedSubProducts property.
     * 
     * @return
     *     possible object is
     *     {@link IncludedSubProducts }
     *     
     */
    public IncludedSubProducts getIncludedSubProducts() {
        return includedSubProducts;
    }

    /**
     * Sets the value of the includedSubProducts property.
     * 
     * @param value
     *     allowed object is
     *     {@link IncludedSubProducts }
     *     
     */
    public void setIncludedSubProducts(IncludedSubProducts value) {
        this.includedSubProducts = value;
    }

    /**
     * Gets the value of the includedCruiseCabinType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludedCruiseCabinType() {
        return includedCruiseCabinType;
    }

    /**
     * Sets the value of the includedCruiseCabinType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludedCruiseCabinType(String value) {
        this.includedCruiseCabinType = value;
    }

    /**
     * Gets the value of the additionalDefiners property.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalDefiners }
     *     
     */
    public AdditionalDefiners getAdditionalDefiners() {
        return additionalDefiners;
    }

    /**
     * Sets the value of the additionalDefiners property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalDefiners }
     *     
     */
    public void setAdditionalDefiners(AdditionalDefiners value) {
        this.additionalDefiners = value;
    }

}
