
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Location complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Location">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="CountryCode" use="required" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}ISO3166CountryCode" />
 *       &lt;attribute name="Name" use="required" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Location")
public class Location {

    @XmlAttribute(name = "CountryCode", required = true)
    protected ISO3166CountryCode countryCode;
    @XmlAttribute(name = "Name", required = true)
    protected String name;

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link ISO3166CountryCode }
     *     
     */
    public ISO3166CountryCode getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO3166CountryCode }
     *     
     */
    public void setCountryCode(ISO3166CountryCode value) {
        this.countryCode = value;
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

}
