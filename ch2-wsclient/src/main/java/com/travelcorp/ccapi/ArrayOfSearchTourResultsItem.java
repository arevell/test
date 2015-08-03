
package com.travelcorp.ccapi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSearchTourResultsItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSearchTourResultsItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchTourResultsItem" type="{http://CCAPI.TravelCorp.com/}SearchTourResultsItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSearchTourResultsItem", propOrder = {
    "searchTourResultsItem"
})
public class ArrayOfSearchTourResultsItem {

    @XmlElement(name = "SearchTourResultsItem", nillable = true)
    protected List<SearchTourResultsItem> searchTourResultsItem;

    /**
     * Gets the value of the searchTourResultsItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchTourResultsItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchTourResultsItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchTourResultsItem }
     * 
     * 
     */
    public List<SearchTourResultsItem> getSearchTourResultsItem() {
        if (searchTourResultsItem == null) {
            searchTourResultsItem = new ArrayList<SearchTourResultsItem>();
        }
        return this.searchTourResultsItem;
    }

}
