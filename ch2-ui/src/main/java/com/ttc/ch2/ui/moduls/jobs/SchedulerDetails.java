package com.ttc.ch2.ui.moduls.jobs;



import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.impl.MessageboxDlg;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.ttc.ch2.common.enums.SystemDirection;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.scheduler.service.JobVO;
import com.ttc.ch2.scheduler.service.QuartzSwitcher;
import com.ttc.ch2.scheduler.service.SchedulerService;
import com.ttc.ch2.scheduler.service.SchedulerServiceException;
import com.ttc.ch2.scheduler.service.SchedulerVO;
import com.ttc.ch2.ui.common.config.Ch2URIs;

@Component("SchedulerDetails")
@Scope("request")
public class SchedulerDetails {
	
	private static final Logger logger=LoggerFactory.getLogger(SchedulerDetails.class);
	
	@Inject
	private SchedulerService schedulerService; 
	
	@Inject
	private QuartzSwitcher switcher;
	
	@Value("${scheduler.name}")
	private String schedulerName;
	
	@Inject
	private BrandDAO brandDAO;
	// view field	
	private SchedulerVO schedulerVO;
	private List<JobVO> rows;
	
	private List<String> activeSchedulerThread;
	
	


	@Init
	public void init() throws SchedulerServiceException {				
		logger.trace("SchedulerDetails:init-start");
		try {													
			this.schedulerVO=schedulerService.getSchedulerData();
			this.rows=schedulerService.getAllJobList();			
			decorateRows();
			this.activeSchedulerThread=Lists.newArrayList();
			
			if(StringUtils.isNotEmpty(Executions.getCurrent().getParameter("thread"))){
				fillThreadInfo();	
			}

		} catch (SchedulerServiceException e) {
			logger.error("Exection on read param scheduler",e);
			throw e;
		}
		logger.trace("SchedulerDetails:init-end");
	}
	
	
	 @Command
	 @NotifyChange({"schedulerVO","rows"})
	 public void changeDirection(@BindingParam("element") String direction) {		 

		 final String localDirection=direction;
		 final SchedulerVO localSchedulerVO= this.schedulerVO;
		 
		 Messagebox.show(Labels.getLabel("jobs.direction.msg.description") , Labels.getLabel("jobs.direction.msg.title"),Messagebox.OK | Messagebox.CANCEL,Messagebox.EXCLAMATION, new EventListener<Event>() {			
				@Override
				public void onEvent(Event e) throws Exception {
					org.zkoss.zk.ui.Component target=e.getTarget();
					if (target instanceof MessageboxDlg) {
						MessageboxDlg dlg = (MessageboxDlg)target;
						if(org.zkoss.zul.Messagebox.Button.OK == dlg.getResult()){
							try{
								 switcher.switchToSystem(SystemDirection.valueOf(localDirection));
								 localSchedulerVO.setSystemDirection(localDirection);
								 Executions.sendRedirect(Ch2URIs.SCHEDULER.getPath()+"?tab=1");
							}
							catch(Exception exception){		
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
					}				
				}
			});		 
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
	
	private void fillThreadInfo(){
		activeSchedulerThread=Lists.newArrayList();
		Set<String> localList=new TreeSet<String>();		
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();		
		for (Thread thread : threadSet) {
			if(thread.getThreadGroup().getName().contains(schedulerName) || thread.getName().contains(schedulerName))
				activeSchedulerThread.add("Group:"+thread.getThreadGroup().getName()+" Id:"+thread.getId()+" Name:"+thread.getName()+" State:"+thread.getState());
			else
				localList.add("Group:"+thread.getThreadGroup().getName()+" Id:"+thread.getId()+" Name:"+thread.getName()+" State:"+thread.getState());
		}
		
		Collections.sort(activeSchedulerThread, Ordering.natural().nullsLast());
	}
	
	public List<String> getActiveSchedulerThread() {
		return activeSchedulerThread;
	}

	public void setActiveSchedulerThread(List<String> activeSchedulerThread) {
		this.activeSchedulerThread = activeSchedulerThread;
	}
}
