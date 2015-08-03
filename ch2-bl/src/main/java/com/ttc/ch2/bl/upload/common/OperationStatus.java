package com.ttc.ch2.bl.upload.common;

import java.util.Date;
import java.util.Set;

import org.elasticsearch.common.collect.Sets;

import com.google.common.base.Preconditions;
import com.ttc.ch2.bl.departure.common.JobStatusChecker;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;
import com.ttc.ch2.domain.user.User;

public class OperationStatus {		
	
	private JobStatusChecker jobStatusChecker;
	private MessageManager messageManager;
	private UploadTourInfoFileStatus status=null;
	private UploadTourInfoFile uploadTourInfoFile;
	private Date startOperation;
	private Date endOperation;
	private User whoUploded;
	
	private Integer insertCount=0;
	private Integer updateCount=0;
	private Integer deleteCount=0;
	private Integer countXml=0;
	private Integer rejectedXmlMd5=0;
	
	private String brandCode;
	private boolean uploadStatusUpdated=false;
	
	private ExtraPermissionChecker extraPermissionChecker;
			
	private TIMessage lastError=null;
	private Set<Long> idsCrSavedOrUpdated=Sets.newTreeSet(); // used in mergeUplodTourInfo
		
	public OperationStatus(UploadTourInfoFile tourInfoHistory,ExtraPermissionChecker extraPermissionChecker){	
		Preconditions.checkArgument(tourInfoHistory.getStatus()!=null);
		this.uploadTourInfoFile=tourInfoHistory;	
		this.extraPermissionChecker=extraPermissionChecker;
		init();
	}

	public OperationStatus() {
		init();
	}
	
	public void addMessage(String productCode,Throwable e,String content,TourInfoMessages msg,Object ... params){
		messageManager.addMessage(productCode,new TIMessage(msg,TourInfoMessages.getMessage(msg, params),e,content));
		updateStatus(msg);
		if(msg.getCode().contains("ERR"))
		{
			lastError=new TIMessage(msg,TourInfoMessages.getMessage(msg, params),e,content);
		}
	}
		
	/*Default status == PROCESSING*/
	private void updateStatus(TourInfoMessages msg){
		
		if (status == UploadTourInfoFileStatus.FAIL)
			return;

		if (msg.getCode().contains("ERR")) {
			status = UploadTourInfoFileStatus.FAIL;
		}

		else if (msg.getCode().contains("WRN")) {
			status = UploadTourInfoFileStatus.WARNING;
		}
	}
		
	public void init() {
		this.messageManager = new MessageManager(this);
		this.startOperation = new Date();
		this.status=uploadTourInfoFile.getStatus();
	}
	

	
	public void finish() {
		this.endOperation=new Date();			
		if(UploadTourInfoFileStatus.PRE_PROCESSING!=status){			
			if (status == UploadTourInfoFileStatus.PROCESSING) {
				uploadTourInfoFile.setStatus(UploadTourInfoFileStatus.SUCCESS);
				status=UploadTourInfoFileStatus.SUCCESS;
			}
			else {
				uploadTourInfoFile.setStatus(status);
			}
			
			if(status==UploadTourInfoFileStatus.FAIL){
				uploadTourInfoFile.setContentRepositories(null);
				idsCrSavedOrUpdated.clear();
			}
			
			messageManager.createTIComments();			
		}
		else{
			uploadTourInfoFile.setStatus(status);
		}
	}
	
	public String getLastErrorMessage() {
		return lastError.getMessage();
	}
	
	
	public void addInsert() {
		insertCount++;
	}

	public void addUpdate() {
		updateCount++;
	}

	public void addCountXml() {
		countXml++;
	}

	public void addRejectedXmlMd5() {
		rejectedXmlMd5++;
	}
	
	public Date getStartOperation() {
		return startOperation;
	}

	public Date getEndOperation() {
		return endOperation;
	}


	public int getInsertCount() {
		return insertCount;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public UploadTourInfoFile getUploadTourInfoFile() {
		return uploadTourInfoFile;
	}

	public UploadTourInfoFileStatus getStatus() {
		return status;
	}

	public void setStatus(UploadTourInfoFileStatus status) {
		this.status = status;
	}

	public Integer getCountXml() {
		return countXml;
	}

	public void setUploadTourInfoFile(UploadTourInfoFile uploadTourInfoFile) {
		this.uploadTourInfoFile = uploadTourInfoFile;
	}
	public Integer getDeleteCount() {
		return deleteCount;
	}
	public void setDeleteCount(Integer deleteCount) {
		this.deleteCount = deleteCount;
	}
	public ExtraPermissionChecker getExtraPermissionChecker() {
		return extraPermissionChecker;
	}
	public MessageManager getMessageManager() {
		return messageManager;
	}
	public Integer getRejectedXmlMd5() {
		return rejectedXmlMd5;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
		getExtraPermissionChecker().setBrandCode(brandCode);		
	}
	public boolean isUploadStatusUpdated() {
		return uploadStatusUpdated;
	}
	public void setUploadStatusUpdated(boolean uploadStatusUpdated) {
		this.uploadStatusUpdated = uploadStatusUpdated;
	}
	public User getWhoUploded() {
		return whoUploded;
	}
	public void setWhoUploded(User whoUploded) {
		this.whoUploded = whoUploded;
	}

	public TIMessage getLastError() {
		return lastError;
	}

	public JobStatusChecker getJobStatusChecker() {
		return jobStatusChecker;
	}

	public void setJobStatusChecker(JobStatusChecker jobStatusChecker) {
		this.jobStatusChecker = jobStatusChecker;
	}
	
	public boolean isCancelProcess() {

		Preconditions.checkState(jobStatusChecker!=null,"JobStatusChecker is not initialize");
		return jobStatusChecker.isCancelProcess();
	}
	
	public boolean isCancelOrInactiveProcess() {
		
		Preconditions.checkState(jobStatusChecker!=null,"JobStatusChecker is not initialize");
		return jobStatusChecker.isCancelOrInactiveProcess();
	}
	
	public JobStatus getJobStatus(){
		Preconditions.checkState(jobStatusChecker!=null,"JobStatusChecker is not initialize");
		return jobStatusChecker.getJobStatus();
	}

	public Set<Long> getIdsCrSavedOrUpdated() {
		return idsCrSavedOrUpdated;
	}

	public void setIdsCrSavedOrUpdated(Set<Long> idsCrSavedOrUpdated) {
		this.idsCrSavedOrUpdated = idsCrSavedOrUpdated;
	}	
}
