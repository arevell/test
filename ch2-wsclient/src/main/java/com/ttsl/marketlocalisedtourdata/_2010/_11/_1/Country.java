
package com.ttsl.marketlocalisedtourdata._2010._11._1;

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
 *       &lt;attribute name="Code" use="required" type="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}iso3166CountyCode" />
 *       &lt;attribute name="Continent" use="required" type="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}continentCode" />
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Country")
public class Country {

    @XmlAttribute(name = "Code", required = true)
    protected Iso3166CountyCode code;
    @XmlAttribute(name = "Continent", required = true)
    protected ContinentCode continent;
    @XmlAttribute(name = "Name", required = true)
    protected String name;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link Iso3166CountyCode }
     *     
     */
    public Iso3166CountyCode getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link Iso3166CountyCode }
     *     
     */
    public void setCode(Iso3166CountyCode value) {
        this.code = value;
    }

    /**
     * Gets the value of the continent property.
     * 
     * @return
     *     possible object is
     *     {@link ContinentCode }
     *     
     */
    public ContinentCode getContinent() {
        return continent;
    }

    /**
     * Sets the value of the continent property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContinentCode }
     *     
     */
    public void setContinent(ContinentCode value) {
        this.continent = value;
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
