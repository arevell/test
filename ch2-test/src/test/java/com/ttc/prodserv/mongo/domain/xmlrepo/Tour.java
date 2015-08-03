package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ttc.prodserv.mongo.domain.EntityBase;

/**
 * <p>Java class for tour complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tour">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operatingProductId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sellingCompanyId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sellingCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="style" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brochureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="airPriceIncluded" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="iTropicsSellable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="durationDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="durationNights" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="startLocationCityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="startLocationCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startLocationCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startLocationCtry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endLocationCityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="endLocationCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endLocationCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endLocationCtry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellableSingleRoomFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sellableTwinRoomFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sellableTwinShareRoomFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sellableTripleRoomFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sellableTripleShareRoomFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sellableQuadRoomFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sellableQuadShareRoomFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="childSellableFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="childAgeFrom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="childAgeTo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="adultSellableFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="adultAgeFrom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="adultAgeTo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@Document(collection = "Tours")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tour", propOrder = {
	"operatingProductId",
	"sellingCompanyId",
	"sellingCompanyCode",
	"tourId",
	"type",
	"code",
	"name",
	"style",
	"description",
	"note",
	"brochureCode",
	"brochureName",
	"airPriceIncluded",
	"iTropicsSellable",
	"durationDays",
	"durationNights",
	"startLocationCityId",
	"startLocationCity",
	"startLocationCityCode",
	"startLocationCtry",
	"endLocationCityId",
	"endLocationCity",
	"endLocationCityCode",
	"endLocationCtry",
	"sellableSingleRoomFlag",
	"sellableTwinRoomFlag",
	"sellableTwinShareRoomFlag",
	"sellableTripleRoomFlag",
	"sellableTripleShareRoomFlag",
	"sellableQuadRoomFlag",
	"sellableQuadShareRoomFlag",
	"childSellableFlag",
	"childAgeFrom",
	"childAgeTo",
	"adultSellableFlag",
	"adultAgeFrom",
	"adultAgeTo",
	"id",
	"version",
	"refreshId",
	"md5Checksum"
})
public class Tour extends EntityBase {

	private static final long serialVersionUID = -5260588919031610655L;

	public static enum TourType {
		MV, TP, NOT_DEFINE
	}

	protected long tourId;
	protected long operatingProductId;
	protected long sellingCompanyId;
	protected String sellingCompanyCode;
	protected String type;
	protected String code;
	protected String name;
	protected String style;
	protected String description;
	protected String note;
	protected String brochureCode;
	protected String brochureName;
	protected boolean airPriceIncluded;
	protected boolean iTropicsSellable;
	protected Integer durationDays;
	protected Integer durationNights;
	protected long startLocationCityId;
	protected String startLocationCity;
	protected String startLocationCityCode;
	protected String startLocationCtry;
	protected long endLocationCityId;
	protected String endLocationCity;
	protected String endLocationCityCode;
	protected String endLocationCtry;
	protected boolean sellableSingleRoomFlag;
	protected boolean sellableTwinRoomFlag;
	protected boolean sellableTwinShareRoomFlag;
	protected boolean sellableTripleRoomFlag;
	protected boolean sellableTripleShareRoomFlag;
	protected boolean sellableQuadRoomFlag;
	protected boolean sellableQuadShareRoomFlag;
	protected boolean childSellableFlag;
	protected Integer childAgeFrom;
	protected Integer childAgeTo;
	protected boolean adultSellableFlag;
	protected Integer adultAgeFrom;
	protected Integer adultAgeTo;

	@XmlAttribute(name = "timestamp")
	@XmlSchemaType(name = "dateTime")
	protected Date timestamp;
	protected String refreshId;

	/**
	 * Gets the value of the operatingProductId property.
	 * 
	 */
	public long getOperatingProductId() {
		return operatingProductId;
	}

	/**
	 * Sets the value of the operatingProductId property.
	 * 
	 */
	public void setOperatingProductId(long value) {
		this.operatingProductId = value;
	}

	/**
	 * Gets the value of the sellingCompanyId property.
	 * 
	 */
	public long getSellingCompanyId() {
		return sellingCompanyId;
	}

	/**
	 * Sets the value of the sellingCompanyId property.
	 * 
	 */
	public void setSellingCompanyId(long value) {
		this.sellingCompanyId = value;
	}

	/**
	 * Gets the value of the sellingCompanyCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSellingCompanyCode() {
		return sellingCompanyCode;
	}

	/**
	 * Sets the value of the sellingCompanyCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSellingCompanyCode(String value) {
		this.sellingCompanyCode = value;
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

	public TourType getTypeAsEnum() {
		if (StringUtils.isEmpty(type))
			return TourType.NOT_DEFINE;
		return TourType.valueOf(type);
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setType(String type) {
		this.type = type;
	}

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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * Gets the value of the description property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the description property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * Gets the value of the note property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the value of the note property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNote(String value) {
		this.note = value;
	}

	/**
	 * Gets the value of the brochureCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBrochureCode() {
		return brochureCode;
	}

	/**
	 * Sets the value of the brochureCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBrochureCode(String value) {
		this.brochureCode = value;
	}

	/**
	 * Gets the value of the brochureName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBrochureName() {
		return brochureName;
	}

	/**
	 * Sets the value of the brochureName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */

	public void setBrochureName(String brochureName) {
		this.brochureName = brochureName;
	}


	/**
	 * Gets the value of the airPriceIncluded property.
	 * 
	 */
	public boolean isAirPriceIncluded() {
		return airPriceIncluded;
	}

	/**
	 * Sets the value of the airPriceIncluded property.
	 * 
	 */
	public void setAirPriceIncluded(boolean value) {
		this.airPriceIncluded = value;
	}

	/**
	 * Gets the value of the iTropicsSellable property.
	 * 
	 */
	public boolean isITropicsSellable() {
		return iTropicsSellable;
	}

	/**
	 * Sets the value of the iTropicsSellable property.
	 * 
	 */
	public void setITropicsSellable(boolean value) {
		this.iTropicsSellable = value;
	}

	/**
	 * Gets the value of the durationDays property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getDurationDays() {
		return durationDays;
	}

	/**
	 * Sets the value of the durationDays property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setDurationDays(Integer value) {
		this.durationDays = value;
	}

	/**
	 * Gets the value of the durationNights property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getDurationNights() {
		return durationNights;
	}

	/**
	 * Sets the value of the durationNights property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setDurationNights(Integer value) {
		this.durationNights = value;
	}

	/**
	 * Gets the value of the startLocationCityId property.
	 * 
	 */
	public long getStartLocationCityId() {
		return startLocationCityId;
	}

	/**
	 * Sets the value of the startLocationCityId property.
	 * 
	 */
	public void setStartLocationCityId(long value) {
		this.startLocationCityId = value;
	}

	/**
	 * Gets the value of the startLocationCity property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStartLocationCity() {
		return startLocationCity;
	}

	/**
	 * Sets the value of the startLocationCity property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStartLocationCity(String value) {
		this.startLocationCity = value;
	}

	/**
	 * Gets the value of the startLocationCityCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStartLocationCityCode() {
		return startLocationCityCode;
	}

	/**
	 * Sets the value of the startLocationCityCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStartLocationCityCode(String value) {
		this.startLocationCityCode = value;
	}

	/**
	 * Gets the value of the startLocationCtry property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStartLocationCtry() {
		return startLocationCtry;
	}

	/**
	 * Sets the value of the startLocationCtry property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStartLocationCtry(String value) {
		this.startLocationCtry = value;
	}

	/**
	 * Gets the value of the endLocationCityId property.
	 * 
	 */
	public long getEndLocationCityId() {
		return endLocationCityId;
	}

	/**
	 * Sets the value of the endLocationCityId property.
	 * 
	 */
	public void setEndLocationCityId(long value) {
		this.endLocationCityId = value;
	}

	/**
	 * Gets the value of the endLocationCity property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEndLocationCity() {
		return endLocationCity;
	}

	/**
	 * Sets the value of the endLocationCity property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEndLocationCity(String value) {
		this.endLocationCity = value;
	}

	/**
	 * Gets the value of the endLocationCityCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEndLocationCityCode() {
		return endLocationCityCode;
	}

	/**
	 * Sets the value of the endLocationCityCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEndLocationCityCode(String value) {
		this.endLocationCityCode = value;
	}

	/**
	 * Gets the value of the endLocationCtry property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEndLocationCtry() {
		return endLocationCtry;
	}

	/**
	 * Sets the value of the endLocationCtry property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEndLocationCtry(String value) {
		this.endLocationCtry = value;
	}

	/**
	 * Gets the value of the sellableSingleRoomFlag property.
	 * 
	 */
	public boolean isSellableSingleRoomFlag() {
		return sellableSingleRoomFlag;
	}

	/**
	 * Sets the value of the sellableSingleRoomFlag property.
	 * 
	 */
	public void setSellableSingleRoomFlag(boolean value) {
		this.sellableSingleRoomFlag = value;
	}

	/**
	 * Gets the value of the sellableTwinRoomFlag property.
	 * 
	 */
	public boolean isSellableTwinRoomFlag() {
		return sellableTwinRoomFlag;
	}

	/**
	 * Sets the value of the sellableTwinRoomFlag property.
	 * 
	 */
	public void setSellableTwinRoomFlag(boolean value) {
		this.sellableTwinRoomFlag = value;
	}

	/**
	 * Gets the value of the sellableTwinShareRoomFlag property.
	 * 
	 */
	public boolean isSellableTwinShareRoomFlag() {
		return sellableTwinShareRoomFlag;
	}

	/**
	 * Sets the value of the sellableTwinShareRoomFlag property.
	 * 
	 */
	public void setSellableTwinShareRoomFlag(boolean value) {
		this.sellableTwinShareRoomFlag = value;
	}

	/**
	 * Gets the value of the sellableTripleRoomFlag property.
	 * 
	 */
	public boolean isSellableTripleRoomFlag() {
		return sellableTripleRoomFlag;
	}

	/**
	 * Sets the value of the sellableTripleRoomFlag property.
	 * 
	 */
	public void setSellableTripleRoomFlag(boolean value) {
		this.sellableTripleRoomFlag = value;
	}

	/**
	 * Gets the value of the sellableTripleShareRoomFlag property.
	 * 
	 */
	public boolean isSellableTripleShareRoomFlag() {
		return sellableTripleShareRoomFlag;
	}

	/**
	 * Sets the value of the sellableTripleShareRoomFlag property.
	 * 
	 */
	public void setSellableTripleShareRoomFlag(boolean value) {
		this.sellableTripleShareRoomFlag = value;
	}

	/**
	 * Gets the value of the sellableQuadRoomFlag property.
	 * 
	 */
	public boolean isSellableQuadRoomFlag() {
		return sellableQuadRoomFlag;
	}

	/**
	 * Sets the value of the sellableQuadRoomFlag property.
	 * 
	 */
	public void setSellableQuadRoomFlag(boolean value) {
		this.sellableQuadRoomFlag = value;
	}

	/**
	 * Gets the value of the sellableQuadShareRoomFlag property.
	 * 
	 */
	public boolean isSellableQuadShareRoomFlag() {
		return sellableQuadShareRoomFlag;
	}

	/**
	 * Sets the value of the sellableQuadShareRoomFlag property.
	 * 
	 */
	public void setSellableQuadShareRoomFlag(boolean value) {
		this.sellableQuadShareRoomFlag = value;
	}

	/**
	 * Gets the value of the childSellableFlag property.
	 * 
	 */
	public boolean isChildSellableFlag() {
		return childSellableFlag;
	}

	/**
	 * Sets the value of the childSellableFlag property.
	 * 
	 */
	public void setChildSellableFlag(boolean value) {
		this.childSellableFlag = value;
	}

	/**
	 * Gets the value of the childAgeFrom property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getChildAgeFrom() {
		return childAgeFrom;
	}

	/**
	 * Sets the value of the childAgeFrom property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setChildAgeFrom(Integer value) {
		this.childAgeFrom = value;
	}

	/**
	 * Gets the value of the childAgeTo property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getChildAgeTo() {
		return childAgeTo;
	}

	/**
	 * Sets the value of the childAgeTo property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setChildAgeTo(Integer value) {
		this.childAgeTo = value;
	}

	/**
	 * Gets the value of the adultSellableFlag property.
	 * 
	 */
	public boolean isAdultSellableFlag() {
		return adultSellableFlag;
	}

	/**
	 * Sets the value of the adultSellableFlag property.
	 * 
	 */
	public void setAdultSellableFlag(boolean value) {
		this.adultSellableFlag = value;
	}

	/**
	 * Gets the value of the adultAgeFrom property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getAdultAgeFrom() {
		return adultAgeFrom;
	}

	/**
	 * Sets the value of the adultAgeFrom property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setAdultAgeFrom(Integer value) {
		this.adultAgeFrom = value;
	}

	/**
	 * Gets the value of the adultAgeTo property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getAdultAgeTo() {
		return adultAgeTo;
	}

	/**
	 * Sets the value of the adultAgeTo property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setAdultAgeTo(Integer value) {
		this.adultAgeTo = value;
	}

	public long getTourId() {
		return tourId;
	}

	public void setTourId(long tourId) {
		this.tourId = tourId;
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
