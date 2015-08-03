
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

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
 *         &lt;element name="OccupancyRule" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}OccupancyRuleType"/>
 *         &lt;element name="Price" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}RoomPriceType"/>
 *         &lt;element name="Type" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}RoomTypeCodeType"/>
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
