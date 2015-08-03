
package com.travelcorp.ccapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchToursResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchToursResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchToursResult" type="{http://CCAPI.TravelCorp.com/}SearchTourResults" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchToursResponse", propOrder = {
    "searchToursResult"
})
public class SearchToursResponse {

    @XmlElement(name = "SearchToursResult")
    protected SearchTourResults searchToursResult;

    /**
     * Gets the value of the searchToursResult property.
     * 
     * @return
     *     possible object is
     *     {@link SearchTourResults }
     *     
     */
    public SearchTourResults getSearchToursResult() {
        return searchToursResult;
    }

    /**
     * Sets the value of the searchToursResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchTourResults }
     *     
     */
    public void setSearchToursResult(SearchTourResults value) {
        this.searchToursResult = value;
    }

}
