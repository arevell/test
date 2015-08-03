
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperatingProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperatingProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Code" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string"/>
 *         &lt;element name="ContractingSeason" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string"/>
 *         &lt;element name="Category" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string"/>
 *         &lt;element name="Classification" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string"/>
 *         &lt;element name="StandardName" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperatingProduct", propOrder = {
    "code",
    "contractingSeason",
    "category",
    "classification",
    "standardName"
})
public class OperatingProduct {

    @XmlElement(name = "Code", required = true)
    protected String code;
    @XmlElement(name = "ContractingSeason", required = true)
    protected String contractingSeason;
    @XmlElement(name = "Category", required = true)
    protected String category;
    @XmlElement(name = "Classification", required = true)
    protected String classification;
    @XmlElement(name = "StandardName", required = true)
    protected String standardName;

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
     * Gets the value of the contractingSeason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractingSeason() {
        return contractingSeason;
    }

    /**
     * Sets the value of the contractingSeason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractingSeason(String value) {
        this.contractingSeason = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the classification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Sets the value of the classification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassification(String value) {
        this.classification = value;
    }

    /**
     * Gets the value of the standardName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandardName() {
        return standardName;
    }

    /**
     * Sets the value of the standardName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandardName(String value) {
        this.standardName = value;
    }

}
