package com.ttc.ch2.ch2_test_load.cucumber;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class DBClient {
	public static final String sqlForTourCodes = "SELECT DISTINCT x2.sellingcompanycode as sccode                                                                                     " +
			"	FROM xml_content_repository t, content_repository r,                                                                                                          " +
			"		XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0' ), '/TourInfo/SellingCompanies/SellingCompany'                " +
			"				PASSING xmltype.createxml(t.tourinfo_xml)                                                                                                         " +
			"				COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x2,                                                                                         " +
			"		XMLTABLE (XMLNAMESPACES( DEFAULT 'http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4' ), '/TourDepartures/SellingCompanies/SellingCompany'  " +
			"				PASSING xmltype.createxml(t.tourdeparture_xml)                                                                                                    " +
			"				COLUMNS sellingcompanycode VARCHAR2(50) PATH '@Code') x3                                                                                          " +
			"	where t.content_repository_id=r.id and x2.sellingcompanycode=x3.sellingcompanycode and r.repository_status='TIandTD'                                          " +
			"      and rownum = 1 ";
	
	private final Connection conn; 
	
	public DBClient() {
		try {
			Properties prop = new Properties();
			InputStream stream = System.getProperty("ch2.properties") != null ? new FileInputStream(System.getProperty("ch2.properties")) :  DBClient.class.getResourceAsStream("/testdb.properties");
			prop.load(stream);
		    Class.forName(prop.getProperty("datasource.driver"));
		    conn = DriverManager.getConnection(prop.getProperty("datasource.url"),prop.getProperty("datasource.user"),prop.getProperty("datasource.password"));
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	    //conn.close();
	}
	
	public void execute(String sql, Map<String,Object> params) {
		try {
			Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    rs.next();
		    Iterator it = params.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        params.put((String)pairs.getKey() , rs.getObject((String)pairs.getKey()));
		    }
		    rs.close();
		    stmt.close();	
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Map<String,Object> getBrochuresParams() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sccode", null);
		params.put("tourcode", null);
		params.put("brandcode", null);                                                                                      
		String sql = "select sc.code as sccode, cr.tour_code as tourcode, b.code as brandcode                                " +
					 " from content_repository cr, sellingcompany_content scc, selling_company sc, brand_content bc, brand b " +
					 " where cr.id=scc.content_id                                                                            " +
					 " 	and scc.selling_company_id=sc.id                                                                     " +
					 " 	and bc.content_id=cr.id                                                                              " +
					 " 	and bc.brand_id=b.id                                                                                 " +
					 " 	and cr.repository_status='TIandTD' and rownum=1";
		execute(sql, params);
		return params;
		
	}
	
	public Map<String,Object> getSellingComapnies() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sccode", null);                                                                                      
		execute(sqlForTourCodes, params);
		return params;
		
	}
}
