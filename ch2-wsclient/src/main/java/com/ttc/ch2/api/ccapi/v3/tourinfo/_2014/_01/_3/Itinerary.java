
package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Itinerary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Itinerary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItinerarySegment" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}ItinerarySegment" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Itinerary", propOrder = {
    "itinerarySegment"
})
public class Itinerary {

    @XmlElement(name = "ItinerarySegment", required = true)
    protected List<ItinerarySegment> itinerarySegment;

    /**
     * Gets the value of the itinerarySegment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itinerarySegment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItinerarySegment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItinerarySegment }
     * 
     * 
     */
    public List<ItinerarySegment> getItinerarySegment() {
        if (itinerarySegment == null) {
            itinerarySegment = new ArrayList<ItinerarySegment>();
        }
        return this.itinerarySegment;
    }

}
