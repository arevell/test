
package com.ttc.ch2.api.ccapi.v3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchToursBaseRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchToursBaseRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="securityKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sellingCompanies" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="continentCodes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryCodes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="durationFrom" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="durationTo" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="months" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keywordsAndPhrases" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="preferedRoomType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priceFrom" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="priceTo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="firstRecordNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="numberOfRecords" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="orderBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderDirection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchToursBaseRequest", propOrder = {
    "securityKey",
    "sellingCompanies",
    "continentCodes",
    "countryCodes",
    "durationFrom",
    "durationTo",
    "months",
    "keywordsAndPhrases",
    "preferedRoomType",
    "priceFrom",
    "priceTo",
    "firstRecordNumber",
    "numberOfRecords",
    "orderBy",
    "orderDirection"
})
@XmlSeeAlso({
    SearchToursRequest.class,
    SearchToursAggregatedRequest.class
})
public class SearchToursBaseRequest {

    @XmlElement(required = true)
    protected String securityKey;
    @XmlElement(required = true)
    protected List<String> sellingCompanies;
    protected String continentCodes;
    protected String countryCodes;
    protected BigInteger durationFrom;
    protected BigInteger durationTo;
    protected String months;
    protected List<String> keywordsAndPhrases;
    protected String preferedRoomType;
    protected Double priceFrom;
    protected Double priceTo;
    @XmlElement(required = true)
    protected BigInteger firstRecordNumber;
    @XmlElement(required = true)
    protected BigInteger numberOfRecords;
    protected String orderBy;
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
     * Gets the value of the sellingCompanies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sellingCompanies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSellingCompanies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSellingCompanies() {
        if (sellingCompanies == null) {
            sellingCompanies = new ArrayList<String>();
        }
        return this.sellingCompanies;
    }

    /**
     * Gets the value of the continentCodes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContinentCodes() {
        return continentCodes;
    }

    /**
     * Sets the value of the continentCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContinentCodes(String value) {
        this.continentCodes = value;
    }

    /**
     * Gets the value of the countryCodes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCodes() {
        return countryCodes;
    }

    /**
     * Sets the value of the countryCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCodes(String value) {
        this.countryCodes = value;
    }

    /**
     * Gets the value of the durationFrom property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDurationFrom() {
        return durationFrom;
    }

    /**
     * Sets the value of the durationFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDurationFrom(BigInteger value) {
        this.durationFrom = value;
    }

    /**
     * Gets the value of the durationTo property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDurationTo() {
        return durationTo;
    }

    /**
     * Sets the value of the durationTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDurationTo(BigInteger value) {
        this.durationTo = value;
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
     * Gets the value of the keywordsAndPhrases property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keywordsAndPhrases property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeywordsAndPhrases().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getKeywordsAndPhrases() {
        if (keywordsAndPhrases == null) {
            keywordsAndPhrases = new ArrayList<String>();
        }
        return this.keywordsAndPhrases;
    }

    /**
     * Gets the value of the preferedRoomType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferedRoomType() {
        return preferedRoomType;
    }

    /**
     * Sets the value of the preferedRoomType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferedRoomType(String value) {
        this.preferedRoomType = value;
    }

    /**
     * Gets the value of the priceFrom property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPriceFrom() {
        return priceFrom;
    }

    /**
     * Sets the value of the priceFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPriceFrom(Double value) {
        this.priceFrom = value;
    }

    /**
     * Gets the value of the priceTo property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPriceTo() {
        return priceTo;
    }

    /**
     * Sets the value of the priceTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPriceTo(Double value) {
        this.priceTo = value;
    }

    /**
     * Gets the value of the firstRecordNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFirstRecordNumber() {
        return firstRecordNumber;
    }

    /**
     * Sets the value of the firstRecordNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFirstRecordNumber(BigInteger value) {
        this.firstRecordNumber = value;
    }

    /**
     * Gets the value of the numberOfRecords property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberOfRecords() {
        return numberOfRecords;
    }

    /**
     * Sets the value of the numberOfRecords property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberOfRecords(BigInteger value) {
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
