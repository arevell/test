
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSClientAmendCancelFeesVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSClientAmendCancelFeesVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingClientExternalFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="bookingClientTTCFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="clientID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isFeeChanged" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productAmendCancelFeesVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSProductAmendCancelFeesVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="seqClientID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totCurExtFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totCurTTCFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totGrossPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totRevExtFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totRevTTCFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalClientFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSClientAmendCancelFeesVO", propOrder = {
    "bookingClientExternalFees",
    "bookingClientTTCFees",
    "clientID",
    "clientName",
    "isFeeChanged",
    "productAmendCancelFeesVOCollection",
    "seqClientID",
    "totCurExtFees",
    "totCurTTCFees",
    "totGrossPrice",
    "totRevExtFees",
    "totRevTTCFees",
    "totalClientFees"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSClientAmendCancelFeesVO {

    protected float bookingClientExternalFees;
    protected float bookingClientTTCFees;
    protected long clientID;
    protected String clientName;
    protected String isFeeChanged;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSProductAmendCancelFeesVO> productAmendCancelFeesVOCollection;
    protected String seqClientID;
    protected float totCurExtFees;
    protected float totCurTTCFees;
    protected float totGrossPrice;
    protected float totRevExtFees;
    protected float totRevTTCFees;
    protected float totalClientFees;

    /**
     * Gets the value of the bookingClientExternalFees property.
     * 
     */
    public float getBookingClientExternalFees() {
        return bookingClientExternalFees;
    }

    /**
     * Sets the value of the bookingClientExternalFees property.
     * 
     */
    public void setBookingClientExternalFees(float value) {
        this.bookingClientExternalFees = value;
    }

    /**
     * Gets the value of the bookingClientTTCFees property.
     * 
     */
    public float getBookingClientTTCFees() {
        return bookingClientTTCFees;
    }

    /**
     * Sets the value of the bookingClientTTCFees property.
     * 
     */
    public void setBookingClientTTCFees(float value) {
        this.bookingClientTTCFees = value;
    }

    /**
     * Gets the value of the clientID property.
     * 
     */
    public long getClientID() {
        return clientID;
    }

    /**
     * Sets the value of the clientID property.
     * 
     */
    public void setClientID(long value) {
        this.clientID = value;
    }

    /**
     * Gets the value of the clientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the value of the clientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientName(String value) {
        this.clientName = value;
    }

    /**
     * Gets the value of the isFeeChanged property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsFeeChanged() {
        return isFeeChanged;
    }

    /**
     * Sets the value of the isFeeChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsFeeChanged(String value) {
        this.isFeeChanged = value;
    }

    /**
     * Gets the value of the productAmendCancelFeesVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productAmendCancelFeesVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductAmendCancelFeesVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSProductAmendCancelFeesVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSProductAmendCancelFeesVO> getProductAmendCancelFeesVOCollection() {
        if (productAmendCancelFeesVOCollection == null) {
            productAmendCancelFeesVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSProductAmendCancelFeesVO>();
        }
        return this.productAmendCancelFeesVOCollection;
    }

    /**
     * Gets the value of the seqClientID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeqClientID() {
        return seqClientID;
    }

    /**
     * Sets the value of the seqClientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeqClientID(String value) {
        this.seqClientID = value;
    }

    /**
     * Gets the value of the totCurExtFees property.
     * 
     */
    public float getTotCurExtFees() {
        return totCurExtFees;
    }

    /**
     * Sets the value of the totCurExtFees property.
     * 
     */
    public void setTotCurExtFees(float value) {
        this.totCurExtFees = value;
    }

    /**
     * Gets the value of the totCurTTCFees property.
     * 
     */
    public float getTotCurTTCFees() {
        return totCurTTCFees;
    }

    /**
     * Sets the value of the totCurTTCFees property.
     * 
     */
    public void setTotCurTTCFees(float value) {
        this.totCurTTCFees = value;
    }

    /**
     * Gets the value of the totGrossPrice property.
     * 
     */
    public float getTotGrossPrice() {
        return totGrossPrice;
    }

    /**
     * Sets the value of the totGrossPrice property.
     * 
     */
    public void setTotGrossPrice(float value) {
        this.totGrossPrice = value;
    }

    /**
     * Gets the value of the totRevExtFees property.
     * 
     */
    public float getTotRevExtFees() {
        return totRevExtFees;
    }

    /**
     * Sets the value of the totRevExtFees property.
     * 
     */
    public void setTotRevExtFees(float value) {
        this.totRevExtFees = value;
    }

    /**
     * Gets the value of the totRevTTCFees property.
     * 
     */
    public float getTotRevTTCFees() {
        return totRevTTCFees;
    }

    /**
     * Sets the value of the totRevTTCFees property.
     * 
     */
    public void setTotRevTTCFees(float value) {
        this.totRevTTCFees = value;
    }

    /**
     * Gets the value of the totalClientFees property.
     * 
     */
    public float getTotalClientFees() {
        return totalClientFees;
    }

    /**
     * Sets the value of the totalClientFees property.
     * 
     */
    public void setTotalClientFees(float value) {
        this.totalClientFees = value;
    }

}
