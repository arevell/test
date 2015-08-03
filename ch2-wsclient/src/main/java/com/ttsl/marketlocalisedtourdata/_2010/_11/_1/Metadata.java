
package com.ttsl.marketlocalisedtourdata._2010._11._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}TourCategories"/>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}Brochures"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tourCategories",
    "brochures"
})
@XmlRootElement(name = "Metadata")
public class Metadata {

    @XmlElement(name = "TourCategories", required = true)
    protected TourCategories tourCategories;
    @XmlElement(name = "Brochures", required = true)
    protected Brochures brochures;

    /**
     * Gets the value of the tourCategories property.
     * 
     * @return
     *     possible object is
     *     {@link TourCategories }
     *     
     */
    public TourCategories getTourCategories() {
        return tourCategories;
    }

    /**
     * Sets the value of the tourCategories property.
     * 
     * @param value
     *     allowed object is
     *     {@link TourCategories }
     *     
     */
    public void setTourCategories(TourCategories value) {
        this.tourCategories = value;
    }

    /**
     * Gets the value of the brochures property.
     * 
     * @return
     *     possible object is
     *     {@link Brochures }
     *     
     */
    public Brochures getBrochures() {
        return brochures;
    }

    /**
     * Sets the value of the brochures property.
     * 
     * @param value
     *     allowed object is
     *     {@link Brochures }
     *     
     */
    public void setBrochures(Brochures value) {
        this.brochures = value;
    }

}
