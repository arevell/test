
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtTranVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtTranVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="bookingTransactionID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="creditCardDetailsVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_finance_WSCreditCardDetailsVO" minOccurs="0"/>
 *         &lt;element name="displayMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pymtMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiptFeeVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsReceiptFeeVO" minOccurs="0"/>
 *         &lt;element name="referenceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tranDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="transactionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtTranVO", propOrder = {
    "amount",
    "bookingTransactionID",
    "creditCardDetailsVO",
    "displayMode",
    "payerName",
    "pymtMode",
    "receiptFeeVO",
    "referenceNo",
    "remarks",
    "tranDate",
    "transactionType"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtTranVO {

    protected float amount;
    protected long bookingTransactionID;
    protected ComTropicsWebserviceITropicsVoLegacyFinanceWSCreditCardDetailsVO creditCardDetailsVO;
    protected String displayMode;
    protected String payerName;
    protected String pymtMode;
    protected WsReceiptFeeVO receiptFeeVO;
    protected String referenceNo;
    protected String remarks;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tranDate;
    protected String transactionType;

    /**
     * Gets the value of the amount property.
     * 
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(float value) {
        this.amount = value;
    }

    /**
     * Gets the value of the bookingTransactionID property.
     * 
     */
    public long getBookingTransactionID() {
        return bookingTransactionID;
    }

    /**
     * Sets the value of the bookingTransactionID property.
     * 
     */
    public void setBookingTransactionID(long value) {
        this.bookingTransactionID = value;
    }

    /**
     * Gets the value of the creditCardDetailsVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyFinanceWSCreditCardDetailsVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyFinanceWSCreditCardDetailsVO getCreditCardDetailsVO() {
        return creditCardDetailsVO;
    }

    /**
     * Sets the value of the creditCardDetailsVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyFinanceWSCreditCardDetailsVO }
     *     
     */
    public void setCreditCardDetailsVO(ComTropicsWebserviceITropicsVoLegacyFinanceWSCreditCardDetailsVO value) {
        this.creditCardDetailsVO = value;
    }

    /**
     * Gets the value of the displayMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayMode() {
        return displayMode;
    }

    /**
     * Sets the value of the displayMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayMode(String value) {
        this.displayMode = value;
    }

    /**
     * Gets the value of the payerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayerName() {
        return payerName;
    }

    /**
     * Sets the value of the payerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayerName(String value) {
        this.payerName = value;
    }

    /**
     * Gets the value of the pymtMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPymtMode() {
        return pymtMode;
    }

    /**
     * Sets the value of the pymtMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPymtMode(String value) {
        this.pymtMode = value;
    }

    /**
     * Gets the value of the receiptFeeVO property.
     * 
     * @return
     *     possible object is
     *     {@link WsReceiptFeeVO }
     *     
     */
    public WsReceiptFeeVO getReceiptFeeVO() {
        return receiptFeeVO;
    }

    /**
     * Sets the value of the receiptFeeVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsReceiptFeeVO }
     *     
     */
    public void setReceiptFeeVO(WsReceiptFeeVO value) {
        this.receiptFeeVO = value;
    }

    /**
     * Gets the value of the referenceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceNo() {
        return referenceNo;
    }

    /**
     * Sets the value of the referenceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceNo(String value) {
        this.referenceNo = value;
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
     * Gets the value of the tranDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTranDate() {
        return tranDate;
    }

    /**
     * Sets the value of the tranDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTranDate(XMLGregorianCalendar value) {
        this.tranDate = value;
    }

    /**
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionType(String value) {
        this.transactionType = value;
    }

}
