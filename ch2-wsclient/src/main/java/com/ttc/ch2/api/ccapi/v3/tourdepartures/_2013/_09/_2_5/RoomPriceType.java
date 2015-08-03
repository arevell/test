
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoomPriceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoomPriceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Adult">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Base" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="Combined" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Child">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Base" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="Combined" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomPriceType", propOrder = {
    "adult",
    "child"
})
public class RoomPriceType {

    @XmlElement(name = "Adult", required = true)
    protected RoomPriceType.Adult adult;
    @XmlElement(name = "Child", required = true)
    protected RoomPriceType.Child child;

    /**
     * Gets the value of the adult property.
     * 
     * @return
     *     possible object is
     *     {@link RoomPriceType.Adult }
     *     
     */
    public RoomPriceType.Adult getAdult() {
        return adult;
    }

    /**
     * Sets the value of the adult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomPriceType.Adult }
     *     
     */
    public void setAdult(RoomPriceType.Adult value) {
        this.adult = value;
    }

    /**
     * Gets the value of the child property.
     * 
     * @return
     *     possible object is
     *     {@link RoomPriceType.Child }
     *     
     */
    public RoomPriceType.Child getChild() {
        return child;
    }

    /**
     * Sets the value of the child property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomPriceType.Child }
     *     
     */
    public void setChild(RoomPriceType.Child value) {
        this.child = value;
    }


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
     *         &lt;element name="Base" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="Combined" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "base",
        "combined"
    })
    public static class Adult {

        @XmlElement(name = "Base", required = true)
        protected BigDecimal base;
        @XmlElement(name = "Combined", required = true)
        protected BigDecimal combined;

        /**
         * Gets the value of the base property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getBase() {
            return base;
        }

        /**
         * Sets the value of the base property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setBase(BigDecimal value) {
            this.base = value;
        }

        /**
         * Gets the value of the combined property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getCombined() {
            return combined;
        }

        /**
         * Sets the value of the combined property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setCombined(BigDecimal value) {
            this.combined = value;
        }

    }


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
     *         &lt;element name="Base" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="Combined" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "base",
        "combined"
    })
    public static class Child {

        @XmlElement(name = "Base", required = true)
        protected BigDecimal base;
        @XmlElement(name = "Combined", required = true)
        protected BigDecimal combined;

        /**
         * Gets the value of the base property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getBase() {
            return base;
        }

        /**
         * Sets the value of the base property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setBase(BigDecimal value) {
            this.base = value;
        }

        /**
         * Gets the value of the combined property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getCombined() {
            return combined;
        }

        /**
         * Sets the value of the combined property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setCombined(BigDecimal value) {
            this.combined = value;
        }

    }

}
