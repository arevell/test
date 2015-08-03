package com.ttc.ch2.dao;

import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DBConfigDAOImpl implements DBConfigDAO {

	@Inject
	private DataSource dataSource;
	
	@Override
	public String getDBNLSparam(String param) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("charset", param);
		Map<String, Object> result = template.queryForMap("SELECT value FROM NLS_DATABASE_PARAMETERS where parameter=:charset", paramSource);
		return (String)result.get("value");
	}
	
}
