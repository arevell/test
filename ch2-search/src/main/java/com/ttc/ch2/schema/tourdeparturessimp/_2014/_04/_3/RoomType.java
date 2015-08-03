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
 * <p>Java class for RoomType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoomType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OccupancyRule" type="{http://schema.ch2.ttc.com/TourDeparturesSimp/2014/04/3.0}OccupancyRuleType"/>
 *         &lt;element name="Price" type="{http://schema.ch2.ttc.com/TourDeparturesSimp/2014/04/3.0}RoomPriceType"/>
 *         &lt;element name="Type" type="{http://schema.ch2.ttc.com/TourDeparturesSimp/2014/04/3.0}RoomTypeCodeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomType", propOrder = {
    "occupancyRule",
    "price",
    "type"
})
public class RoomType {

    @XmlElement(name = "OccupancyRule", required = true)
    protected OccupancyRuleType occupancyRule;
    @XmlElement(name = "Price", required = true)
    protected RoomPriceType price;
    @XmlElement(name = "Type", required = true)
    protected RoomTypeCodeType type;

    /**
     * Gets the value of the occupancyRule property.
     * 
     * @return
     *     possible object is
     *     {@link OccupancyRuleType }
     *     
     */
    public OccupancyRuleType getOccupancyRule() {
        return occupancyRule;
    }

    /**
     * Sets the value of the occupancyRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link OccupancyRuleType }
     *     
     */
    public void setOccupancyRule(OccupancyRuleType value) {
        this.occupancyRule = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link RoomPriceType }
     *     
     */
    public RoomPriceType getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomPriceType }
     *     
     */
    public void setPrice(RoomPriceType value) {
        this.price = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link RoomTypeCodeType }
     *     
     */
    public RoomTypeCodeType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomTypeCodeType }
     *     
     */
    public void setType(RoomTypeCodeType value) {
        this.type = value;
    }

}
