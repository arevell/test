
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="TourName" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string"/>
 *         &lt;element name="CataloguedTour" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}CataloguedTour"/>
 *         &lt;element name="SellingCompanies" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}SellingCompanies"/>
 *         &lt;element name="TourVariationDefiners" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}TourVariationDefiners"/>
 *         &lt;element name="ContinentsVisited" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}ContinentsVisited"/>
 *         &lt;element name="CountriesVisited" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}CountriesVisited"/>
 *         &lt;element name="LocationsVisited" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}LocationsVisited"/>
 *         &lt;element name="Description" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string"/>
 *         &lt;element name="Assets" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}Assets"/>
 *         &lt;element name="Itinerary" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}Itinerary"/>
 *         &lt;element name="WhatsIncluded" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}WhatsIncluded" minOccurs="0"/>
 *         &lt;element name="Highlights" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}Highlights" minOccurs="0"/>
 *         &lt;element name="AirportTransfers" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}AirportTransfers" minOccurs="0"/>
 *         &lt;element name="Notes" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}Notes" minOccurs="0"/>
 *         &lt;element name="AdditionalInfo" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}AdditionalInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="BrandCode" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="CMSId" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="CMSTourId" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="Duration" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="TourCode" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
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
    "cataloguedTour",
    "sellingCompanies",
    "tourVariationDefiners",
    "continentsVisited",
    "countriesVisited",
    "locationsVisited",
    "description",
    "assets",
    "itinerary",
    "whatsIncluded",
    "highlights",
    "airportTransfers",
    "notes",
    "additionalInfo"
})
@XmlRootElement(name = "TourInfo")
public class TourInfo {

    @XmlElement(name = "TourName", required = true)
    protected String tourName;
    @XmlElement(name = "CataloguedTour", required = true)
    protected CataloguedTour cataloguedTour;
    @XmlElement(name = "SellingCompanies", required = true)
    protected SellingCompanies sellingCompanies;
    @XmlElement(name = "TourVariationDefiners", required = true)
    protected TourVariationDefiners tourVariationDefiners;
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
    @XmlElement(name = "AdditionalInfo")
    protected AdditionalInfo additionalInfo;
    @XmlAttribute(name = "BrandCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String brandCode;
    @XmlAttribute(name = "CMSId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String cmsId;
    @XmlAttribute(name = "CMSTourId")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String cmsTourId;
    @XmlAttribute(name = "Duration", required = true)
    protected BigInteger duration;
    @XmlAttribute(name = "TourCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String tourCode;

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
     * Gets the value of the cataloguedTour property.
     * 
     * @return
     *     possible object is
     *     {@link CataloguedTour }
     *     
     */
    public CataloguedTour getCataloguedTour() {
        return cataloguedTour;
    }

    /**
     * Sets the value of the cataloguedTour property.
     * 
     * @param value
     *     allowed object is
     *     {@link CataloguedTour }
     *     
     */
    public void setCataloguedTour(CataloguedTour value) {
        this.cataloguedTour = value;
    }

    /**
     * Gets the value of the sellingCompanies property.
     * 
     * @return
     *     possible object is
     *     {@link SellingCompanies }
     *     
     */
    public SellingCompanies getSellingCompanies() {
        return sellingCompanies;
    }

    /**
     * Sets the value of the sellingCompanies property.
     * 
     * @param value
     *     allowed object is
     *     {@link SellingCompanies }
     *     
     */
    public void setSellingCompanies(SellingCompanies value) {
        this.sellingCompanies = value;
    }

    /**
     * Gets the value of the tourVariationDefiners property.
     * 
     * @return
     *     possible object is
     *     {@link TourVariationDefiners }
     *     
     */
    public TourVariationDefiners getTourVariationDefiners() {
        return tourVariationDefiners;
    }

    /**
     * Sets the value of the tourVariationDefiners property.
     * 
     * @param value
     *     allowed object is
     *     {@link TourVariationDefiners }
     *     
     */
    public void setTourVariationDefiners(TourVariationDefiners value) {
        this.tourVariationDefiners = value;
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

    /**
     * Gets the value of the additionalInfo property.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalInfo }
     *     
     */
    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Sets the value of the additionalInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalInfo }
     *     
     */
    public void setAdditionalInfo(AdditionalInfo value) {
        this.additionalInfo = value;
    }

    /**
     * Gets the value of the brandCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * Sets the value of the brandCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandCode(String value) {
        this.brandCode = value;
    }

    /**
     * Gets the value of the cmsId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCMSId() {
        return cmsId;
    }

    /**
     * Sets the value of the cmsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCMSId(String value) {
        this.cmsId = value;
    }

    /**
     * Gets the value of the cmsTourId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCMSTourId() {
        return cmsTourId;
    }

    /**
     * Sets the value of the cmsTourId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCMSTourId(String value) {
        this.cmsTourId = value;
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
     * Gets the value of the tourCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourCode() {
        return tourCode;
    }

    /**
     * Sets the value of the tourCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourCode(String value) {
        this.tourCode = value;
    }

}
