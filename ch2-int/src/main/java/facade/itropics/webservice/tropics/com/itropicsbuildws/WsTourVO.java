
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTourVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTourVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="airPriceIncluded" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="amendableFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="availabilityStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brochurecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departure" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsDepartureArrayVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="duration" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}duration" minOccurs="0"/>
 *         &lt;element name="endLocation" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}location" minOccurs="0"/>
 *         &lt;element name="itropicsSellable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="sellingCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startLocation" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}location" minOccurs="0"/>
 *         &lt;element name="tourCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tourId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tourName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTourVO", propOrder = {
    "airPriceIncluded",
    "amendableFlag",
    "availabilityStatus",
    "brochurecode",
    "currencyCode",
    "departure",
    "duration",
    "endLocation",
    "itropicsSellable",
    "sellingCompanyCode",
    "startLocation",
    "tourCode",
    "tourId",
    "tourName"
})
public class WsTourVO {

    protected Boolean airPriceIncluded;
    protected Boolean amendableFlag;
    protected String availabilityStatus;
    protected String brochurecode;
    protected String currencyCode;
    @XmlElement(nillable = true)
    protected List<WsDepartureArrayVO> departure;
    protected Duration duration;
    protected Location endLocation;
    protected Boolean itropicsSellable;
    protected String sellingCompanyCode;
    protected Location startLocation;
    protected String tourCode;
    protected Long tourId;
    protected String tourName;

    /**
     * Gets the value of the airPriceIncluded property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAirPriceIncluded() {
        return airPriceIncluded;
    }

    /**
     * Sets the value of the airPriceIncluded property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAirPriceIncluded(Boolean value) {
        this.airPriceIncluded = value;
    }

    /**
     * Gets the value of the amendableFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAmendableFlag() {
        return amendableFlag;
    }

    /**
     * Sets the value of the amendableFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAmendableFlag(Boolean value) {
        this.amendableFlag = value;
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
     * Gets the value of the brochurecode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrochurecode() {
        return brochurecode;
    }

    /**
     * Sets the value of the brochurecode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrochurecode(String value) {
        this.brochurecode = value;
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
     * Gets the value of the departure property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the departure property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeparture().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsDepartureArrayVO }
     * 
     * 
     */
    public List<WsDepartureArrayVO> getDeparture() {
        if (departure == null) {
            departure = new ArrayList<WsDepartureArrayVO>();
        }
        return this.departure;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setDuration(Duration value) {
        this.duration = value;
    }

    /**
     * Gets the value of the endLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getEndLocation() {
        return endLocation;
    }

    /**
     * Sets the value of the endLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setEndLocation(Location value) {
        this.endLocation = value;
    }

    /**
     * Gets the value of the itropicsSellable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isItropicsSellable() {
        return itropicsSellable;
    }

    /**
     * Sets the value of the itropicsSellable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setItropicsSellable(Boolean value) {
        this.itropicsSellable = value;
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
     * Gets the value of the startLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getStartLocation() {
        return startLocation;
    }

    /**
     * Sets the value of the startLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setStartLocation(Location value) {
        this.startLocation = value;
    }

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
     * Gets the value of the tourId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTourId() {
        return tourId;
    }

    /**
     * Sets the value of the tourId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTourId(Long value) {
        this.tourId = value;
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

}
