package com.ttc.ch2.cucumber;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class SearchLib {
			public static final String sqlForContinentsCodes = "SELECT x1.continentcode, x2.sellingcompanycode                                                              " +
		       " FROM xml_content_repository t, content_repository r,                                                                                                         " +
		       "      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/ContinentsVisited/Continent'                  " +
		       "                PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                     " +
		       "                COLUMNS continentcode VARCHAR2(10) PATH 'Code') x1,                                                                                           " +
		       "      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/SellingCompanies/SellingCompany'              " +
		       "                PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                     " +
		       "                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x2,                                                                                     " +
		       "      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4' ), '/TourDepartures/SellingCompanies/SellingCompany'" +
		       "                PASSING xmltype.createxml(t.tourdeparture_xml)                                                                                                " +
		       "                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x3																					  " +
		       " where t.content_repository_id=r.id and x2.sellingcompanycode=x3.sellingcompanycode and r.repository_status='TIandTD'                                         " +
		       "   and (:sccode is null or x3.sellingcompanycode=:sccode) and rownum <= :records group by x1.continentcode, x2.sellingcompanycode";
			
			public static final String sqlForContryCodes = "SELECT  x1.countrycode, x2.sellingcompanycode, x1.continentcode                                                     " +
				" FROM xml_content_repository t, content_repository r,                                                                                                          " +
				"      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/CountriesVisited/Country'                      " +
				"                PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                      " +
				"                COLUMNS countrycode VARCHAR2(2) PATH 'Code',                                                                                                   " +
				"						continentcode VARCHAR2(7) PATH 'ContinentCode') x1,                                                                                     " +
				"      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/SellingCompanies/SellingCompany'               " +
				"                PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                      " +
				"                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x2,                                                                                      " +
				"      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4' ), '/TourDepartures/SellingCompanies/SellingCompany' " +
				"                PASSING xmltype.createxml(t.tourdeparture_xml)                                                                                                 " +
				"                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x3                                                                                       " +
				" where t.content_repository_id=r.id and x2.sellingcompanycode=x3.sellingcompanycode                                                                            " +
				" 	and r.repository_status='TIandTD' and (:sccode is null or x3.sellingcompanycode=:sccode) and rownum <= :records group by x1.countrycode, x2.sellingcompanycode, x1.continentcode ";
			
			
			public static final String sqlForDurations = "SELECT r.tour_code, extractValue(xmltype.createxml(t.tourinfo_xml),'/TourInfo/@Duration',                            " +
			   "           'xmlns=\"http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0\"') as duration, x2.*                                                              " +
			   " FROM xml_content_repository t, content_repository r,                                                                                                           " +
			   "      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/SellingCompanies/SellingCompany'                " +
			   "                PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                       " +
			   "                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x2,                                                                                       " +
			   "      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4' ), '/TourDepartures/SellingCompanies/SellingCompany'  " +
			   "                PASSING xmltype.createxml(t.tourdeparture_xml)                                                                                                  " +
			   "                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x3                                                                                        " +
			   " where t.content_repository_id=r.id and x2.sellingcompanycode=x3.sellingcompanycode and r.repository_status='TIandTD' and (:sccode is null or x3.sellingcompanycode=:sccode) and rownum <= :records";
			
			public static final String sqlForMonths = "SELECT  x1.startdatetime, x2.sellingcompanycode                                                                          " +
			   " FROM xml_content_repository t, content_repository r,                                                                                                            " +
			   "      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4' ), '/TourDepartures/SellingCompanies/SellingCompany'   " +
			   "                PASSING xmltype.createxml(t.tourdeparture_xml)                                                                                                   " +
			   "                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code',                                                                                            " +
			   "                        departures xmltype PATH 'Departures' ) x3,                                                                                               " +
			   "      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4' ), '/Departures/Departure'                             " +
			   "                PASSING x3.departures                                                                                                                            " +
			   "                COLUMNS startdatetime VARCHAR2(20) PATH 'StartDateTime') x1,                                                                                     " +
			   "      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/SellingCompanies/SellingCompany'                 " +
			   "                PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                        " +
			   "                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x2                                                                                         " +
			   " where t.content_repository_id=r.id and x2.sellingcompanycode=x3.sellingcompanycode and r.repository_status='TIandTD'                                            " +
			   "   and (:sccode is null or x3.sellingcompanycode=:sccode) and rownum <= :records group by x1.startdatetime, x2.sellingcompanycode";
			
			public static final String sqlForPreferedRoomType = "SELECT  x1.rtype, x1.sellable,  x2.sellingcompanycode                                                         " +
				" FROM xml_content_repository t, content_repository r,                                                                                                          " +
				"      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/TourVariationDefiners/RoomTypes/RoomType'      " +
				"                PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                      " +
				"                COLUMNS rtype VARCHAR2(10) PATH '@Type',                                                                                                       " +
				"                        sellable varchar(8) PATH '@Sellable') x1,                                                                                              " +
				"      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/SellingCompanies/SellingCompany'               " +
				"                PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                      " +
				"                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x2,                                                                                      " +
				"      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4' ), '/TourDepartures/SellingCompanies/SellingCompany' " +
				"                PASSING xmltype.createxml(t.tourdeparture_xml)                                                                                                 " +
				"                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x3                                                                                       " +
				" where t.content_repository_id=r.id and x2.sellingcompanycode=x3.sellingcompanycode and r.repository_status='TIandTD'                                          " +
				"  and x1.sellable='true' (:sccode is null or and x3.sellingcompanycode=:sccode) and rownum <= :records group by x1.rtype, x1.sellable,  x2.sellingcompanycode";

			
			public static final String sqlForPrices = "SELECT r.tour_code,x2.sellingcompanycode, x1.room_type ,min(x1.combined_price) pmin,max(x1.combined_price)  pmax         " +
		        " FROM xml_content_repository t, content_repository r,                                                                                                           " +
		        "      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4' ), '/TourDepartures/SellingCompanies/SellingCompany'  " +
		        "                PASSING xmltype.createxml(t.tourdeparture_xml)                                                                                                  " +
		        "                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code',                                                                                           " +
		        "                        departures xmltype PATH 'Departures' ) x3,                                                                                              " +
		        "      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4' ), '/Departures/Departure/TourRules/Rooms/Room'       " +
		        "                PASSING x3.departures                                                                                                                           " +
		        "                COLUMNS combined_price VARCHAR2(20) PATH 'Price/Adult/Combined',                                                                                " +
		        "                        room_type VARCHAR2(20) PATH 'Type' ) x1,                                                                                                " +
		        "      XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/SellingCompanies/SellingCompany'                " +
		        "                PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                       " +
		        "                COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x2                                                                                        " +
		        " where t.content_repository_id=r.id and x2.sellingcompanycode=x3.sellingcompanycode and r.repository_status='TIandTD'                                           " +
		        "     and (:sccode is null or x3.sellingcompanycode=:sccode) and rownum <= :records   																								 " +
		        " group by r.tour_code,x2.sellingcompanycode, x1.room_type ";
			
			public static final String sqlForTourCodes = "SELECT r.tour_code,  x2.sellingcompanycode, extractValue(xmltype.createxml(t.tourinfo_xml),'/TourInfo/@BrandCode',      " +
			    "           'xmlns=\"http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0\"') as brandcode                                                                    " +
				"	FROM xml_content_repository t, content_repository r,                                                                                                          " +
				"		XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/SellingCompanies/SellingCompany'                " +
				"				PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                         " +
				"				COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x2,                                                                                         " +
				"		XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4' ), '/TourDepartures/SellingCompanies/SellingCompany'  " +
				"				PASSING xmltype.createxml(t.tourdeparture_xml)                                                                                                    " +
				"				COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x3                                                                                          " +
				"	where t.content_repository_id=r.id and x2.sellingcompanycode=x3.sellingcompanycode and r.repository_status='TIandTD'                                          " +
				"     and (:sccode is null or x2.sellingcompanycode=:sccode) and rownum <= :records ";
			
			public static final String sqlForTourCategories = "SELECT  agg.sellingcompanycode, agg.categoryname,  LISTAGG( agg.categoryvalue, ',')                               " +
				" WITHIN GROUP (ORDER BY agg.categoryvalue) AS categoryvalues                                                                                                    " +
				" from (   select distinct x2.sellingcompanycode, x4.categoryname, x5.categoryvalue                                                                              " +
				" 	FROM xml_content_repository t, content_repository r,                                                                                                         " +
				" 		XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/SellingCompanies/SellingCompany'               " +
				" 				PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                        " +
				" 				COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code',                                                                                            " +
                "         tourcategories xmltype PATH 'TourCategories' ) x2,                                                                                                     " +
				" 		XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4' ), '/TourDepartures/SellingCompanies/SellingCompany' " +
				" 				PASSING xmltype.createxml(t.tourdeparture_xml)                                                                                                   " +
				" 				COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x3,                                                                                        " +
				" 		XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourCategories/TourCategory'                            " +
				" 				PASSING x2.tourcategories                                                                                                                        " +
				" 				COLUMNS categoryname VARCHAR2(50) PATH '@Name',                                                                                                  " +
                "         categoryvalue xmltype PATH 'CategoryValue' ) x4,                                                                                                       " +
                "       XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/CategoryValue'                                          " +
				" 				PASSING x4.categoryvalue                                                                                                                         " +
				" 				COLUMNS categoryvalue VARCHAR(50) PATH 'text()' ) x5                                                                                             " +
				" 		where t.content_repository_id=r.id and x2.sellingcompanycode=x3.sellingcompanycode and r.repository_status='TIandTD'                                     " +    
				" 			and (:sccode is null or x2.sellingcompanycode=:sccode) ) agg where rownum <= :records group by agg.sellingcompanycode, agg.categoryname";
			
			
			public static final String sqlForGetBrouchureRest=
			"SELECT TOUR_CODE,S.CODE SCODE,B.CODE BCODE FROM CONTENT_REPOSITORY C "+
			"join SELLINGCOMPANY_CONTENT SC on SC.CONTENT_ID=C.ID "+
			"join SELLING_COMPANY S on SC.SELLING_COMPANY_ID=S.ID "+
			"join Brand B on B.ID=S.BRAND_ID "+
			"where C.TOURDEPARTURE_XML_MD5 IS NOT NULL and C.TOURINFO_XML_MD5 IS NOT NULL order by B.CODE"; 
			
			
			public static final String sqlForKeywordAndPhrases = "select r.tour_code ,x.*                                 " + 
					"from CONTENT_REPOSITORY r, xml_content_repository x, SELLINGCOMPANY_CONTENT sc, SELLING_COMPANY s    " +
					"where r.id=x.CONTENT_REPOSITORY_ID                                                                   " +
					"	and s.ID=sc.SELLING_COMPANY_ID                                                                    " +
					"	and sc.CONTENT_ID=r.id                                                                            " +
					"	and r.REPOSITORY_STATUS='TIandTD'                                                                 " +
					"	and s.CODE=:sccode                                                                                " +
					"	and rownum = :records ";
			
			
			public static NamedParameterJdbcTemplate prepareTemplate() throws IOException {
				DataSource dataSource = CcapiCucumberHelper.applicationContext.getBean(DataSource.class);
			    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				return jdbcTemplate;
			}
			
			public static NamedParameterJdbcTemplate prepareTemplateForSearch() throws IOException {
				DataSource dataSource = getACforSearch().getBean(DataSource.class);
			    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				return jdbcTemplate;
			}
			
			private static ApplicationContext aplicationContextForSearch = null;
			
			public static ApplicationContext getACforSearch() {
				if(aplicationContextForSearch == null) {
					aplicationContextForSearch = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/testForSearchCtx.xml", 
							"classpath:/META-INF/spring/daoCtx.xml", "classpath:/META-INF/spring/blCtx.xml", "classpath:/META-INF/spring/searchCtx.xml");
				}
				return aplicationContextForSearch;
			
			}
}
