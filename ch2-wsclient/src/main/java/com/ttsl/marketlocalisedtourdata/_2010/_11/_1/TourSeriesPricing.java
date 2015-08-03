
package com.ttsl.marketlocalisedtourdata._2010._11._1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;/sequence>
 *       &lt;attribute name="AdultPortTax" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="AirPriceIncluded" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ChildPortTax" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="FoodFundPrice" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="PriceIsIndicative" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "TourSeriesPricing")
public class TourSeriesPricing {

    @XmlAttribute(name = "AdultPortTax", required = true)
    protected BigDecimal adultPortTax;
    @XmlAttribute(name = "AirPriceIncluded", required = true)
    protected boolean airPriceIncluded;
    @XmlAttribute(name = "ChildPortTax", required = true)
    protected BigDecimal childPortTax;
    @XmlAttribute(name = "FoodFundPrice", required = true)
    protected BigDecimal foodFundPrice;
    @XmlAttribute(name = "PriceIsIndicative", required = true)
    protected boolean priceIsIndicative;

    /**
     * Gets the value of the adultPortTax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdultPortTax() {
        return adultPortTax;
    }

    /**
     * Sets the value of the adultPortTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdultPortTax(BigDecimal value) {
        this.adultPortTax = value;
    }

    /**
     * Gets the value of the airPriceIncluded property.
     * 
     */
    public boolean isAirPriceIncluded() {
        return airPriceIncluded;
    }

    /**
     * Sets the value of the airPriceIncluded property.
     * 
     */
    public void setAirPriceIncluded(boolean value) {
        this.airPriceIncluded = value;
    }

    /**
     * Gets the value of the childPortTax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChildPortTax() {
        return childPortTax;
    }

    /**
     * Sets the value of the childPortTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChildPortTax(BigDecimal value) {
        this.childPortTax = value;
    }

    /**
     * Gets the value of the foodFundPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFoodFundPrice() {
        return foodFundPrice;
    }

    /**
     * Sets the value of the foodFundPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFoodFundPrice(BigDecimal value) {
        this.foodFundPrice = value;
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

}
