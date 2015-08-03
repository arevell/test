
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amountPaid" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="comm" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="dueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dueDateChanged" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="grossPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="invoiceInfoVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSInvoiceInfoVO" minOccurs="0"/>
 *         &lt;element name="nettPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pymtPlanID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pymtReceivedFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pymtType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtDetailsVO", propOrder = {
    "amountPaid",
    "comm",
    "discount",
    "dueDate",
    "dueDateChanged",
    "endDate",
    "grossPrice",
    "invoiceInfoVO",
    "nettPrice",
    "productCode",
    "productName",
    "pymtPlanID",
    "pymtReceivedFlag",
    "pymtType",
    "startDate"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtDetailsVO {

    protected float amountPaid;
    protected float comm;
    protected float discount;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dueDate;
    protected String dueDateChanged;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected float grossPrice;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSInvoiceInfoVO invoiceInfoVO;
    protected float nettPrice;
    protected String productCode;
    protected String productName;
    protected long pymtPlanID;
    protected String pymtReceivedFlag;
    protected String pymtType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;

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
     * Gets the value of the comm property.
     * 
     */
    public float getComm() {
        return comm;
    }

    /**
     * Sets the value of the comm property.
     * 
     */
    public void setComm(float value) {
        this.comm = value;
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
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDate(XMLGregorianCalendar value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the dueDateChanged property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDueDateChanged() {
        return dueDateChanged;
    }

    /**
     * Sets the value of the dueDateChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDueDateChanged(String value) {
        this.dueDateChanged = value;
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
     * Gets the value of the pymtPlanID property.
     * 
     */
    public long getPymtPlanID() {
        return pymtPlanID;
    }

    /**
     * Sets the value of the pymtPlanID property.
     * 
     */
    public void setPymtPlanID(long value) {
        this.pymtPlanID = value;
    }

    /**
     * Gets the value of the pymtReceivedFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPymtReceivedFlag() {
        return pymtReceivedFlag;
    }

    /**
     * Sets the value of the pymtReceivedFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPymtReceivedFlag(String value) {
        this.pymtReceivedFlag = value;
    }

    /**
     * Gets the value of the pymtType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPymtType() {
        return pymtType;
    }

    /**
     * Sets the value of the pymtType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPymtType(String value) {
        this.pymtType = value;
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

}
