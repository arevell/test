package com.ttc.ch2.bl.upload;

import java.util.List;
import java.util.Set;

import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;

public interface UploadService {
	
	public void mergeUploadTourInfoFileWithCr( UploadTourInfoFile uploadTourInfoFile,Set<Long> idsCr);
	public void mergeOnlyUploadTourInfoFile( UploadTourInfoFile uploadTourInfoFile);
	public List<UploadTourInfoFile> getUploadTourInfoList(QueryCondition queryCondition,UploadTourInfoFile filter);
	public List<UploadTourInfoFile> getUploadTourInfoList(QueryCondition queryCondition,UploadTourInfoFile filter,List<Brand> brands);
	
	public int getUploadTourInfoCount(UploadTourInfoFile filter,List<Brand> brands);
	public List<TIComment> getCommensForTourInfo(UploadTourInfoFile uFile);
	public UploadTourInfoFile getFullData(Long id);
	
	public UploadTourInfoFile getUploadTourInfoFileByName(String fileName);
	
	public void addPreProcesingUploadTourInfo(UploadTourInfoFile uploadTourInfoFile) throws UploadServiceException;
	
}
