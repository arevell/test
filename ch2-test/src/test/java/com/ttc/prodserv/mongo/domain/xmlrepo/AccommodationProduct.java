package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "accommodationProduct", propOrder = {
	"productId",
	"associatedProductId",
	"name",
	"code",
	"hotelName",
	"preAccommodation",
	"postAccommodation",
	"childPrice",
	"singleRoomPrice",
	"twinRoomPrice",
	"tripleRoomPrice",
	"quadRoomPrice",
	"singleRoomAvailable",
	"twinRoomAvailable",
	"twinShareRoomAvailable",
	"tripleRoomAvailable",
	"tripleShareRoomAvailable",
	"quadRoomAvailable",
	"quadShareRoomAvailable",
	"address"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class AccommodationProduct implements Serializable {

	private static final long serialVersionUID = 7444308811712588805L;

	private long productId;
	private long associatedProductId;

	private String name;
	private String code;
	private String hotelName;

	private boolean preAccommodation;
	private boolean postAccommodation;

	private BigDecimal childPrice;
	private BigDecimal singleRoomPrice;
	private BigDecimal twinRoomPrice;
	private BigDecimal tripleRoomPrice;
	private BigDecimal quadRoomPrice;

	private boolean singleRoomAvailable;
	private boolean twinRoomAvailable;
	private boolean twinShareRoomAvailable;
	private boolean tripleRoomAvailable;
	private boolean tripleShareRoomAvailable;
	private boolean quadRoomAvailable;
	private boolean quadShareRoomAvailable;

	private Address address;


	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getAssociatedProductId() {
		return associatedProductId;
	}

	public void setAssociatedProductId(long associatedProductId) {
		this.associatedProductId = associatedProductId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public boolean isPreAccommodation() {
		return preAccommodation;
	}

	public void setPreAccommodation(boolean preAccommodation) {
		this.preAccommodation = preAccommodation;
	}

	public boolean isPostAccommodation() {
		return postAccommodation;
	}

	public void setPostAccommodation(boolean postAccommodation) {
		this.postAccommodation = postAccommodation;
	}

	public BigDecimal getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(BigDecimal childPrice) {
		this.childPrice = childPrice;
	}

	public BigDecimal getSingleRoomPrice() {
		return singleRoomPrice;
	}

	public void setSingleRoomPrice(BigDecimal singleRoomPrice) {
		this.singleRoomPrice = singleRoomPrice;
	}

	public BigDecimal getTwinRoomPrice() {
		return twinRoomPrice;
	}

	public void setTwinRoomPrice(BigDecimal twinRoomPrice) {
		this.twinRoomPrice = twinRoomPrice;
	}

	public BigDecimal getTripleRoomPrice() {
		return tripleRoomPrice;
	}

	public void setTripleRoomPrice(BigDecimal tripleRoomPrice) {
		this.tripleRoomPrice = tripleRoomPrice;
	}

	public BigDecimal getQuadRoomPrice() {
		return quadRoomPrice;
	}

	public void setQuadRoomPrice(BigDecimal quadRoomPrice) {
		this.quadRoomPrice = quadRoomPrice;
	}

	public boolean isSingleRoomAvailable() {
		return singleRoomAvailable;
	}

	public void setSingleRoomAvailable(boolean singleRoomAvailable) {
		this.singleRoomAvailable = singleRoomAvailable;
	}

	public boolean isTwinRoomAvailable() {
		return twinRoomAvailable;
	}

	public void setTwinRoomAvailable(boolean twinRoomAvailable) {
		this.twinRoomAvailable = twinRoomAvailable;
	}

	public boolean isTwinShareRoomAvailable() {
		return twinShareRoomAvailable;
	}

	public void setTwinShareRoomAvailable(boolean twinShareRoomAvailable) {
		this.twinShareRoomAvailable = twinShareRoomAvailable;
	}

	public boolean isTripleRoomAvailable() {
		return tripleRoomAvailable;
	}

	public void setTripleRoomAvailable(boolean tripleRoomAvailable) {
		this.tripleRoomAvailable = tripleRoomAvailable;
	}

	public boolean isTripleShareRoomAvailable() {
		return tripleShareRoomAvailable;
	}

	public void setTripleShareRoomAvailable(boolean tripleShareRoomAvailable) {
		this.tripleShareRoomAvailable = tripleShareRoomAvailable;
	}

	public boolean isQuadRoomAvailable() {
		return quadRoomAvailable;
	}

	public void setQuadRoomAvailable(boolean quadRoomAvailable) {
		this.quadRoomAvailable = quadRoomAvailable;
	}

	public boolean isQuadShareRoomAvailable() {
		return quadShareRoomAvailable;
	}

	public void setQuadShareRoomAvailable(boolean quadShareRoomAvailable) {
		this.quadShareRoomAvailable = quadShareRoomAvailable;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
