package com.ttc.ch2.hibernate.security;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.user.UserCCAPI;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class UserCCAPIDAOImpl extends BaseDao<UserCCAPI,  Long> implements UserCCAPIDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserCCAPIDAOImpl.class);
	
	@Override
	public UserCCAPI findByToken(String token) {		
		logger.trace("UserCCAPIDAOImpl:findByToken-start");
		
			Session session=getSession();
			Query query=session.getNamedQuery("UserCCAPI.findByToken").setString("token", token).setMaxResults(1).setComment("Search user by token:"+token);
			UserCCAPI qResult=(UserCCAPI) query.uniqueResult();
		
		logger.trace("UserCCAPIDAOImpl:findByToken-end");
		return qResult;		
	}

	@Override
	public int getUsersCount(UserCCAPI filter) {
		logger.trace("UserCCAPIDAOImpl:getUsersCount-start");
		Search search = new Search();
		sertupFiltrCondition(filter, search);
		int result=this.count(search);
		logger.trace("UserCCAPIDAOImpl:getUsersCount-end");
		return result;
	}

	@Override
	public List<UserCCAPI> getUsers(QueryCondition conditions, UserCCAPI filter) {
		logger.trace("UserCCAPIDAOImpl:getUsers-start");
		Search search = new Search();
		setupSortCondition(conditions, search);
		sertupFiltrCondition(filter, search);		
		List<UserCCAPI> result=this.search(search);
		logger.trace("UserCCAPIDAOImpl:getUsers-end");
		return result;
	}
	
	
	private void sertupFiltrCondition(UserCCAPI filter, Search search) {
		if(filter != null) {
			if(filter.getEnabled() != null){
				search.addFilterEqual("enabled", filter.getEnabled());
			}
			if(filter.getUsername() != null){
				search.addFilterILike("username", "%" + filter.getUsername() +"%");
			}
			if(StringUtils.isNotBlank(filter.getEmail())){
				search.addFilterILike("email", "%" + filter.getEmail() +"%");
			}
			if(StringUtils.isNotBlank(filter.getAddress())){
				search.addFilterILike("address", "%" + filter.getAddress()+"%");
			}
			
			if (filter.getDelFlag() != null) {
				search.addFilterEqual("delFlag", filter.getDelFlag());
			}
		}
	}

	private void setupSortCondition(QueryCondition conditions,Search search){	
		if(conditions !=null) {
				search.setMaxResults(conditions.getPageSize());
				search.setFirstResult(conditions.getStartNumberItem());
				for(SortCondition sc: conditions.getSortConditions()) {
					if(sc.getDirection().equals(SortCondition.Direction.ASC)) {
						search.addSortAsc(sc.getColumnName(),true);
					}
					else {
						search.addSortDesc(sc.getColumnName(),true);
					}
				}
			}		
	}
	
}
