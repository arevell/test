//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.06 at 11:32:21 AM CEST 
//


package com.ttc.ch2.schema.tourinfo._2014._01._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for City complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="City">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string"/>
 *         &lt;element name="Airports" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}Airports"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "City", propOrder = {
    "name",
    "airports"
})
public class City {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Airports", required = true)
    protected Airports airports;

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
     * Gets the value of the airports property.
     * 
     * @return
     *     possible object is
     *     {@link Airports }
     *     
     */
    public Airports getAirports() {
        return airports;
    }

    /**
     * Sets the value of the airports property.
     * 
     * @param value
     *     allowed object is
     *     {@link Airports }
     *     
     */
    public void setAirports(Airports value) {
        this.airports = value;
    }

}
