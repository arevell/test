//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.29 at 04:55:42 PM CET 
//


package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CombinedIncludedChargesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CombinedIncludedChargesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FoodFund" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}FoodFundType" minOccurs="0"/>
 *         &lt;element name="MandatoryMiscellaneousProducts" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}MandatoryMiscellaneousProductsType" minOccurs="0"/>
 *         &lt;element name="PortTax" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}PortTaxType" minOccurs="0"/>
 *         &lt;element name="Surcharges" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}SurchargesType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CombinedIncludedChargesType", propOrder = {
    "foodFund",
    "mandatoryMiscellaneousProducts",
    "portTax",
    "surcharges"
})
public class CombinedIncludedChargesType {

    @XmlElement(name = "FoodFund")
    protected FoodFundType foodFund;
    @XmlElement(name = "MandatoryMiscellaneousProducts")
    protected MandatoryMiscellaneousProductsType mandatoryMiscellaneousProducts;
    @XmlElement(name = "PortTax")
    protected PortTaxType portTax;
    @XmlElement(name = "Surcharges")
    protected SurchargesType surcharges;

    /**
     * Gets the value of the foodFund property.
     * 
     * @return
     *     possible object is
     *     {@link FoodFundType }
     *     
     */
    public FoodFundType getFoodFund() {
        return foodFund;
    }

    /**
     * Sets the value of the foodFund property.
     * 
     * @param value
     *     allowed object is
     *     {@link FoodFundType }
     *     
     */
    public void setFoodFund(FoodFundType value) {
        this.foodFund = value;
    }

    /**
     * Gets the value of the mandatoryMiscellaneousProducts property.
     * 
     * @return
     *     possible object is
     *     {@link MandatoryMiscellaneousProductsType }
     *     
     */
    public MandatoryMiscellaneousProductsType getMandatoryMiscellaneousProducts() {
        return mandatoryMiscellaneousProducts;
    }

    /**
     * Sets the value of the mandatoryMiscellaneousProducts property.
     * 
     * @param value
     *     allowed object is
     *     {@link MandatoryMiscellaneousProductsType }
     *     
     */
    public void setMandatoryMiscellaneousProducts(MandatoryMiscellaneousProductsType value) {
        this.mandatoryMiscellaneousProducts = value;
    }

    /**
     * Gets the value of the portTax property.
     * 
     * @return
     *     possible object is
     *     {@link PortTaxType }
     *     
     */
    public PortTaxType getPortTax() {
        return portTax;
    }

    /**
     * Sets the value of the portTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortTaxType }
     *     
     */
    public void setPortTax(PortTaxType value) {
        this.portTax = value;
    }

    /**
     * Gets the value of the surcharges property.
     * 
     * @return
     *     possible object is
     *     {@link SurchargesType }
     *     
     */
    public SurchargesType getSurcharges() {
        return surcharges;
    }

    /**
     * Sets the value of the surcharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link SurchargesType }
     *     
     */
    public void setSurcharges(SurchargesType value) {
        this.surcharges = value;
    }

}
