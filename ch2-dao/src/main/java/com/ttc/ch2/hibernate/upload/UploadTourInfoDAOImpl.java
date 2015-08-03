package com.ttc.ch2.hibernate.upload;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockOptions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.common.exceptions.DaoException;
import com.ttc.ch2.dao.upload.UploadTourInfoDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;


@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class UploadTourInfoDAOImpl extends BaseDao<UploadTourInfoFile,  Long>  implements UploadTourInfoDAO{

		
	@Override
	public List<UploadTourInfoFile> getUploadTourInfoList(	QueryCondition queryCondition, UploadTourInfoFile filter) {				
		return getUploadTourInfoList(queryCondition, filter, null);
	}
	
	@Override
	public List<UploadTourInfoFile> getUploadTourInfoList(	QueryCondition queryCondition, UploadTourInfoFile filter,List<Brand> brands) {		
		Search search = new Search();
		setupSortCondition(search, queryCondition);
		searchCondition(filter, search,brands);		
		return search(search);
	}
	
	@Override
	public int getUploadTourInfoCount(UploadTourInfoFile filter) {
		return getUploadTourInfoCount(filter,null);
	}
	
	
	@Override
	public int getUploadTourInfoCount(UploadTourInfoFile filter,List<Brand> brands) {
		Search search = new Search();
		searchCondition(filter, search, brands);
		return this.count(search);
	}
	
	private void searchCondition(UploadTourInfoFile filter,	Search search, List<Brand> brands){
		if(filter!=null)
		{
			if(filter.getDateUpload() != null) {
				search.addFilterGreaterOrEqual("dateUpload", filter.getDateUpload());
			}
			if(StringUtils.isNotEmpty(filter.getName())) {
				search.addFilterLike("name", "%" + filter.getName()+"%");
			}			
			if(filter.getSourceUploadFile() != null) {
				search.addFilterEqual("sourceUploadFile", filter.getSourceUploadFile());
			}
			if(filter.getStatus() != null) {
				search.addFilterEqual("status", filter.getStatus());
			}
			if(filter.getBrand()!=null){
				search.addFilterEqual("brand", filter.getBrand());
			}
		}
		
		if(brands!=null && brands.size()>0)
		{
			search.addFilter(Filter.in("brand", brands));
		}		
	}
	
	public List<UploadTourInfoFile> getUploadTourInfoListAndLock(UploadTourInfoFile filter)
	{
		List<UploadTourInfoFile> list=getUploadTourInfoList(null, filter);
		List<UploadTourInfoFile> result=Lists.newArrayList();
		for (UploadTourInfoFile uploadTourInfoFile : list) {
			result.add((UploadTourInfoFile)getSession().get(UploadTourInfoFile.class, uploadTourInfoFile.getId(), LockOptions.UPGRADE));	
		}
		return result;
	}
	
	
	@Override
	public UploadTourInfoFile getUploadTourInfoFileByExample(UploadTourInfoFile eUploadTourInfoFile) {	
		Filter filter=getFilterFromExample(eUploadTourInfoFile);		
		Search search = new Search();
		search.addFilter(filter);
		List<UploadTourInfoFile> list=this.search(search);
		
		if(list.size()>1)
			throw new DaoException("To many record in UploadTourInfoDAOImpl->getUploadTourInfoFileByExample");
		
		return list.size() ==1 ? list.get(0) : null;
	}
}
