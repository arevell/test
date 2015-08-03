package com.ttc.ch2.dao.upload;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;

public interface UploadTourInfoDAO extends GenericDAO<UploadTourInfoFile, Long> {
	
	List<UploadTourInfoFile> getUploadTourInfoList(QueryCondition queryCondition,UploadTourInfoFile filter);
	
	int getUploadTourInfoCount(UploadTourInfoFile filter);

	List<UploadTourInfoFile> getUploadTourInfoList(QueryCondition queryCondition, UploadTourInfoFile filter,List<Brand> brands);
	
	int getUploadTourInfoCount(UploadTourInfoFile filter,List<Brand> brands);
	
	List<UploadTourInfoFile> getUploadTourInfoListAndLock(UploadTourInfoFile filter);
	
	public boolean save(UploadTourInfoFile entity);
	
	public UploadTourInfoFile getUploadTourInfoFileByExample(UploadTourInfoFile eUploadTourInfoFile);
			
}