
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TourDeparturesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TourDeparturesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OperatingProductCode" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}string"/>
 *         &lt;element name="SellingCompanies" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}SellingCompaniesType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TourCode" use="required" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}string" />
 *       &lt;attribute name="OnlineBookable" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TourDeparturesType", propOrder = {
    "operatingProductCode",
    "sellingCompanies"
})
public class TourDeparturesType {

    @XmlElement(name = "OperatingProductCode", required = true)
    protected String operatingProductCode;
    @XmlElement(name = "SellingCompanies", required = true)
    protected SellingCompaniesType sellingCompanies;
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
     * Gets the value of the sellingCompanies property.
     * 
     * @return
     *     possible object is
     *     {@link SellingCompaniesType }
     *     
     */
    public SellingCompaniesType getSellingCompanies() {
        return sellingCompanies;
    }

    /**
     * Sets the value of the sellingCompanies property.
     * 
     * @param value
     *     allowed object is
     *     {@link SellingCompaniesType }
     *     
     */
    public void setSellingCompanies(SellingCompaniesType value) {
        this.sellingCompanies = value;
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
