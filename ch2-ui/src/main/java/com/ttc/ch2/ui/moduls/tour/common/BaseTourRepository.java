package com.ttc.ch2.ui.moduls.tour.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.zkoss.bind.SimpleForm;
import org.zkoss.zul.Filedownload;

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
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.security.UserContext;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;

public abstract class BaseTourRepository {

	protected ContentRepository filter;
	protected PagingContentRepositoryListModel listModel;	
	protected TourRepositoryForm form;
	

	
	@Inject
	protected ContentRepositoryService contentRepositoryService; 
	
	protected abstract RepositoryStatus[] getRepositoryStatus();
	
	
	protected ContentRepository getContentRepositoryWithXML(String tourCode,String brandCode) {
		return contentRepositoryService.findByTourCodeWithXML(tourCode,brandCode);
	}
	
	protected void downloadOperation( String xmlcontent,String xmlFileName)
	{
		InputStream is=null;
		try {				
			is = new ByteArrayInputStream(xmlcontent.getBytes("UTF-8"));
			if (is != null)
	            Filedownload.save(is, "text/xml", xmlFileName);
		} catch (UnsupportedEncodingException e) {
			throw new CH2Exception(xmlFileName+" encoding problem" );
		}
	}
	
	
	protected void searchTable(SimpleForm form2) {
		if (form.getSelectedBrand() != null && form.getSelectedBrand().getId() != null) {
			filter.getBrands().clear();
			filter.getBrands().add(form.getSelectedBrand());
		} else {
			filter.getBrands().addAll(((UserGui) SessionHelper.getAttributeFromUserContext(UserContext.UserContextStaticName.LOGED_USER)).getBrands());
		}
		
		
		String tourCodeValue=(String) form2.getField("tourCode");		
		if (StringUtils.isNotEmpty(tourCodeValue)) {
			filter.setTourCode(tourCodeValue);
		} else {
			filter.setTourCode(null);
		}
		
		QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_CR);
		listModel=new PagingContentRepositoryListModel(contentRepositoryService,condition,filter,0,getRepositoryStatus());
	}
	
	protected void initializeForm()
	{
		filter=new ContentRepository();
		form=new TourRepositoryForm();				
		Set<Brand> userBrands=((UserGui)SessionHelper.getAttributeFromUserContext(UserContext.UserContextStaticName.LOGED_USER)).getBrands();		
		List<Brand> brands=Lists.newArrayList(userBrands);
		Collections.sort(brands, new OrderingBrandByCode());
		filter.getBrands().add(brands.iterator().next());		
		form.setBrands(brands);
		form.setSelectedBrand(brands.iterator().next());
	}
	
	protected void initializeListModel(){
		QueryCondition condition=new QueryCondition(0, PagingContentRepositoryListModel.PAGE_SIZE,new SortCondition("tourCode", Direction.ASC));		
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_CR, condition);
		listModel=new PagingContentRepositoryListModel(contentRepositoryService,condition,filter,getRepositoryStatus());
	}
	
	
	protected void clearForm(){				
		Brand brandSelected=form.getSelectedBrand();
		initializeForm();
		form.setSelectedBrand(brandSelected);
		filter.getBrands().clear();
		filter.getBrands().add(form.getSelectedBrand());		
		initializeListModel();
	}


	public ContentRepository getFilter() {
		return filter;
	}


	public void setFilter(ContentRepository filter) {
		this.filter = filter;
	}


	public PagingContentRepositoryListModel getListModel() {
		return listModel;
	}


	public void setListModel(PagingContentRepositoryListModel listModel) {
		this.listModel = listModel;
	}


	public TourRepositoryForm getForm() {
		return form;
	}


	public void setForm(TourRepositoryForm form) {
		this.form = form;
	}
	
}
