package com.ttc.ch2.hibernate.tour;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.predicates.FindEntityByIdPredicate;
import com.ttc.ch2.common.predicates.FindeSellingCompanyByCodePredicate;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.common.EntityBase;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.domain.user.UserCCAPI;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class ContentRepositoryDAOImpl extends BaseDao<ContentRepository, Long> implements ContentRepositoryDAO {

	private static final Logger logger = LoggerFactory.getLogger(ContentRepositoryDAOImpl.class);
	@Inject
	private BrandDAO brandDAO;

	@Inject
	private SessionFactory sessionFactory;
	
	/**
	 * If tour code not exist method return permisionGranted=false
	 * */
	public boolean checkPermissionToFile(UserCCAPI user, String tourCode,String brandCode,FunctionType function) {
		boolean permisionGranted=false; 
		ContentRepository cr=findByTourCode(tourCode,brandCode);
		if(cr!=null){
			 Multimap<FunctionType,SellingCompany>  authorites=AuthorityHelper.transformAuthorityforUserCcapi(user);
			for (SellingCompany company : authorites.get(function)) {				
				Optional<SellingCompany> result=Iterables.tryFind(cr.getSellingCompanies(),new FindeSellingCompanyByCodePredicate(company.getCode()));
				if(result.isPresent()){
					permisionGranted=true;
					break;
				}				
			}
		}
		return permisionGranted;
	}

	public int deleteEmptyContentRepository(String brandCode)
	{		
		logger.trace("ContentRepositoryDAOImpl:deleteEmptyContentRepository-start");				
		Search search = new Search();
		search.addFilterEqual("brands.code", brandCode);
		search.addFilterEmpty("tourInfoXMLMD5");
		search.addFilterEmpty("tourDepartureXMLMD5");
		logger.trace("Deleting execute query");
		List<ContentRepository> result=search(search);		
		int count=0;
		for (ContentRepository contentRepository : result) {
			logger.trace("Start Deleting content:"+contentRepository.getTourCode()+" id:"+contentRepository.getId());
			remove(contentRepository);
			flush();
			logger.trace("End Deleting content:"+contentRepository.getTourCode()+" id:"+contentRepository.getId());
			count++;
		}
		
//		return searchUnique(search);
		
//		Session session = sessionFactory.getCurrentSession();				
//		StringBuilder sb=new StringBuilder();		
//		sb.append("select r from ContentRepository r ");
//		sb.append("inner join r.brands as B ");		
//		sb.append("where r.tourInfoXMLMD5 is null and r.tourDepartureXMLMD5 is null ");		
//		sb.append("and B.code=:brandCode");								
//		Query query = session.createQuery(sb.toString());
//		query.setParameter("brandCode",brandCode);
//		List<Long> result=query.list();
		
//		Iterable<List<Long>> itr=Iterables.partition(result, 800);
//		int count=0;
//		if(result.size()>0){		
//			for (Iterator<List<Long>> iterator = itr.iterator(); iterator.hasNext(); ) {
//				List<Long> part =  iterator.next();
//				
//				logger.trace("Deleting part for brand:"+brandCode+" size:"+part.size());
//				logger.trace("Deleting for brand:"+brandCode+" "+Joiner.on(",").join(part));
//				Query delXCRQuery = session.createQuery("DELETE FROM XMLContentRepository r where r.contentRepository.id in :ids");
//				delXCRQuery.setParameterList("ids",part);	
//				delXCRQuery.executeUpdate();
//				
//				Query delQuery = session.createQuery("DELETE FROM ContentRepository r where r.id in :ids");
//				delQuery.setParameterList("ids",part);		
//				count+=delQuery.executeUpdate();
//				logger.trace("Deleting for brand:"+brandCode+" "+Joiner.on(",").join(part));
//			}
//		}		
//		Query query = session.createQuery("DELETE FROM ContentRepository r where r.tourInfoXMLMD5 is null and r.tourDepartureXMLMD5 is null and r.brands in (:p_brands)");
//		Query query = session.createQuery("UPDATE ContentRepository set repositoryStatus=:status " + " where tourInfoXMLMD5 is null and tourDepartureXMLMD5 is null and brands in (:p_brands)");
//		query.setParameter("status", RepositoryStatus.Empty);
//		query.setParameterList("p_brands",Lists.newArrayList(brandDAO.findByBrandCode(brandCode)));
//		int count=query.executeUpdate();		
		logger.trace("ContentRepositoryDAOImpl:deleteEmptyContentRepository-end");
		return count;			
	}
	
	public List<ContentRepository> getContentRepositoriesElementsToClear(Set<Long> crToSave,Brand brand,RepositoryStatus ... status)
	{	
		Session session = sessionFactory.getCurrentSession();
		StringBuilder sb=new StringBuilder();
		sb.append("select r from ContentRepository r inner join r.brands as B where");		
		sb.append(" B.id=:brandId and r.repositoryStatus in :status");		
		Query query = session.createQuery(sb.toString());		
		query.setParameter("brandId", brand.getId());
		query.setParameterList("status", status);
		
		List<ContentRepository> allCr=query.list();
		
		for (Long contentRepositoryId : crToSave) {			
		 Optional<ContentRepository> find=Iterables.tryFind(allCr, new FindEntityByIdPredicate(contentRepositoryId));
			if (find.isPresent()) {
			 allCr.remove(find.get());
			}						
		}		
		return allCr;
	}
	
	public List<Long> getContentRepositoriesIdsToClear(Set<Long> crToRetain, Brand brand, RepositoryStatus ... status)
	{			
		Session session = sessionFactory.getCurrentSession();
		StringBuilder sb=new StringBuilder();
		sb.append("select r.id from ContentRepository r inner join r.brands as B where");		
		sb.append(" B.id=:brandId and r.repositoryStatus in :status");		
//		StringBuilder sb=new StringBuilder();
//		sb.append("select r from ContentRepository r inner join r.brands as B where");		
//		sb.append(" B.id=:brandId and r.repositoryStatus in :status");		
		Query query = session.createQuery(sb.toString());		
		query.setParameter("brandId", brand.getId());
		query.setParameterList("status", status);		
		
		@SuppressWarnings("unchecked")
		List<Long> idsToClear=query.list();
		idsToClear.removeAll(crToRetain);
		return idsToClear;
	}
	

	@Override
	public List<ContentRepository> getContentRepositoriesList(QueryCondition queryCondition, ContentRepository filter) {
		List<ContentRepository> result=Lists.newArrayList();
		List<RepositoryStatus> statuses = Lists.newArrayList();
		if(filter.getRepositoryStatus() != RepositoryStatus.Initial)
			statuses.add(filter.getRepositoryStatus());
		result=getContentRepositoriesList(queryCondition, filter, statuses);
		return result;
	}
	
	
	
	
	@Override
	public int getContentRepositoriesCount(QueryCondition queryCondition, ContentRepository filter) {
		List<RepositoryStatus> statuses = Lists.newArrayList();
		if(filter.getRepositoryStatus() != RepositoryStatus.Initial)
			statuses.add(filter.getRepositoryStatus());
		return getContentRepositoriesCount(queryCondition, filter, statuses);
	}

	@Override
	public ContentRepository findByTourCode(String tourCode,String brandCode) {
		Search search = new Search();
		search.addFilterEqual("tourCode", tourCode);
		search.addFilterEqual("brands.code", brandCode);
		return searchUnique(search);
	}

	@Override
	public List<ContentRepository> findByTourCodes(List<String> tourCodes, String brandCode) {

		Search search = new Search();

		search.addFilterIn("tourCode", tourCodes);
		search.addFilterEqual("brands.code", brandCode);

		return search(search);
	}

	@Override
	public ContentRepository findByTourCodeWithLock(String tourCode,String brandCode) {
		Search search = new Search();
		search.addFilterEqual("tourCode", tourCode);
		search.addFilterEqual("brands.code", brandCode);
		ContentRepository cr=searchUnique(search);
		Session session = sessionFactory.getCurrentSession();
		if(cr!=null)
		{
			session.lock(cr, LockMode.PESSIMISTIC_WRITE);
			//cr.getXmlContentRepository().getId();
		}
//		ContentRepository result=session.load(ContentRepository.class, cr.getId(),LockOptions.WAIT_FOREVER); 		
//		session.buildLockRequest(LockOptions.WAIT_FOREVER).setLockMode(LockOptions.PESSIMISTIC_WRITE).setTimeOut(60000).lock(entity)
		
		return cr;
	}
	

	class  TransoformToListId implements Function<EntityBase, Long>
	{
	
		@Override
		public Long apply(EntityBase input) {		
			return input.getId();
		}
	}
	
	//count cant use fetch
	private String getContentRepositoriesQueryString(ContentRepository filter, List<RepositoryStatus> statuses, boolean isEualsPreffered,boolean useBrandFetch) {
		boolean isWhereUsed=false;
		StringBuffer sb=new StringBuffer();
		sb.append("from com.ttc.ch2.domain.tour.ContentRepository CR ");
		if(filter.getBrands().size() > 0 && filter.getSellingCompanies().size() == 0) {
			isWhereUsed=true;
			sb.append("inner join ").append(useBrandFetch ? "fetch" : "").append(" CR.brands as B ");		
			sb.append("where B in (:p_brands) ");
		}
		else if(filter.getBrands().size() == 0 && filter.getSellingCompanies().size() > 0) {
			isWhereUsed=true;
			sb.append("inner join CR.sellingCompanies as S ");		
			sb.append("where S in (:p_selingcompanies) ");
		}
		else if(filter.getBrands().size() > 0 && filter.getSellingCompanies().size() > 0) {
			isWhereUsed=true;
			sb.append("inner join CR.sellingCompanies as S inner join ").append(useBrandFetch ? "fetch" : "").append(" CR.brands as B ");		
			sb.append("where S in (:p_selingcompanies) and B in (:p_brands) ");
		}
		
		if(StringUtils.isNotEmpty(filter.getTourCode())) {
			if(!isWhereUsed){
				sb.append("where ");
				isWhereUsed=true;
			}
			else
				sb.append("and ");
			if(isEualsPreffered)
				sb.append("CR.tourCode = :p_tourCode ");
			else
				sb.append("upper(CR.tourCode) like :p_tourCode ");				
		}
		if(statuses != null && statuses.size() > 0) {
			if(!isWhereUsed){
				sb.append("where ");
				isWhereUsed=true;
			}
			else
				sb.append("and ");
			sb.append("CR.repositoryStatus in (:p_repositorystatuses) ");
		}
		
		
		return sb.toString();
	}
	
	private void setContentRepositoriesParams(ContentRepository filter, List<RepositoryStatus> statuses, Query q, boolean isEualsPreffered) {
		if(filter.getBrands().size() > 0 && filter.getSellingCompanies().size() == 0) {
			q.setParameterList("p_brands",filter.getBrands());
		}
		else if(filter.getBrands().size() == 0 && filter.getSellingCompanies().size() > 0) {
			q.setParameterList("p_selingcompanies", filter.getSellingCompanies());
		}
		else if(filter.getBrands().size() > 0 && filter.getSellingCompanies().size() > 0) {
			q.setParameterList("p_brands",filter.getBrands());
			q.setParameterList("p_selingcompanies", filter.getSellingCompanies());
		}
		
		if(StringUtils.isNotEmpty(filter.getTourCode())) {
			if(isEualsPreffered)
				q.setString("p_tourCode", filter.getTourCode());
			else
				q.setString("p_tourCode", "%"+filter.getTourCode().toUpperCase()+"%");
		}
		
		if(statuses != null && statuses.size() > 0) {
			q.setParameterList("p_repositorystatuses", statuses);
		}
	}

	

	@Override
	public List<ContentRepository> getContentRepositoriesList(QueryCondition queryCondition, ContentRepository filter,	List<RepositoryStatus> statuses) {
		List<ContentRepository> result=Lists.newArrayList();
		if(filter != null) {
			StringBuffer sb=new StringBuffer("select distinct CR ");
			sb.append(getContentRepositoriesQueryString(filter, statuses, queryCondition != null && queryCondition.isEqualsPrefered(),true));	
			
			if(queryCondition != null && queryCondition.getSortConditions() != null && queryCondition.getSortConditions().size() > 0 ) {
				sb.append("order by ");
				for (Iterator<SortCondition> iterator = queryCondition.getSortConditions().iterator(); iterator.hasNext();) {
					SortCondition sc = iterator.next();
					if(sc.getDirection().equals(SortCondition.Direction.ASC)) {
						sb.append("CR."+sc.getColumnName()+" ASC" ).append(iterator.hasNext() ? ",":"");
					}
					else {
						sb.append("CR."+sc.getColumnName()+" DESC" ).append(iterator.hasNext() ? ",":"");
					}
				}
			}	
			
			Query q=sessionFactory.getCurrentSession().createQuery(sb.toString());
			
			setContentRepositoriesParams(filter, statuses, q, queryCondition != null && queryCondition.isEqualsPrefered());
			
			if(queryCondition != null && queryCondition.getPageSize() != -1 && queryCondition.getStartNumberItem() != -1) {
				q.setFirstResult(queryCondition.getStartNumberItem());
				q.setMaxResults(queryCondition.getPageSize());
			}
			
			// distinct is not allowed on CLOB columns in ORACLE		
			result=q.list();				
		}
		return result;
	}
	

	
	public List<Long> getContentRepositoriesIdsList(QueryCondition queryCondition, ContentRepository filter, List<RepositoryStatus> statuses) {

		if(filter == null) {
			return Lists.newArrayList();
		}

		StringBuffer sb=new StringBuffer("select distinct CR.id ");
		sb.append(getContentRepositoriesQueryString(filter, statuses, queryCondition != null && queryCondition.isEqualsPrefered(),false));				
		sb.append("order by CR.id ASC");		

		//			select  CR.id from com.ttc.ch2.domain.tour.ContentRepository CR 
		//			inner join fetch 
		//			CR.brands as B 
		//			where B in (:p_brands) 
		//			and CR.repositoryStatus in (:p_repositorystatuses) order by CR.id ASC

		Query q=sessionFactory.getCurrentSession().createQuery(sb.toString());			
		setContentRepositoriesParams(filter, statuses, q, queryCondition != null && queryCondition.isEqualsPrefered());			

		// distinct is not allowed on CLOB columns in ORACLE					
		@SuppressWarnings("unchecked")
		List<Long> result = q.list();				

		return result;		
	}
	
	

	@Override
	public int getContentRepositoriesCount(QueryCondition queryCondition, ContentRepository filter, List<RepositoryStatus> statuses) {
		if(filter != null) {
			StringBuffer sb=new StringBuffer("select count(distinct CR.id) ");
			sb.append(getContentRepositoriesQueryString(filter, statuses, queryCondition != null && queryCondition.isEqualsPrefered(),false));
			Query q=sessionFactory.getCurrentSession().createQuery(sb.toString());
			setContentRepositoriesParams(filter, statuses, q, queryCondition != null && queryCondition.isEqualsPrefered());
			Long count = (Long)q.uniqueResult();
			return count.intValue();
		}
		else
			return 0;
	}
	
	@Override
	public List<ContentRepository> getContentRepositoriesForExport() {
		List<ContentRepository> result;	        
	    ContentRepository filter = new ContentRepository();
		QueryCondition qc = new QueryCondition(-1, -1);
			
		qc.setSortConditions(Lists.newArrayList(new SortCondition("cataloguedTourCode", Direction.ASC), 
				new SortCondition("tourCode", Direction.ASC)));
		qc.setEqualsPrefered(true);
		filter.setRepositoryStatus(ContentRepository.RepositoryStatus.TIandTD);
			
		result = getContentRepositoriesList(qc, filter);
		return result;
	}

	@Override
	public List<ContentRepository> getContentRepositoriesForExport(String brandCode) {

		List<ContentRepository> result;

		ContentRepository filter = new ContentRepository();

		QueryCondition qc = new QueryCondition(-1, -1);
		qc.setSortConditions(Lists.newArrayList(new SortCondition("cataloguedTourCode", Direction.ASC), new SortCondition("tourCode", Direction.ASC)));
		qc.setEqualsPrefered(true);

		filter.setRepositoryStatus(ContentRepository.RepositoryStatus.TIandTD);
		filter.getBrands().add(brandDAO.findByBrandCode(brandCode));

		result = getContentRepositoriesList(qc, filter);

		return result;
	}

    
    
}
