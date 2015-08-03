
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SellingCompanyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SellingCompanyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Departures" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}DeparturesType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Code" use="required" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}string" />
 *       &lt;attribute name="CurrencyCode" use="required" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}string" />
 *       &lt;attribute name="InventoryBrochureCode" use="required" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SellingCompanyType", propOrder = {
    "departures"
})
public class SellingCompanyType {

    @XmlElement(name = "Departures", required = true)
    protected DeparturesType departures;
    @XmlAttribute(name = "Code", required = true)
    protected String code;
    @XmlAttribute(name = "CurrencyCode", required = true)
    protected String currencyCode;
    @XmlAttribute(name = "InventoryBrochureCode", required = true)
    protected String inventoryBrochureCode;

    /**
     * Gets the value of the departures property.
     * 
     * @return
     *     possible object is
     *     {@link DeparturesType }
     *     
     */
    public DeparturesType getDepartures() {
        return departures;
    }

    /**
     * Sets the value of the departures property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeparturesType }
     *     
     */
    public void setDepartures(DeparturesType value) {
        this.departures = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the inventoryBrochureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInventoryBrochureCode() {
        return inventoryBrochureCode;
    }

    /**
     * Sets the value of the inventoryBrochureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInventoryBrochureCode(String value) {
        this.inventoryBrochureCode = value;
    }

}
