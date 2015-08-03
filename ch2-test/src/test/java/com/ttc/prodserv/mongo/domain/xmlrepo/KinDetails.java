
package com.ttc.prodserv.mongo.domain.xmlrepo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for kinDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="kinDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kinContactName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kinPhoneAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kinPhoneCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kinPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kinRelation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "kinDetails", propOrder = {
    "kinContactName",
    "kinPhoneAreaCode",
    "kinPhoneCountryCode",
    "kinPhoneNumber",
    "kinRelation"
})
public class KinDetails {

    protected String kinContactName;
    protected String kinPhoneAreaCode;
    protected String kinPhoneCountryCode;
    protected String kinPhoneNumber;
    protected String kinRelation;

    /**
     * Gets the value of the kinContactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKinContactName() {
        return kinContactName;
    }

    /**
     * Sets the value of the kinContactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKinContactName(String value) {
        this.kinContactName = value;
    }

    /**
     * Gets the value of the kinPhoneAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKinPhoneAreaCode() {
        return kinPhoneAreaCode;
    }

    /**
     * Sets the value of the kinPhoneAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKinPhoneAreaCode(String value) {
        this.kinPhoneAreaCode = value;
    }

    /**
     * Gets the value of the kinPhoneCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKinPhoneCountryCode() {
        return kinPhoneCountryCode;
    }

    /**
     * Sets the value of the kinPhoneCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKinPhoneCountryCode(String value) {
        this.kinPhoneCountryCode = value;
    }

    /**
     * Gets the value of the kinPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKinPhoneNumber() {
        return kinPhoneNumber;
    }

    /**
     * Sets the value of the kinPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKinPhoneNumber(String value) {
        this.kinPhoneNumber = value;
    }

    /**
     * Gets the value of the kinRelation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKinRelation() {
        return kinRelation;
    }

    /**
     * Sets the value of the kinRelation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKinRelation(String value) {
        this.kinRelation = value;
    }

}
