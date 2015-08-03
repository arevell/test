
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSBkgInvoiceDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSBkgInvoiceDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookingInvoiceID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryChannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fromAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceDetailsID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="invoiceInfoVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceInfoVO" minOccurs="0"/>
 *         &lt;element name="invoiceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceProductPymtVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceProductPymtVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="invoiceTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="issuedForCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latestInvoiceFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="leadClientAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pymtTranVOCollection" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSProductDetailsVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalAmountPaid" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalBalance" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalCommission" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalDiscount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalGrossPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="travelAgencyAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSBkgInvoiceDetailsVO", propOrder = {
    "bookingID",
    "bookingInvoiceID",
    "currency",
    "deliveryChannel",
    "fromAddress",
    "invoiceDetailsID",
    "invoiceInfoVO",
    "invoiceNo",
    "invoiceProductPymtVOCollection",
    "invoiceTitle",
    "invoiceType",
    "issueDate",
    "issuedForCode",
    "latestInvoiceFlag",
    "leadClientAddress",
    "pymtTranVOCollection",
    "remarks",
    "totalAmountPaid",
    "totalBalance",
    "totalCommission",
    "totalDiscount",
    "totalGrossPrice",
    "travelAgencyAddress"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSBkgInvoiceDetailsVO {

    protected String bookingID;
    protected long bookingInvoiceID;
    protected String currency;
    protected String deliveryChannel;
    protected String fromAddress;
    protected long invoiceDetailsID;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO invoiceInfoVO;
    protected String invoiceNo;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceProductPymtVO> invoiceProductPymtVOCollection;
    protected String invoiceTitle;
    protected String invoiceType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar issueDate;
    protected String issuedForCode;
    protected boolean latestInvoiceFlag;
    protected String leadClientAddress;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO> pymtTranVOCollection;
    protected String remarks;
    protected float totalAmountPaid;
    protected float totalBalance;
    protected float totalCommission;
    protected float totalDiscount;
    protected float totalGrossPrice;
    protected String travelAgencyAddress;

    /**
     * Gets the value of the bookingID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingID() {
        return bookingID;
    }

    /**
     * Sets the value of the bookingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingID(String value) {
        this.bookingID = value;
    }

    /**
     * Gets the value of the bookingInvoiceID property.
     * 
     */
    public long getBookingInvoiceID() {
        return bookingInvoiceID;
    }

    /**
     * Sets the value of the bookingInvoiceID property.
     * 
     */
    public void setBookingInvoiceID(long value) {
        this.bookingInvoiceID = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the deliveryChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryChannel() {
        return deliveryChannel;
    }

    /**
     * Sets the value of the deliveryChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryChannel(String value) {
        this.deliveryChannel = value;
    }

    /**
     * Gets the value of the fromAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * Sets the value of the fromAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromAddress(String value) {
        this.fromAddress = value;
    }

    /**
     * Gets the value of the invoiceDetailsID property.
     * 
     */
    public long getInvoiceDetailsID() {
        return invoiceDetailsID;
    }

    /**
     * Sets the value of the invoiceDetailsID property.
     * 
     */
    public void setInvoiceDetailsID(long value) {
        this.invoiceDetailsID = value;
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
     * Gets the value of the invoiceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * Sets the value of the invoiceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceNo(String value) {
        this.invoiceNo = value;
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
     * Gets the value of the invoiceTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    /**
     * Sets the value of the invoiceTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceTitle(String value) {
        this.invoiceTitle = value;
    }

    /**
     * Gets the value of the invoiceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceType() {
        return invoiceType;
    }

    /**
     * Sets the value of the invoiceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceType(String value) {
        this.invoiceType = value;
    }

    /**
     * Gets the value of the issueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the value of the issueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setIssueDate(XMLGregorianCalendar value) {
        this.issueDate = value;
    }

    /**
     * Gets the value of the issuedForCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuedForCode() {
        return issuedForCode;
    }

    /**
     * Sets the value of the issuedForCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuedForCode(String value) {
        this.issuedForCode = value;
    }

    /**
     * Gets the value of the latestInvoiceFlag property.
     * 
     */
    public boolean isLatestInvoiceFlag() {
        return latestInvoiceFlag;
    }

    /**
     * Sets the value of the latestInvoiceFlag property.
     * 
     */
    public void setLatestInvoiceFlag(boolean value) {
        this.latestInvoiceFlag = value;
    }

    /**
     * Gets the value of the leadClientAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeadClientAddress() {
        return leadClientAddress;
    }

    /**
     * Sets the value of the leadClientAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeadClientAddress(String value) {
        this.leadClientAddress = value;
    }

    /**
     * Gets the value of the pymtTranVOCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pymtTranVOCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPymtTranVOCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO> getPymtTranVOCollection() {
        if (pymtTranVOCollection == null) {
            pymtTranVOCollection = new ArrayList<ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSProductDetailsVO>();
        }
        return this.pymtTranVOCollection;
    }

    /**
     * Gets the value of the remarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarks(String value) {
        this.remarks = value;
    }

    /**
     * Gets the value of the totalAmountPaid property.
     * 
     */
    public float getTotalAmountPaid() {
        return totalAmountPaid;
    }

    /**
     * Sets the value of the totalAmountPaid property.
     * 
     */
    public void setTotalAmountPaid(float value) {
        this.totalAmountPaid = value;
    }

    /**
     * Gets the value of the totalBalance property.
     * 
     */
    public float getTotalBalance() {
        return totalBalance;
    }

    /**
     * Sets the value of the totalBalance property.
     * 
     */
    public void setTotalBalance(float value) {
        this.totalBalance = value;
    }

    /**
     * Gets the value of the totalCommission property.
     * 
     */
    public float getTotalCommission() {
        return totalCommission;
    }

    /**
     * Sets the value of the totalCommission property.
     * 
     */
    public void setTotalCommission(float value) {
        this.totalCommission = value;
    }

    /**
     * Gets the value of the totalDiscount property.
     * 
     */
    public float getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * Sets the value of the totalDiscount property.
     * 
     */
    public void setTotalDiscount(float value) {
        this.totalDiscount = value;
    }

    /**
     * Gets the value of the totalGrossPrice property.
     * 
     */
    public float getTotalGrossPrice() {
        return totalGrossPrice;
    }

    /**
     * Sets the value of the totalGrossPrice property.
     * 
     */
    public void setTotalGrossPrice(float value) {
        this.totalGrossPrice = value;
    }

    /**
     * Gets the value of the travelAgencyAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTravelAgencyAddress() {
        return travelAgencyAddress;
    }

    /**
     * Sets the value of the travelAgencyAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTravelAgencyAddress(String value) {
        this.travelAgencyAddress = value;
    }

}
