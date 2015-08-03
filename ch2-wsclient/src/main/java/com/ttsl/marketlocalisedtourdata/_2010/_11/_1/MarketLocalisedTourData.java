
package com.ttsl.marketlocalisedtourdata._2010._11._1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}TourInfo"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}DepartureInfo"/>
 *       &lt;/sequence>
 *       &lt;attribute name="BookableOnline" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="BrandCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TropicsBrochureCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CatalogueCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Currency" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Duration" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="MarketVariationCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OperatingProductCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SellingCompanyCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TourPackage" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tourInfo",
    "departureInfo"
})
@XmlRootElement(name = "MarketLocalisedTourData")
public class MarketLocalisedTourData {

    @XmlElement(name = "TourInfo", required = true)
    protected TourInfo tourInfo;
    @XmlElement(name = "DepartureInfo", required = true)
    protected DepartureInfo departureInfo;
    @XmlAttribute(name = "BookableOnline", required = true)
    protected boolean bookableOnline;
    @XmlAttribute(name = "BrandCode", required = true)
    protected String brandCode;
    @XmlAttribute(name = "TropicsBrochureCode", required = true)
    protected String tropicsBrochureCode;
    @XmlAttribute(name = "CatalogueCode", required = true)
    protected String catalogueCode;
    @XmlAttribute(name = "Currency", required = true)
    protected String currency;
    @XmlAttribute(name = "Duration", required = true)
    protected BigInteger duration;
    @XmlAttribute(name = "MarketVariationCode", required = true)
    protected String marketVariationCode;
    @XmlAttribute(name = "OperatingProductCode", required = true)
    protected String operatingProductCode;
    @XmlAttribute(name = "SellingCompanyCode", required = true)
    protected String sellingCompanyCode;
    @XmlAttribute(name = "TourPackage")
    protected Boolean tourPackage;

    /**
     * Gets the value of the tourInfo property.
     * 
     * @return
     *     possible object is
     *     {@link TourInfo }
     *     
     */
    public TourInfo getTourInfo() {
        return tourInfo;
    }

    /**
     * Sets the value of the tourInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TourInfo }
     *     
     */
    public void setTourInfo(TourInfo value) {
        this.tourInfo = value;
    }

    /**
     * Gets the value of the departureInfo property.
     * 
     * @return
     *     possible object is
     *     {@link DepartureInfo }
     *     
     */
    public DepartureInfo getDepartureInfo() {
        return departureInfo;
    }

    /**
     * Sets the value of the departureInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link DepartureInfo }
     *     
     */
    public void setDepartureInfo(DepartureInfo value) {
        this.departureInfo = value;
    }

    /**
     * Gets the value of the bookableOnline property.
     * 
     */
    public boolean isBookableOnline() {
        return bookableOnline;
    }

    /**
     * Sets the value of the bookableOnline property.
     * 
     */
    public void setBookableOnline(boolean value) {
        this.bookableOnline = value;
    }

    /**
     * Gets the value of the brandCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * Sets the value of the brandCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandCode(String value) {
        this.brandCode = value;
    }

    /**
     * Gets the value of the tropicsBrochureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTropicsBrochureCode() {
        return tropicsBrochureCode;
    }

    /**
     * Sets the value of the tropicsBrochureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTropicsBrochureCode(String value) {
        this.tropicsBrochureCode = value;
    }

    /**
     * Gets the value of the catalogueCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatalogueCode() {
        return catalogueCode;
    }

    /**
     * Sets the value of the catalogueCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatalogueCode(String value) {
        this.catalogueCode = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDuration(BigInteger value) {
        this.duration = value;
    }

    /**
     * Gets the value of the marketVariationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketVariationCode() {
        return marketVariationCode;
    }

    /**
     * Sets the value of the marketVariationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketVariationCode(String value) {
        this.marketVariationCode = value;
    }

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
     * Gets the value of the tourPackage property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTourPackage() {
        return tourPackage;
    }

    /**
     * Sets the value of the tourPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTourPackage(Boolean value) {
        this.tourPackage = value;
    }

}
