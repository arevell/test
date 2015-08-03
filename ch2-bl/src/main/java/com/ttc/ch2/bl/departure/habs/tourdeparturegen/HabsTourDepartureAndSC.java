package com.ttc.ch2.bl.departure.habs.tourdeparturegen;

import java.util.ArrayList;
import java.util.List;

import com.ttc.ch2.domain.SellingCompany;
import com.wsout.habs.itropicsbuildws.WsDeparturesVO;

import facade.itropics.webservice.tropics.com.itropicsbuildws.WsProductVO;




public class HabsTourDepartureAndSC {
	private WsProductVO product;
	private WsDeparturesVO departures;
	private SellingCompany sellingCompany;
	private String md5FromHabs;
	
	private List<HabsTourDepartureAndSC> otherReferences;
	private Boolean isUsed;
	

	public List<HabsTourDepartureAndSC> getOtherReferences() {
		if(otherReferences == null)
			otherReferences = new ArrayList<HabsTourDepartureAndSC>();
		return otherReferences;
	}
	public Boolean getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	public WsProductVO getProduct() {
		return product;
	}
	public void setProduct(WsProductVO product) {
		this.product = product;
	}
	public WsDeparturesVO getDepartures() {
		return departures;
	}
	public void setDepartures(WsDeparturesVO departures) {
		this.departures = departures;
	}
	public void setOtherReferences(List<HabsTourDepartureAndSC> otherReferences) {
		this.otherReferences = otherReferences;
	}
	public SellingCompany getSellingCompany() {
		return sellingCompany;
	}
	public void setSellingCompany(SellingCompany sellingCompany) {
		this.sellingCompany = sellingCompany;
	}
	public String getMd5FromHabs() {
		return md5FromHabs;
	}
	public void setMd5FromHabs(String md5FromHabs) {
		this.md5FromHabs = md5FromHabs;
	}
	
	
}
