package com.ttc.ch2.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.common.SortCondition;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class SellingCompanyDAOImpl extends BaseDao<SellingCompany, Long> implements SellingCompanyDAO {

	@Override
	public SellingCompany findBySellingCompanyCode(String code) {

		Search search = new Search();
		search.addFilterEqual("code", code);

		return searchUnique(search);
	}

	@Override
	public List<SellingCompany> findByBrandCode(String brandCode) {

		Search search = new Search();
		search.addFilterEqual("brand.code", brandCode);

		return search(search);
	}

	@Override
	public List<SellingCompany> findBySellingCompanyCodes(List<String> codes) {

		Search search = new Search();
		search.addFilterIn("code", codes);

		return search(search);
	}

	public List<SellingCompany> findAllSellingCompany(SortCondition sc) {

		Search search = new Search();

		if (sc != null) {
			if (sc.getDirection().equals(SortCondition.Direction.ASC)) {
				search.addSortAsc(sc.getColumnName());
			} else {
				search.addSortDesc(sc.getColumnName());
			}
		}

		return this.search(search);
	}
}
