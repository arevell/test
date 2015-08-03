
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PriceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PriceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Adult" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Child" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PriceType", propOrder = {
    "adult",
    "child"
})
public class PriceType {

    @XmlElement(name = "Adult", required = true)
    protected BigDecimal adult;
    @XmlElement(name = "Child", required = true)
    protected BigDecimal child;

    /**
     * Gets the value of the adult property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdult() {
        return adult;
    }

    /**
     * Sets the value of the adult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdult(BigDecimal value) {
        this.adult = value;
    }

    /**
     * Gets the value of the child property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChild() {
        return child;
    }

    /**
     * Sets the value of the child property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChild(BigDecimal value) {
        this.child = value;
    }

}
