//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.15 at 12:21:35 PM CEST 
//


package com.ttc.ch2.schema.tourdeparturessimp._2014._04._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PassengerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PassengerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://schema.ch2.ttc.com/TourDeparturesSimp/2014/04/3.0}PassengerTypeType"/>
 *         &lt;element name="AgeFrom" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="AgeTo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PassengerType", propOrder = {
    "type",
    "ageFrom",
    "ageTo"
})
public class PassengerType {

    @XmlElement(name = "Type", required = true)
    protected PassengerTypeType type;
    @XmlElement(name = "AgeFrom")
    protected byte ageFrom;
    @XmlElement(name = "AgeTo")
    protected byte ageTo;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link PassengerTypeType }
     *     
     */
    public PassengerTypeType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link PassengerTypeType }
     *     
     */
    public void setType(PassengerTypeType value) {
        this.type = value;
    }

    /**
     * Gets the value of the ageFrom property.
     * 
     */
    public byte getAgeFrom() {
        return ageFrom;
    }

    /**
     * Sets the value of the ageFrom property.
     * 
     */
    public void setAgeFrom(byte value) {
        this.ageFrom = value;
    }

    /**
     * Gets the value of the ageTo property.
     * 
     */
    public byte getAgeTo() {
        return ageTo;
    }

    /**
     * Sets the value of the ageTo property.
     * 
     */
    public void setAgeTo(byte value) {
        this.ageTo = value;
    }

}
