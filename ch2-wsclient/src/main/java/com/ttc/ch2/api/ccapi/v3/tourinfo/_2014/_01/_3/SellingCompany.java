
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for SellingCompany complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SellingCompany">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Brochure" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}Brochure" maxOccurs="unbounded"/>
 *         &lt;element name="TermsAndConditionsLinkURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="TourCategories" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}TourCategories"/>
 *         &lt;element name="MarketingFlags" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}MarketingFlags" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CurrencyCode" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="Code" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SellingCompany", propOrder = {
    "brochure",
    "termsAndConditionsLinkURL",
    "tourCategories",
    "marketingFlags"
})
public class SellingCompany {

    @XmlElement(name = "Brochure", required = true)
    protected List<Brochure> brochure;
    @XmlElement(name = "TermsAndConditionsLinkURL")
    @XmlSchemaType(name = "anyURI")
    protected String termsAndConditionsLinkURL;
    @XmlElement(name = "TourCategories", required = true)
    protected TourCategories tourCategories;
    @XmlElement(name = "MarketingFlags")
    protected MarketingFlags marketingFlags;
    @XmlAttribute(name = "CurrencyCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String currencyCode;
    @XmlAttribute(name = "Code", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String code;

    /**
     * Gets the value of the brochure property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the brochure property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBrochure().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Brochure }
     * 
     * 
     */
    public List<Brochure> getBrochure() {
        if (brochure == null) {
            brochure = new ArrayList<Brochure>();
        }
        return this.brochure;
    }

    /**
     * Gets the value of the termsAndConditionsLinkURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermsAndConditionsLinkURL() {
        return termsAndConditionsLinkURL;
    }

    /**
     * Sets the value of the termsAndConditionsLinkURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermsAndConditionsLinkURL(String value) {
        this.termsAndConditionsLinkURL = value;
    }

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
     * Gets the value of the marketingFlags property.
     * 
     * @return
     *     possible object is
     *     {@link MarketingFlags }
     *     
     */
    public MarketingFlags getMarketingFlags() {
        return marketingFlags;
    }

    /**
     * Sets the value of the marketingFlags property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarketingFlags }
     *     
     */
    public void setMarketingFlags(MarketingFlags value) {
        this.marketingFlags = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

}
