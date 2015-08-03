package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tourSellingCompanies", propOrder = {
	"tourCode",
	"sellingCompanyCode"
})
public class TourSellingCompanies {

	private String tourCode;
	private List<String> sellingCompanyCode;

	public String getTourCode() {
		return tourCode;
	}

	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}

	public List<String> getSellingCompanyCode() {
		return sellingCompanyCode;
	}

	public void setSellingCompanyCode(List<String> sellingCompanyCode) {
		this.sellingCompanyCode = sellingCompanyCode;
	}
}
