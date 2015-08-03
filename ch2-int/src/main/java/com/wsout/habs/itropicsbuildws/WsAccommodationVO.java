
package com.wsout.habs.itropicsbuildws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsAccommodationVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsAccommodationVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccommodationProduct" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}wsAccommodationProductVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsAccommodationVO", propOrder = {
    "accommodationProduct"
})
public class WsAccommodationVO {

    @XmlElement(name = "AccommodationProduct")
    protected List<WsAccommodationProductVO> accommodationProduct;

    /**
     * Gets the value of the accommodationProduct property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accommodationProduct property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccommodationProduct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsAccommodationProductVO }
     * 
     * 
     */
    public List<WsAccommodationProductVO> getAccommodationProduct() {
        if (accommodationProduct == null) {
            accommodationProduct = new ArrayList<WsAccommodationProductVO>();
        }
        return this.accommodationProduct;
    }

}
