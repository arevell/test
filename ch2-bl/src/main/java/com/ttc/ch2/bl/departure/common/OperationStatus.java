package com.ttc.ch2.bl.departure.common;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.elasticsearch.common.collect.Sets;

import com.google.common.base.Preconditions;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.common.JobCoordinator;
import com.ttc.ch2.common.enums.SystemDirection;
import com.ttc.ch2.domain.departure.TourDepartureHistory;
import com.ttc.ch2.domain.departure.TourDepartureHistory.TourDepartureStatus;
import com.ttc.ch2.domain.export.CRExport;
import com.ttc.ch2.domain.jobs.QuartzJob;
import com.ttc.ch2.domain.jobs.QuartzJob.JobStatus;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.search.export.IndexSynchronizerVO;

public class OperationStatus implements Serializable {
				
	private static final long serialVersionUID = -7807994931476572052L;
	
	private QuartzJob.JobName jobName;
		
	private MessagesManager messageManager=null;
	
	private TourDepartureStatus status=TourDepartureStatus.OPERATION_IN_PROGESS;
		
	private Map<Date,IndexSynchronizerVO> indexMap;	
	private Map<Date,FileCollectVO> fileCollectMap;
	private JobStatusChecker jobStatusChecker;
	private JobCoordinator jobCoordinator;
	private TourDepartureHistory tourDepartureHistory;
	private QuartzJobHistory quartzJobHistory;
	private CRExport crExport;
	private String user;
	
	private Date startOperation;
	private Date endOperation;
	
	private Integer importCount=0;	
	private Integer insertCount=0;
	private Integer updateCount=0;
	private Integer rejectedCountMd5=0;
	private Integer rejectedCountDeparture=0;
	private Integer rejectedInvalidSchema=0;	
	private Integer tourWithOutCheckSum=0;
	
	private  SystemDirection systemDirection;
	
	private Integer deletedCount=0;
	private String currentBrand;
	
	private Set<Long> crSavedOrUpdateForBrand;
	private Set<Long> crRejctedMd5ForBrand;
	private Set<Long> crUnsavedForBrand;
	
	private StopWatch tropicsComunicationWatch=null;
	
	public OperationStatus()
	{			
		init();
	}
	
	public TDMessage addException(String brandCode,String sellingCompanyCode,String productCode, Throwable e,Date time,Long duration,TropicSynchronizeMessages msg,Object ... params)
	{
		TDMessage tdMsg=new TDMessage(brandCode,sellingCompanyCode,productCode,msg,TropicSynchronizeMessages.getMessage(msg, params),e,duration,time);
		messageManager.addMessage(tdMsg);
		updateStatus(msg);
		return tdMsg;
	}
	
	public TDMessage addException(String sellingCompanyCode,String productCode, Throwable e,Date time,long duration,TropicSynchronizeMessages msg,Object ... params)
	{
		Preconditions.checkState(StringUtils.isNotEmpty(currentBrand), "OperationStatus.addException-> currentBrand is emepty");
		return addException(currentBrand, sellingCompanyCode, productCode, e, time, duration, msg, params);
	}
	
	public TDMessage addMessage(String brandCode,String sellingCompanyCode,String productCode,Date time,Long duration,TropicSynchronizeMessages msg,Object ... params)
	{
		TDMessage tdMsg=new TDMessage(brandCode,sellingCompanyCode,productCode,msg,TropicSynchronizeMessages.getMessage(msg, params),null,duration,time);
		messageManager.addMessage(tdMsg);
		updateStatus(msg);
		return tdMsg;
	}
	
	public TDMessage addMessage(String brandCode,String sellingCompanyCode,String productCode,Date time,TropicSynchronizeMessages msg,Object ... params)
	{
		return addMessage(brandCode, sellingCompanyCode, productCode, time, null, msg, params);
	}
	
	public TDMessage addMessage(String brandCode,String sellingCompanyCode,String productCode,TropicSynchronizeMessages msg,Object ... params)
	{
		return addMessage(brandCode, sellingCompanyCode, productCode, null, null, msg, params);
	}
	
	public TDMessage addMessage(String sellingCompanyCode,String productCode,TropicSynchronizeMessages msg,Object ... params)
	{
		Preconditions.checkState(StringUtils.isNotEmpty(currentBrand), "OperationStatus.addMessage-> currentBrand is emepty");
		return addMessage(currentBrand, sellingCompanyCode, productCode, null, null, msg, params);
	}

	
	/*Default status == SUCCESS_OPERATION_END*/
	private void updateStatus(TropicSynchronizeMessages msg){
		
		if (status == TourDepartureStatus.ERROR_OPERATION_END)
			return;

		if (msg.getCode().contains("ERR")) {
			status = TourDepartureStatus.ERROR_OPERATION_END;
		}

		else if (msg.getCode().contains("WRN")) {
			status = TourDepartureStatus.WARNING_OPERATION_END;
		}
	}
	

	public void init() {		
		this.messageManager=new MessagesManager(this);
		this.startOperation=new Date();	
		tourDepartureHistory=new TourDepartureHistory();
		tourDepartureHistory.setOperationStart(startOperation);
		tourDepartureHistory.setModifiedBy(user);
		tourDepartureHistory.setUpdatedCount(0);
		tourDepartureHistory.setImportedCount(0);
		tourDepartureHistory.setStatus(status);	
		tropicsComunicationWatch=new StopWatch();
	}
	
	
	
	public void finish() {
		
		if(status==TourDepartureStatus.OPERATION_IN_PROGESS)
			status=TourDepartureStatus.SUCCESS_OPERATION_END;
				
		this.endOperation=new Date();
		tourDepartureHistory.setOperationEnd(endOperation);
		tourDepartureHistory.setStatus(status);
		tourDepartureHistory.setUpdatedCount(getUpdateCount()+getInsertCount());
		tourDepartureHistory.setImportedCount(getImportCount());
		
//		to do create TDComments hear
		if(jobName==QuartzJob.JobName.DepartureSynchronizeJob){
		messageManager.createTdiReport();
		}
		else{
			messageManager.createTdeReport();	
		}
	}
	public void addImport() {
		importCount++;
		tourDepartureHistory.setImportedCount(importCount);
	}

	public void addUpdate() {
		updateCount++;
		tourDepartureHistory.setUpdatedCount(updateCount);
	}
	
	public void addInsert() {
		insertCount++;
	}

	public void addTourWithOutCheckSum(int count){
		this.tourWithOutCheckSum+=count;
	}
	
	public void addRejectMd5() {
		rejectedCountMd5++;
	}

	public void addRejectedCountDeparture() {
		rejectedCountDeparture++;
	}
	
	public void addRejectedInvalidSchema() {
		rejectedInvalidSchema++;
	}

	public void setDeletedCount(int count) {
		deletedCount += count;
	}
	

	public TourDepartureStatus getStatus() {
		return status;
	}

	
	public void setStatus(TourDepartureStatus status) {
		this.status = status;
	}

	public TourDepartureHistory getTourDepartureHistory() {
		return tourDepartureHistory;
	}

	public Date getStartOperation() {
		return startOperation;
	}

	public Date getEndOperation() {
		return endOperation;
	}

	public int getImportCount() {
		return importCount;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public Integer getInsertCount() {
		return insertCount;
	}

	public String getCurrentBrand() {
		return currentBrand;
	}

	public void setCurrentBrand(String currentBrand) {
		this.currentBrand = currentBrand;
	}

	public MessagesManager getMessageManager() {
		return messageManager;
	}

	public Integer getRejectMd5Count() {
		return rejectedCountMd5;
	}
	
	public Integer getDeleteCount() {
		return deletedCount;
	}

	public Set<Long> getCrNotClearedPerBrand() {
		Set<Long> result=Sets.newTreeSet();
		result.addAll(crRejctedMd5ForBrand);
		result.addAll(crSavedOrUpdateForBrand);		
		result.addAll(crUnsavedForBrand);		
		return result;
	}


	public QuartzJobHistory getQuartzJobHistory() {
		return quartzJobHistory;
	}
	

	public Integer getRejectedCountDeparture() {
		return rejectedCountDeparture;
	}

	public CRExport getCrExport() {
		return crExport;
	}

	public void setCrExport(CRExport creExport) {
		this.crExport = creExport;
	}
		
	public void setTourDepartureHistory(TourDepartureHistory tourDepartureHistory) {
		this.tourDepartureHistory = tourDepartureHistory;
	}
	
	public void setQuartzJobHistory(QuartzJobHistory quartzJobHistory) {
		this.quartzJobHistory = quartzJobHistory;
	}
	
	public  OperationStatus createOpStatusForThread(){		
		OperationStatus opStatus=new OperationStatus();
		opStatus.init();
		opStatus.setTourDepartureHistory(null);
		opStatus.setQuartzJobHistory(null);
		opStatus.setCrExport(null);
		return opStatus;
	}
	
	public void margeOpStatus(OperationStatus opStatus){
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public Set<Long> getCrSavedOrUpdateForBrand() {
		return crSavedOrUpdateForBrand;
	}

	public void setCrSavedOrUpdateForBrand(Set<Long> crSavedOrUpdateBrand) {
		this.crSavedOrUpdateForBrand = crSavedOrUpdateBrand;
	}

	public Set<Long> getCrRejctedMd5ForBrand() {
		return crRejctedMd5ForBrand;
	}

	public void setCrRejctedMd5ForBrand(Set<Long> crRejctedMd5Brand) {
		this.crRejctedMd5ForBrand = crRejctedMd5Brand;
	}

	public Set<Long> getCrUnsavedForBrand() {
		return crUnsavedForBrand;
	}

	public void setCrUnsavedForBrand(Set<Long> crUnsavedForBrand) {
		this.crUnsavedForBrand = crUnsavedForBrand;
	}
		
	public JobCoordinator getJobCoordinator() {
		return jobCoordinator;
	}

	public void setJobCoordinator(JobCoordinator jobCoordinator) {
		this.jobCoordinator = jobCoordinator;
	}

	public Map<Date, IndexSynchronizerVO> getIndexMap() {
		if(indexMap == null)
			indexMap = new HashMap<Date,IndexSynchronizerVO>();
		return indexMap;
	}

	public void setIndexMap(Map<Date, IndexSynchronizerVO> indexMap) {
		this.indexMap = indexMap;
	}

	public Map<Date, FileCollectVO> getFileCollectMap() {
		return fileCollectMap;
	}

	public void setFileCollectMap(Map<Date, FileCollectVO> fileCollectMap) {
		this.fileCollectMap = fileCollectMap;
	}

	public SystemDirection getSystemDirection() {
		return systemDirection;
	}

	public void setSystemDirection(SystemDirection systemDirection) {
		this.systemDirection = systemDirection;
	}
	
	public Integer getRejectedInvalidSchema() {
		return rejectedInvalidSchema;
	}

	public QuartzJob.JobName getJobName() {
		return jobName;
	}

	public void setJobName(QuartzJob.JobName jobName) {
		this.jobName = jobName;
	}

	public StopWatch getTropicsComunicationWatch() {
		return tropicsComunicationWatch;
	}

	public Integer getTourWithOutCheckSum() {
		return tourWithOutCheckSum;
	}
	
	public boolean needClearDataFromRecReportDataForIndex(){
					
		 if(indexMap.isEmpty())
			 return false;
				
		 if(indexMap.size()>1){
			 // 'indexMap' has partial indexing data			 
			 return true;
		 }
		 
		 IndexSynchronizerVO vo=indexMap.values().iterator().next();
		 if(vo.hasErrors() && vo.getIndexingAll()){
			 return false;
		 }else{
			 return true;
		 }
	}
	
	public boolean needClearDataFromRecReportDataForFileCollect(){
		
		 if(fileCollectMap.isEmpty())
			 return false;
		 
		 TreeSet<Date> keys=Sets.newTreeSet(fileCollectMap.keySet());
		 
		 FileCollectVO vo=fileCollectMap.get(keys.last());
		 		 
		 if(vo.hasErrors())
			 return false;
		 else
			 return true;		
	}
}
