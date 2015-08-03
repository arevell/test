package com.ttc.ch2.domain.messages;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name = "EMAIL_ADDRESS")
public class EmailAddress extends EntityBase {

	public enum AddressType {
		ConsolidationReportRecipient, ItineraryReportRecipient
	};

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "BRAND_ID", nullable = false)
	private Brand brand;

	@Enumerated(EnumType.STRING)
	@Column(name = "ADDRESS_TYPE", nullable = false)
	private AddressType addressType;

	@Column(name = "ADDRESS_TEXT", length = 255, nullable = false)
	private String addressText;

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public String getAddressText() {
		return addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}
}
