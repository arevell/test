
package com.wsout.habs.itropicsbuildws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsFoodFundPriceVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsFoodFundPriceVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Price" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsPriceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsFoodFundPriceVO", propOrder = {
    "price"
})
public class WsFoodFundPriceVO {

    @XmlElement(name = "Price")
    protected WsPriceVO price;

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link WsPriceVO }
     *     
     */
    public WsPriceVO getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsPriceVO }
     *     
     */
    public void setPrice(WsPriceVO value) {
        this.price = value;
    }

}
