
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DiscountType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiscountType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Code" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}string"/>
 *         &lt;element name="Type" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}string"/>
 *         &lt;element name="Basis" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}string"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="IsPercentage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PaymentDueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscountType", propOrder = {
    "code",
    "type",
    "basis",
    "amount",
    "isPercentage",
    "paymentDueDate"
})
public class DiscountType {

    @XmlElement(name = "Code", required = true)
    protected String code;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Basis", required = true)
    protected String basis;
    @XmlElement(name = "Amount", required = true)
    protected BigDecimal amount;
    @XmlElement(name = "IsPercentage")
    protected boolean isPercentage;
    @XmlElement(name = "PaymentDueDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar paymentDueDate;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the basis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasis() {
        return basis;
    }

    /**
     * Sets the value of the basis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasis(String value) {
        this.basis = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the isPercentage property.
     * 
     */
    public boolean isIsPercentage() {
        return isPercentage;
    }

    /**
     * Sets the value of the isPercentage property.
     * 
     */
    public void setIsPercentage(boolean value) {
        this.isPercentage = value;
    }

    /**
     * Gets the value of the paymentDueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPaymentDueDate() {
        return paymentDueDate;
    }

    /**
     * Sets the value of the paymentDueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPaymentDueDate(XMLGregorianCalendar value) {
        this.paymentDueDate = value;
    }

}
