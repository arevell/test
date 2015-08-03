
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ItinerarySegment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ItinerarySegment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Title" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string"/>
 *         &lt;element name="Text" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}Text" minOccurs="0"/>
 *         &lt;element name="Accommodation" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string" minOccurs="0"/>
 *         &lt;element name="Meals" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}Meals" minOccurs="0"/>
 *         &lt;element name="LocationsVisited" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}LocationsVisited" minOccurs="0"/>
 *         &lt;element name="OptionalExtras" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}OptionalExtras" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Duration" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="StartDay" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItinerarySegment", propOrder = {
    "title",
    "text",
    "accommodation",
    "meals",
    "locationsVisited",
    "optionalExtras"
})
public class ItinerarySegment {

    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Text")
    protected Text text;
    @XmlElement(name = "Accommodation")
    protected String accommodation;
    @XmlElement(name = "Meals")
    protected Meals meals;
    @XmlElement(name = "LocationsVisited")
    protected LocationsVisited locationsVisited;
    @XmlElement(name = "OptionalExtras")
    protected OptionalExtras optionalExtras;
    @XmlAttribute(name = "Duration", required = true)
    protected BigInteger duration;
    @XmlAttribute(name = "StartDay", required = true)
    protected BigInteger startDay;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link Text }
     *     
     */
    public Text getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link Text }
     *     
     */
    public void setText(Text value) {
        this.text = value;
    }

    /**
     * Gets the value of the accommodation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccommodation() {
        return accommodation;
    }

    /**
     * Sets the value of the accommodation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccommodation(String value) {
        this.accommodation = value;
    }

    /**
     * Gets the value of the meals property.
     * 
     * @return
     *     possible object is
     *     {@link Meals }
     *     
     */
    public Meals getMeals() {
        return meals;
    }

    /**
     * Sets the value of the meals property.
     * 
     * @param value
     *     allowed object is
     *     {@link Meals }
     *     
     */
    public void setMeals(Meals value) {
        this.meals = value;
    }

    /**
     * Gets the value of the locationsVisited property.
     * 
     * @return
     *     possible object is
     *     {@link LocationsVisited }
     *     
     */
    public LocationsVisited getLocationsVisited() {
        return locationsVisited;
    }

    /**
     * Sets the value of the locationsVisited property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationsVisited }
     *     
     */
    public void setLocationsVisited(LocationsVisited value) {
        this.locationsVisited = value;
    }

    /**
     * Gets the value of the optionalExtras property.
     * 
     * @return
     *     possible object is
     *     {@link OptionalExtras }
     *     
     */
    public OptionalExtras getOptionalExtras() {
        return optionalExtras;
    }

    /**
     * Sets the value of the optionalExtras property.
     * 
     * @param value
     *     allowed object is
     *     {@link OptionalExtras }
     *     
     */
    public void setOptionalExtras(OptionalExtras value) {
        this.optionalExtras = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDuration(BigInteger value) {
        this.duration = value;
    }

    /**
     * Gets the value of the startDay property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStartDay() {
        return startDay;
    }

    /**
     * Sets the value of the startDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStartDay(BigInteger value) {
        this.startDay = value;
    }

}
