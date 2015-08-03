
package com.ttc.prodserv.mongo.domain.xmlrepo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for thirdPartyInsuranceDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="thirdPartyInsuranceDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="companyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneBaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="policyNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "thirdPartyInsuranceDetails", propOrder = {
    "companyName",
    "phoneAreaCode",
    "phoneBaseNumber",
    "phoneCountryCode",
    "policyNumber"
})
public class ThirdPartyInsuranceDetails {

    protected String companyName;
    protected String phoneAreaCode;
    protected String phoneBaseNumber;
    protected String phoneCountryCode;
    protected String policyNumber;

    /**
     * Gets the value of the companyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the value of the companyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Gets the value of the phoneAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    /**
     * Sets the value of the phoneAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneAreaCode(String value) {
        this.phoneAreaCode = value;
    }

    /**
     * Gets the value of the phoneBaseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneBaseNumber() {
        return phoneBaseNumber;
    }

    /**
     * Sets the value of the phoneBaseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneBaseNumber(String value) {
        this.phoneBaseNumber = value;
    }

    /**
     * Gets the value of the phoneCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    /**
     * Sets the value of the phoneCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneCountryCode(String value) {
        this.phoneCountryCode = value;
    }

    /**
     * Gets the value of the policyNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyNumber() {
        return policyNumber;
    }

    /**
     * Sets the value of the policyNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyNumber(String value) {
        this.policyNumber = value;
    }

}
