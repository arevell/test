
package com.wsout.habs.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsOccupancyRuleVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsOccupancyRuleVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MaximumAdults" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MaximumPassengers" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MinimumAdults" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MinimumPassengers" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MinimumPayingAdults" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsOccupancyRuleVO", propOrder = {
    "maximumAdults",
    "maximumPassengers",
    "minimumAdults",
    "minimumPassengers",
    "minimumPayingAdults"
})
public class WsOccupancyRuleVO {

    @XmlElement(name = "MaximumAdults")
    protected Integer maximumAdults;
    @XmlElement(name = "MaximumPassengers")
    protected Integer maximumPassengers;
    @XmlElement(name = "MinimumAdults")
    protected Integer minimumAdults;
    @XmlElement(name = "MinimumPassengers")
    protected Integer minimumPassengers;
    @XmlElement(name = "MinimumPayingAdults")
    protected Integer minimumPayingAdults;

    /**
     * Gets the value of the maximumAdults property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaximumAdults() {
        return maximumAdults;
    }

    /**
     * Sets the value of the maximumAdults property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaximumAdults(Integer value) {
        this.maximumAdults = value;
    }

    /**
     * Gets the value of the maximumPassengers property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaximumPassengers() {
        return maximumPassengers;
    }

    /**
     * Sets the value of the maximumPassengers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaximumPassengers(Integer value) {
        this.maximumPassengers = value;
    }

    /**
     * Gets the value of the minimumAdults property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinimumAdults() {
        return minimumAdults;
    }

    /**
     * Sets the value of the minimumAdults property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinimumAdults(Integer value) {
        this.minimumAdults = value;
    }

    /**
     * Gets the value of the minimumPassengers property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinimumPassengers() {
        return minimumPassengers;
    }

    /**
     * Sets the value of the minimumPassengers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinimumPassengers(Integer value) {
        this.minimumPassengers = value;
    }

    /**
     * Gets the value of the minimumPayingAdults property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinimumPayingAdults() {
        return minimumPayingAdults;
    }

    /**
     * Sets the value of the minimumPayingAdults property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinimumPayingAdults(Integer value) {
        this.minimumPayingAdults = value;
    }

}
