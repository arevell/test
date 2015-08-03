package com.ttc.ch2.bl.outgoingarchives;

import java.util.List;

import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;

public class OutgoingArchivesVO {

	private Brand brand;
	private List<SellingCompany> sellingCompanies;

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<SellingCompany> getSellingCompanies() {
		return sellingCompanies;
	}

	public void setSellingCompanies(List<SellingCompany> sellingCompanies) {
		this.sellingCompanies = sellingCompanies;
	}
}
