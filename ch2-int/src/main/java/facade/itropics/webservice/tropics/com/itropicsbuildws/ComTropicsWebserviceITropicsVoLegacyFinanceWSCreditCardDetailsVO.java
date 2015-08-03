
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
 * <p>Java class for com_tropics_webservice_iTropics_vo_legacy_finance_WSCreditCardDetailsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="com_tropics_webservice_iTropics_vo_legacy_finance_WSCreditCardDetailsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressVO" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}com_tropics_webservice_iTropics_vo_legacy_commonvo_WSAddressVO" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="authenShopperID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CV2Number" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cardHolderFName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardHolderLName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="concurrencyCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="creditCardID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="creditCardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditCardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditCardTypeID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="debitCreditIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="delFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endMonth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="exponent" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="issueNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="lastEvent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="orderCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="selectedCompanyList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="startMonth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="warning" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "com_tropics_webservice_iTropics_vo_legacy_finance_WSCreditCardDetailsVO", propOrder = {
    "addressVO",
    "amount",
    "authenShopperID",
    "cv2Number",
    "cardHolderFName",
    "cardHolderLName",
    "concurrencyCounter",
    "createdBy",
    "createdDate",
    "creditCardID",
    "creditCardNo",
    "creditCardType",
    "creditCardTypeID",
    "currencyCode",
    "debitCreditIndicator",
    "delFlag",
    "endMonth",
    "endYear",
    "error",
    "exponent",
    "issueNo",
    "lastEvent",
    "lastModifiedBy",
    "lastModifiedDate",
    "orderCode",
    "selectedCompanyList",
    "startMonth",
    "startYear",
    "success",
    "warning"
})
public class ComTropicsWebserviceITropicsVoLegacyFinanceWSCreditCardDetailsVO {

    protected ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO addressVO;
    protected float amount;
    protected long authenShopperID;
    @XmlElement(name = "CV2Number")
    protected long cv2Number;
    protected String cardHolderFName;
    protected String cardHolderLName;
    protected int concurrencyCounter;
    protected String createdBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    protected long creditCardID;
    protected String creditCardNo;
    protected String creditCardType;
    protected long creditCardTypeID;
    protected String currencyCode;
    protected String debitCreditIndicator;
    protected String delFlag;
    protected String endMonth;
    protected String endYear;
    @XmlElement(nillable = true)
    protected List<String> error;
    protected int exponent;
    protected long issueNo;
    protected String lastEvent;
    protected String lastModifiedBy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedDate;
    protected String orderCode;
    @XmlElement(type = Long.class)
    protected List<Long> selectedCompanyList;
    protected String startMonth;
    protected String startYear;
    @XmlElement(nillable = true)
    protected List<String> success;
    @XmlElement(nillable = true)
    protected List<String> warning;

    /**
     * Gets the value of the addressVO property.
     * 
     * @return
     *     possible object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO }
     *     
     */
    public ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO getAddressVO() {
        return addressVO;
    }

    /**
     * Sets the value of the addressVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO }
     *     
     */
    public void setAddressVO(ComTropicsWebserviceITropicsVoLegacyCommonvoWSAddressVO value) {
        this.addressVO = value;
    }

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
     * Gets the value of the authenShopperID property.
     * 
     */
    public long getAuthenShopperID() {
        return authenShopperID;
    }

    /**
     * Sets the value of the authenShopperID property.
     * 
     */
    public void setAuthenShopperID(long value) {
        this.authenShopperID = value;
    }

    /**
     * Gets the value of the cv2Number property.
     * 
     */
    public long getCV2Number() {
        return cv2Number;
    }

    /**
     * Sets the value of the cv2Number property.
     * 
     */
    public void setCV2Number(long value) {
        this.cv2Number = value;
    }

    /**
     * Gets the value of the cardHolderFName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardHolderFName() {
        return cardHolderFName;
    }

    /**
     * Sets the value of the cardHolderFName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardHolderFName(String value) {
        this.cardHolderFName = value;
    }

    /**
     * Gets the value of the cardHolderLName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardHolderLName() {
        return cardHolderLName;
    }

    /**
     * Sets the value of the cardHolderLName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardHolderLName(String value) {
        this.cardHolderLName = value;
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
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDate(XMLGregorianCalendar value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the creditCardID property.
     * 
     */
    public long getCreditCardID() {
        return creditCardID;
    }

    /**
     * Sets the value of the creditCardID property.
     * 
     */
    public void setCreditCardID(long value) {
        this.creditCardID = value;
    }

    /**
     * Gets the value of the creditCardNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardNo() {
        return creditCardNo;
    }

    /**
     * Sets the value of the creditCardNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardNo(String value) {
        this.creditCardNo = value;
    }

    /**
     * Gets the value of the creditCardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardType() {
        return creditCardType;
    }

    /**
     * Sets the value of the creditCardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardType(String value) {
        this.creditCardType = value;
    }

    /**
     * Gets the value of the creditCardTypeID property.
     * 
     */
    public long getCreditCardTypeID() {
        return creditCardTypeID;
    }

    /**
     * Sets the value of the creditCardTypeID property.
     * 
     */
    public void setCreditCardTypeID(long value) {
        this.creditCardTypeID = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the debitCreditIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitCreditIndicator() {
        return debitCreditIndicator;
    }

    /**
     * Sets the value of the debitCreditIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitCreditIndicator(String value) {
        this.debitCreditIndicator = value;
    }

    /**
     * Gets the value of the delFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * Sets the value of the delFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelFlag(String value) {
        this.delFlag = value;
    }

    /**
     * Gets the value of the endMonth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndMonth() {
        return endMonth;
    }

    /**
     * Sets the value of the endMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndMonth(String value) {
        this.endMonth = value;
    }

    /**
     * Gets the value of the endYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndYear() {
        return endYear;
    }

    /**
     * Sets the value of the endYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndYear(String value) {
        this.endYear = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the error property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getError() {
        if (error == null) {
            error = new ArrayList<String>();
        }
        return this.error;
    }

    /**
     * Gets the value of the exponent property.
     * 
     */
    public int getExponent() {
        return exponent;
    }

    /**
     * Sets the value of the exponent property.
     * 
     */
    public void setExponent(int value) {
        this.exponent = value;
    }

    /**
     * Gets the value of the issueNo property.
     * 
     */
    public long getIssueNo() {
        return issueNo;
    }

    /**
     * Sets the value of the issueNo property.
     * 
     */
    public void setIssueNo(long value) {
        this.issueNo = value;
    }

    /**
     * Gets the value of the lastEvent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastEvent() {
        return lastEvent;
    }

    /**
     * Sets the value of the lastEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastEvent(String value) {
        this.lastEvent = value;
    }

    /**
     * Gets the value of the lastModifiedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets the value of the lastModifiedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModifiedBy(String value) {
        this.lastModifiedBy = value;
    }

    /**
     * Gets the value of the lastModifiedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Sets the value of the lastModifiedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModifiedDate(XMLGregorianCalendar value) {
        this.lastModifiedDate = value;
    }

    /**
     * Gets the value of the orderCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * Sets the value of the orderCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderCode(String value) {
        this.orderCode = value;
    }

    /**
     * Gets the value of the selectedCompanyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectedCompanyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectedCompanyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getSelectedCompanyList() {
        if (selectedCompanyList == null) {
            selectedCompanyList = new ArrayList<Long>();
        }
        return this.selectedCompanyList;
    }

    /**
     * Gets the value of the startMonth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartMonth() {
        return startMonth;
    }

    /**
     * Sets the value of the startMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartMonth(String value) {
        this.startMonth = value;
    }

    /**
     * Gets the value of the startYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartYear() {
        return startYear;
    }

    /**
     * Sets the value of the startYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartYear(String value) {
        this.startYear = value;
    }

    /**
     * Gets the value of the success property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the success property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuccess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSuccess() {
        if (success == null) {
            success = new ArrayList<String>();
        }
        return this.success;
    }

    /**
     * Gets the value of the warning property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the warning property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWarning().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getWarning() {
        if (warning == null) {
            warning = new ArrayList<String>();
        }
        return this.warning;
    }

}
