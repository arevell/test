package com.ttc.ch2.common;

public enum SchemaParam {

	TD_1_1_0("/com/ttc/ch2/api/ccapi/v1/MarketVariationDepartureInfo.1.1.xsd", "com.ttsl.marketvariationdepartureinfo._2010._09._1"),
	TD_3_0_0("/com/ttc/ch2/api/ccapi/v3/TourDepartures.2.5.4_wsdl.xsd", "com.ttc.ch2.api.ccapi.v3.tourdepartures._2013._09._2_5"),
	TI_2_4_0("/com/ttc/ch2/api/ccapi/v1/TourInfo.2.4.xsd", "com.ttsl.tourinfo._2010._08._2"),
	TI_3_0_0("/com/ttc/ch2/api/ccapi/v3/TourInfo.3.0_wsdl.xsd", "com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3");

	String schemaPath;
	String jaxbContextPath;

	private SchemaParam(String schemaPath, String jaxbContextPath) {
		this.schemaPath = schemaPath;
		this.jaxbContextPath = jaxbContextPath;
	}

	public String getSchemaPath() {
		return schemaPath;
	}

	public String getJaxbContextPath() {
		return jaxbContextPath;
	}
}