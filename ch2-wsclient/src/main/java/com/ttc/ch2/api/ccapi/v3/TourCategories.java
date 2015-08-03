
package com.ttc.ch2.api.ccapi.v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TourCategories complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TourCategories">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tourCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryValue" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TourCategories", propOrder = {
    "tourCategory",
    "categoryValue"
})
public class TourCategories {

    @XmlElement(required = true)
    protected String tourCategory;
    @XmlElement(required = true)
    protected List<String> categoryValue;

    /**
     * Gets the value of the tourCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourCategory() {
        return tourCategory;
    }

    /**
     * Sets the value of the tourCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourCategory(String value) {
        this.tourCategory = value;
    }

    /**
     * Gets the value of the categoryValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoryValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoryValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCategoryValue() {
        if (categoryValue == null) {
            categoryValue = new ArrayList<String>();
        }
        return this.categoryValue;
    }

}
