
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Country complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Country">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Code" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}ISO3166CountryCode"/>
 *         &lt;element name="Name" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string"/>
 *         &lt;element name="ContinentCode" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}ContinentCode"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Country", propOrder = {
    "code",
    "name",
    "continentCode"
})
public class Country {

    @XmlElement(name = "Code", required = true)
    protected ISO3166CountryCode code;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ContinentCode", required = true)
    protected ContinentCode continentCode;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link ISO3166CountryCode }
     *     
     */
    public ISO3166CountryCode getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO3166CountryCode }
     *     
     */
    public void setCode(ISO3166CountryCode value) {
        this.code = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the continentCode property.
     * 
     * @return
     *     possible object is
     *     {@link ContinentCode }
     *     
     */
    public ContinentCode getContinentCode() {
        return continentCode;
    }

    /**
     * Sets the value of the continentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContinentCode }
     *     
     */
    public void setContinentCode(ContinentCode value) {
        this.continentCode = value;
    }

}
