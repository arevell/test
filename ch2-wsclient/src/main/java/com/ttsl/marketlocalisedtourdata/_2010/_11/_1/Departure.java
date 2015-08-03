
package com.ttsl.marketlocalisedtourdata._2010._11._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}DeparturePricing" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="AvailabilityStatus" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DefiniteDeparture" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DepartureCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="EndDate" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="StartDate" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "departurePricing"
})
@XmlRootElement(name = "Departure")
public class Departure {

    @XmlElement(name = "DeparturePricing")
    protected DeparturePricing departurePricing;
    @XmlAttribute(name = "AvailabilityStatus", required = true)
    protected String availabilityStatus;
    @XmlAttribute(name = "DefiniteDeparture", required = true)
    protected boolean definiteDeparture;
    @XmlAttribute(name = "DepartureCode", required = true)
    protected String departureCode;
    @XmlAttribute(name = "EndDate", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String endDate;
    @XmlAttribute(name = "StartDate", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String startDate;

    /**
     * Gets the value of the departurePricing property.
     * 
     * @return
     *     possible object is
     *     {@link DeparturePricing }
     *     
     */
    public DeparturePricing getDeparturePricing() {
        return departurePricing;
    }

    /**
     * Sets the value of the departurePricing property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeparturePricing }
     *     
     */
    public void setDeparturePricing(DeparturePricing value) {
        this.departurePricing = value;
    }

    /**
     * Gets the value of the availabilityStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    /**
     * Sets the value of the availabilityStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailabilityStatus(String value) {
        this.availabilityStatus = value;
    }

    /**
     * Gets the value of the definiteDeparture property.
     * 
     */
    public boolean isDefiniteDeparture() {
        return definiteDeparture;
    }

    /**
     * Sets the value of the definiteDeparture property.
     * 
     */
    public void setDefiniteDeparture(boolean value) {
        this.definiteDeparture = value;
    }

    /**
     * Gets the value of the departureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureCode() {
        return departureCode;
    }

    /**
     * Sets the value of the departureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureCode(String value) {
        this.departureCode = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

}
