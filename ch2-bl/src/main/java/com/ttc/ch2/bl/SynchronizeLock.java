package com.ttc.ch2.bl;

import java.util.Map;

import javax.inject.Inject;

import org.elasticsearch.common.collect.Maps;
import org.springframework.stereotype.Component;

import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;

@Component
public class SynchronizeLock {

	@Inject
	private BrandDAO brandDAO;
	
	private Map<String,Brand> brands;
	
	public void init() {
		brands = Maps.newHashMap();
		for (Brand brand : brandDAO.findAll()) {
			brands.put(brand.getCode(), brand);
		}
	}
		
	public Brand getBrandLockByCode(String code) {
		return brands.get(code);
	}
	
}
