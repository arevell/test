
package com.ttsl.marketlocalisedtourdata._2010._11._1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="AmountIsAbsolute" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="SurchargeAmount" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="SurchargeDescription" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SurchargeType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Surcharge")
public class Surcharge {

    @XmlAttribute(name = "AmountIsAbsolute", required = true)
    protected boolean amountIsAbsolute;
    @XmlAttribute(name = "SurchargeAmount", required = true)
    protected BigDecimal surchargeAmount;
    @XmlAttribute(name = "SurchargeDescription", required = true)
    protected String surchargeDescription;
    @XmlAttribute(name = "SurchargeType", required = true)
    protected String surchargeType;

    /**
     * Gets the value of the amountIsAbsolute property.
     * 
     */
    public boolean isAmountIsAbsolute() {
        return amountIsAbsolute;
    }

    /**
     * Sets the value of the amountIsAbsolute property.
     * 
     */
    public void setAmountIsAbsolute(boolean value) {
        this.amountIsAbsolute = value;
    }

    /**
     * Gets the value of the surchargeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSurchargeAmount() {
        return surchargeAmount;
    }

    /**
     * Sets the value of the surchargeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSurchargeAmount(BigDecimal value) {
        this.surchargeAmount = value;
    }

    /**
     * Gets the value of the surchargeDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurchargeDescription() {
        return surchargeDescription;
    }

    /**
     * Sets the value of the surchargeDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurchargeDescription(String value) {
        this.surchargeDescription = value;
    }

    /**
     * Gets the value of the surchargeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurchargeType() {
        return surchargeType;
    }

    /**
     * Sets the value of the surchargeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurchargeType(String value) {
        this.surchargeType = value;
    }

}
