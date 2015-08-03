
package com.wsout.habs.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsmVorTPDetailVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsmVorTPDetailVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="brochureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="durationDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="durationNights" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="endLocationCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endLocationCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endLocationCityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="endLocationCtry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatingProductCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatingProductId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="operatingRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priceIsIndicative" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startLocationCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startLocationCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startLocationCityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="startLocationCtry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mVorTPid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mVorTPname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsmVorTPDetailVO", propOrder = {
    "brochureCode",
    "description",
    "durationDays",
    "durationNights",
    "endLocationCity",
    "endLocationCityCode",
    "endLocationCityId",
    "endLocationCtry",
    "operatingProductCode",
    "operatingProductId",
    "operatingRegion",
    "priceIsIndicative",
    "productCategory",
    "productStatus",
    "startLocationCity",
    "startLocationCityCode",
    "startLocationCityId",
    "startLocationCtry",
    "mVorTPid",
    "mVorTPname"
})
public class WsmVorTPDetailVO {

    protected String brochureCode;
    protected String description;
    protected Integer durationDays;
    protected Integer durationNights;
    protected String endLocationCity;
    protected String endLocationCityCode;
    protected Long endLocationCityId;
    protected String endLocationCtry;
    protected String operatingProductCode;
    protected Long operatingProductId;
    protected String operatingRegion;
    protected String priceIsIndicative;
    protected String productCategory;
    protected String productStatus;
    protected String startLocationCity;
    protected String startLocationCityCode;
    protected Long startLocationCityId;
    protected String startLocationCtry;
    protected Long mVorTPid;
    protected String mVorTPname;

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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the durationDays property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDurationDays() {
        return durationDays;
    }

    /**
     * Sets the value of the durationDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDurationDays(Integer value) {
        this.durationDays = value;
    }

    /**
     * Gets the value of the durationNights property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDurationNights() {
        return durationNights;
    }

    /**
     * Sets the value of the durationNights property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDurationNights(Integer value) {
        this.durationNights = value;
    }

    /**
     * Gets the value of the endLocationCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndLocationCity() {
        return endLocationCity;
    }

    /**
     * Sets the value of the endLocationCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndLocationCity(String value) {
        this.endLocationCity = value;
    }

    /**
     * Gets the value of the endLocationCityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndLocationCityCode() {
        return endLocationCityCode;
    }

    /**
     * Sets the value of the endLocationCityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndLocationCityCode(String value) {
        this.endLocationCityCode = value;
    }

    /**
     * Gets the value of the endLocationCityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEndLocationCityId() {
        return endLocationCityId;
    }

    /**
     * Sets the value of the endLocationCityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEndLocationCityId(Long value) {
        this.endLocationCityId = value;
    }

    /**
     * Gets the value of the endLocationCtry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndLocationCtry() {
        return endLocationCtry;
    }

    /**
     * Sets the value of the endLocationCtry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndLocationCtry(String value) {
        this.endLocationCtry = value;
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
     * Gets the value of the operatingProductId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOperatingProductId() {
        return operatingProductId;
    }

    /**
     * Sets the value of the operatingProductId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOperatingProductId(Long value) {
        this.operatingProductId = value;
    }

    /**
     * Gets the value of the operatingRegion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingRegion() {
        return operatingRegion;
    }

    /**
     * Sets the value of the operatingRegion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingRegion(String value) {
        this.operatingRegion = value;
    }

    /**
     * Gets the value of the priceIsIndicative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceIsIndicative() {
        return priceIsIndicative;
    }

    /**
     * Sets the value of the priceIsIndicative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceIsIndicative(String value) {
        this.priceIsIndicative = value;
    }

    /**
     * Gets the value of the productCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     * Sets the value of the productCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCategory(String value) {
        this.productCategory = value;
    }

    /**
     * Gets the value of the productStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductStatus() {
        return productStatus;
    }

    /**
     * Sets the value of the productStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductStatus(String value) {
        this.productStatus = value;
    }

    /**
     * Gets the value of the startLocationCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartLocationCity() {
        return startLocationCity;
    }

    /**
     * Sets the value of the startLocationCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartLocationCity(String value) {
        this.startLocationCity = value;
    }

    /**
     * Gets the value of the startLocationCityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartLocationCityCode() {
        return startLocationCityCode;
    }

    /**
     * Sets the value of the startLocationCityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartLocationCityCode(String value) {
        this.startLocationCityCode = value;
    }

    /**
     * Gets the value of the startLocationCityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStartLocationCityId() {
        return startLocationCityId;
    }

    /**
     * Sets the value of the startLocationCityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStartLocationCityId(Long value) {
        this.startLocationCityId = value;
    }

    /**
     * Gets the value of the startLocationCtry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartLocationCtry() {
        return startLocationCtry;
    }

    /**
     * Sets the value of the startLocationCtry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartLocationCtry(String value) {
        this.startLocationCtry = value;
    }

    /**
     * Gets the value of the mVorTPid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMVorTPid() {
        return mVorTPid;
    }

    /**
     * Sets the value of the mVorTPid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMVorTPid(Long value) {
        this.mVorTPid = value;
    }

    /**
     * Gets the value of the mVorTPname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMVorTPname() {
        return mVorTPname;
    }

    /**
     * Sets the value of the mVorTPname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMVorTPname(String value) {
        this.mVorTPname = value;
    }

}
