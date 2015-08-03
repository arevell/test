
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSPriceDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSPriceDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingLineItemId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientProductID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="commission" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="grossPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="gstVat" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ITropicsProdDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nettPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsDiscountDetailsVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSDiscountDetailsVOCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_pricecommon_WSPriceDetailsVO", propOrder = {
    "bookingLineItemId",
    "clientProductID",
    "commission",
    "discount",
    "endDate",
    "grossPrice",
    "gstVat",
    "iTropicsProdDescription",
    "nettPrice",
    "productCode",
    "productId",
    "productName",
    "productType",
    "startDate",
    "status",
    "wsDiscountDetailsVOCollection"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSPriceDetailsVO {

    protected long bookingLineItemId;
    protected long clientProductID;
    protected float commission;
    protected float discount;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected float grossPrice;
    protected float gstVat;
    @XmlElement(name = "ITropicsProdDescription")
    protected String iTropicsProdDescription;
    protected float nettPrice;
    protected String productCode;
    protected long productId;
    protected String productName;
    protected String productType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    protected String status;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSDiscountDetailsVOCollection wsDiscountDetailsVOCollection;

    /**
     * Gets the value of the bookingLineItemId property.
     * 
     */
    public long getBookingLineItemId() {
        return bookingLineItemId;
    }

    /**
     * Sets the value of the bookingLineItemId property.
     * 
     */
    public void setBookingLineItemId(long value) {
        this.bookingLineItemId = value;
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
     * Gets the value of the gstVat property.
     * 
     */
    public float getGstVat() {
        return gstVat;
    }

    /**
     * Sets the value of the gstVat property.
     * 
     */
    public void setGstVat(float value) {
        this.gstVat = value;
    }

    /**
     * Gets the value of the iTropicsProdDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITropicsProdDescription() {
        return iTropicsProdDescription;
    }

    /**
     * Sets the value of the iTropicsProdDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITropicsProdDescription(String value) {
        this.iTropicsProdDescription = value;
    }

    /**
     * Gets the value of the nettPrice property.
     * 
     */
    public float getNettPrice() {
        return nettPrice;
    }

    /**
     * Sets the value of the nettPrice property.
     * 
     */
    public void setNettPrice(float value) {
        this.nettPrice = value;
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
     * Gets the value of the productId property.
     * 
     */
    public long getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     */
    public void setProductId(long value) {
        this.productId = value;
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
     * Gets the value of the wsDiscountDetailsVOCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSDiscountDetailsVOCollection }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSDiscountDetailsVOCollection getWsDiscountDetailsVOCollection() {
        return wsDiscountDetailsVOCollection;
    }

    /**
     * Sets the value of the wsDiscountDetailsVOCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSDiscountDetailsVOCollection }
     *     
     */
    public void setWsDiscountDetailsVOCollection(ComTropicsWebserviceITropicsVoLegacyBookingBookingpricePricecommonWSDiscountDetailsVOCollection value) {
        this.wsDiscountDetailsVOCollection = value;
    }

}
