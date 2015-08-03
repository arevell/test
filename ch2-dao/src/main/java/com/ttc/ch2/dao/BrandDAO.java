package com.ttc.ch2.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.Brand;

public interface BrandDAO extends GenericDAO<Brand, Long> {

		Brand findByBrandCode(String code);
}
