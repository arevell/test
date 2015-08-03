package com.ttc.ch2.common;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;


@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
public class BaseDao<T, ID extends Serializable> extends  GenericDAOImpl<T, ID> {

	public static final int MAX_ELEMENTS_INSIDE_SQL_IN_CLAUSE = 999;
	
	@Inject
	protected SessionFactory sessionFactory;
	
	@PostConstruct
	public void init()
	{
		this.setSessionFactory(sessionFactory);
	}
	
	
	protected void setupSortCondition(Search search,QueryCondition conditions)
	{
		if(conditions != null) {
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
	
	
	@Override
	public T find(Serializable id) {
		return super.find(id);
	}

	@Override
	public T[] find(Serializable... ids) {
		return super.find(ids);
	}

	@Override
	public T getReference(Serializable id) {
		return super.getReference(id);
	}

	@Override
	public T[] getReferences(Serializable... ids) {
		return super.getReferences(ids);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean save(T entity) {
		return super.save(entity);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean[] save(T... entities) {
		return super.save(entities);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean remove(T entity) {
		return super.remove(entity);
	}

	@Transactional(readOnly=false)
	@Override
	public void remove(T... entities) {
		super.remove(entities);
		
	}

	@Transactional(readOnly=false)
	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByIds(Serializable... ids) {
		super.removeByIds(ids);
	}

	@Override
	public List<T> findAll() {
		return super.findAll();
	}

	@Override
	public <RT> List<RT> search(ISearch search) {
		return super.search(search);
	}

	@Override
	public <RT> RT searchUnique(ISearch search) {
		return super.searchUnique(search);
	}

	@Override
	public int count(ISearch search) {
		return super.count(search);
	}

	@Override
	public <RT> SearchResult<RT> searchAndCount(ISearch search) {
		return super.searchAndCount(search);
	}

	@Override
	public boolean isAttached(T entity) {
		return super.isAttached(entity);
	}

	@Override
	public void refresh(T... entities) {
		super.refresh(entities);
		
	}

	@Override
	public void flush() {
		super.flush();
		
	}

	@Override
	public Filter getFilterFromExample(T example) {
		return super.getFilterFromExample(example);
	}

	@Override
	public Filter getFilterFromExample(T example, ExampleOptions options) {
		return super.getFilterFromExample(example, options);
	}
	
	
	public void evictEntity(T entity){
		getSession().evict(entity);
	}
	
    public void clearSession() {
        Session session = getSession();
        session.clear();
    }
    
}
