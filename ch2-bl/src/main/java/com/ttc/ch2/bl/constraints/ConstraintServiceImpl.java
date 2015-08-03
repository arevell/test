package com.ttc.ch2.bl.constraints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.elasticsearch.common.collect.Sets;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConstraintServiceImpl implements ConstraintService {

//	private static final String queryForTourWithoutBrand="SELECT tour_code FROM CONTENT_REPOSITORY where Id not in (select DISTINCT Content_Id from BRAND_CONTENT) and NOT REPOSITORY_STATUS='Empty' order by id desc";
	private static final String queryForTourWithoutBrand="SELECT tour_code FROM CONTENT_REPOSITORY R " +
														 "LEFT JOIN BRAND_CONTENT B on B.CONTENT_ID=R.ID " +
														 "WHERE B.CONTENT_ID IS NULL and NOT R.REPOSITORY_STATUS='Empty'";
	
	
	@Inject
	@Qualifier("dataSource")
	private DataSource ds;
	
	@Override
	public Set<String> getToursWithoutBrand() throws ConstraintServiceException {
		
		Set<String> toursWithoutBrand=Sets.newTreeSet();
		
		try{
		NamedParameterJdbcTemplate tpl=new NamedParameterJdbcTemplate(ds);		
		List<Map<String, Object>> tours=tpl.queryForList(queryForTourWithoutBrand, new HashMap<String,Object>());
			for (Map<String, Object> map : tours) {
				toursWithoutBrand.add((String) map.get("tour_code"));	
			}
		}
		catch(Exception e){
			throw new ConstraintServiceException(e);
		}
		
		return toursWithoutBrand;
	}

}
