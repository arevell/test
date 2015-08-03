package com.ttc.ch2.ui.moduls.tour;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;

import com.google.common.collect.Lists;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.common.ordering.OrderingBrandByCode;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.Ch2Properties;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.exceptions.PermissionException;
import com.ttc.ch2.ui.common.exceptions.SessionIncorrectException;
import com.ttc.ch2.ui.moduls.tour.common.BrandCbxDec;
import com.ttc.common.params.ParamsUtils;

/**
 * Confroler for Content hub -> Easy View in menu
 * */
@Component("TourMatchStatus")
@Scope("request")
public class TourMatchStatus 
{
	private static final Logger logger=LoggerFactory.getLogger(TourMatchStatus.class);
	
	private String pathApp;
	private String selectedBrandName;
	private ContentRepository  selected;
	private List<ContentRepository> rows;	
	private ContentRepository filter;	
	private SortCondition sortCondition=null;
	
	private String onlyOneBrandName;
	private BrandCbxDec brandCbxDec;
	private String brandFilterAsString;
//	private Brand brandFilter;
	private List<Brand> brandList=Lists.newArrayList();
	private ListModel<String> chooseBrand;
	
	
	
	@Inject
	private ContentRepositoryService contentRepositoryService;
	
	@Inject
	private Ch2Properties ch2Properties;
		
	@Init(superclass=true)
	public void init() { 
		logger.trace("TourMatchStatus:init-start");
		pathApp=StringUtils.isNotBlank(ch2Properties.getAppName()) ? "/"+ch2Properties.getAppName(): "" ;
		if (SessionHelper.getAttributeFromUserContext(TourMatchStatus.class.getSimpleName() + "filter") != null) {
			filter = (ContentRepository) SessionHelper.getAttributeFromUserContext(TourMatchStatus.class.getSimpleName() + "filter");
		} else {
			filter=new ContentRepository();
			SessionHelper.putAttributeToUserContext(TourMatchStatus.class.getSimpleName() + "filter", this.filter);
		}
		if (SessionHelper.getAttributeFromUserContext(TourMatchStatus.class.getSimpleName() + "sortCondition") != null) {
			sortCondition = (SortCondition) SessionHelper.getAttributeFromUserContext(TourMatchStatus.class.getSimpleName() + "sortCondition");
		} else {
			this.sortCondition = new SortCondition("tourCode", Direction.ASC);
			SessionHelper.putAttributeToUserContext(TourMatchStatus.class.getSimpleName() + "sortCondition", this.sortCondition);
		}
				
		UserGui loggedUser=SessionHelper.getLoggedUser();
		Set<Brand> brands=loggedUser.getBrands();		
		if (!brands.isEmpty()) {
			brandList = Lists.newArrayList(brands);
			Collections.sort(brandList, new OrderingBrandByCode());
			if (brands.size() == 1)
			{
				
				onlyOneBrandName = brands.iterator().next().getBrandName();
				selectedBrandName =onlyOneBrandName;
			}
			else
			{
				if(StringUtils.isEmpty(selectedBrandName))
					selectedBrandName=brandList.get(0).getBrandName();
			}			
		}
		
		if (SessionHelper.getAttributeFromUserContext(TourMatchStatus.class.getSimpleName() + "brandFilterAsString") != null) {
			brandFilterAsString = (String) SessionHelper.getAttributeFromUserContext(TourMatchStatus.class.getSimpleName() + "brandFilterAsString");
		} else {			
			this.brandFilterAsString=brandList.get(0).getBrandName();
			SessionHelper.putAttributeToUserContext(TourMatchStatus.class.getSimpleName() + "brandFilterAsString", this.brandFilterAsString);
		}
		
		
		
		brandCbxDec=new BrandCbxDec(brandList);
		chooseBrand=new ListModelArray<String>(brandCbxDec.getValues());
		handlerRequest();							
		logger.trace("TourMatchStatus:init-end");
	}

	
	@Command
	public void onShowDetalis(@BindingParam("element") ContentRepository selected){
		logger.trace("TourMatchStatus:onShowDetalis-start");
	    Executions.sendRedirect(Ch2URIs.TOUR_INF_DEP_VIEW.getPath()+"?param="+ParamsUtils.getInstance().addParam("id", selected.getId()).encodeAllParams());
	    logger.trace("TourMatchStatus:enclosing_method-end");
	}
	
	@Command
	@NotifyChange({"rows","selectedBrandName"})
	public void onSearch()
	{
		logger.trace("TourMatchStatus:onSearch-start");
//		if(StringUtils.isEmpty(filter.getTourCode()))
//			filter.setTourCode(null);
						
		SessionHelper.putAttributeToUserContext(TourMatchStatus.class.getSimpleName() + "filter", this.filter);
		Executions.sendRedirect(Ch2URIs.TOUR_MATCH_STATUS_HTML.getPath());
		logger.trace("TourMatchStatus:onSearch-end");
	}
	
	@Command
    @NotifyChange({"brandFilter","selectedBrandName"})
    public void changeFilter() {
    	logger.trace("TourMatchStatus:changeFilter-start");
    	SessionHelper.putAttributeToUserContext(TourMatchStatus.class.getSimpleName() + "brandFilterAsString", this.brandFilterAsString);
    	logger.trace("TourMatchStatus:paramsHandle-end");
    }
	
	@Command
	@NotifyChange({"rows","selectedBrandName"})
	public void onClear()
	{
		logger.trace("TourMatchStatus:onClear-start");		
		filter.setTourCode(null);	
		onSearch();		
		logger.trace("TourMatchStatus:onClear-end");
	}
	
	
	private void handlerRequest()
	{
		if(brandFilterAsString!=null && brandCbxDec.getValueByString(brandFilterAsString)!=null)
		{
			filter.getBrands().clear();
			Brand b=brandCbxDec.getValueByString(brandFilterAsString);			
			filter.getBrands().add(b);		
			selectedBrandName =b.getBrandName();
			
		}
		else
		{
			filter.getBrands().clear();
			filter.getBrands().addAll(brandList);
		}
		
		String param=Executions.getCurrent().getParameter("param");
		if (StringUtils.isNotBlank(param)) 		
		{
			Map<String,String> params = ParamsUtils.getInstance().decodeAllParam(param);				
			String sortColumn=params.get("sort");
			String direction=params.get("direction");	
			createSortCondition(sortColumn, direction);						
			rows=contentRepositoryService.getContentRepositoriesList(buildQueryCondition(),filter,RepositoryStatus.TIandTD,RepositoryStatus.TourInfoOnly,RepositoryStatus.TourDepartureOnly);
		}
		else
		{
			this.sortCondition=new SortCondition("tourCode",Direction.ASC);
			rows = contentRepositoryService.getContentRepositoriesList(buildQueryCondition(),filter,RepositoryStatus.TIandTD,RepositoryStatus.TourInfoOnly,RepositoryStatus.TourDepartureOnly);
		}
	}
	
	private QueryCondition buildQueryCondition()
	{
		QueryCondition condition=new QueryCondition(-1,-1);
			if(sortCondition.getColumnName().equals("tourCode"))
				condition.getSortConditions().add(this.sortCondition);
		else {
			condition.getSortConditions().add(this.sortCondition);
			condition.getSortConditions().add(new SortCondition("tourCode", Direction.ASC));

		}
		return condition;
	}

	private void createSortCondition(String sortColumn,String direction)
	{	
		String column = "";
		if ("TC".equals(sortColumn)) {
			column = "tourCode";
		} else if ("TI".equals(sortColumn)) {
			column = "isTourInfoAvailable";
		} else if ("TI_date".equals(sortColumn)) {
			column = "tourInfoModified";
		} else if ("TD".equals(sortColumn)) {
			column = "isTourDepartureAvailable";
		} else if ("TD_date".equals(sortColumn)) {
			column = "tourDepartureModified";
		} else if ("STAT".equals(sortColumn)) {
			column = "status";
		}
		
		Direction choosenDirection=Direction.ASC;
		SortCondition oldSortCondition=(SortCondition) SessionHelper.getAttributeFromUserContext(TourMatchStatus.class.getSimpleName()+"sortCondition");
		if(oldSortCondition!=null){
			if(oldSortCondition.getColumnName().equals(column)){
				choosenDirection=oldSortCondition.getDirection()==Direction.ASC ? Direction.DESC : Direction.ASC;
			}
			else if(column.equals("isTourInfoAvailable") || column.equals("isTourDepartureAvailable")){
				choosenDirection=Direction.DESC;
			}
		}
		
		
		this.sortCondition=new SortCondition(column,choosenDirection);
		SessionHelper.putAttributeToUserContext(TourMatchStatus.class.getSimpleName()+"sortCondition", this.sortCondition);
	}
		
	//  geters setters	
	public ContentRepository getSelected() {
		return selected;
	}

	public void setSelected(ContentRepository selected) {
		this.selected = selected;
	}

	public List<ContentRepository> getRows() {
		return rows;
	}

	public void setRows(List<ContentRepository> rows) {
		this.rows = rows;
	}


	public ContentRepository getFilter() {
		return filter;
	}

	public void setFilter(ContentRepository filter) {
		this.filter = filter;
	}

	public SortCondition getSortCondition() {
		return sortCondition;
	}
	
	public List<Brand> getBrandList() {
		return brandList;
	}


	public void setBrandList(List<Brand> brandList) {
		this.brandList = brandList;
	}


	public String getOnlyOneBrandName() {
		return onlyOneBrandName;
	}


	public void setOnlyOneBrandName(String onlyOneBrandName) {
		this.onlyOneBrandName = onlyOneBrandName;
	}
	
	public int getBrandListSize()
	{
		return brandList.size();
	}

	public String getSelectedBrandName() {
		return selectedBrandName;
	}


	public String getBrandFilterAsString() {
		return brandFilterAsString;
	}


	public void setBrandFilterAsString(String brandFilterAsString) {
		this.brandFilterAsString = brandFilterAsString;
	}


	public ListModel<String> getChooseBrand() {
		return chooseBrand;
	}


	public String getPathApp() {
		return pathApp;
	}


	public void setPathApp(String pathApp) {
		this.pathApp = pathApp;
	}



}
