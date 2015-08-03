package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "miscellaneousProduct", propOrder = {
	"productId",
	"associatedProductId",
	"categoryId",
	"serviceTypeId",
	"code",
	"name",
	"description",
	"category",
	"dayNumber",
	"productDate",
	"mandatory",
	"forcedButRemovable",
	"benefit",
	"sourcePrice",
	"childPrice",
	"adultPrice"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class MiscellaneousProduct implements Serializable {

	private static final long serialVersionUID = -9125248983150482885L;

	private long productId;
	private long associatedProductId;
	private long categoryId;
	private long serviceTypeId;

	private String code;
	private String name;
	private String description;
	private String category;

	private long dayNumber;
	private Date productDate;

	private boolean mandatory;
	private boolean forcedButRemovable;
	private boolean benefit;

	private BigDecimal sourcePrice;
	private BigDecimal childPrice;
	private BigDecimal adultPrice;


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

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(long serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(long dayNumber) {
		this.dayNumber = dayNumber;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public boolean isForcedButRemovable() {
		return forcedButRemovable;
	}

	public void setForcedButRemovable(boolean forcedButRemovable) {
		this.forcedButRemovable = forcedButRemovable;
	}

	public boolean isBenefit() {
		return benefit;
	}

	public void setBenefit(boolean benefit) {
		this.benefit = benefit;
	}

	public BigDecimal getSourcePrice() {
		return sourcePrice;
	}

	public void setSourcePrice(BigDecimal sourcePrice) {
		this.sourcePrice = sourcePrice;
	}

	public BigDecimal getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(BigDecimal childPrice) {
		this.childPrice = childPrice;
	}

	public BigDecimal getAdultPrice() {
		return adultPrice;
	}

	public void setAdultPrice(BigDecimal adultPrice) {
		this.adultPrice = adultPrice;
	}
}
