package com.ttc.ch2.ui.moduls.tour.common;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ttc.ch2.domain.Brand;

public class TourRepositoryForm
{
	private String tourCode;
	private List<Brand> brands;
	private Brand selectedBrand;

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public Brand getSelectedBrand() {
		return selectedBrand;
	}

	public void setSelectedBrand(Brand selectedBrand) {
		this.selectedBrand = selectedBrand;
	}

	public String getTourCode() {
		return tourCode;
	}

	public void setTourCode(String tourCode) {
		if(StringUtils.isNotEmpty(tourCode))
		{
		this.tourCode = tourCode.toUpperCase();
		}
	}
}