
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirLowFareResultVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirLowFareResultVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="baseBookingLineItemID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fareDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isAmend" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mvIPID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pnr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sellingCompanyID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="tokenId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="utcEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="utcStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAirItineraryPricingInfoVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirItineraryPricingInfoVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsAirItineraryVOArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirItineraryVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_air_booking_WSAirLowFareResultVO", propOrder = {
    "baseBookingLineItemID",
    "endDate",
    "fareDescription",
    "isAmend",
    "mvIPID",
    "pnr",
    "productID",
    "sellingCompanyID",
    "startDate",
    "tokenId",
    "utcEndDate",
    "utcStartDate",
    "wsAirItineraryPricingInfoVO",
    "wsAirItineraryVOArray"
})
public class ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirLowFareResultVO {

    protected long baseBookingLineItemID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected String fareDescription;
    protected boolean isAmend;
    protected long mvIPID;
    protected String pnr;
    protected long productID;
    protected long sellingCompanyID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    protected long tokenId;
    protected String utcEndDate;
    protected String utcStartDate;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryPricingInfoVO> wsAirItineraryPricingInfoVO;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryVO> wsAirItineraryVOArray;

    /**
     * Gets the value of the baseBookingLineItemID property.
     * 
     */
    public long getBaseBookingLineItemID() {
        return baseBookingLineItemID;
    }

    /**
     * Sets the value of the baseBookingLineItemID property.
     * 
     */
    public void setBaseBookingLineItemID(long value) {
        this.baseBookingLineItemID = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the fareDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareDescription() {
        return fareDescription;
    }

    /**
     * Sets the value of the fareDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareDescription(String value) {
        this.fareDescription = value;
    }

    /**
     * Gets the value of the isAmend property.
     * 
     */
    public boolean isIsAmend() {
        return isAmend;
    }

    /**
     * Sets the value of the isAmend property.
     * 
     */
    public void setIsAmend(boolean value) {
        this.isAmend = value;
    }

    /**
     * Gets the value of the mvIPID property.
     * 
     */
    public long getMvIPID() {
        return mvIPID;
    }

    /**
     * Sets the value of the mvIPID property.
     * 
     */
    public void setMvIPID(long value) {
        this.mvIPID = value;
    }

    /**
     * Gets the value of the pnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPnr() {
        return pnr;
    }

    /**
     * Sets the value of the pnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPnr(String value) {
        this.pnr = value;
    }

    /**
     * Gets the value of the productID property.
     * 
     */
    public long getProductID() {
        return productID;
    }

    /**
     * Sets the value of the productID property.
     * 
     */
    public void setProductID(long value) {
        this.productID = value;
    }

    /**
     * Gets the value of the sellingCompanyID property.
     * 
     */
    public long getSellingCompanyID() {
        return sellingCompanyID;
    }

    /**
     * Sets the value of the sellingCompanyID property.
     * 
     */
    public void setSellingCompanyID(long value) {
        this.sellingCompanyID = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the tokenId property.
     * 
     */
    public long getTokenId() {
        return tokenId;
    }

    /**
     * Sets the value of the tokenId property.
     * 
     */
    public void setTokenId(long value) {
        this.tokenId = value;
    }

    /**
     * Gets the value of the utcEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtcEndDate() {
        return utcEndDate;
    }

    /**
     * Sets the value of the utcEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtcEndDate(String value) {
        this.utcEndDate = value;
    }

    /**
     * Gets the value of the utcStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtcStartDate() {
        return utcStartDate;
    }

    /**
     * Sets the value of the utcStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtcStartDate(String value) {
        this.utcStartDate = value;
    }

    /**
     * Gets the value of the wsAirItineraryPricingInfoVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsAirItineraryPricingInfoVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsAirItineraryPricingInfoVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryPricingInfoVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryPricingInfoVO> getWsAirItineraryPricingInfoVO() {
        if (wsAirItineraryPricingInfoVO == null) {
            wsAirItineraryPricingInfoVO = new ArrayList<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryPricingInfoVO>();
        }
        return this.wsAirItineraryPricingInfoVO;
    }

    /**
     * Gets the value of the wsAirItineraryVOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsAirItineraryVOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsAirItineraryVOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryVO> getWsAirItineraryVOArray() {
        if (wsAirItineraryVOArray == null) {
            wsAirItineraryVOArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyAirBookingWSAirItineraryVO>();
        }
        return this.wsAirItineraryVOArray;
    }

}
