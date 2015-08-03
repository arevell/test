package com.ttc.ch2.bl.filecollect;

import java.util.ArrayList;
import java.util.List;

import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;

public class FileCollectVO {

	private Brand brand;
	private List<SellingCompany> sellingCompanies;
	private List<String> toursListNotAdded;
	private List<String> zipListNotAdded;
	private List<String> zipListNotDeleted;

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

	public List<String> getToursListNotAdded() {

		if (toursListNotAdded == null) {
			toursListNotAdded =  new ArrayList<String>();
		}

		return toursListNotAdded;
	}

	public List<String> getZipListNotAdded() {

		if (zipListNotAdded == null) {
			zipListNotAdded = new ArrayList<String>();
		}

		return zipListNotAdded;
	}

	public List<String> getZipListNotDeleted() {

		if (zipListNotDeleted == null) {
			zipListNotDeleted = new ArrayList<String>();
		}

		return zipListNotDeleted;
	}

	public boolean hasErrors() {
		return (toursListNotAdded != null && toursListNotAdded.size() > 0) || (zipListNotAdded != null && zipListNotAdded.size() > 0) || (zipListNotDeleted != null && zipListNotDeleted.size() > 0);
	}
}
