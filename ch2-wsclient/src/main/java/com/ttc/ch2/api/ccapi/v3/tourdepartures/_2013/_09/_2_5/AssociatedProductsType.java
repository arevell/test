
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssociatedProductsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssociatedProductsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccommodationProducts" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}AccommodationProductsType" minOccurs="0"/>
 *         &lt;element name="MiscellaneousProducts" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}MiscellaneousProductsType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssociatedProductsType", propOrder = {
    "accommodationProducts",
    "miscellaneousProducts"
})
public class AssociatedProductsType {

    @XmlElement(name = "AccommodationProducts")
    protected AccommodationProductsType accommodationProducts;
    @XmlElement(name = "MiscellaneousProducts")
    protected MiscellaneousProductsType miscellaneousProducts;

    /**
     * Gets the value of the accommodationProducts property.
     * 
     * @return
     *     possible object is
     *     {@link AccommodationProductsType }
     *     
     */
    public AccommodationProductsType getAccommodationProducts() {
        return accommodationProducts;
    }

    /**
     * Sets the value of the accommodationProducts property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccommodationProductsType }
     *     
     */
    public void setAccommodationProducts(AccommodationProductsType value) {
        this.accommodationProducts = value;
    }

    /**
     * Gets the value of the miscellaneousProducts property.
     * 
     * @return
     *     possible object is
     *     {@link MiscellaneousProductsType }
     *     
     */
    public MiscellaneousProductsType getMiscellaneousProducts() {
        return miscellaneousProducts;
    }

    /**
     * Sets the value of the miscellaneousProducts property.
     * 
     * @param value
     *     allowed object is
     *     {@link MiscellaneousProductsType }
     *     
     */
    public void setMiscellaneousProducts(MiscellaneousProductsType value) {
        this.miscellaneousProducts = value;
    }

}
