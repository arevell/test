package com.ttc.ch2.scheduler.common;

import java.util.EnumMap;

import com.google.common.base.Preconditions;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.common.JobCoordinator;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.domain.RecReportData;
import com.ttc.ch2.scheduler.service.SchedulerForDepExtImportService;
import com.ttc.ch2.search.export.IndexSynchronizerVO;

public class DepartureExtendedCoordinator implements JobCoordinator{

	private SchedulerForDepExtImportService schedulerForDepExtImportService;
	private String brandCode;
	public DepartureExtendedCoordinator(String brandCode) {
		super();
		this.brandCode = brandCode;
		this.schedulerForDepExtImportService = SpringContext.getApplicationContext().getBean(SchedulerForDepExtImportService.class);
	}
	
	@Override
	public void setup(EnumMap<Params, Object> params) {
		
//		Preconditions.checkArgument(params.containsKey(JobCoordinator.Params.FILE_COLLECT_VO) && params.get(JobCoordinator.Params.FILE_COLLECT_VO) instanceof FileCollectVO,"JobCoordinator expect fileCollectVO");
//		Preconditions.checkArgument(params.containsKey(JobCoordinator.Params.INDEX_VO) && params.get(JobCoordinator.Params.INDEX_VO) instanceof IndexSynchronizerVO ,"JobCoordinator expect indexSynchronizerVO");						
		FileCollectVO fVo=(FileCollectVO) params.get(JobCoordinator.Params.FILE_COLLECT_VO);
		IndexSynchronizerVO iVo=(IndexSynchronizerVO) params.get(JobCoordinator.Params.INDEX_VO);
		schedulerForDepExtImportService.setupRecReportData(brandCode,iVo, fVo);
	}
	
	@Override
	public void setupIndexingResult(IndexSynchronizerVO iVo){
		
		Preconditions.checkArgument(iVo!=null);
		
		if(iVo.getIndexingAll()){
			schedulerForDepExtImportService.clearRecReportData(brandCode,RecReportData.Type.IndexSearchingVO);
			
			if(iVo.hasErrors()){
				schedulerForDepExtImportService.setupRecReportData(brandCode,iVo, null);
			}
		}
	}

	@Override
	public void setupFileCollectResult(FileCollectVO iVo) {
		Preconditions.checkArgument(iVo!=null);
		schedulerForDepExtImportService.clearRecReportData(brandCode,RecReportData.Type.FileCollectVO);
		
		if(iVo.hasErrors()){
			schedulerForDepExtImportService.setupRecReportData(brandCode,null,iVo);
		}
		
	}
	
}