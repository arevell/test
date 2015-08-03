package com.ttc.prodserv.mongo.domain;

import org.apache.commons.lang.StringUtils;

import com.ttc.prodserv.mongo.domain.xmlrepo.OperatingProduct;
import com.ttc.prodserv.mongo.domain.xmlrepo.Product;
import com.ttc.prodserv.mongo.domain.xmlrepo.SellingCompany;
import com.ttc.prodserv.mongo.domain.xmlrepo.Tour;
import com.ttc.prodserv.mongo.domain.xmlrepo.ToursSellingCompanies;

public class UniqueIdHelper {

	private static final String INDEX_SEPARATOR = "_";


	public static String getId(SellingCompany sc) {
		return String.valueOf(sc.getSellingCompanyId());
	}

	public static String getId(OperatingProduct op) {
		return String.valueOf(op.getOperatingProductId());
	}

	public static String getId(Tour tr) {
		return String.valueOf(tr.getSellingCompanyCode() + INDEX_SEPARATOR + tr.getCode());
	}

	public static String getId(Product dr, String sellingCompanyCode) {
		return sellingCompanyCode + INDEX_SEPARATOR + dr.getCode();
	}

	public static String getId(ToursSellingCompanies trsc) {
		return trsc.getBrandCode();
	}

	public static String getDatesAndRatesId(String productCode, String sellingCompanyCode) {
		return sellingCompanyCode + INDEX_SEPARATOR + productCode;
	}

	public static String getTourId(String tourCode, String sellingCompanyCode) {
		return sellingCompanyCode + INDEX_SEPARATOR + tourCode;
	}

	public static String getSellingCompanyCodeFromId(String indexValue) {
		return StringUtils.substringBefore(indexValue, INDEX_SEPARATOR);
	}

	public static String getTourCodeFromId(String indexValue) {
		return StringUtils.substringAfter(indexValue, INDEX_SEPARATOR);
	}

	public static void setupId(SellingCompany sc) {
		sc.setId(getId(sc));
	}

	public static void setupId(OperatingProduct op) {
		op.setId(getId(op));
	}

	public static void setupId(Tour tr) {
		tr.setId(getId(tr));
	}

	public static void setupId(Product dr, String sellingCompanyCode) {
		dr.setId(getId(dr, sellingCompanyCode));
	}

	public static void setupId(ToursSellingCompanies trsc) {
		trsc.setId(getId(trsc));
	}
}
