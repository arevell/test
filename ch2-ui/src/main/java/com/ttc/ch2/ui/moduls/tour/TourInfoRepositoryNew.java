package com.ttc.ch2.ui.moduls.tour;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.SimpleForm;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.event.PagingEvent;

import com.google.common.base.Preconditions;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.ch2.ui.moduls.tour.common.BaseTourRepository;
import com.ttc.ch2.ui.moduls.tour.common.PagingContentRepositoryListModel;



/**
 * Confroler for Content hub ->Content Repository tour info -> Version old / Version new
 * */
@Component("TourInfoRepositoryNew")
@Scope("request")
public class TourInfoRepositoryNew  extends BaseTourRepository
{	
	private static final Logger logger=LoggerFactory.getLogger(TourInfoRepositoryNew.class);
    private static final Logger activityLogger = LoggerFactory.getLogger("ch2.activity.TourInfoRepositoryNew");
	
	
	@Init
	public void init() throws Exception {	 		
		logger.trace("TourInfoRepositoryNew:init-start");
		initializeForm();
		initializeListModel();
		logger.trace("TourInfoRepositoryNew:init-end");
	}
	
	@Command("onPagingList")
	@NotifyChange("listModel")
	public void onPagingList(BindContext ctx) {	
		logger.trace("TourInfoRepositoryNew:onPagingList-start");
		PagingEvent event = (PagingEvent) ctx.getTriggerEvent();		
		QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_CR);
		listModel=new PagingContentRepositoryListModel(contentRepositoryService,condition,filter,event.getActivePage(),getRepositoryStatus());
		logger.trace("TourInfoRepositoryNew:onPagingList-end");
	}
	
	@Command("download")
	public void download(@BindingParam("element") ContentRepository selected) {
		logger.trace("TourInfoRepositoryNew:download-start");
		//in system Cr should have only one brand
		Brand brand=selected.getBrands().iterator().next();	
		ContentRepository cr = getContentRepositoryWithXML(selected.getTourCode(),brand.getCode());
		Preconditions.checkState(StringUtils.isNotEmpty(cr.getXmlContentRepository().get(0).getTourInfoXML()),"ContentRepository don't have NewTourInfoXML");
		downloadOperation(cr.getXmlContentRepository().get(0).getTourInfoXML(), selected.getTiFileName());		
		activityLogger.info("USER: {} downloaded file: {}", SecurityHelper.getLoginSilent(), selected.getTiFileName());
		logger.trace("TourInfoRepositoryNew:download-end");
	}
	
	@Command
	@NotifyChange({"listModel"})
	public void onSearch(@BindingParam("element") SimpleForm form) {
		logger.trace("TourInfoRepositoryNew:onSearch-start");
		searchTable(form);
		logger.trace("TourInfoRepositoryNew:onSearch-end");
	}
	
	@Command
	@NotifyChange({"listModel","form"})
	public void onClear() {
		logger.trace("TourInfoRepositoryOld:onClear-start");
		clearForm();		
		logger.trace("TourInfoRepositoryOld:onClear-end");
	}
	
	@Override
	protected RepositoryStatus[] getRepositoryStatus() {
		return new RepositoryStatus[]{ RepositoryStatus.TIandTD,RepositoryStatus.TourInfoOnly};
	}
	

}
