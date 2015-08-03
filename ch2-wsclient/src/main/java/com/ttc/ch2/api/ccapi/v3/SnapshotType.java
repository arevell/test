
package com.ttc.ch2.api.ccapi.v3;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SnapshotType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SnapshotType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileNameDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countFile" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="brandCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="noProcessingFile" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SnapshotType", propOrder = {
    "fileName",
    "fileNameDesc",
    "message",
    "typeMsg",
    "countFile",
    "brandCode",
    "dateUpdate",
    "noProcessingFile"
})
public class SnapshotType {

    protected String fileName;
    protected String fileNameDesc;
    protected String message;
    protected String typeMsg;
    protected BigInteger countFile;
    @XmlElement(required = true)
    protected String brandCode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateUpdate;
    protected BigInteger noProcessingFile;

    /**
     * Gets the value of the fileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the fileNameDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileNameDesc() {
        return fileNameDesc;
    }

    /**
     * Sets the value of the fileNameDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileNameDesc(String value) {
        this.fileNameDesc = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the typeMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeMsg() {
        return typeMsg;
    }

    /**
     * Sets the value of the typeMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeMsg(String value) {
        this.typeMsg = value;
    }

    /**
     * Gets the value of the countFile property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCountFile() {
        return countFile;
    }

    /**
     * Sets the value of the countFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCountFile(BigInteger value) {
        this.countFile = value;
    }

    /**
     * Gets the value of the brandCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * Sets the value of the brandCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandCode(String value) {
        this.brandCode = value;
    }

    /**
     * Gets the value of the dateUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateUpdate() {
        return dateUpdate;
    }

    /**
     * Sets the value of the dateUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateUpdate(XMLGregorianCalendar value) {
        this.dateUpdate = value;
    }

    /**
     * Gets the value of the noProcessingFile property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNoProcessingFile() {
        return noProcessingFile;
    }

    /**
     * Sets the value of the noProcessingFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNoProcessingFile(BigInteger value) {
        this.noProcessingFile = value;
    }

}
