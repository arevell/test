
package com.travelcorp.ccapi;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchTourResultsItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchTourResultsItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MVCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SellingCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TourName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BrochureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Highlights" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sellable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchTourResultsItem", propOrder = {
    "mvCode",
    "sellingCompanyCode",
    "tourName",
    "brochureCode",
    "price",
    "duration",
    "highlights",
    "sellable"
})
public class SearchTourResultsItem {

    @XmlElement(name = "MVCode")
    protected String mvCode;
    @XmlElement(name = "SellingCompanyCode")
    protected String sellingCompanyCode;
    @XmlElement(name = "TourName")
    protected String tourName;
    @XmlElement(name = "BrochureCode")
    protected String brochureCode;
    @XmlElement(name = "Price", required = true)
    protected BigDecimal price;
    @XmlElement(name = "Duration")
    protected int duration;
    @XmlElement(name = "Highlights")
    protected String highlights;
    @XmlElement(name = "Sellable")
    protected boolean sellable;

    /**
     * Gets the value of the mvCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMVCode() {
        return mvCode;
    }

    /**
     * Sets the value of the mvCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMVCode(String value) {
        this.mvCode = value;
    }

    /**
     * Gets the value of the sellingCompanyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingCompanyCode() {
        return sellingCompanyCode;
    }

    /**
     * Sets the value of the sellingCompanyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingCompanyCode(String value) {
        this.sellingCompanyCode = value;
    }

    /**
     * Gets the value of the tourName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourName() {
        return tourName;
    }

    /**
     * Sets the value of the tourName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourName(String value) {
        this.tourName = value;
    }

    /**
     * Gets the value of the brochureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrochureCode() {
        return brochureCode;
    }

    /**
     * Sets the value of the brochureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrochureCode(String value) {
        this.brochureCode = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     */
    public void setDuration(int value) {
        this.duration = value;
    }

    /**
     * Gets the value of the highlights property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHighlights() {
        return highlights;
    }

    /**
     * Sets the value of the highlights property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHighlights(String value) {
        this.highlights = value;
    }

    /**
     * Gets the value of the sellable property.
     * 
     */
    public boolean isSellable() {
        return sellable;
    }

    /**
     * Sets the value of the sellable property.
     * 
     */
    public void setSellable(boolean value) {
        this.sellable = value;
    }

}
