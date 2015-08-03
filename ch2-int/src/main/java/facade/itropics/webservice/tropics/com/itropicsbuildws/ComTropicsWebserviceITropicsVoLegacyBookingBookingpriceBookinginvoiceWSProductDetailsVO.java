
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSProductDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSProductDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amountPaid" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="bookingLineItemID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientIDs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commPct" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="commission" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="departArrTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fromLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="grossPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="invoiceInfoVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceInfoVO" minOccurs="0"/>
 *         &lt;element name="invoiceProductDetailsID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="invoiceProductPymtVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceProductPymtVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="landAirFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noOfClients" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pricePerClient" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSProductDetailsVO", propOrder = {
    "amountPaid",
    "bookingLineItemID",
    "category",
    "clientIDs",
    "commPct",
    "commission",
    "departArrTime",
    "discount",
    "duration",
    "fromLoc",
    "grossPrice",
    "invoiceInfoVO",
    "invoiceProductDetailsID",
    "invoiceProductPymtVOCollection",
    "landAirFlag",
    "noOfClients",
    "pricePerClient",
    "productCode",
    "productName",
    "productType",
    "startDate",
    "status",
    "toLoc"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO {

    protected float amountPaid;
    protected long bookingLineItemID;
    protected String category;
    protected String clientIDs;
    protected float commPct;
    protected float commission;
    protected String departArrTime;
    protected float discount;
    protected int duration;
    protected String fromLoc;
    protected float grossPrice;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO invoiceInfoVO;
    protected long invoiceProductDetailsID;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceProductPymtVO> invoiceProductPymtVOCollection;
    protected String landAirFlag;
    protected int noOfClients;
    protected float pricePerClient;
    protected String productCode;
    protected String productName;
    protected String productType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    protected String status;
    protected String toLoc;

    /**
     * Gets the value of the amountPaid property.
     * 
     */
    public float getAmountPaid() {
        return amountPaid;
    }

    /**
     * Sets the value of the amountPaid property.
     * 
     */
    public void setAmountPaid(float value) {
        this.amountPaid = value;
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
     * Gets the value of the clientIDs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientIDs() {
        return clientIDs;
    }

    /**
     * Sets the value of the clientIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientIDs(String value) {
        this.clientIDs = value;
    }

    /**
     * Gets the value of the commPct property.
     * 
     */
    public float getCommPct() {
        return commPct;
    }

    /**
     * Sets the value of the commPct property.
     * 
     */
    public void setCommPct(float value) {
        this.commPct = value;
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
     * Gets the value of the departArrTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartArrTime() {
        return departArrTime;
    }

    /**
     * Sets the value of the departArrTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartArrTime(String value) {
        this.departArrTime = value;
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
     * Gets the value of the invoiceInfoVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO getInvoiceInfoVO() {
        return invoiceInfoVO;
    }

    /**
     * Sets the value of the invoiceInfoVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO }
     *     
     */
    public void setInvoiceInfoVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO value) {
        this.invoiceInfoVO = value;
    }

    /**
     * Gets the value of the invoiceProductDetailsID property.
     * 
     */
    public long getInvoiceProductDetailsID() {
        return invoiceProductDetailsID;
    }

    /**
     * Sets the value of the invoiceProductDetailsID property.
     * 
     */
    public void setInvoiceProductDetailsID(long value) {
        this.invoiceProductDetailsID = value;
    }

    /**
     * Gets the value of the invoiceProductPymtVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceProductPymtVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoiceProductPymtVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceProductPymtVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceProductPymtVO> getInvoiceProductPymtVOCollection() {
        if (invoiceProductPymtVOCollection == null) {
            invoiceProductPymtVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceProductPymtVO>();
        }
        return this.invoiceProductPymtVOCollection;
    }

    /**
     * Gets the value of the landAirFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLandAirFlag() {
        return landAirFlag;
    }

    /**
     * Sets the value of the landAirFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLandAirFlag(String value) {
        this.landAirFlag = value;
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
     * Gets the value of the pricePerClient property.
     * 
     */
    public float getPricePerClient() {
        return pricePerClient;
    }

    /**
     * Sets the value of the pricePerClient property.
     * 
     */
    public void setPricePerClient(float value) {
        this.pricePerClient = value;
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

}
