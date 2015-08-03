package com.ttc.ch2.hibernate.security;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.security.GroupDAO;
import com.ttc.ch2.domain.auth.Group;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class GroupDAOImpl extends BaseDao<Group, Long> implements GroupDAO {


		
}
