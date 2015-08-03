package com.ttc.ch2.hibernate.security;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.common.exceptions.DaoException;
import com.ttc.ch2.dao.security.UserGuiDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.user.UserGui;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class UserGuiDAOImpl extends BaseDao< UserGui,  Long> implements UserGuiDAO {

	
	public int getUsersCount(UserGui filter)
	{
		Search search = new Search();
		setupFiltrCondition(filter, search);
		return this.count(search);
	}

	public List<UserGui> getUsers(QueryCondition conditions, UserGui filter )
	{
		Search search = new Search();
		setupSortCondition(conditions, search);		
		setupFiltrCondition(filter, search);
		return this.search(search);
	}
	
	public UserGui findByExample(UserGui exemple)
	{
		Filter filter=getFilterFromExample(exemple);		
		Search search = new Search();
		search.addFilter(filter);
		List<UserGui> list=this.search(search);
		
		if(list.size()>1)
			throw new DaoException("To many record in result:UserGuiDAOImpl->findByExample");
		
		return list.size() ==1 ? list.get(0) : null;
	}
	
	
	private void setupFiltrCondition(UserGui filter,Search search )
	{
		if(filter != null) {
			if(filter.getEnabled() != null){
				search.addFilterEqual("enabled", filter.getEnabled());
			}
			if(StringUtils.isNotBlank(filter.getUsername())){
				search.addFilterILike("username", "%" + filter.getUsername() +"%");
			}
			if(StringUtils.isNotBlank(filter.getEmail())){
				search.addFilterILike("email", "%" + filter.getEmail() +"%");
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
