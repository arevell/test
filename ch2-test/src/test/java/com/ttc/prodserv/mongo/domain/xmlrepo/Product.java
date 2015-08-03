package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ttc.prodserv.mongo.domain.EntityBase;

/**
 * <p>Java class for product complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="product">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departures" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}departures" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@Document(collection = "DatesAndRates")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "product", propOrder = {
    "code",
    "departures",
    "id",
    "version",
    "refreshId",
    "md5Checksum"
})
public class Product extends EntityBase {

	private static final long serialVersionUID = 7430691525551550801L;

	protected String code;
	protected Departures departures;
    @XmlAttribute(name = "timestamp")
    @XmlSchemaType(name = "dateTime")
    protected Date timestamp;

    protected String refreshId;
    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the departures property.
     * 
     * @return
     *     possible object is
     *     {@link Departures }
     *     
     */
    public Departures getDepartures() {
        return departures;
    }

    /**
     * Sets the value of the departures property.
     * 
     * @param value
     *     allowed object is
     *     {@link Departures }
     *     
     */
    public void setDepartures(Departures value) {
        this.departures = value;
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
