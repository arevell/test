
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Continent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Continent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Code" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}ContinentCode"/>
 *         &lt;element name="Name" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Continent", propOrder = {
    "code",
    "name"
})
public class Continent {

    @XmlElement(name = "Code", required = true)
    protected ContinentCode code;
    @XmlElement(name = "Name", required = true)
    protected String name;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link ContinentCode }
     *     
     */
    public ContinentCode getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContinentCode }
     *     
     */
    public void setCode(ContinentCode value) {
        this.code = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
