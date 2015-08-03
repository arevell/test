package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.annotation.Transient;

@XmlType(name = "insuranceProduct", propOrder = {
	"code",
	"name",
	"category",
	"price"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class InsuranceProduct implements Serializable {

	private static final long serialVersionUID = -4720433378031040905L;

	private String code;
	private String name;
	private String category;

	private BigDecimal price;

	@Transient
	@XmlTransient
	private Date associationFromDate;
	@Transient
	@XmlTransient
	private Date associationToDate;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getAssociationFromDate() {
		return associationFromDate;
	}

	public void setAssociationFromDate(Date associationFromDate) {
		this.associationFromDate = associationFromDate;
	}

	public Date getAssociationToDate() {
		return associationToDate;
	}

	public void setAssociationToDate(Date associationToDate) {
		this.associationToDate = associationToDate;
	}
}
