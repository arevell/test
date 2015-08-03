package com.ttc.ch2.bl.departure.common.tourdeparturegen;

import java.util.ArrayList;
import java.util.List;

import com.ttc.ch2.domain.SellingCompany;

import facade.itropics.webservice.tropics.com.itropicsbuildws.WsDeparturesVO;
import facade.itropics.webservice.tropics.com.itropicsbuildws.WsProductVO;

public class TourDepartureAndSC {
	private WsProductVO product;
	private WsDeparturesVO departures;
	private SellingCompany sellingCompany;
	
	private List<TourDepartureAndSC> otherReferences;
	private Boolean isUsed;
	

	public List<TourDepartureAndSC> getOtherReferences() {
		if(otherReferences == null)
			otherReferences = new ArrayList<TourDepartureAndSC>();
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
	public void setOtherReferences(List<TourDepartureAndSC> otherReferences) {
		this.otherReferences = otherReferences;
	}
	public SellingCompany getSellingCompany() {
		return sellingCompany;
	}
	public void setSellingCompany(SellingCompany sellingCompany) {
		this.sellingCompany = sellingCompany;
	}
	
	
}
