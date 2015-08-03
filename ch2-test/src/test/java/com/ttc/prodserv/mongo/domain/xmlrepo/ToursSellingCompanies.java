package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ttc.prodserv.mongo.domain.EntityBase;

@Document(collection = "ToursSellingCompanies")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "toursSellingCompanies", propOrder = {
	"brandCode",
	"tourSellingCompanies",
	"id",
	"version",
	"refreshId",
	"md5Checksum"
})
public class ToursSellingCompanies extends EntityBase {

	private static final long serialVersionUID = 71204597167380418L;

	public static enum BrandCode {
		BV, CH, IV, TT
	}

	private String brandCode;
	private List<TourSellingCompanies> tourSellingCompanies;

	@XmlAttribute(name = "timestamp")
	@XmlSchemaType(name = "dateTime")
	private Date timestamp;

	private String refreshId;

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public List<TourSellingCompanies> getTourSellingCompanies() {
		return tourSellingCompanies;
	}

	public void setTourSellingCompanies(List<TourSellingCompanies> tourSellingCompanies) {
		this.tourSellingCompanies = tourSellingCompanies;
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
