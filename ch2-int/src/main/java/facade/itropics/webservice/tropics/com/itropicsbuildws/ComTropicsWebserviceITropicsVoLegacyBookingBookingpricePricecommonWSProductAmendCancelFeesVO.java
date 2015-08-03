
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSProductAmendCancelFeesVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSProductAmendCancelFeesVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingLineItemID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientProductID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="commission" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="commissionPct" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="externalFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="feesBreakupID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="grossPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="isFeeChanged" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newExternalFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="newTTCFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="noOfClients" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="TTCFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalFees" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSProductAmendCancelFeesVO", propOrder = {
    "bookingLineItemID",
    "clientProductID",
    "commission",
    "commissionPct",
    "externalFees",
    "feesBreakupID",
    "grossPrice",
    "isFeeChanged",
    "newExternalFees",
    "newTTCFees",
    "noOfClients",
    "productName",
    "productType",
    "startDate",
    "ttcFees",
    "totalFees"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSProductAmendCancelFeesVO {

    protected long bookingLineItemID;
    protected long clientProductID;
    protected float commission;
    protected float commissionPct;
    protected float externalFees;
    protected long feesBreakupID;
    protected float grossPrice;
    protected String isFeeChanged;
    protected float newExternalFees;
    protected float newTTCFees;
    protected int noOfClients;
    protected String productName;
    protected String productType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "TTCFees")
    protected float ttcFees;
    protected float totalFees;

    /**
     * Gets the value of the bookingLineItemID property.
     * 
     */
    public long getBookingLineItemID() {
        return bookingLineItemID;
    }

    /**
     * Sets the value of the bookingLineItemID property.
     * 
     */
    public void setBookingLineItemID(long value) {
        this.bookingLineItemID = value;
    }

    /**
     * Gets the value of the clientProductID property.
     * 
     */
    public long getClientProductID() {
        return clientProductID;
    }

    /**
     * Sets the value of the clientProductID property.
     * 
     */
    public void setClientProductID(long value) {
        this.clientProductID = value;
    }

    /**
     * Gets the value of the commission property.
     * 
     */
    public float getCommission() {
        return commission;
    }

    /**
     * Sets the value of the commission property.
     * 
     */
    public void setCommission(float value) {
        this.commission = value;
    }

    /**
     * Gets the value of the commissionPct property.
     * 
     */
    public float getCommissionPct() {
        return commissionPct;
    }

    /**
     * Sets the value of the commissionPct property.
     * 
     */
    public void setCommissionPct(float value) {
        this.commissionPct = value;
    }

    /**
     * Gets the value of the externalFees property.
     * 
     */
    public float getExternalFees() {
        return externalFees;
    }

    /**
     * Sets the value of the externalFees property.
     * 
     */
    public void setExternalFees(float value) {
        this.externalFees = value;
    }

    /**
     * Gets the value of the feesBreakupID property.
     * 
     */
    public long getFeesBreakupID() {
        return feesBreakupID;
    }

    /**
     * Sets the value of the feesBreakupID property.
     * 
     */
    public void setFeesBreakupID(long value) {
        this.feesBreakupID = value;
    }

    /**
     * Gets the value of the grossPrice property.
     * 
     */
    public float getGrossPrice() {
        return grossPrice;
    }

    /**
     * Sets the value of the grossPrice property.
     * 
     */
    public void setGrossPrice(float value) {
        this.grossPrice = value;
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
     * Gets the value of the newExternalFees property.
     * 
     */
    public float getNewExternalFees() {
        return newExternalFees;
    }

    /**
     * Sets the value of the newExternalFees property.
     * 
     */
    public void setNewExternalFees(float value) {
        this.newExternalFees = value;
    }

    /**
     * Gets the value of the newTTCFees property.
     * 
     */
    public float getNewTTCFees() {
        return newTTCFees;
    }

    /**
     * Sets the value of the newTTCFees property.
     * 
     */
    public void setNewTTCFees(float value) {
        this.newTTCFees = value;
    }

    /**
     * Gets the value of the noOfClients property.
     * 
     */
    public int getNoOfClients() {
        return noOfClients;
    }

    /**
     * Sets the value of the noOfClients property.
     * 
     */
    public void setNoOfClients(int value) {
        this.noOfClients = value;
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
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
     * Gets the value of the ttcFees property.
     * 
     */
    public float getTTCFees() {
        return ttcFees;
    }

    /**
     * Sets the value of the ttcFees property.
     * 
     */
    public void setTTCFees(float value) {
        this.ttcFees = value;
    }

    /**
     * Gets the value of the totalFees property.
     * 
     */
    public float getTotalFees() {
        return totalFees;
    }

    /**
     * Sets the value of the totalFees property.
     * 
     */
    public void setTotalFees(float value) {
        this.totalFees = value;
    }

}
