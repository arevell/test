//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.15 at 12:21:35 PM CEST 
//


package com.ttc.ch2.schema.tourdeparturessimp._2014._04._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TourDeparturesSimpType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TourDeparturesSimpType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OperatingProductCode" type="{http://schema.ch2.ttc.com/TourDeparturesSimp/2014/04/3.0}string"/>
 *         &lt;element name="SellingCompany" type="{http://schema.ch2.ttc.com/TourDeparturesSimp/2014/04/3.0}SellingCompanyType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TourCode" use="required" type="{http://schema.ch2.ttc.com/TourDeparturesSimp/2014/04/3.0}string" />
 *       &lt;attribute name="OnlineBookable" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TourDeparturesSimpType", propOrder = {
    "operatingProductCode",
    "sellingCompany"
})
public class TourDeparturesSimpType {

    @XmlElement(name = "OperatingProductCode", required = true)
    protected String operatingProductCode;
    @XmlElement(name = "SellingCompany", required = true)
    protected SellingCompanyType sellingCompany;
    @XmlAttribute(name = "TourCode", required = true)
    protected String tourCode;
    @XmlAttribute(name = "OnlineBookable", required = true)
    protected boolean onlineBookable;

    /**
     * Gets the value of the operatingProductCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingProductCode() {
        return operatingProductCode;
    }

    /**
     * Sets the value of the operatingProductCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingProductCode(String value) {
        this.operatingProductCode = value;
    }

    /**
     * Gets the value of the sellingCompany property.
     * 
     * @return
     *     possible object is
     *     {@link SellingCompanyType }
     *     
     */
    public SellingCompanyType getSellingCompany() {
        return sellingCompany;
    }

    /**
     * Sets the value of the sellingCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link SellingCompanyType }
     *     
     */
    public void setSellingCompany(SellingCompanyType value) {
        this.sellingCompany = value;
    }

    /**
     * Gets the value of the tourCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourCode() {
        return tourCode;
    }

    /**
     * Sets the value of the tourCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourCode(String value) {
        this.tourCode = value;
    }

    /**
     * Gets the value of the onlineBookable property.
     * 
     */
    public boolean isOnlineBookable() {
        return onlineBookable;
    }

    /**
     * Sets the value of the onlineBookable property.
     * 
     */
    public void setOnlineBookable(boolean value) {
        this.onlineBookable = value;
    }

}
