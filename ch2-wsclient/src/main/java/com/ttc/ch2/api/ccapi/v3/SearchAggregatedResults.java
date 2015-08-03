
package com.ttc.ch2.api.ccapi.v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchAggregatedResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchAggregatedResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cataloguedTourCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cataloguedTourName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchAggregatedSubResults" type="{http://www.ttc.com/ch2/api/ccapi/v3}SearchAggregatedSubResults" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchAggregatedResults", propOrder = {
    "cataloguedTourCode",
    "cataloguedTourName",
    "searchAggregatedSubResults"
})
public class SearchAggregatedResults {

    protected String cataloguedTourCode;
    protected String cataloguedTourName;
    @XmlElement(required = true)
    protected List<SearchAggregatedSubResults> searchAggregatedSubResults;

    /**
     * Gets the value of the cataloguedTourCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCataloguedTourCode() {
        return cataloguedTourCode;
    }

    /**
     * Sets the value of the cataloguedTourCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCataloguedTourCode(String value) {
        this.cataloguedTourCode = value;
    }

    /**
     * Gets the value of the cataloguedTourName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCataloguedTourName() {
        return cataloguedTourName;
    }

    /**
     * Sets the value of the cataloguedTourName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCataloguedTourName(String value) {
        this.cataloguedTourName = value;
    }

    /**
     * Gets the value of the searchAggregatedSubResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchAggregatedSubResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchAggregatedSubResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchAggregatedSubResults }
     * 
     * 
     */
    public List<SearchAggregatedSubResults> getSearchAggregatedSubResults() {
        if (searchAggregatedSubResults == null) {
            searchAggregatedSubResults = new ArrayList<SearchAggregatedSubResults>();
        }
        return this.searchAggregatedSubResults;
    }

}
