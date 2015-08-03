
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MarketingFlags complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MarketingFlags">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MarketingPriority" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}string" minOccurs="0"/>
 *         &lt;element name="MostPopular" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="KeywordsPhrases" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}KeywordsPhrases" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketingFlags", propOrder = {
    "marketingPriority",
    "mostPopular",
    "keywordsPhrases"
})
public class MarketingFlags {

    @XmlElement(name = "MarketingPriority")
    protected String marketingPriority;
    @XmlElement(name = "MostPopular")
    protected Boolean mostPopular;
    @XmlElement(name = "KeywordsPhrases")
    protected KeywordsPhrases keywordsPhrases;

    /**
     * Gets the value of the marketingPriority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketingPriority() {
        return marketingPriority;
    }

    /**
     * Sets the value of the marketingPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketingPriority(String value) {
        this.marketingPriority = value;
    }

    /**
     * Gets the value of the mostPopular property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMostPopular() {
        return mostPopular;
    }

    /**
     * Sets the value of the mostPopular property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMostPopular(Boolean value) {
        this.mostPopular = value;
    }

    /**
     * Gets the value of the keywordsPhrases property.
     * 
     * @return
     *     possible object is
     *     {@link KeywordsPhrases }
     *     
     */
    public KeywordsPhrases getKeywordsPhrases() {
        return keywordsPhrases;
    }

    /**
     * Sets the value of the keywordsPhrases property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeywordsPhrases }
     *     
     */
    public void setKeywordsPhrases(KeywordsPhrases value) {
        this.keywordsPhrases = value;
    }

}
