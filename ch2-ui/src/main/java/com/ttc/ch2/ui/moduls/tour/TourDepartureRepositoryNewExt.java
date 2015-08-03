package com.ttc.ch2.ui.moduls.tour;

import javax.inject.Inject;

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
import org.zkoss.zul.event.PagingEvent;

import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.PathType;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.ch2.ui.moduls.tour.common.BaseTourRepositoryExt;
import com.ttc.ch2.ui.moduls.tour.common.PagingContentRepositoryListModel;
import com.ttc.ch2.ui.moduls.tour.services.TourRepositoryExtWeaveService;



/**
 * Confroler for Content hub ->Content Repository tour departure -> Version old / Version new
 * */
@Component("TourDepartureRepositoryNewExt")
@Scope("request")
public class TourDepartureRepositoryNewExt  extends BaseTourRepositoryExt
{	
	private static final Logger logger=LoggerFactory.getLogger(TourDepartureRepositoryNewExt.class);
    private static final Logger activityLogger = LoggerFactory.getLogger("ch2.activity.TourInfoRepositoryOld");

    @Inject
    private TourRepositoryExtWeaveService service;
    
	private String panelTitle;
	@Init
	public void init() throws Exception
	{		 		
		logger.trace("TourDepartureRepositoryNewExt:init-start");
		initialize();		
		panelTitle=Labels.getLabel("tour.tour_departure_repository.main_panel_title")+" "+brand.getBrandName();	    
		logger.trace("TourDepartureRepositoryNewExt:init-end");
	}
	
	@Command("onPagingList")
	@NotifyChange("listModel")
	public void onPagingList(BindContext ctx)
	{		
		logger.trace("TourDepartureRepositoryNewExt:onPagingList-start");
		PagingEvent event = (PagingEvent) ctx.getTriggerEvent();	
		
		QueryCondition condition=(QueryCondition) SessionHelper.getSession().getAttribute(UserContextStaticName.SORT_CONDITION_CR);
		listModel=new PagingContentRepositoryListModel(contentRepositoryService,condition,getDefaultFilter(FunctionType.CR_VIEW_V3),event.getActivePage(),getRepositoryStatus());
		logger.trace("TourDepartureRepositoryNewExt:onPagingList-end");
	}
	
	@Command("download")
	public void download(@BindingParam("element") ContentRepository selected)
	{
		logger.trace("TourDepartureRepositoryNewExt:download-start");	
		service.downloadDepartureNewExt(selected);		
        activityLogger.info("USER: {} downloaded file: {}", SecurityHelper.getLoginSilent(), selected.getTdFileName());
		logger.trace("TourDepartureRepositoryNewExt:download-end");
	}
	
	@Override
	protected RepositoryStatus[] getRepositoryStatus() {		
		return new RepositoryStatus[]{ RepositoryStatus.TIandTD,RepositoryStatus.TourDepartureOnly};
	}
	
	public String generateLink(ContentRepository selected){		
		//http://localhost:7070/ch2-ui/tour_info/CH/?token=ch
		return "/"+PathType.TOUR_DEPARTURE.getPartPath()+"/"+brandCode+"/V3/"+selected.getTourCode()+".xml?token="+SecurityHelper.getToken();
	}

	public String getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
	}
}
