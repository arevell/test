
package com.ttsl.marketlocalisedtourdata._2010._11._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0}ItinerarySegment" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "itinerarySegment"
})
@XmlRootElement(name = "Itinerary")
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
