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
 * <p>Java class for operatingProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="operatingProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatingRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roomOccuMinAdultSingle" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxAdultSingle" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinPaxSingle" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxPaxSingle" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinAdultTwin" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxAdultTwin" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinPaxTwin" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxPaxTwin" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinAdultTwinShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxAdultTwinShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinPaxTwinShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxPaxTwinShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinAdultTriple" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxAdultTriple" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinPaxTriple" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxPaxTriple" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinAdultTripleShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxAdultTripleShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinPaxTripleShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxPaxTripleShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinAdultQuad" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxAdultQuad" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinPaxQuad" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxPaxQuad" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinAdultQuadShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxAdultQuadShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMinPaxQuadShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="roomOccuMaxPaxQuadShare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="payingAdults" type="{http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd}payingAdults" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@Document(collection = "OperatingProducts")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operatingProduct", propOrder = {
	"operatingProductId",
	"code",
	"name",
	"type",
	"category",
	"operatingRegion",
	"productClassification",
	"roomOccuMinAdultSingle",
	"roomOccuMaxAdultSingle",
	"roomOccuMinPaxSingle",
	"roomOccuMaxPaxSingle",
	"roomOccuMinAdultTwin",
	"roomOccuMaxAdultTwin",
	"roomOccuMinPaxTwin",
	"roomOccuMaxPaxTwin",
	"roomOccuMinAdultTwinShare",
	"roomOccuMaxAdultTwinShare",
	"roomOccuMinPaxTwinShare",
	"roomOccuMaxPaxTwinShare",
	"roomOccuMinAdultTriple",
	"roomOccuMaxAdultTriple",
	"roomOccuMinPaxTriple",
	"roomOccuMaxPaxTriple",
	"roomOccuMinAdultTripleShare",
	"roomOccuMaxAdultTripleShare",
	"roomOccuMinPaxTripleShare",
	"roomOccuMaxPaxTripleShare",
	"roomOccuMinAdultQuad",
	"roomOccuMaxAdultQuad",
	"roomOccuMinPaxQuad",
	"roomOccuMaxPaxQuad",
	"roomOccuMinAdultQuadShare",
	"roomOccuMaxAdultQuadShare",
	"roomOccuMinPaxQuadShare",
	"roomOccuMaxPaxQuadShare",
	"payingAdults",
	"id",
	"version",
	"refreshId",
	"md5Checksum"
})
public class OperatingProduct extends EntityBase {

	private static final long serialVersionUID = 989999763573025471L;

	protected long operatingProductId;
	protected String code;
	protected String name;
	protected String type;
	protected String category;
	protected String operatingRegion;
	protected String productClassification;
	protected Integer roomOccuMinAdultSingle;
	protected Integer roomOccuMaxAdultSingle;
	protected Integer roomOccuMinPaxSingle;
	protected Integer roomOccuMaxPaxSingle;
	protected Integer roomOccuMinAdultTwin;
	protected Integer roomOccuMaxAdultTwin;
	protected Integer roomOccuMinPaxTwin;
	protected Integer roomOccuMaxPaxTwin;
	protected Integer roomOccuMinAdultTwinShare;
	protected Integer roomOccuMaxAdultTwinShare;
	protected Integer roomOccuMinPaxTwinShare;
	protected Integer roomOccuMaxPaxTwinShare;
	protected Integer roomOccuMinAdultTriple;
	protected Integer roomOccuMaxAdultTriple;
	protected Integer roomOccuMinPaxTriple;
	protected Integer roomOccuMaxPaxTriple;
	protected Integer roomOccuMinAdultTripleShare;
	protected Integer roomOccuMaxAdultTripleShare;
	protected Integer roomOccuMinPaxTripleShare;
	protected Integer roomOccuMaxPaxTripleShare;
	protected Integer roomOccuMinAdultQuad;
	protected Integer roomOccuMaxAdultQuad;
	protected Integer roomOccuMinPaxQuad;
	protected Integer roomOccuMaxPaxQuad;
	protected Integer roomOccuMinAdultQuadShare;
	protected Integer roomOccuMaxAdultQuadShare;
	protected Integer roomOccuMinPaxQuadShare;
	protected Integer roomOccuMaxPaxQuadShare;
	protected PayingAdults payingAdults;

	@XmlAttribute(name = "timestamp")
	@XmlSchemaType(name = "dateTime")
	protected Date timestamp;
	protected String refreshId;

	/**
	 * Gets the value of the code property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the value of the code property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCode(String value) {
		this.code = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setType(String value) {
		this.type = value;
	}

	/**
	 * Gets the value of the category property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the value of the category property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCategory(String value) {
		this.category = value;
	}

	/**
	 * Gets the value of the operatingRegion property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOperatingRegion() {
		return operatingRegion;
	}

	/**
	 * Sets the value of the operatingRegion property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOperatingRegion(String value) {
		this.operatingRegion = value;
	}

	public String getProductClassification() {
		return productClassification;
	}


	public void setProductClassification(String productClassification) {
		this.productClassification = productClassification;
	}

	/**
	 * Gets the value of the roomOccuMinAdultSingle property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinAdultSingle() {
		return roomOccuMinAdultSingle;
	}

	/**
	 * Sets the value of the roomOccuMinAdultSingle property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinAdultSingle(Integer value) {
		this.roomOccuMinAdultSingle = value;
	}

	/**
	 * Gets the value of the roomOccuMaxAdultSingle property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxAdultSingle() {
		return roomOccuMaxAdultSingle;
	}

	/**
	 * Sets the value of the roomOccuMaxAdultSingle property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxAdultSingle(Integer value) {
		this.roomOccuMaxAdultSingle = value;
	}

	/**
	 * Gets the value of the roomOccuMinPaxSingle property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinPaxSingle() {
		return roomOccuMinPaxSingle;
	}

	/**
	 * Sets the value of the roomOccuMinPaxSingle property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinPaxSingle(Integer value) {
		this.roomOccuMinPaxSingle = value;
	}

	/**
	 * Gets the value of the roomOccuMaxPaxSingle property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxPaxSingle() {
		return roomOccuMaxPaxSingle;
	}

	/**
	 * Sets the value of the roomOccuMaxPaxSingle property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxPaxSingle(Integer value) {
		this.roomOccuMaxPaxSingle = value;
	}

	/**
	 * Gets the value of the roomOccuMinAdultTwin property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinAdultTwin() {
		return roomOccuMinAdultTwin;
	}

	/**
	 * Sets the value of the roomOccuMinAdultTwin property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinAdultTwin(Integer value) {
		this.roomOccuMinAdultTwin = value;
	}

	/**
	 * Gets the value of the roomOccuMaxAdultTwin property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxAdultTwin() {
		return roomOccuMaxAdultTwin;
	}

	/**
	 * Sets the value of the roomOccuMaxAdultTwin property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxAdultTwin(Integer value) {
		this.roomOccuMaxAdultTwin = value;
	}

	/**
	 * Gets the value of the roomOccuMinPaxTwin property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinPaxTwin() {
		return roomOccuMinPaxTwin;
	}

	/**
	 * Sets the value of the roomOccuMinPaxTwin property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinPaxTwin(Integer value) {
		this.roomOccuMinPaxTwin = value;
	}

	/**
	 * Gets the value of the roomOccuMaxPaxTwin property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxPaxTwin() {
		return roomOccuMaxPaxTwin;
	}

	/**
	 * Sets the value of the roomOccuMaxPaxTwin property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxPaxTwin(Integer value) {
		this.roomOccuMaxPaxTwin = value;
	}

	/**
	 * Gets the value of the roomOccuMinAdultTwinShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinAdultTwinShare() {
		return roomOccuMinAdultTwinShare;
	}

	/**
	 * Sets the value of the roomOccuMinAdultTwinShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinAdultTwinShare(Integer value) {
		this.roomOccuMinAdultTwinShare = value;
	}

	/**
	 * Gets the value of the roomOccuMaxAdultTwinShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxAdultTwinShare() {
		return roomOccuMaxAdultTwinShare;
	}

	/**
	 * Sets the value of the roomOccuMaxAdultTwinShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxAdultTwinShare(Integer value) {
		this.roomOccuMaxAdultTwinShare = value;
	}

	/**
	 * Gets the value of the roomOccuMinPaxTwinShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinPaxTwinShare() {
		return roomOccuMinPaxTwinShare;
	}

	/**
	 * Sets the value of the roomOccuMinPaxTwinShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinPaxTwinShare(Integer value) {
		this.roomOccuMinPaxTwinShare = value;
	}

	/**
	 * Gets the value of the roomOccuMaxPaxTwinShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxPaxTwinShare() {
		return roomOccuMaxPaxTwinShare;
	}

	/**
	 * Sets the value of the roomOccuMaxPaxTwinShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxPaxTwinShare(Integer value) {
		this.roomOccuMaxPaxTwinShare = value;
	}

	/**
	 * Gets the value of the roomOccuMinAdultTriple property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinAdultTriple() {
		return roomOccuMinAdultTriple;
	}

	/**
	 * Sets the value of the roomOccuMinAdultTriple property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinAdultTriple(Integer value) {
		this.roomOccuMinAdultTriple = value;
	}

	/**
	 * Gets the value of the roomOccuMaxAdultTriple property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxAdultTriple() {
		return roomOccuMaxAdultTriple;
	}

	/**
	 * Sets the value of the roomOccuMaxAdultTriple property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxAdultTriple(Integer value) {
		this.roomOccuMaxAdultTriple = value;
	}

	/**
	 * Gets the value of the roomOccuMinPaxTriple property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinPaxTriple() {
		return roomOccuMinPaxTriple;
	}

	/**
	 * Sets the value of the roomOccuMinPaxTriple property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinPaxTriple(Integer value) {
		this.roomOccuMinPaxTriple = value;
	}

	/**
	 * Gets the value of the roomOccuMaxPaxTriple property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxPaxTriple() {
		return roomOccuMaxPaxTriple;
	}

	/**
	 * Sets the value of the roomOccuMaxPaxTriple property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxPaxTriple(Integer value) {
		this.roomOccuMaxPaxTriple = value;
	}

	/**
	 * Gets the value of the roomOccuMinAdultTripleShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinAdultTripleShare() {
		return roomOccuMinAdultTripleShare;
	}

	/**
	 * Sets the value of the roomOccuMinAdultTripleShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinAdultTripleShare(Integer value) {
		this.roomOccuMinAdultTripleShare = value;
	}

	/**
	 * Gets the value of the roomOccuMaxAdultTripleShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxAdultTripleShare() {
		return roomOccuMaxAdultTripleShare;
	}

	/**
	 * Sets the value of the roomOccuMaxAdultTripleShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxAdultTripleShare(Integer value) {
		this.roomOccuMaxAdultTripleShare = value;
	}

	/**
	 * Gets the value of the roomOccuMinPaxTripleShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinPaxTripleShare() {
		return roomOccuMinPaxTripleShare;
	}

	/**
	 * Sets the value of the roomOccuMinPaxTripleShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinPaxTripleShare(Integer value) {
		this.roomOccuMinPaxTripleShare = value;
	}

	/**
	 * Gets the value of the roomOccuMaxPaxTripleShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxPaxTripleShare() {
		return roomOccuMaxPaxTripleShare;
	}

	/**
	 * Sets the value of the roomOccuMaxPaxTripleShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxPaxTripleShare(Integer value) {
		this.roomOccuMaxPaxTripleShare = value;
	}

	/**
	 * Gets the value of the roomOccuMinAdultQuad property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinAdultQuad() {
		return roomOccuMinAdultQuad;
	}

	/**
	 * Sets the value of the roomOccuMinAdultQuad property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinAdultQuad(Integer value) {
		this.roomOccuMinAdultQuad = value;
	}

	/**
	 * Gets the value of the roomOccuMaxAdultQuad property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxAdultQuad() {
		return roomOccuMaxAdultQuad;
	}

	/**
	 * Sets the value of the roomOccuMaxAdultQuad property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxAdultQuad(Integer value) {
		this.roomOccuMaxAdultQuad = value;
	}

	/**
	 * Gets the value of the roomOccuMinPaxQuad property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinPaxQuad() {
		return roomOccuMinPaxQuad;
	}

	/**
	 * Sets the value of the roomOccuMinPaxQuad property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinPaxQuad(Integer value) {
		this.roomOccuMinPaxQuad = value;
	}

	/**
	 * Gets the value of the roomOccuMaxPaxQuad property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxPaxQuad() {
		return roomOccuMaxPaxQuad;
	}

	/**
	 * Sets the value of the roomOccuMaxPaxQuad property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxPaxQuad(Integer value) {
		this.roomOccuMaxPaxQuad = value;
	}

	/**
	 * Gets the value of the roomOccuMinAdultQuadShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinAdultQuadShare() {
		return roomOccuMinAdultQuadShare;
	}

	/**
	 * Sets the value of the roomOccuMinAdultQuadShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinAdultQuadShare(Integer value) {
		this.roomOccuMinAdultQuadShare = value;
	}

	/**
	 * Gets the value of the roomOccuMaxAdultQuadShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxAdultQuadShare() {
		return roomOccuMaxAdultQuadShare;
	}

	/**
	 * Sets the value of the roomOccuMaxAdultQuadShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxAdultQuadShare(Integer value) {
		this.roomOccuMaxAdultQuadShare = value;
	}

	/**
	 * Gets the value of the roomOccuMinPaxQuadShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMinPaxQuadShare() {
		return roomOccuMinPaxQuadShare;
	}

	/**
	 * Sets the value of the roomOccuMinPaxQuadShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMinPaxQuadShare(Integer value) {
		this.roomOccuMinPaxQuadShare = value;
	}

	/**
	 * Gets the value of the roomOccuMaxPaxQuadShare property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getRoomOccuMaxPaxQuadShare() {
		return roomOccuMaxPaxQuadShare;
	}

	/**
	 * Sets the value of the roomOccuMaxPaxQuadShare property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setRoomOccuMaxPaxQuadShare(Integer value) {
		this.roomOccuMaxPaxQuadShare = value;
	}

	/**
	 * Gets the value of the payingAdults property.
	 * 
	 * @return possible object is {@link PayingAdults }
	 * 
	 */
	public PayingAdults getPayingAdults() {
		return payingAdults;
	}

	/**
	 * Sets the value of the payingAdults property.
	 * 
	 * @param value
	 *            allowed object is {@link PayingAdults }
	 * 
	 */
	public void setPayingAdults(PayingAdults value) {
		this.payingAdults = value;
	}

	public long getOperatingProductId() {
		return operatingProductId;
	}

	public void setOperatingProductId(long operatingProductId) {
		this.operatingProductId = operatingProductId;
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
