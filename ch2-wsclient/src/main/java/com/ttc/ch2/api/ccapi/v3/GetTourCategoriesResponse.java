
package com.ttc.ch2.api.ccapi.v3;

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
 *         &lt;element name="messageContext" type="{http://www.ttc.com/ch2/api/ccapi/v3}MessageContext" minOccurs="0"/>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tourCategories" type="{http://www.ttc.com/ch2/api/ccapi/v3}TourCategories" maxOccurs="unbounded"/>
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
    "tourCategories"
})
@XmlRootElement(name = "GetTourCategoriesResponse")
public class GetTourCategoriesResponse {

    protected MessageContext messageContext;
    protected boolean successful;
    @XmlElement(required = true)
    protected List<TourCategories> tourCategories;

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
     * Gets the value of the tourCategories property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tourCategories property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTourCategories().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TourCategories }
     * 
     * 
     */
    public List<TourCategories> getTourCategories() {
        if (tourCategories == null) {
            tourCategories = new ArrayList<TourCategories>();
        }
        return this.tourCategories;
    }

}
