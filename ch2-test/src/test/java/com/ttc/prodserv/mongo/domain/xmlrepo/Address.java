package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "address", propOrder = {
	"cityId",
	"regionId",
	"countryId",
	"cityName",
	"regionName",
	"countryName",
	"zipCode",
	"mail",
	"webSite",
	"addressline1",
	"addressline2",
	"addressline3",
	"addressline4",
	"contact1part1",
	"contact1part2",
	"contact1part3",
	"contact2part1",
	"contact2part2",
	"contact2part3",
	"emergencypart1",
	"emergencypart2",
	"emergencypart3",
	"fax1part1",
	"fax1part2",
	"fax1part3",
	"fax2part1",
	"fax2part2",
	"fax2part3"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Address implements Serializable {

	private static final long serialVersionUID = 1209335879991736392L;

	private long cityId;
	private long regionId;
	private long countryId;

	private String cityName;
	private String regionName;
	private String countryName;

	private String zipCode;
	private String mail;
	private String webSite;

	private String addressline1;
	private String addressline2;
	private String addressline3;
	private String addressline4;

	private String contact1part1;
	private String contact1part2;
	private String contact1part3;

	private String contact2part1;
	private String contact2part2;
	private String contact2part3;

	private String emergencypart1;
	private String emergencypart2;
	private String emergencypart3;

	private String fax1part1;
	private String fax1part2;
	private String fax1part3;

	private String fax2part1;
	private String fax2part2;
	private String fax2part3;


	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public long getRegionId() {
		return regionId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getAddressline3() {
		return addressline3;
	}

	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}

	public String getAddressline4() {
		return addressline4;
	}

	public void setAddressline4(String addressline4) {
		this.addressline4 = addressline4;
	}

	public String getContact1part1() {
		return contact1part1;
	}

	public void setContact1part1(String contact1part1) {
		this.contact1part1 = contact1part1;
	}

	public String getContact1part2() {
		return contact1part2;
	}

	public void setContact1part2(String contact1part2) {
		this.contact1part2 = contact1part2;
	}

	public String getContact1part3() {
		return contact1part3;
	}

	public void setContact1part3(String contact1part3) {
		this.contact1part3 = contact1part3;
	}

	public String getContact2part1() {
		return contact2part1;
	}

	public void setContact2part1(String contact2part1) {
		this.contact2part1 = contact2part1;
	}

	public String getContact2part2() {
		return contact2part2;
	}

	public void setContact2part2(String contact2part2) {
		this.contact2part2 = contact2part2;
	}

	public String getContact2part3() {
		return contact2part3;
	}

	public void setContact2part3(String contact2part3) {
		this.contact2part3 = contact2part3;
	}

	public String getEmergencypart1() {
		return emergencypart1;
	}

	public void setEmergencypart1(String emergencypart1) {
		this.emergencypart1 = emergencypart1;
	}

	public String getEmergencypart2() {
		return emergencypart2;
	}

	public void setEmergencypart2(String emergencypart2) {
		this.emergencypart2 = emergencypart2;
	}

	public String getEmergencypart3() {
		return emergencypart3;
	}

	public void setEmergencypart3(String emergencypart3) {
		this.emergencypart3 = emergencypart3;
	}

	public String getFax1part1() {
		return fax1part1;
	}

	public void setFax1part1(String fax1part1) {
		this.fax1part1 = fax1part1;
	}

	public String getFax1part2() {
		return fax1part2;
	}

	public void setFax1part2(String fax1part2) {
		this.fax1part2 = fax1part2;
	}

	public String getFax1part3() {
		return fax1part3;
	}

	public void setFax1part3(String fax1part3) {
		this.fax1part3 = fax1part3;
	}

	public String getFax2part1() {
		return fax2part1;
	}

	public void setFax2part1(String fax2part1) {
		this.fax2part1 = fax2part1;
	}

	public String getFax2part2() {
		return fax2part2;
	}

	public void setFax2part2(String fax2part2) {
		this.fax2part2 = fax2part2;
	}

	public String getFax2part3() {
		return fax2part3;
	}

	public void setFax2part3(String fax2part3) {
		this.fax2part3 = fax2part3;
	}
}
