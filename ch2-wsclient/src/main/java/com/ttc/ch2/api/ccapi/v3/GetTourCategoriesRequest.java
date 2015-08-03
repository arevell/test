
package com.ttc.ch2.api.ccapi.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="securityKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sellingCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "securityKey",
    "sellingCompany"
})
@XmlRootElement(name = "GetTourCategoriesRequest")
public class GetTourCategoriesRequest {

    @XmlElement(required = true)
    protected String securityKey;
    @XmlElement(required = true)
    protected String sellingCompany;

    /**
     * Gets the value of the securityKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityKey() {
        return securityKey;
    }

    /**
     * Sets the value of the securityKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityKey(String value) {
        this.securityKey = value;
    }

    /**
     * Gets the value of the sellingCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingCompany() {
        return sellingCompany;
    }

    /**
     * Sets the value of the sellingCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingCompany(String value) {
        this.sellingCompany = value;
    }

}
