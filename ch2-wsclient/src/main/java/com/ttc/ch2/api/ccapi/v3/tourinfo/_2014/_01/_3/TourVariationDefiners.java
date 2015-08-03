
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TourVariationDefiners complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TourVariationDefiners">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OperatingProduct" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}OperatingProduct"/>
 *         &lt;element name="IncludedSubProducts" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}IncludedSubProducts" minOccurs="0"/>
 *         &lt;element name="RoomTypes" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}RoomTypes"/>
 *         &lt;element name="StartCity" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}City"/>
 *         &lt;element name="EndCity" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}City"/>
 *         &lt;element name="IsTourPackage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IncludedCruiseCabinType" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string" minOccurs="0"/>
 *         &lt;element name="AdditionalDefiners" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}AdditionalDefiners" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TourVariationDefiners", propOrder = {
    "operatingProduct",
    "includedSubProducts",
    "roomTypes",
    "startCity",
    "endCity",
    "isTourPackage",
    "includedCruiseCabinType",
    "additionalDefiners"
})
public class TourVariationDefiners {

    @XmlElement(name = "OperatingProduct", required = true)
    protected OperatingProduct operatingProduct;
    @XmlElement(name = "IncludedSubProducts")
    protected IncludedSubProducts includedSubProducts;
    @XmlElement(name = "RoomTypes", required = true)
    protected RoomTypes roomTypes;
    @XmlElement(name = "StartCity", required = true)
    protected City startCity;
    @XmlElement(name = "EndCity", required = true)
    protected City endCity;
    @XmlElement(name = "IsTourPackage")
    protected boolean isTourPackage;
    @XmlElement(name = "IncludedCruiseCabinType")
    protected String includedCruiseCabinType;
    @XmlElement(name = "AdditionalDefiners")
    protected AdditionalDefiners additionalDefiners;

    /**
     * Gets the value of the operatingProduct property.
     * 
     * @return
     *     possible object is
     *     {@link OperatingProduct }
     *     
     */
    public OperatingProduct getOperatingProduct() {
        return operatingProduct;
    }

    /**
     * Sets the value of the operatingProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperatingProduct }
     *     
     */
    public void setOperatingProduct(OperatingProduct value) {
        this.operatingProduct = value;
    }

    /**
     * Gets the value of the includedSubProducts property.
     * 
     * @return
     *     possible object is
     *     {@link IncludedSubProducts }
     *     
     */
    public IncludedSubProducts getIncludedSubProducts() {
        return includedSubProducts;
    }

    /**
     * Sets the value of the includedSubProducts property.
     * 
     * @param value
     *     allowed object is
     *     {@link IncludedSubProducts }
     *     
     */
    public void setIncludedSubProducts(IncludedSubProducts value) {
        this.includedSubProducts = value;
    }

    /**
     * Gets the value of the roomTypes property.
     * 
     * @return
     *     possible object is
     *     {@link RoomTypes }
     *     
     */
    public RoomTypes getRoomTypes() {
        return roomTypes;
    }

    /**
     * Sets the value of the roomTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomTypes }
     *     
     */
    public void setRoomTypes(RoomTypes value) {
        this.roomTypes = value;
    }

    /**
     * Gets the value of the startCity property.
     * 
     * @return
     *     possible object is
     *     {@link City }
     *     
     */
    public City getStartCity() {
        return startCity;
    }

    /**
     * Sets the value of the startCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link City }
     *     
     */
    public void setStartCity(City value) {
        this.startCity = value;
    }

    /**
     * Gets the value of the endCity property.
     * 
     * @return
     *     possible object is
     *     {@link City }
     *     
     */
    public City getEndCity() {
        return endCity;
    }

    /**
     * Sets the value of the endCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link City }
     *     
     */
    public void setEndCity(City value) {
        this.endCity = value;
    }

    /**
     * Gets the value of the isTourPackage property.
     * 
     */
    public boolean isIsTourPackage() {
        return isTourPackage;
    }

    /**
     * Sets the value of the isTourPackage property.
     * 
     */
    public void setIsTourPackage(boolean value) {
        this.isTourPackage = value;
    }

    /**
     * Gets the value of the includedCruiseCabinType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludedCruiseCabinType() {
        return includedCruiseCabinType;
    }

    /**
     * Sets the value of the includedCruiseCabinType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludedCruiseCabinType(String value) {
        this.includedCruiseCabinType = value;
    }

    /**
     * Gets the value of the additionalDefiners property.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalDefiners }
     *     
     */
    public AdditionalDefiners getAdditionalDefiners() {
        return additionalDefiners;
    }

    /**
     * Sets the value of the additionalDefiners property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalDefiners }
     *     
     */
    public void setAdditionalDefiners(AdditionalDefiners value) {
        this.additionalDefiners = value;
    }

}
