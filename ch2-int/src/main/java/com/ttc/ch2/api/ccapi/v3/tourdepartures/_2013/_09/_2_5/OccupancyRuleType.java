//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.01 at 03:43:32 PM CEST 
//


package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OccupancyRuleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OccupancyRuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MaximumAdults" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="MaximumPassengers" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="MinimumAdults" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="MinimumPassengers" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="MinimumPayingAdults" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OccupancyRuleType", propOrder = {
    "maximumAdults",
    "maximumPassengers",
    "minimumAdults",
    "minimumPassengers",
    "minimumPayingAdults"
})
public class OccupancyRuleType {

    @XmlElement(name = "MaximumAdults")
    protected short maximumAdults;
    @XmlElement(name = "MaximumPassengers")
    protected short maximumPassengers;
    @XmlElement(name = "MinimumAdults")
    protected short minimumAdults;
    @XmlElement(name = "MinimumPassengers")
    protected short minimumPassengers;
    @XmlElement(name = "MinimumPayingAdults")
    protected short minimumPayingAdults;

    /**
     * Gets the value of the maximumAdults property.
     * 
     */
    public short getMaximumAdults() {
        return maximumAdults;
    }

    /**
     * Sets the value of the maximumAdults property.
     * 
     */
    public void setMaximumAdults(short value) {
        this.maximumAdults = value;
    }

    /**
     * Gets the value of the maximumPassengers property.
     * 
     */
    public short getMaximumPassengers() {
        return maximumPassengers;
    }

    /**
     * Sets the value of the maximumPassengers property.
     * 
     */
    public void setMaximumPassengers(short value) {
        this.maximumPassengers = value;
    }

    /**
     * Gets the value of the minimumAdults property.
     * 
     */
    public short getMinimumAdults() {
        return minimumAdults;
    }

    /**
     * Sets the value of the minimumAdults property.
     * 
     */
    public void setMinimumAdults(short value) {
        this.minimumAdults = value;
    }

    /**
     * Gets the value of the minimumPassengers property.
     * 
     */
    public short getMinimumPassengers() {
        return minimumPassengers;
    }

    /**
     * Sets the value of the minimumPassengers property.
     * 
     */
    public void setMinimumPassengers(short value) {
        this.minimumPassengers = value;
    }

    /**
     * Gets the value of the minimumPayingAdults property.
     * 
     */
    public short getMinimumPayingAdults() {
        return minimumPayingAdults;
    }

    /**
     * Sets the value of the minimumPayingAdults property.
     * 
     */
    public void setMinimumPayingAdults(short value) {
        this.minimumPayingAdults = value;
    }

}
