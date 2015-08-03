
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirItineraryPricingInfoVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirItineraryPricingInfoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="airTaxVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirTaxVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clientRefId" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fareRuleInfoVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_air_booking_WSFareRuleInfoVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fareType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fuelSurcharge" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ptcFareInfoVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_air_booking_WSPTCFareInfoVO" minOccurs="0"/>
 *         &lt;element name="QTax" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalCalculatedFareAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalCalculatedTax" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalFareAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalFareCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalTax" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ttlDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="validatingCarrierCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirItineraryPricingInfoVO", propOrder = {
    "airTaxVOArray",
    "clientRefId",
    "fareRuleInfoVOArray",
    "fareType",
    "fuelSurcharge",
    "ptcFareInfoVO",
    "qTax",
    "totalCalculatedFareAmount",
    "totalCalculatedTax",
    "totalFareAmount",
    "totalFareCurrencyCode",
    "totalTax",
    "ttlDate",
    "validatingCarrierCode"
})
public class ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryPricingInfoVO {

    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO> airTaxVOArray;
    @XmlElement(nillable = true)
    protected List<String> clientRefId;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSFareRuleInfoVO> fareRuleInfoVOArray;
    protected String fareType;
    protected float fuelSurcharge;
    protected ComTropicsWebserviceITropicsVoLegacyAirBookingWSPTCFareInfoVO ptcFareInfoVO;
    @XmlElement(name = "QTax")
    protected float qTax;
    protected float totalCalculatedFareAmount;
    protected float totalCalculatedTax;
    protected float totalFareAmount;
    protected String totalFareCurrencyCode;
    protected float totalTax;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ttlDate;
    protected String validatingCarrierCode;

    /**
     * Gets the value of the airTaxVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the airTaxVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAirTaxVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO> getAirTaxVOArray() {
        if (airTaxVOArray == null) {
            airTaxVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirTaxVO>();
        }
        return this.airTaxVOArray;
    }

    /**
     * Gets the value of the clientRefId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientRefId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientRefId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getClientRefId() {
        if (clientRefId == null) {
            clientRefId = new ArrayList<String>();
        }
        return this.clientRefId;
    }

    /**
     * Gets the value of the fareRuleInfoVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fareRuleInfoVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFareRuleInfoVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyAirBookingWSFareRuleInfoVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSFareRuleInfoVO> getFareRuleInfoVOArray() {
        if (fareRuleInfoVOArray == null) {
            fareRuleInfoVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyAirBookingWSFareRuleInfoVO>();
        }
        return this.fareRuleInfoVOArray;
    }

    /**
     * Gets the value of the fareType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareType() {
        return fareType;
    }

    /**
     * Sets the value of the fareType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareType(String value) {
        this.fareType = value;
    }

    /**
     * Gets the value of the fuelSurcharge property.
     * 
     */
    public float getFuelSurcharge() {
        return fuelSurcharge;
    }

    /**
     * Sets the value of the fuelSurcharge property.
     * 
     */
    public void setFuelSurcharge(float value) {
        this.fuelSurcharge = value;
    }

    /**
     * Gets the value of the ptcFareInfoVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyAirBookingWSPTCFareInfoVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyAirBookingWSPTCFareInfoVO getPtcFareInfoVO() {
        return ptcFareInfoVO;
    }

    /**
     * Sets the value of the ptcFareInfoVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyAirBookingWSPTCFareInfoVO }
     *     
     */
    public void setPtcFareInfoVO(ComTropicsWebserviceITropicsVoLegacyAirBookingWSPTCFareInfoVO value) {
        this.ptcFareInfoVO = value;
    }

    /**
     * Gets the value of the qTax property.
     * 
     */
    public float getQTax() {
        return qTax;
    }

    /**
     * Sets the value of the qTax property.
     * 
     */
    public void setQTax(float value) {
        this.qTax = value;
    }

    /**
     * Gets the value of the totalCalculatedFareAmount property.
     * 
     */
    public float getTotalCalculatedFareAmount() {
        return totalCalculatedFareAmount;
    }

    /**
     * Sets the value of the totalCalculatedFareAmount property.
     * 
     */
    public void setTotalCalculatedFareAmount(float value) {
        this.totalCalculatedFareAmount = value;
    }

    /**
     * Gets the value of the totalCalculatedTax property.
     * 
     */
    public float getTotalCalculatedTax() {
        return totalCalculatedTax;
    }

    /**
     * Sets the value of the totalCalculatedTax property.
     * 
     */
    public void setTotalCalculatedTax(float value) {
        this.totalCalculatedTax = value;
    }

    /**
     * Gets the value of the totalFareAmount property.
     * 
     */
    public float getTotalFareAmount() {
        return totalFareAmount;
    }

    /**
     * Sets the value of the totalFareAmount property.
     * 
     */
    public void setTotalFareAmount(float value) {
        this.totalFareAmount = value;
    }

    /**
     * Gets the value of the totalFareCurrencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalFareCurrencyCode() {
        return totalFareCurrencyCode;
    }

    /**
     * Sets the value of the totalFareCurrencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalFareCurrencyCode(String value) {
        this.totalFareCurrencyCode = value;
    }

    /**
     * Gets the value of the totalTax property.
     * 
     */
    public float getTotalTax() {
        return totalTax;
    }

    /**
     * Sets the value of the totalTax property.
     * 
     */
    public void setTotalTax(float value) {
        this.totalTax = value;
    }

    /**
     * Gets the value of the ttlDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTtlDate() {
        return ttlDate;
    }

    /**
     * Sets the value of the ttlDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTtlDate(XMLGregorianCalendar value) {
        this.ttlDate = value;
    }

    /**
     * Gets the value of the validatingCarrierCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidatingCarrierCode() {
        return validatingCarrierCode;
    }

    /**
     * Sets the value of the validatingCarrierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidatingCarrierCode(String value) {
        this.validatingCarrierCode = value;
    }

}
