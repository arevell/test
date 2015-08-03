
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSItineraryDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSItineraryDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arrTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="benefitFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bkgID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bookingLineItemID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientProductID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="commission" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="departTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fromLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="grossPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="noOfClients" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="versionAttribute" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingmain_bookingcommon_WSItineraryDetailsVO", propOrder = {
    "arrTime",
    "benefitFlag",
    "bkgID",
    "bookingLineItemID",
    "category",
    "clientProductID",
    "commission",
    "departTime",
    "discount",
    "duration",
    "fromLoc",
    "grossPrice",
    "noOfClients",
    "productCode",
    "productName",
    "productType",
    "startDate",
    "status",
    "toLoc",
    "versionAttribute"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingmainBookingcommonWSItineraryDetailsVO {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar arrTime;
    protected String benefitFlag;
    protected long bkgID;
    protected long bookingLineItemID;
    protected String category;
    protected long clientProductID;
    protected float commission;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar departTime;
    protected float discount;
    protected int duration;
    protected String fromLoc;
    protected float grossPrice;
    protected int noOfClients;
    protected String productCode;
    protected String productName;
    protected String productType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    protected String status;
    protected String toLoc;
    protected long versionAttribute;

    /**
     * Gets the value of the arrTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArrTime() {
        return arrTime;
    }

    /**
     * Sets the value of the arrTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArrTime(XMLGregorianCalendar value) {
        this.arrTime = value;
    }

    /**
     * Gets the value of the benefitFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenefitFlag() {
        return benefitFlag;
    }

    /**
     * Sets the value of the benefitFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenefitFlag(String value) {
        this.benefitFlag = value;
    }

    /**
     * Gets the value of the bkgID property.
     * 
     */
    public long getBkgID() {
        return bkgID;
    }

    /**
     * Sets the value of the bkgID property.
     * 
     */
    public void setBkgID(long value) {
        this.bkgID = value;
    }

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
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
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
     * Gets the value of the departTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartTime() {
        return departTime;
    }

    /**
     * Sets the value of the departTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartTime(XMLGregorianCalendar value) {
        this.departTime = value;
    }

    /**
     * Gets the value of the discount property.
     * 
     */
    public float getDiscount() {
        return discount;
    }

    /**
     * Sets the value of the discount property.
     * 
     */
    public void setDiscount(float value) {
        this.discount = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     */
    public void setDuration(int value) {
        this.duration = value;
    }

    /**
     * Gets the value of the fromLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromLoc() {
        return fromLoc;
    }

    /**
     * Sets the value of the fromLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromLoc(String value) {
        this.fromLoc = value;
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
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the toLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToLoc() {
        return toLoc;
    }

    /**
     * Sets the value of the toLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToLoc(String value) {
        this.toLoc = value;
    }

    /**
     * Gets the value of the versionAttribute property.
     * 
     */
    public long getVersionAttribute() {
        return versionAttribute;
    }

    /**
     * Sets the value of the versionAttribute property.
     * 
     */
    public void setVersionAttribute(long value) {
        this.versionAttribute = value;
    }

}
