
package com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DepartureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DepartureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DepartureCode" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}string"/>
 *         &lt;element name="AvailabilityStatus" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}AvailabilityStatusType"/>
 *         &lt;element name="StartDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DateRangeIncludesTravelTime" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DefiniteDeparture" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Notes" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}Notes" minOccurs="0"/>
 *         &lt;element name="TourRules" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}TourRulesType"/>
 *         &lt;element name="AssociatedProducts" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}AssociatedProductsType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepartureType", propOrder = {
    "departureCode",
    "availabilityStatus",
    "startDateTime",
    "endDateTime",
    "dateRangeIncludesTravelTime",
    "definiteDeparture",
    "notes",
    "tourRules",
    "associatedProducts"
})
public class DepartureType {

    @XmlElement(name = "DepartureCode", required = true)
    protected String departureCode;
    @XmlElement(name = "AvailabilityStatus", required = true)
    protected AvailabilityStatusType availabilityStatus;
    @XmlElement(name = "StartDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDateTime;
    @XmlElement(name = "EndDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDateTime;
    @XmlElement(name = "DateRangeIncludesTravelTime")
    protected boolean dateRangeIncludesTravelTime;
    @XmlElement(name = "DefiniteDeparture")
    protected boolean definiteDeparture;
    @XmlElement(name = "Notes")
    protected Notes notes;
    @XmlElement(name = "TourRules", required = true)
    protected TourRulesType tourRules;
    @XmlElement(name = "AssociatedProducts")
    protected AssociatedProductsType associatedProducts;

    /**
     * Gets the value of the departureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureCode() {
        return departureCode;
    }

    /**
     * Sets the value of the departureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureCode(String value) {
        this.departureCode = value;
    }

    /**
     * Gets the value of the availabilityStatus property.
     * 
     * @return
     *     possible object is
     *     {@link AvailabilityStatusType }
     *     
     */
    public AvailabilityStatusType getAvailabilityStatus() {
        return availabilityStatus;
    }

    /**
     * Sets the value of the availabilityStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link AvailabilityStatusType }
     *     
     */
    public void setAvailabilityStatus(AvailabilityStatusType value) {
        this.availabilityStatus = value;
    }

    /**
     * Gets the value of the startDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the value of the startDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDateTime(XMLGregorianCalendar value) {
        this.startDateTime = value;
    }

    /**
     * Gets the value of the endDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDateTime() {
        return endDateTime;
    }

    /**
     * Sets the value of the endDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDateTime(XMLGregorianCalendar value) {
        this.endDateTime = value;
    }

    /**
     * Gets the value of the dateRangeIncludesTravelTime property.
     * 
     */
    public boolean isDateRangeIncludesTravelTime() {
        return dateRangeIncludesTravelTime;
    }

    /**
     * Sets the value of the dateRangeIncludesTravelTime property.
     * 
     */
    public void setDateRangeIncludesTravelTime(boolean value) {
        this.dateRangeIncludesTravelTime = value;
    }

    /**
     * Gets the value of the definiteDeparture property.
     * 
     */
    public boolean isDefiniteDeparture() {
        return definiteDeparture;
    }

    /**
     * Sets the value of the definiteDeparture property.
     * 
     */
    public void setDefiniteDeparture(boolean value) {
        this.definiteDeparture = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link Notes }
     *     
     */
    public Notes getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Notes }
     *     
     */
    public void setNotes(Notes value) {
        this.notes = value;
    }

    /**
     * Gets the value of the tourRules property.
     * 
     * @return
     *     possible object is
     *     {@link TourRulesType }
     *     
     */
    public TourRulesType getTourRules() {
        return tourRules;
    }

    /**
     * Sets the value of the tourRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link TourRulesType }
     *     
     */
    public void setTourRules(TourRulesType value) {
        this.tourRules = value;
    }

    /**
     * Gets the value of the associatedProducts property.
     * 
     * @return
     *     possible object is
     *     {@link AssociatedProductsType }
     *     
     */
    public AssociatedProductsType getAssociatedProducts() {
        return associatedProducts;
    }

    /**
     * Sets the value of the associatedProducts property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssociatedProductsType }
     *     
     */
    public void setAssociatedProducts(AssociatedProductsType value) {
        this.associatedProducts = value;
    }

}
