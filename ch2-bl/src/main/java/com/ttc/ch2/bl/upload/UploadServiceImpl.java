package com.ttc.ch2.bl.upload;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.elasticsearch.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.ttc.ch2.bl.lock.DbLocker;
import com.ttc.ch2.bl.lock.DbLocker.LockSql;
import com.ttc.ch2.bl.lock.Executor;
import com.ttc.ch2.bl.lock.ExecutorException;
import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.tour.ContentRepositoryDAO;
import com.ttc.ch2.dao.upload.UploadTourInfoDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class UploadServiceImpl implements UploadService{

	private static final Logger logger=LoggerFactory.getLogger(UploadServiceImpl.class);
		
	@Inject
	private UploadTourInfoDAO uploadTourInfoDAO;
	
	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private ContentRepositoryDAO contentRepositoryDAO;
	
	@Inject
	private UserService userService;
	
	@Inject
	private ApplicationContext ctx;
	
		
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void mergeOnlyUploadTourInfoFile(UploadTourInfoFile uploadTourInfoFile){
				
		Set<Long> emptyIds=Sets.newHashSet();
		mergeUploadFile(uploadTourInfoFile,emptyIds);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void mergeUploadTourInfoFileWithCr(UploadTourInfoFile uploadTourInfoFile,Set<Long> ids){
				
		mergeUploadFile(uploadTourInfoFile,ids);
	}
	
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void addPreProcesingUploadTourInfo(final UploadTourInfoFile uploadTourInfoFile) {
		
		Preconditions.checkArgument(uploadTourInfoFile!=null,"UploadServiceImpl->addPreProcesingUploadTourInfo arg uploadTourInfoFile is null");
		Preconditions.checkState(uploadTourInfoFile.getStatus()!=null,"UploadServiceImpl->addPreProcesingUploadTourInfo arg uploadTourInfoFile.status is null");
		Preconditions.checkState(uploadTourInfoFile.getStatus()==UploadTourInfoFileStatus.PRE_PROCESSING,"UploadServiceImpl->addPreProcesingUploadTourInfo arg uploadTourInfoFile.status is incorrect:"+ uploadTourInfoFile.getStatus());
		
		Executor executor=new Executor() {			
			@Override
			public void execute() throws ExecutorException {
				try{
					UploadTourInfoFile filter=new UploadTourInfoFile();
					filter.setBrand(uploadTourInfoFile.getBrand());
					filter.setStatus(UploadTourInfoFileStatus.PRE_PROCESSING);
					List<UploadTourInfoFile> list=uploadTourInfoDAO.getUploadTourInfoList(null,filter);
					for (UploadTourInfoFile uFile : list) {
						uFile.setStatus(UploadTourInfoFileStatus.REJECTED);
						uploadTourInfoDAO.save(uFile);
					}
					
					Set<Long> emptyIds=Sets.newHashSet();
					mergeUploadFile(uploadTourInfoFile,emptyIds);					
					uploadTourInfoDAO.flush();
				}
				catch(Exception e){
					logger.error("",e);
					throw new UploadServiceException(e);
				}				
			}
		};
		
		DbLocker dblocker=ctx.getBean(DbLocker.class);
		try {
			dblocker.executeOperation(executor, LockSql.UPLOAD_LOCK);
		} catch (ExecutorException e) {
			if(e.getCause()!=null && e.getCause() instanceof Exception )
				throw new UploadServiceException((Exception)e.getCause());
			else
				throw new UploadServiceException(e);
		}
	}
	
	
	@Override
	public List<UploadTourInfoFile> getUploadTourInfoList(QueryCondition queryCondition, UploadTourInfoFile filter) {
		
		try{
			return uploadTourInfoDAO.getUploadTourInfoList(queryCondition, filter);	
		}
		catch(Exception e)
		{
			throw new UploadServiceException(e);
		}				
	}
	
	@Override
	public List<UploadTourInfoFile> getUploadTourInfoList(QueryCondition queryCondition,UploadTourInfoFile filter,List<Brand> brands){		
		try{
			return uploadTourInfoDAO.getUploadTourInfoList(queryCondition, filter,brands);	
		}
		catch(Exception e)
		{
			throw new UploadServiceException(e);
		}				
	}

	@Override
	public int getUploadTourInfoCount(UploadTourInfoFile filter,List<Brand> brands) {
		
		try{
			return uploadTourInfoDAO.getUploadTourInfoCount(filter,brands);	
		}
		catch(Exception e)
		{
			throw new UploadServiceException(e);
		}		
	}
	
	public List<TIComment> getCommensForTourInfo(UploadTourInfoFile uFile)
	{
		if(uFile.getId()!=null)
			uploadTourInfoDAO.refresh(uFile);
		return Lists.newArrayList(uFile.getComments());
	}


	@Override
	public UploadTourInfoFile getFullData(Long id) {
		UploadTourInfoFile uploadTourInfoFile=uploadTourInfoDAO.find(id);
		uploadTourInfoFile.getZipData().getData();
		for (ContentRepository cr : uploadTourInfoFile.getContentRepositories()) {
			cr.getId();
		}
		for (TIComment comment : uploadTourInfoFile.getComments()) {
			comment.getId();
		}
		
		return uploadTourInfoFile;
	}
	
	
	private void mergeUploadFile(UploadTourInfoFile uploadTourInfoFile,Set<Long> idsCr){
		
		Preconditions.checkArgument(uploadTourInfoFile!=null,"UploadTourInfoFile is null");	
		try{			
			UploadTourInfoFile localUploadTourInfoFile;			
			if(uploadTourInfoFile.getId()!=null)
			{								
				Preconditions.checkArgument(idsCr!=null,"Argument idsCr (Long ids with saved or updated Cr) is null");
				
				localUploadTourInfoFile=uploadTourInfoDAO.find(uploadTourInfoFile.getId());
				localUploadTourInfoFile.setStatus(uploadTourInfoFile.getStatus());
				List<ContentRepository> localContentRepositories=Lists.newArrayList(); 
				for (Long id : idsCr) {
					ContentRepository locCr=contentRepositoryDAO.find(id);
					locCr.getUploadTourInfoFile().add(localUploadTourInfoFile);
					localContentRepositories.add(locCr);
				}
				localUploadTourInfoFile.getContentRepositories().clear();
				localUploadTourInfoFile.getContentRepositories().addAll(localContentRepositories);
				
				for (TIComment comment : uploadTourInfoFile.getComments()) {
					if(comment.getId()==null){
						localUploadTourInfoFile.getComments().add(comment);
						comment.setUploadTourInfoFile(localUploadTourInfoFile);
					}					
				}			
			}
			else{
				localUploadTourInfoFile=uploadTourInfoFile;
			}
			
			if(localUploadTourInfoFile.getBrand()!=null)
				localUploadTourInfoFile.setBrand(brandDAO.find(uploadTourInfoFile.getBrand().getId()));
			if(localUploadTourInfoFile.getUser()!=null)
				localUploadTourInfoFile.setUser(userService.findUserByName(uploadTourInfoFile.getUser().getUsername()));
			
				
			uploadTourInfoDAO.save(localUploadTourInfoFile);	
			uploadTourInfoDAO.flush();
		}catch(Exception e){
			throw new UploadServiceException(e);
		}			
	}

	@Override
	public UploadTourInfoFile getUploadTourInfoFileByName(String name) {

		UploadTourInfoFile eUploadTourInfoFile=new UploadTourInfoFile();
		eUploadTourInfoFile.setName(name);
		
		return uploadTourInfoDAO.getUploadTourInfoFileByExample(eUploadTourInfoFile);		
	}
}
