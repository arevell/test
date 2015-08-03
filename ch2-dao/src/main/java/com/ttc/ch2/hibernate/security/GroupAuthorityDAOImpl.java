package com.ttc.ch2.hibernate.security;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.security.GroupAuthorityDAO;
import com.ttc.ch2.domain.auth.GroupAuthority;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class GroupAuthorityDAOImpl extends BaseDao<GroupAuthority, Long> implements GroupAuthorityDAO {
	
		
}
