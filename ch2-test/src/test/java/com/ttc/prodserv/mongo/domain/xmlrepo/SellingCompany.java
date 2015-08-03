package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ttc.prodserv.mongo.domain.EntityBase;

/**
 * <p>Java class for sellingCompany complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sellingCompany">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="legalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brandCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brandName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@Document(collection = "SellingCompanies")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sellingCompany", propOrder = {
    "sellingCompanyId",
    "name",
    "legalName",
    "brandCode",
    "brandName",
    "currencyCode",
    "note",
    "id",
    "version",
    "refreshId",
    "md5Checksum"
})
public class SellingCompany extends EntityBase {

	private static final long serialVersionUID = -3813390023184248408L;

	protected long sellingCompanyId;
    protected String name;
    protected String legalName;
    protected String brandCode;
    protected String brandName;
    protected String currencyCode;
    protected String note;
    @XmlAttribute(name = "timestamp")
    @XmlSchemaType(name = "dateTime")
    protected Date timestamp;
    
    protected String refreshId;

   
    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the legalName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * Sets the value of the legalName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalName(String value) {
        this.legalName = value;
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
     * Gets the value of the brandName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Sets the value of the brandName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandName(String value) {
        this.brandName = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

	public long getSellingCompanyId() {
		return sellingCompanyId;
	}

	public void setSellingCompanyId(long sellingCompanyId) {
		this.sellingCompanyId = sellingCompanyId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getRefreshId() {
		return refreshId;
	}

	public void setRefreshId(String refreshId) {
		this.refreshId = refreshId;
	}

}
