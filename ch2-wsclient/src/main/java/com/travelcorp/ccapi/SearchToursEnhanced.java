
package com.travelcorp.ccapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchToursEnhanced complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchToursEnhanced">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="securityKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellingCompanyCodes" type="{http://CCAPI.TravelCorp.com/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="continent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="months" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keywords" type="{http://CCAPI.TravelCorp.com/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="FirstRecordNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NumberOfRecords" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OrderBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderDirection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchToursEnhanced", propOrder = {
    "securityKey",
    "sellingCompanyCodes",
    "continent",
    "country",
    "duration",
    "months",
    "keywords",
    "firstRecordNumber",
    "numberOfRecords",
    "orderBy",
    "orderDirection"
})
public class SearchToursEnhanced {

    protected String securityKey;
    protected ArrayOfString sellingCompanyCodes;
    protected String continent;
    protected String country;
    protected String duration;
    protected String months;
    protected ArrayOfString keywords;
    @XmlElement(name = "FirstRecordNumber")
    protected int firstRecordNumber;
    @XmlElement(name = "NumberOfRecords")
    protected int numberOfRecords;
    @XmlElement(name = "OrderBy")
    protected String orderBy;
    @XmlElement(name = "OrderDirection")
    protected String orderDirection;

    /**
     * Gets the value of the securityKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityKey() {
        return securityKey;
    }

    /**
     * Sets the value of the securityKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityKey(String value) {
        this.securityKey = value;
    }

    /**
     * Gets the value of the sellingCompanyCodes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getSellingCompanyCodes() {
        return sellingCompanyCodes;
    }

    /**
     * Sets the value of the sellingCompanyCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setSellingCompanyCodes(ArrayOfString value) {
        this.sellingCompanyCodes = value;
    }

    /**
     * Gets the value of the continent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Sets the value of the continent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContinent(String value) {
        this.continent = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuration(String value) {
        this.duration = value;
    }

    /**
     * Gets the value of the months property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonths() {
        return months;
    }

    /**
     * Sets the value of the months property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonths(String value) {
        this.months = value;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getKeywords() {
        return keywords;
    }

    /**
     * Sets the value of the keywords property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setKeywords(ArrayOfString value) {
        this.keywords = value;
    }

    /**
     * Gets the value of the firstRecordNumber property.
     * 
     */
    public int getFirstRecordNumber() {
        return firstRecordNumber;
    }

    /**
     * Sets the value of the firstRecordNumber property.
     * 
     */
    public void setFirstRecordNumber(int value) {
        this.firstRecordNumber = value;
    }

    /**
     * Gets the value of the numberOfRecords property.
     * 
     */
    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    /**
     * Sets the value of the numberOfRecords property.
     * 
     */
    public void setNumberOfRecords(int value) {
        this.numberOfRecords = value;
    }

    /**
     * Gets the value of the orderBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * Sets the value of the orderBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderBy(String value) {
        this.orderBy = value;
    }

    /**
     * Gets the value of the orderDirection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderDirection() {
        return orderDirection;
    }

    /**
     * Sets the value of the orderDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderDirection(String value) {
        this.orderDirection = value;
    }

}
