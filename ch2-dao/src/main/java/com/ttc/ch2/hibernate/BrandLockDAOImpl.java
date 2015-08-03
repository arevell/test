package com.ttc.ch2.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.BrandLockDAO;
import com.ttc.ch2.domain.BrandLock;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class BrandLockDAOImpl extends BaseDao<BrandLock,  Long> implements BrandLockDAO {


	
	
}
