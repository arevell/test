//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.29 at 04:55:42 PM CET 
//


package com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContinentsVisited complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContinentsVisited">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Continent" type="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}Continent" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContinentsVisited", propOrder = {
    "continent"
})
public class ContinentsVisited {

    @XmlElement(name = "Continent", required = true)
    protected List<Continent> continent;

    /**
     * Gets the value of the continent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the continent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContinent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Continent }
     * 
     * 
     */
    public List<Continent> getContinent() {
        if (continent == null) {
            continent = new ArrayList<Continent>();
        }
        return this.continent;
    }

}
