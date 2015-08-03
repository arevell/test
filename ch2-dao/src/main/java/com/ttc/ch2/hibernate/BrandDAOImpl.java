package com.ttc.ch2.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class BrandDAOImpl extends BaseDao<Brand,  Long> implements BrandDAO {

	@Override
	public Brand findByBrandCode(String code) {	
		Session session=getSession();		
		Query query = session.getNamedQuery("Brand.findByBrandCode").setString("code", code).setMaxResults(1).setComment("Search brand by code(cant be cached):"+code);
		return (Brand) query.uniqueResult();		
//		Search search = new Search();
//		search.addFilterEqual("code", code);		
//		return this.searchUnique(search);
	}	
}
