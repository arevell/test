
package com.travcorp.contenthub.tour_data._2010._11._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TourDetailsFull complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TourDetailsFull">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="securityKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellingCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marketVariationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TourDetailsFull", propOrder = {
    "securityKey",
    "sellingCompanyCode",
    "marketVariationCode"
})
public class TourDetailsFull {

    protected String securityKey;
    protected String sellingCompanyCode;
    protected String marketVariationCode;

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

}
