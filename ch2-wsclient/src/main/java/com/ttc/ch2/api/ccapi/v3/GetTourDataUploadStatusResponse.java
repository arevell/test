
package com.ttc.ch2.api.ccapi.v3;

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
 *         &lt;element name="currentBrandStatus" type="{http://www.ttc.com/ch2/api/ccapi/v3}CurrentBrandStatusType"/>
 *         &lt;element name="uploadFileStatus" type="{http://www.ttc.com/ch2/api/ccapi/v3}UploadFileStatusType" minOccurs="0"/>
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
    "currentBrandStatus",
    "uploadFileStatus"
})
@XmlRootElement(name = "GetTourDataUploadStatusResponse")
public class GetTourDataUploadStatusResponse {

    protected MessageContext messageContext;
    protected boolean successful;
    @XmlElement(required = true)
    protected CurrentBrandStatusType currentBrandStatus;
    protected UploadFileStatusType uploadFileStatus;

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
     * Gets the value of the currentBrandStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CurrentBrandStatusType }
     *     
     */
    public CurrentBrandStatusType getCurrentBrandStatus() {
        return currentBrandStatus;
    }

    /**
     * Sets the value of the currentBrandStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrentBrandStatusType }
     *     
     */
    public void setCurrentBrandStatus(CurrentBrandStatusType value) {
        this.currentBrandStatus = value;
    }

    /**
     * Gets the value of the uploadFileStatus property.
     * 
     * @return
     *     possible object is
     *     {@link UploadFileStatusType }
     *     
     */
    public UploadFileStatusType getUploadFileStatus() {
        return uploadFileStatus;
    }

    /**
     * Sets the value of the uploadFileStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link UploadFileStatusType }
     *     
     */
    public void setUploadFileStatus(UploadFileStatusType value) {
        this.uploadFileStatus = value;
    }

}
