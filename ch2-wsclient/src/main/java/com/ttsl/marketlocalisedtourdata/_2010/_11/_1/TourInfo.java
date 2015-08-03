
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
 *         &lt;element name="TourName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}Metadata"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}ContinentsVisited"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}CountriesVisited"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}LocationsVisited"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}Assets"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}Itinerary"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}WhatsIncluded" minOccurs="0"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}Highlights" minOccurs="0"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}AirportTransfers" minOccurs="0"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}Notes" minOccurs="0"/>
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
    "tourName",
    "metadata",
    "continentsVisited",
    "countriesVisited",
    "locationsVisited",
    "description",
    "assets",
    "itinerary",
    "whatsIncluded",
    "highlights",
    "airportTransfers",
    "notes"
})
@XmlRootElement(name = "TourInfo")
public class TourInfo {

    @XmlElement(name = "TourName", required = true)
    protected String tourName;
    @XmlElement(name = "Metadata", required = true)
    protected Metadata metadata;
    @XmlElement(name = "ContinentsVisited", required = true)
    protected ContinentsVisited continentsVisited;
    @XmlElement(name = "CountriesVisited", required = true)
    protected CountriesVisited countriesVisited;
    @XmlElement(name = "LocationsVisited", required = true)
    protected LocationsVisited locationsVisited;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Assets", required = true)
    protected Assets assets;
    @XmlElement(name = "Itinerary", required = true)
    protected Itinerary itinerary;
    @XmlElement(name = "WhatsIncluded")
    protected WhatsIncluded whatsIncluded;
    @XmlElement(name = "Highlights")
    protected Highlights highlights;
    @XmlElement(name = "AirportTransfers")
    protected AirportTransfers airportTransfers;
    @XmlElement(name = "Notes")
    protected Notes notes;

    /**
     * Gets the value of the tourName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourName() {
        return tourName;
    }

    /**
     * Sets the value of the tourName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourName(String value) {
        this.tourName = value;
    }

    /**
     * Gets the value of the metadata property.
     * 
     * @return
     *     possible object is
     *     {@link Metadata }
     *     
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * Sets the value of the metadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link Metadata }
     *     
     */
    public void setMetadata(Metadata value) {
        this.metadata = value;
    }

    /**
     * Gets the value of the continentsVisited property.
     * 
     * @return
     *     possible object is
     *     {@link ContinentsVisited }
     *     
     */
    public ContinentsVisited getContinentsVisited() {
        return continentsVisited;
    }

    /**
     * Sets the value of the continentsVisited property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContinentsVisited }
     *     
     */
    public void setContinentsVisited(ContinentsVisited value) {
        this.continentsVisited = value;
    }

    /**
     * Gets the value of the countriesVisited property.
     * 
     * @return
     *     possible object is
     *     {@link CountriesVisited }
     *     
     */
    public CountriesVisited getCountriesVisited() {
        return countriesVisited;
    }

    /**
     * Sets the value of the countriesVisited property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountriesVisited }
     *     
     */
    public void setCountriesVisited(CountriesVisited value) {
        this.countriesVisited = value;
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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the assets property.
     * 
     * @return
     *     possible object is
     *     {@link Assets }
     *     
     */
    public Assets getAssets() {
        return assets;
    }

    /**
     * Sets the value of the assets property.
     * 
     * @param value
     *     allowed object is
     *     {@link Assets }
     *     
     */
    public void setAssets(Assets value) {
        this.assets = value;
    }

    /**
     * Gets the value of the itinerary property.
     * 
     * @return
     *     possible object is
     *     {@link Itinerary }
     *     
     */
    public Itinerary getItinerary() {
        return itinerary;
    }

    /**
     * Sets the value of the itinerary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Itinerary }
     *     
     */
    public void setItinerary(Itinerary value) {
        this.itinerary = value;
    }

    /**
     * Gets the value of the whatsIncluded property.
     * 
     * @return
     *     possible object is
     *     {@link WhatsIncluded }
     *     
     */
    public WhatsIncluded getWhatsIncluded() {
        return whatsIncluded;
    }

    /**
     * Sets the value of the whatsIncluded property.
     * 
     * @param value
     *     allowed object is
     *     {@link WhatsIncluded }
     *     
     */
    public void setWhatsIncluded(WhatsIncluded value) {
        this.whatsIncluded = value;
    }

    /**
     * Gets the value of the highlights property.
     * 
     * @return
     *     possible object is
     *     {@link Highlights }
     *     
     */
    public Highlights getHighlights() {
        return highlights;
    }

    /**
     * Sets the value of the highlights property.
     * 
     * @param value
     *     allowed object is
     *     {@link Highlights }
     *     
     */
    public void setHighlights(Highlights value) {
        this.highlights = value;
    }

    /**
     * Gets the value of the airportTransfers property.
     * 
     * @return
     *     possible object is
     *     {@link AirportTransfers }
     *     
     */
    public AirportTransfers getAirportTransfers() {
        return airportTransfers;
    }

    /**
     * Sets the value of the airportTransfers property.
     * 
     * @param value
     *     allowed object is
     *     {@link AirportTransfers }
     *     
     */
    public void setAirportTransfers(AirportTransfers value) {
        this.airportTransfers = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link Notes }
     *     
     */
    public Notes getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Notes }
     *     
     */
    public void setNotes(Notes value) {
        this.notes = value;
    }

}
