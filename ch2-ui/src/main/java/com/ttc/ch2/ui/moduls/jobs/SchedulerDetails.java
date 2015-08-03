package com.ttc.ch2.ui.moduls.jobs;



import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.impl.MessageboxDlg;

import com.ttc.ch2.common.enums.CronExpresion;
import com.ttc.ch2.common.ordering.OrderingBrandByCode;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.scheduler.service.JobVO;
import com.ttc.ch2.scheduler.service.QuartzJobCh2Service;
import com.ttc.ch2.scheduler.service.SchedulerForImportService;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;
import com.ttc.ch2.scheduler.service.SchedulerVO;
import com.ttc.ch2.scheduler.service.StatusOperationService;
import com.ttc.ch2.scheduler.service.StatusOperationVO;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.ch2.ui.moduls.jobs.common.PagingQuartzHistoryListModel;
import com.ttc.ch2.ui.moduls.jobs.common.QuartzJobHistoryForm;
import com.ttc.ch2.ui.moduls.jobs.common.SchedulerForm;

@Component("SchedulerDetails")
@Scope("request")
public class SchedulerDetails {
	
	private static final Logger logger=LoggerFactory.getLogger(SchedulerDetails.class);
	
	@Inject
	private SchedulerForImportService schedulerService; 
	
	@Inject
	private QuartzJobCh2Service quartzJobCh2Service;
	
	@Inject
	private StatusOperationService statusOperationService;
	
	@Inject
	private BrandDAO brandDAO;
		

	private SchedulerForm form;
	private QuartzJobHistoryForm quartzJobHistoryForm;
	
	
			
	// view field	
	private SchedulerVO schedulerVO;
	private List<JobVO> rows;
	private PagingQuartzHistoryListModel listModel;
	private List<String> statusChoose;
	private List<StatusOperationVO> statusRows;
	private boolean disableAllButtons;
	
	private String pbOperationTitle;
	
	@Init
	public void init() throws SchedulerServiceException {				
		logger.trace("SchedulerDetails:init-start");
		try {			
									
			this.disableAllButtons=false;
			this.schedulerVO=schedulerService.getSchedulerData();
			this.rows=schedulerService.getJobList();			
			decorateRows();
			
			this.quartzJobHistoryForm=new QuartzJobHistoryForm();
						
			List<Brand> brands=brandDAO.findAll();
			Collections.sort(brands, new OrderingBrandByCode());
			
			CronExpresion ce=schedulerService.setupInconsistentCronExpresionIfNecessary();			
			this.form=new SchedulerForm(brands,ce);
						
			QuartzJobHistory filter=new QuartzJobHistory();			
			QueryCondition condition=new QueryCondition(0, PagingQuartzHistoryListModel.PAGE_SIZE,new SortCondition("startDate", Direction.DESC));		
			SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_JOB, condition);			
			SessionHelper.putAttributeToUserContext(UserContextStaticName.FORM_LIST_JOB, filter);
			
			this.listModel=new PagingQuartzHistoryListModel(quartzJobCh2Service,condition, filter);			
			statusRows=statusOperationService.getStausOfProcess();
			
		} catch (SchedulerServiceException e) {
			logger.error("Exection on read param scheduler",e);
			throw e;
		}
		logger.trace("SchedulerDetails:init-end");
	}
		
	
	
	@NotifyChange({"schedulerVO","rows","listModel","form"})
	@Command
	public void changeJobTime() throws SchedulerServiceException
	{				
		try {
			if(form.getChgTime()==null)
				return;			
//			schedulerService.changeJobTime(form.getChgTime(),form.getBrandCode());
			
			String  selectedBrand=null;
			if(SchedulerForm.emptyBrand!=form.getSelectedBrand()){
				selectedBrand=form.getSelectedBrand().getCode();				
			}
			schedulerService.changeJobTime(form.getChgTime(),selectedBrand);
			this.schedulerVO=schedulerService.getSchedulerData();
			this.rows=schedulerService.getJobList();
			decorateRows();
			Messagebox.show(Labels.getLabel("common.operation.success"), Labels.getLabel("jobs.scheduler.component.chg_time_window_title"), Messagebox.OK, Messagebox.INFORMATION);
			form.setChgTime(null);
			form.setSelectedBrand(SchedulerForm.emptyBrand);
		} catch (SchedulerServiceException e) {						
			logger.error("Exection on start scheduler",e);
			Messagebox.show(e.getMessage(), Labels.getLabel("jobs.scheduler.component.chg_time_window_title"), Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	
    
	@Command("onPagingList")
	@NotifyChange({"listModel","quartzJobHistoryForm"})
	public void onPagingList(BindContext ctx)
	{		
		logger.trace("SchedulerDetails:onPagingList-start");
		PagingEvent event = (PagingEvent) ctx.getTriggerEvent();			
		QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_JOB);
		QuartzJobHistory filter=(QuartzJobHistory) SessionHelper.getAttributeFromUserContext(UserContextStaticName.FORM_LIST_JOB);
    	listModel=new PagingQuartzHistoryListModel(quartzJobCh2Service,condition,filter,event.getActivePage());		
		logger.trace("SchedulerDetails:onPagingList-end");
	}
	
	 @Command
	 @NotifyChange({"listModel","quartzJobHistoryForm"})
	 public void changeFilter() {
		 	logger.trace("SchedulerDetails:changeFilter-start");
		 	QuartzJobHistory filter=new QuartzJobHistory();
		 	filter.setStartDate(quartzJobHistoryForm.getStartDate());	
		 	filter.setStatus(quartzJobHistoryForm.getJobHistoryStatusFromSelectedValue());
		 	SessionHelper.putAttributeToUserContext(UserContextStaticName.FORM_LIST_JOB, filter);		 	
	    	QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_JOB);
	    	listModel=new PagingQuartzHistoryListModel(quartzJobCh2Service,condition,filter,0);   
	    	logger.trace("SchedulerDetails:changeFilter-end");
	 }
	 
		

	 @NotifyChange({"form","schedulerVO","disableAllButtons","listModel","quartzJobHistoryForm","rows","statusRows"})
	 @Command
	 public void changeCronTime() {					 
		 try {
			 disableAllButtons=true;
			 Messagebox.show(Labels.getLabel("jobs.chgrefreshtime.description") , Labels.getLabel("jobs.chgrefreshtime.title"),Messagebox.OK | Messagebox.CANCEL,Messagebox.EXCLAMATION,new ChangeRefreshTimeListener());	
		 } catch (Exception exception) {
				logger.error("",exception);
			    String msg="";
				if(exception.getCause()!=null){
					msg=exception.getCause().getMessage();
				}
				else{
					msg=exception.getMessage();
				}							
				Messagebox.show(msg,"Error", Messagebox.OK, Messagebox.ERROR);
		}		 
//		 try {
////			 disableAllButtons=true;
//			 schedulerService.setupNewCronExpression(form.getSelectedCronTimeVO().getExpresion());
//			 this.schedulerVO=schedulerService.getSchedulerData();
//			 this.rows=schedulerService.getJobList();
//			 decorateRows();
//			 Messagebox.show(Labels.getLabel("common.operation.success"), Labels.getLabel("jobs.scheduler.component.chg_time_window_title"), Messagebox.OK, Messagebox.INFORMATION);
//		} catch (Exception e) {
//			if(e.getCause()!=null && e.getCause() instanceof ActiveJobException){
//				Messagebox.show(e.getCause().getMessage(), Labels.getLabel("jobs.scheduler.component.chg_time_window_title"), Messagebox.OK, Messagebox.ERROR);	
//			}else{
//				logger.error("Exection on start scheduler",e);
//				Messagebox.show(e.getMessage(), Labels.getLabel("jobs.scheduler.component.chg_time_window_title"), Messagebox.OK, Messagebox.ERROR);				
//			}
//		}		 
	 }
	 
	
	 @Command
	 @NotifyChange({"listModel","quartzJobHistoryForm","rows","statusRows","disableAllButtons"})
	 public void stopJob(@BindingParam("element") JobVO jobVO)
	 {
		 final String brandCode=jobVO.getBrandCode();	
		 try{
			 if(schedulerService.resetAllowed(brandCode)==false)
			 {
				schedulerService.stopJob(brandCode);
				QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_JOB);
				QuartzJobHistory filter=(QuartzJobHistory) SessionHelper.getAttributeFromUserContext(UserContextStaticName.FORM_LIST_JOB);
		    	listModel=new PagingQuartzHistoryListModel(quartzJobCh2Service,condition,filter,0);
		    	rows=schedulerService.getJobList();	
		    	decorateRows();
		    	statusRows=statusOperationService.getStausOfProcess();
			 }
			else{		
				disableAllButtons=true;
				 Messagebox.show(Labels.getLabel("jobs.reset.msg.description") , Labels.getLabel("jobs.reset.msg.title"),Messagebox.OK | Messagebox.CANCEL,Messagebox.EXCLAMATION, 
						 new StopJobListener(brandCode));		 
				 }
		 }catch(Exception exception){
			 logger.error("",exception);
			 String msg="";
				if(exception.getCause()!=null){
					msg=exception.getCause().getMessage();
				}
				else{
					msg=exception.getMessage();
				}							
				Messagebox.show(msg,"Error", Messagebox.OK, Messagebox.ERROR);
		 }		 
	 }
	 
	 
	 private void decorateRows(){
		 
		 for (JobVO row : rows) {
				row.setPbOperationTitle(row.isNeedReset() ? Labels.getLabel("common.pb_reset") : "Stop Job");
				StringBuilder namePb=new StringBuilder("");
				List<Brand> brands=brandDAO.findAll();
				for (Iterator<Brand> iterator = brands.iterator(); iterator.hasNext(); ) {
					Brand brand = iterator.next();
					namePb.append("pbReset_").append(brand.getCode());
					if(iterator.hasNext())
						namePb.append(",");
				}
				
				String pbList=namePb.toString();
				if(StringUtils.isNotEmpty(pbList)){
					pbList+=",self,pbRefresh,submitForm";
				}
				else{
					pbList="self,pbRefresh,submitForm";
				}
				row.setListButtons(pbList);
		 }
	 }
	
	// geters setters
	public SchedulerVO getSchedulerVO() {
		return schedulerVO;
	}

	public void setSchedulerVO(SchedulerVO schedulerVO) {
		this.schedulerVO = schedulerVO;
	}

	public List<JobVO> getRows() {
		return rows;
	}

	public void setRows(List<JobVO> rows) {
		this.rows = rows;
	}

	public SchedulerForm getForm() {
		return form;
	}

	public void setForm(SchedulerForm form) {
		this.form = form;
	}

	public PagingQuartzHistoryListModel getListModel() {
		return listModel;
	}

	public void setListModel(PagingQuartzHistoryListModel listModel) {
		this.listModel = listModel;
	}

	public QuartzJobHistoryForm getQuartzJobHistoryForm() {
		return quartzJobHistoryForm;
	}

	public void setQuartzJobHistoryForm(QuartzJobHistoryForm quartzJobHistoryForm) {
		this.quartzJobHistoryForm = quartzJobHistoryForm;
	}

	public List<String> getStatusChoose() {
		return statusChoose;
	}

	public void setStatusChoose(List<String> statusChoose) {
		this.statusChoose = statusChoose;
	}



	public String getPbOperationTitle() {
		return pbOperationTitle;
	}



	public void setPbOperationTitle(String pbOperationTitle) {
		this.pbOperationTitle = pbOperationTitle;
	}



	public List<StatusOperationVO> getStatusRows() {
		return statusRows;
	}



	public void setStatusRows(List<StatusOperationVO> statusRows) {
		this.statusRows = statusRows;
	}



	public boolean isDisableAllButtons() {
		return disableAllButtons;
	}



	public void setDisableAllButtons(boolean disableAllButtons) {
		this.disableAllButtons = disableAllButtons;
	}
	
	
	class ChangeRefreshTimeListener implements EventListener<Event>{
		
		@Override
		public void onEvent(Event e) throws Exception {
			org.zkoss.zk.ui.Component target=e.getTarget();
			if (target instanceof MessageboxDlg) {
				MessageboxDlg dlg = (MessageboxDlg)target;
				if(org.zkoss.zul.Messagebox.Button.OK == dlg.getResult()){
					try{
						 schedulerService.setupNewCronExpression(form.getSelectedCronTimeVO().getExpresion());						
					}
					catch(Exception exception){							
						String msg="";
						if(exception.getCause()!=null){
							msg=exception.getCause().getMessage();
						}
						else{
							msg=exception.getMessage();
						}							
						Messagebox.show(msg,"Error", Messagebox.OK, Messagebox.ERROR);
					}
					Executions.sendRedirect(Ch2URIs.SCHEDULER.getPath());
				}					
			}				
		}
	}

	
	class StopJobListener implements EventListener<Event>{

		private String brandCode;
		public StopJobListener(String brandCode) {
			super();
			this.brandCode = brandCode;
		}
		
		@Override
		public void onEvent(Event e) throws Exception {
			org.zkoss.zk.ui.Component target=e.getTarget();
			if (target instanceof MessageboxDlg) {
				MessageboxDlg dlg = (MessageboxDlg)target;
				if(org.zkoss.zul.Messagebox.Button.OK == dlg.getResult()){
					try{
						schedulerService.resetStateOfProcessUplodImport(brandCode);
						
					}
					catch(Exception exception){							
						String msg="";
						if(exception.getCause()!=null){
							msg=exception.getCause().getMessage();
						}
						else{
							msg=exception.getMessage();
						}							
						Messagebox.show(msg,"Error", Messagebox.OK, Messagebox.ERROR);
					}
					Executions.sendRedirect(Ch2URIs.SCHEDULER.getPath());
				}					
			}	
			
		}
		
	}
	
}
