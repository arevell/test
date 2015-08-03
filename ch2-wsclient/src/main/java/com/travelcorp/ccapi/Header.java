
package com.travelcorp.ccapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Header complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Header">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SearchResultsTotalRecords" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NumberOfRecords" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SubsetReturned" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderDirection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorMessaages" type="{http://CCAPI.TravelCorp.com/}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Header", propOrder = {
    "successful",
    "searchResultsTotalRecords",
    "numberOfRecords",
    "subsetReturned",
    "orderBy",
    "orderDirection",
    "errorMessaages"
})
public class Header {

    @XmlElement(name = "Successful")
    protected boolean successful;
    @XmlElement(name = "SearchResultsTotalRecords")
    protected int searchResultsTotalRecords;
    @XmlElement(name = "NumberOfRecords")
    protected int numberOfRecords;
    @XmlElement(name = "SubsetReturned")
    protected String subsetReturned;
    @XmlElement(name = "OrderBy")
    protected String orderBy;
    @XmlElement(name = "OrderDirection")
    protected String orderDirection;
    @XmlElement(name = "ErrorMessaages")
    protected ArrayOfString errorMessaages;

    /**
     * Gets the value of the successful property.
     * 
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Sets the value of the successful property.
     * 
     */
    public void setSuccessful(boolean value) {
        this.successful = value;
    }

    /**
     * Gets the value of the searchResultsTotalRecords property.
     * 
     */
    public int getSearchResultsTotalRecords() {
        return searchResultsTotalRecords;
    }

    /**
     * Sets the value of the searchResultsTotalRecords property.
     * 
     */
    public void setSearchResultsTotalRecords(int value) {
        this.searchResultsTotalRecords = value;
    }

    /**
     * Gets the value of the numberOfRecords property.
     * 
     */
    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    /**
     * Sets the value of the numberOfRecords property.
     * 
     */
    public void setNumberOfRecords(int value) {
        this.numberOfRecords = value;
    }

    /**
     * Gets the value of the subsetReturned property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsetReturned() {
        return subsetReturned;
    }

    /**
     * Sets the value of the subsetReturned property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsetReturned(String value) {
        this.subsetReturned = value;
    }

    /**
     * Gets the value of the orderBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * Sets the value of the orderBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderBy(String value) {
        this.orderBy = value;
    }

    /**
     * Gets the value of the orderDirection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderDirection() {
        return orderDirection;
    }

    /**
     * Sets the value of the orderDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderDirection(String value) {
        this.orderDirection = value;
    }

    /**
     * Gets the value of the errorMessaages property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getErrorMessaages() {
        return errorMessaages;
    }

    /**
     * Sets the value of the errorMessaages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setErrorMessaages(ArrayOfString value) {
        this.errorMessaages = value;
    }

}
