
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for wsDepartureVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsDepartureVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DepartureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AvailabilityStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EndDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="DateRangeIncludesTravelTime" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PriceIsIndicative" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="OnlineBookable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DefiniteDeparture" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsEligibleForFrequentTravellerDiscount" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CanSearchForFlights" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Notes" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsiProductNoteVO" minOccurs="0"/>
 *         &lt;element name="StartCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EndCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TourRules" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsTourRulesVO" minOccurs="0"/>
 *         &lt;element name="AssociatedProducts" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsAssociatedProductsVO" minOccurs="0"/>
 *         &lt;element name="Discounts" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsProdDepartureEPDDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsDepartureVO", propOrder = {
    "departureCode",
    "currencyCode",
    "availabilityStatus",
    "startDateTime",
    "endDateTime",
    "dateRangeIncludesTravelTime",
    "priceIsIndicative",
    "onlineBookable",
    "definiteDeparture",
    "isEligibleForFrequentTravellerDiscount",
    "canSearchForFlights",
    "notes",
    "startCity",
    "endCity",
    "tourRules",
    "associatedProducts",
    "discounts"
})
public class WsDepartureVO {

    @XmlElement(name = "DepartureCode")
    protected String departureCode;
    @XmlElement(name = "CurrencyCode")
    protected String currencyCode;
    @XmlElement(name = "AvailabilityStatus")
    protected String availabilityStatus;
    @XmlElement(name = "StartDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDateTime;
    @XmlElement(name = "EndDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDateTime;
    @XmlElement(name = "DateRangeIncludesTravelTime")
    protected Boolean dateRangeIncludesTravelTime;
    @XmlElement(name = "PriceIsIndicative")
    protected Boolean priceIsIndicative;
    @XmlElement(name = "OnlineBookable")
    protected Boolean onlineBookable;
    @XmlElement(name = "DefiniteDeparture")
    protected Boolean definiteDeparture;
    @XmlElement(name = "IsEligibleForFrequentTravellerDiscount")
    protected Boolean isEligibleForFrequentTravellerDiscount;
    @XmlElement(name = "CanSearchForFlights")
    protected Boolean canSearchForFlights;
    @XmlElement(name = "Notes")
    protected WsiProductNoteVO notes;
    @XmlElement(name = "StartCity")
    protected String startCity;
    @XmlElement(name = "EndCity")
    protected String endCity;
    @XmlElement(name = "TourRules")
    protected WsTourRulesVO tourRules;
    @XmlElement(name = "AssociatedProducts")
    protected WsAssociatedProductsVO associatedProducts;
    @XmlElement(name = "Discounts")
    protected WsProdDepartureEPDDetails discounts;

    /**
     * Gets the value of the departureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureCode() {
        return departureCode;
    }

    /**
     * Sets the value of the departureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureCode(String value) {
        this.departureCode = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the availabilityStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    /**
     * Sets the value of the availabilityStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailabilityStatus(String value) {
        this.availabilityStatus = value;
    }

    /**
     * Gets the value of the startDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the value of the startDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDateTime(XMLGregorianCalendar value) {
        this.startDateTime = value;
    }

    /**
     * Gets the value of the endDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDateTime() {
        return endDateTime;
    }

    /**
     * Sets the value of the endDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDateTime(XMLGregorianCalendar value) {
        this.endDateTime = value;
    }

    /**
     * Gets the value of the dateRangeIncludesTravelTime property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateRangeIncludesTravelTime() {
        return dateRangeIncludesTravelTime;
    }

    /**
     * Sets the value of the dateRangeIncludesTravelTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateRangeIncludesTravelTime(Boolean value) {
        this.dateRangeIncludesTravelTime = value;
    }

    /**
     * Gets the value of the priceIsIndicative property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPriceIsIndicative() {
        return priceIsIndicative;
    }

    /**
     * Sets the value of the priceIsIndicative property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPriceIsIndicative(Boolean value) {
        this.priceIsIndicative = value;
    }

    /**
     * Gets the value of the onlineBookable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnlineBookable() {
        return onlineBookable;
    }

    /**
     * Sets the value of the onlineBookable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnlineBookable(Boolean value) {
        this.onlineBookable = value;
    }

    /**
     * Gets the value of the definiteDeparture property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDefiniteDeparture() {
        return definiteDeparture;
    }

    /**
     * Sets the value of the definiteDeparture property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefiniteDeparture(Boolean value) {
        this.definiteDeparture = value;
    }

    /**
     * Gets the value of the isEligibleForFrequentTravellerDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsEligibleForFrequentTravellerDiscount() {
        return isEligibleForFrequentTravellerDiscount;
    }

    /**
     * Sets the value of the isEligibleForFrequentTravellerDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsEligibleForFrequentTravellerDiscount(Boolean value) {
        this.isEligibleForFrequentTravellerDiscount = value;
    }

    /**
     * Gets the value of the canSearchForFlights property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCanSearchForFlights() {
        return canSearchForFlights;
    }

    /**
     * Sets the value of the canSearchForFlights property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCanSearchForFlights(Boolean value) {
        this.canSearchForFlights = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link WsiProductNoteVO }
     *     
     */
    public WsiProductNoteVO getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsiProductNoteVO }
     *     
     */
    public void setNotes(WsiProductNoteVO value) {
        this.notes = value;
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
     * Gets the value of the tourRules property.
     * 
     * @return
     *     possible object is
     *     {@link WsTourRulesVO }
     *     
     */
    public WsTourRulesVO getTourRules() {
        return tourRules;
    }

    /**
     * Sets the value of the tourRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsTourRulesVO }
     *     
     */
    public void setTourRules(WsTourRulesVO value) {
        this.tourRules = value;
    }

    /**
     * Gets the value of the associatedProducts property.
     * 
     * @return
     *     possible object is
     *     {@link WsAssociatedProductsVO }
     *     
     */
    public WsAssociatedProductsVO getAssociatedProducts() {
        return associatedProducts;
    }

    /**
     * Sets the value of the associatedProducts property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsAssociatedProductsVO }
     *     
     */
    public void setAssociatedProducts(WsAssociatedProductsVO value) {
        this.associatedProducts = value;
    }

    /**
     * Gets the value of the discounts property.
     * 
     * @return
     *     possible object is
     *     {@link WsProdDepartureEPDDetails }
     *     
     */
    public WsProdDepartureEPDDetails getDiscounts() {
        return discounts;
    }

    /**
     * Sets the value of the discounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsProdDepartureEPDDetails }
     *     
     */
    public void setDiscounts(WsProdDepartureEPDDetails value) {
        this.discounts = value;
    }

}
