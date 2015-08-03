
package com.travelcorp.ccapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchTourResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchTourResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://CCAPI.TravelCorp.com/}Header" minOccurs="0"/>
 *         &lt;element name="Results" type="{http://CCAPI.TravelCorp.com/}ArrayOfSearchTourResultsItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchTourResults", propOrder = {
    "header",
    "results"
})
public class SearchTourResults {

    @XmlElement(name = "Header")
    protected Header header;
    @XmlElement(name = "Results")
    protected ArrayOfSearchTourResultsItem results;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the results property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSearchTourResultsItem }
     *     
     */
    public ArrayOfSearchTourResultsItem getResults() {
        return results;
    }

    /**
     * Sets the value of the results property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSearchTourResultsItem }
     *     
     */
    public void setResults(ArrayOfSearchTourResultsItem value) {
        this.results = value;
    }

}
