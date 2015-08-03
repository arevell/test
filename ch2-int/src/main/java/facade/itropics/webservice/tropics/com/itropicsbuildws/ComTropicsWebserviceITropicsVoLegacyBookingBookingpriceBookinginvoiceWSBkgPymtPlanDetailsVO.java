
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSBkgPymtPlanDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSBkgPymtPlanDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amountpaid" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="autoCancelDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="autoCancelDateChanged" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="autocanceltimes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="bkgID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bookingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="bookingID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardTypesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSCreditCardMasterVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="concurrencyCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="creditAgreementFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lineItemIDArray" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nettPaymentFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payerFName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payerLName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pymtModesArray" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSMasterDataVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pymtPlanDetailsVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtPlanDetailsVO" minOccurs="0"/>
 *         &lt;element name="pymtTranDetailsVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSPymtTranDetailsVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_booking_bookingprice_bookinginvoice_WSBkgPymtPlanDetailsVO", propOrder = {
    "amountpaid",
    "autoCancelDate",
    "autoCancelDateChanged",
    "autocanceltimes",
    "bkgID",
    "bookingDate",
    "bookingID",
    "cardTypesArray",
    "comments",
    "concurrencyCounter",
    "creditAgreementFlag",
    "lineItemIDArray",
    "nettPaymentFlag",
    "payerFName",
    "payerLName",
    "pymtModesArray",
    "pymtPlanDetailsVO",
    "pymtTranDetailsVO"
})
public class ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSBkgPymtPlanDetailsVO {

    protected float amountpaid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar autoCancelDate;
    protected String autoCancelDateChanged;
    protected int autocanceltimes;
    protected long bkgID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar bookingDate;
    protected String bookingID;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSCreditCardMasterVO> cardTypesArray;
    protected String comments;
    protected int concurrencyCounter;
    protected String creditAgreementFlag;
    @XmlElement(nillable = true)
    protected List<Long> lineItemIDArray;
    protected String nettPaymentFlag;
    protected String payerFName;
    protected String payerLName;
    @XmlElement(nillable = true)
    protected List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> pymtModesArray;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtPlanDetailsVO pymtPlanDetailsVO;
    protected ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtTranDetailsVO pymtTranDetailsVO;

    /**
     * Gets the value of the amountpaid property.
     * 
     */
    public float getAmountpaid() {
        return amountpaid;
    }

    /**
     * Sets the value of the amountpaid property.
     * 
     */
    public void setAmountpaid(float value) {
        this.amountpaid = value;
    }

    /**
     * Gets the value of the autoCancelDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAutoCancelDate() {
        return autoCancelDate;
    }

    /**
     * Sets the value of the autoCancelDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAutoCancelDate(XMLGregorianCalendar value) {
        this.autoCancelDate = value;
    }

    /**
     * Gets the value of the autoCancelDateChanged property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoCancelDateChanged() {
        return autoCancelDateChanged;
    }

    /**
     * Sets the value of the autoCancelDateChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoCancelDateChanged(String value) {
        this.autoCancelDateChanged = value;
    }

    /**
     * Gets the value of the autocanceltimes property.
     * 
     */
    public int getAutocanceltimes() {
        return autocanceltimes;
    }

    /**
     * Sets the value of the autocanceltimes property.
     * 
     */
    public void setAutocanceltimes(int value) {
        this.autocanceltimes = value;
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
     * Gets the value of the bookingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBookingDate() {
        return bookingDate;
    }

    /**
     * Sets the value of the bookingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBookingDate(XMLGregorianCalendar value) {
        this.bookingDate = value;
    }

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
     * Gets the value of the cardTypesArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cardTypesArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCardTypesArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSCreditCardMasterVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSCreditCardMasterVO> getCardTypesArray() {
        if (cardTypesArray == null) {
            cardTypesArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyCommonvoWSCreditCardMasterVO>();
        }
        return this.cardTypesArray;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

    /**
     * Gets the value of the concurrencyCounter property.
     * 
     */
    public int getConcurrencyCounter() {
        return concurrencyCounter;
    }

    /**
     * Sets the value of the concurrencyCounter property.
     * 
     */
    public void setConcurrencyCounter(int value) {
        this.concurrencyCounter = value;
    }

    /**
     * Gets the value of the creditAgreementFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditAgreementFlag() {
        return creditAgreementFlag;
    }

    /**
     * Sets the value of the creditAgreementFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditAgreementFlag(String value) {
        this.creditAgreementFlag = value;
    }

    /**
     * Gets the value of the lineItemIDArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineItemIDArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineItemIDArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getLineItemIDArray() {
        if (lineItemIDArray == null) {
            lineItemIDArray = new ArrayList<Long>();
        }
        return this.lineItemIDArray;
    }

    /**
     * Gets the value of the nettPaymentFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNettPaymentFlag() {
        return nettPaymentFlag;
    }

    /**
     * Sets the value of the nettPaymentFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNettPaymentFlag(String value) {
        this.nettPaymentFlag = value;
    }

    /**
     * Gets the value of the payerFName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayerFName() {
        return payerFName;
    }

    /**
     * Sets the value of the payerFName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayerFName(String value) {
        this.payerFName = value;
    }

    /**
     * Gets the value of the payerLName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayerLName() {
        return payerLName;
    }

    /**
     * Sets the value of the payerLName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayerLName(String value) {
        this.payerLName = value;
    }

    /**
     * Gets the value of the pymtModesArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pymtModesArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPymtModesArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO }
     * 
     * 
     */
    public List<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO> getPymtModesArray() {
        if (pymtModesArray == null) {
            pymtModesArray = new ArrayList<ComTropicsWebserviceITropicsVoLegacyCommonvoWSMasterDataVO>();
        }
        return this.pymtModesArray;
    }

    /**
     * Gets the value of the pymtPlanDetailsVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtPlanDetailsVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtPlanDetailsVO getPymtPlanDetailsVO() {
        return pymtPlanDetailsVO;
    }

    /**
     * Sets the value of the pymtPlanDetailsVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtPlanDetailsVO }
     *     
     */
    public void setPymtPlanDetailsVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtPlanDetailsVO value) {
        this.pymtPlanDetailsVO = value;
    }

    /**
     * Gets the value of the pymtTranDetailsVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtTranDetailsVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtTranDetailsVO getPymtTranDetailsVO() {
        return pymtTranDetailsVO;
    }

    /**
     * Sets the value of the pymtTranDetailsVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtTranDetailsVO }
     *     
     */
    public void setPymtTranDetailsVO(ComTropicsWebserviceITropicsVoLegacyBookingBookingpriceBookinginvoiceWSPymtTranDetailsVO value) {
        this.pymtTranDetailsVO = value;
    }

}
