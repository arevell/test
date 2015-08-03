package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "discounts")
public class Discounts implements Serializable {

	private static final long serialVersionUID = 1883239412642367794L;

	private List<Discount> discountsList;

	@XmlElement(name = "discount")
	public List<Discount> getDiscountsList() {

		if (discountsList == null) {
			discountsList = new ArrayList<Discount>();
		}

		return discountsList;
	}
}
