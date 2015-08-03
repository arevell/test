
package com.ttsl.marketlocalisedtourdata._2010._11._1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}MandatoryProducts" minOccurs="0"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}Surcharges" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="AdultQuadRoomPrice" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="AdultQuadRoomPriceCombined" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="AdultSingleRoomPrice" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="AdultSingleRoomPriceCombined" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="AdultTripleRoomPrice" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="AdultTripleRoomPriceCombined" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="AdultTwinRoomPrice" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="AdultTwinRoomPriceCombined" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="ChildPrice" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="LandOnlyReduction" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="TeenagerDiscount" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mandatoryProducts",
    "surcharges"
})
@XmlRootElement(name = "DeparturePricing")
public class DeparturePricing {

    @XmlElement(name = "MandatoryProducts")
    protected MandatoryProducts mandatoryProducts;
    @XmlElement(name = "Surcharges")
    protected Surcharges surcharges;
    @XmlAttribute(name = "AdultQuadRoomPrice", required = true)
    protected BigDecimal adultQuadRoomPrice;
    @XmlAttribute(name = "AdultQuadRoomPriceCombined", required = true)
    protected BigDecimal adultQuadRoomPriceCombined;
    @XmlAttribute(name = "AdultSingleRoomPrice", required = true)
    protected BigDecimal adultSingleRoomPrice;
    @XmlAttribute(name = "AdultSingleRoomPriceCombined", required = true)
    protected BigDecimal adultSingleRoomPriceCombined;
    @XmlAttribute(name = "AdultTripleRoomPrice", required = true)
    protected BigDecimal adultTripleRoomPrice;
    @XmlAttribute(name = "AdultTripleRoomPriceCombined", required = true)
    protected BigDecimal adultTripleRoomPriceCombined;
    @XmlAttribute(name = "AdultTwinRoomPrice", required = true)
    protected BigDecimal adultTwinRoomPrice;
    @XmlAttribute(name = "AdultTwinRoomPriceCombined", required = true)
    protected BigDecimal adultTwinRoomPriceCombined;
    @XmlAttribute(name = "ChildPrice", required = true)
    protected BigDecimal childPrice;
    @XmlAttribute(name = "LandOnlyReduction", required = true)
    protected BigDecimal landOnlyReduction;
    @XmlAttribute(name = "TeenagerDiscount", required = true)
    protected BigDecimal teenagerDiscount;

    /**
     * Gets the value of the mandatoryProducts property.
     * 
     * @return
     *     possible object is
     *     {@link MandatoryProducts }
     *     
     */
    public MandatoryProducts getMandatoryProducts() {
        return mandatoryProducts;
    }

    /**
     * Sets the value of the mandatoryProducts property.
     * 
     * @param value
     *     allowed object is
     *     {@link MandatoryProducts }
     *     
     */
    public void setMandatoryProducts(MandatoryProducts value) {
        this.mandatoryProducts = value;
    }

    /**
     * Gets the value of the surcharges property.
     * 
     * @return
     *     possible object is
     *     {@link Surcharges }
     *     
     */
    public Surcharges getSurcharges() {
        return surcharges;
    }

    /**
     * Sets the value of the surcharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link Surcharges }
     *     
     */
    public void setSurcharges(Surcharges value) {
        this.surcharges = value;
    }

    /**
     * Gets the value of the adultQuadRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultQuadRoomPrice() {
        return adultQuadRoomPrice;
    }

    /**
     * Sets the value of the adultQuadRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultQuadRoomPrice(BigDecimal value) {
        this.adultQuadRoomPrice = value;
    }

    /**
     * Gets the value of the adultQuadRoomPriceCombined property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultQuadRoomPriceCombined() {
        return adultQuadRoomPriceCombined;
    }

    /**
     * Sets the value of the adultQuadRoomPriceCombined property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultQuadRoomPriceCombined(BigDecimal value) {
        this.adultQuadRoomPriceCombined = value;
    }

    /**
     * Gets the value of the adultSingleRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultSingleRoomPrice() {
        return adultSingleRoomPrice;
    }

    /**
     * Sets the value of the adultSingleRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultSingleRoomPrice(BigDecimal value) {
        this.adultSingleRoomPrice = value;
    }

    /**
     * Gets the value of the adultSingleRoomPriceCombined property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultSingleRoomPriceCombined() {
        return adultSingleRoomPriceCombined;
    }

    /**
     * Sets the value of the adultSingleRoomPriceCombined property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultSingleRoomPriceCombined(BigDecimal value) {
        this.adultSingleRoomPriceCombined = value;
    }

    /**
     * Gets the value of the adultTripleRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultTripleRoomPrice() {
        return adultTripleRoomPrice;
    }

    /**
     * Sets the value of the adultTripleRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultTripleRoomPrice(BigDecimal value) {
        this.adultTripleRoomPrice = value;
    }

    /**
     * Gets the value of the adultTripleRoomPriceCombined property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultTripleRoomPriceCombined() {
        return adultTripleRoomPriceCombined;
    }

    /**
     * Sets the value of the adultTripleRoomPriceCombined property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultTripleRoomPriceCombined(BigDecimal value) {
        this.adultTripleRoomPriceCombined = value;
    }

    /**
     * Gets the value of the adultTwinRoomPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultTwinRoomPrice() {
        return adultTwinRoomPrice;
    }

    /**
     * Sets the value of the adultTwinRoomPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultTwinRoomPrice(BigDecimal value) {
        this.adultTwinRoomPrice = value;
    }

    /**
     * Gets the value of the adultTwinRoomPriceCombined property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultTwinRoomPriceCombined() {
        return adultTwinRoomPriceCombined;
    }

    /**
     * Sets the value of the adultTwinRoomPriceCombined property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultTwinRoomPriceCombined(BigDecimal value) {
        this.adultTwinRoomPriceCombined = value;
    }

    /**
     * Gets the value of the childPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChildPrice() {
        return childPrice;
    }

    /**
     * Sets the value of the childPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChildPrice(BigDecimal value) {
        this.childPrice = value;
    }

    /**
     * Gets the value of the landOnlyReduction property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLandOnlyReduction() {
        return landOnlyReduction;
    }

    /**
     * Sets the value of the landOnlyReduction property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLandOnlyReduction(BigDecimal value) {
        this.landOnlyReduction = value;
    }

    /**
     * Gets the value of the teenagerDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTeenagerDiscount() {
        return teenagerDiscount;
    }

    /**
     * Sets the value of the teenagerDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTeenagerDiscount(BigDecimal value) {
        this.teenagerDiscount = value;
    }

}
