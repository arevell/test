
package com.ttsl.marketlocalisedtourdata._2010._11._1;

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
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}TourSeriesPricing"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}Departures"/>
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
    "tourSeriesPricing",
    "departures"
})
@XmlRootElement(name = "DepartureInfo")
public class DepartureInfo {

    @XmlElement(name = "TourSeriesPricing", required = true)
    protected TourSeriesPricing tourSeriesPricing;
    @XmlElement(name = "Departures", required = true)
    protected Departures departures;

    /**
     * Gets the value of the tourSeriesPricing property.
     * 
     * @return
     *     possible object is
     *     {@link TourSeriesPricing }
     *     
     */
    public TourSeriesPricing getTourSeriesPricing() {
        return tourSeriesPricing;
    }

    /**
     * Sets the value of the tourSeriesPricing property.
     * 
     * @param value
     *     allowed object is
     *     {@link TourSeriesPricing }
     *     
     */
    public void setTourSeriesPricing(TourSeriesPricing value) {
        this.tourSeriesPricing = value;
    }

    /**
     * Gets the value of the departures property.
     * 
     * @return
     *     possible object is
     *     {@link Departures }
     *     
     */
    public Departures getDepartures() {
        return departures;
    }

    /**
     * Sets the value of the departures property.
     * 
     * @param value
     *     allowed object is
     *     {@link Departures }
     *     
     */
    public void setDepartures(Departures value) {
        this.departures = value;
    }

}
