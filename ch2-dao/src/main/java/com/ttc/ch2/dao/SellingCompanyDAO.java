package com.ttc.ch2.dao;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.common.SortCondition;

public interface SellingCompanyDAO extends GenericDAO<SellingCompany, Long> {

	SellingCompany findBySellingCompanyCode(String code);

	List<SellingCompany> findByBrandCode(String brandCode);

	List<SellingCompany> findBySellingCompanyCodes(List<String> codes);

	List<SellingCompany> findAllSellingCompany(SortCondition sortCondition);
}
