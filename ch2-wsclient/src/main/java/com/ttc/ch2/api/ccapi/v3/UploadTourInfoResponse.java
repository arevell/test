
package com.ttc.ch2.api.ccapi.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
    "successful"
})
@XmlRootElement(name = "UploadTourInfoResponse")
public class UploadTourInfoResponse {

    protected MessageContext messageContext;
    protected boolean successful;

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

}
