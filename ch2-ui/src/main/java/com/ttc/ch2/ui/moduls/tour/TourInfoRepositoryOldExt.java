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
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.security.PathType;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.ch2.ui.moduls.tour.common.BaseTourRepositoryExt;
import com.ttc.ch2.ui.moduls.tour.common.PagingContentRepositoryListModel;
import com.ttc.ch2.ui.moduls.tour.services.TourRepositoryExtWeaveService;



/**
 * Confroler for Content hub ->Content Repository tour info -> Version old / Version new
 * */
@Component("TourInfoRepositoryOldExt")
@Scope("request")
public class TourInfoRepositoryOldExt  extends BaseTourRepositoryExt
{	
	private static final Logger logger=LoggerFactory.getLogger(TourInfoRepositoryOldExt.class);

    @Inject
    private TourRepositoryExtWeaveService service;
    
	private String panelTitle;
	@Init
	public void init() throws Exception
	{		 		
		logger.trace("TourInfoRepositoryOldExt:init-start");
		initialize();
		panelTitle=Labels.getLabel("tour.tour_info_repository.main_panel_title")+" "+brand.getBrandName();
		logger.trace("TourInfoRepositoryOldExt:init-end");
	}
	
	@Command("onPagingList")
	@NotifyChange("listModel")
	public void onPagingList(BindContext ctx)
	{		
		logger.trace("TourInfoRepositoryOldExt:onPagingList-start");
		PagingEvent event = (PagingEvent) ctx.getTriggerEvent();				
		QueryCondition condition=(QueryCondition) SessionHelper.getSession().getAttribute(UserContextStaticName.SORT_CONDITION_CR);
		listModel=new PagingContentRepositoryListModel(contentRepositoryService,condition,getDefaultFilter(FunctionType.CR_VIEW_V1),event.getActivePage(),getRepositoryStatus());		
		logger.trace("TourInfoRepositoryOldExt:onPagingList-end");
	}
	
	@Command("download")
	public void download(@BindingParam("element") ContentRepository selected)
	{
		logger.trace("TourInfoRepositoryOldExt:download-start");
		service.downloadInfoOldExt(selected);
		logger.trace("TourInfoRepositoryOldExt:download-end");
	}
	
	@Override
	protected RepositoryStatus[] getRepositoryStatus() {
		
		return new RepositoryStatus[]{ RepositoryStatus.TIandTD,RepositoryStatus.TourInfoOnly};
	}


	public String generateLink(ContentRepository selected){		
		//http://localhost:7070/ch2-ui/tour_info/CH/?token=ch
		return "/"+PathType.TOUR_INFO.getPartPath()+"/"+brandCode+"/"+selected.getTourCode()+".xml?token="+SecurityHelper.getToken()+"&zk=true";
	}

	public String getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
	}
}
