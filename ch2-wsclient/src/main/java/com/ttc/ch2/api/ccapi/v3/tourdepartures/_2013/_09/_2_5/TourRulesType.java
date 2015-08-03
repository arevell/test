
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TourRulesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TourRulesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Passengers" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}PassengersType"/>
 *         &lt;element name="Rooms" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}RoomsType"/>
 *         &lt;element name="CombinedIncludedCharges" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}CombinedIncludedChargesType" minOccurs="0"/>
 *         &lt;element name="PriceIsIndicative" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Discounts" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}DiscountsType" minOccurs="0"/>
 *         &lt;element name="IsEligibleForFrequentTravellerDiscount" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CanSearchForFlights" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TourRulesType", propOrder = {
    "passengers",
    "rooms",
    "combinedIncludedCharges",
    "priceIsIndicative",
    "discounts",
    "isEligibleForFrequentTravellerDiscount",
    "canSearchForFlights"
})
public class TourRulesType {

    @XmlElement(name = "Passengers", required = true)
    protected PassengersType passengers;
    @XmlElement(name = "Rooms", required = true)
    protected RoomsType rooms;
    @XmlElement(name = "CombinedIncludedCharges")
    protected CombinedIncludedChargesType combinedIncludedCharges;
    @XmlElement(name = "PriceIsIndicative")
    protected boolean priceIsIndicative;
    @XmlElement(name = "Discounts")
    protected DiscountsType discounts;
    @XmlElement(name = "IsEligibleForFrequentTravellerDiscount")
    protected boolean isEligibleForFrequentTravellerDiscount;
    @XmlElement(name = "CanSearchForFlights")
    protected boolean canSearchForFlights;

    /**
     * Gets the value of the passengers property.
     * 
     * @return
     *     possible object is
     *     {@link PassengersType }
     *     
     */
    public PassengersType getPassengers() {
        return passengers;
    }

    /**
     * Sets the value of the passengers property.
     * 
     * @param value
     *     allowed object is
     *     {@link PassengersType }
     *     
     */
    public void setPassengers(PassengersType value) {
        this.passengers = value;
    }

    /**
     * Gets the value of the rooms property.
     * 
     * @return
     *     possible object is
     *     {@link RoomsType }
     *     
     */
    public RoomsType getRooms() {
        return rooms;
    }

    /**
     * Sets the value of the rooms property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomsType }
     *     
     */
    public void setRooms(RoomsType value) {
        this.rooms = value;
    }

    /**
     * Gets the value of the combinedIncludedCharges property.
     * 
     * @return
     *     possible object is
     *     {@link CombinedIncludedChargesType }
     *     
     */
    public CombinedIncludedChargesType getCombinedIncludedCharges() {
        return combinedIncludedCharges;
    }

    /**
     * Sets the value of the combinedIncludedCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link CombinedIncludedChargesType }
     *     
     */
    public void setCombinedIncludedCharges(CombinedIncludedChargesType value) {
        this.combinedIncludedCharges = value;
    }

    /**
     * Gets the value of the priceIsIndicative property.
     * 
     */
    public boolean isPriceIsIndicative() {
        return priceIsIndicative;
    }

    /**
     * Sets the value of the priceIsIndicative property.
     * 
     */
    public void setPriceIsIndicative(boolean value) {
        this.priceIsIndicative = value;
    }

    /**
     * Gets the value of the discounts property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountsType }
     *     
     */
    public DiscountsType getDiscounts() {
        return discounts;
    }

    /**
     * Sets the value of the discounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountsType }
     *     
     */
    public void setDiscounts(DiscountsType value) {
        this.discounts = value;
    }

    /**
     * Gets the value of the isEligibleForFrequentTravellerDiscount property.
     * 
     */
    public boolean isIsEligibleForFrequentTravellerDiscount() {
        return isEligibleForFrequentTravellerDiscount;
    }

    /**
     * Sets the value of the isEligibleForFrequentTravellerDiscount property.
     * 
     */
    public void setIsEligibleForFrequentTravellerDiscount(boolean value) {
        this.isEligibleForFrequentTravellerDiscount = value;
    }

    /**
     * Gets the value of the canSearchForFlights property.
     * 
     */
    public boolean isCanSearchForFlights() {
        return canSearchForFlights;
    }

    /**
     * Sets the value of the canSearchForFlights property.
     * 
     */
    public void setCanSearchForFlights(boolean value) {
        this.canSearchForFlights = value;
    }

}
