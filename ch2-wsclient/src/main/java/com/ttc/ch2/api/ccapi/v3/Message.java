
package com.ttc.ch2.api.ccapi.v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Message complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Message">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="interpolatedMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="messageType" type="{http://www.ttc.com/ch2/api/ccapi/v3}MessageType"/>
 *         &lt;element name="messageSubject" type="{http://www.ttc.com/ch2/api/ccapi/v3}MessageSubject" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", propOrder = {
    "interpolatedMessage",
    "messageType",
    "messageSubject"
})
public class Message {

    protected String interpolatedMessage;
    @XmlElement(required = true)
    protected MessageType messageType;
    protected List<MessageSubject> messageSubject;

    /**
     * Gets the value of the interpolatedMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterpolatedMessage() {
        return interpolatedMessage;
    }

    /**
     * Sets the value of the interpolatedMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterpolatedMessage(String value) {
        this.interpolatedMessage = value;
    }

    /**
     * Gets the value of the messageType property.
     * 
     * @return
     *     possible object is
     *     {@link MessageType }
     *     
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * Sets the value of the messageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageType }
     *     
     */
    public void setMessageType(MessageType value) {
        this.messageType = value;
    }

    /**
     * Gets the value of the messageSubject property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageSubject property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageSubject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageSubject }
     * 
     * 
     */
    public List<MessageSubject> getMessageSubject() {
        if (messageSubject == null) {
            messageSubject = new ArrayList<MessageSubject>();
        }
        return this.messageSubject;
    }

}
