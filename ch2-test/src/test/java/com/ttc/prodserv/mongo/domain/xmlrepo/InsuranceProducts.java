package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "insuranceProducts")
public class InsuranceProducts implements Serializable {

	private static final long serialVersionUID = 5562719296797300823L;

	private List<InsuranceProduct> insuranceProduct;

	@XmlElement(name = "insuranceProduct")
	public List<InsuranceProduct> getInsuranceProduct() {

		if (insuranceProduct == null) {
			insuranceProduct = new ArrayList<InsuranceProduct>();
		}

		return insuranceProduct;
	}
}
