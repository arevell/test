//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.29 at 04:55:42 PM CET 
//


package com.ttc.ch2.api.ccapi.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5.TourDeparturesType;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo;


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
 *         &lt;element name="messageContext" type="{http://www.ttc.com/ch2/api/ccapi/v3}MessageContext" minOccurs="0"/>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4}TourDepartures" minOccurs="0"/>
 *         &lt;element ref="{http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0}TourInfo" minOccurs="0"/>
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
    "messageContext",
    "successful",
    "tourDepartures",
    "tourInfo"
})
@XmlRootElement(name = "GetTourDetailsFullResponse")
public class GetTourDetailsFullResponse {

    protected MessageContext messageContext;
    protected boolean successful;
    @XmlElement(name = "TourDepartures", namespace = "http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4")
    protected TourDeparturesType tourDepartures;
    @XmlElement(name = "TourInfo", namespace = "http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0")
    protected TourInfo tourInfo;

    /**
     * Gets the value of the messageContext property.
     * 
     * @return
     *     possible object is
     *     {@link MessageContext }
     *     
     */
    public MessageContext getMessageContext() {
        return messageContext;
    }

    /**
     * Sets the value of the messageContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageContext }
     *     
     */
    public void setMessageContext(MessageContext value) {
        this.messageContext = value;
    }

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
     * Gets the value of the tourDepartures property.
     * 
     * @return
     *     possible object is
     *     {@link TourDeparturesType }
     *     
     */
    public TourDeparturesType getTourDepartures() {
        return tourDepartures;
    }

    /**
     * Sets the value of the tourDepartures property.
     * 
     * @param value
     *     allowed object is
     *     {@link TourDeparturesType }
     *     
     */
    public void setTourDepartures(TourDeparturesType value) {
        this.tourDepartures = value;
    }

    /**
     * Gets the value of the tourInfo property.
     * 
     * @return
     *     possible object is
     *     {@link TourInfo }
     *     
     */
    public TourInfo getTourInfo() {
        return tourInfo;
    }

    /**
     * Sets the value of the tourInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TourInfo }
     *     
     */
    public void setTourInfo(TourInfo value) {
        this.tourInfo = value;
    }

}
