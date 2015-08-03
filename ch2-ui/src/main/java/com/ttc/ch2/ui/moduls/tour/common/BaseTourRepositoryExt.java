package com.ttc.ch2.ui.moduls.tour.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Filedownload;

import com.google.common.collect.Maps;
import com.ttc.ch2.bl.contentrepository.ContentRepositoryService;
import com.ttc.ch2.common.AuthorityHelper;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.tour.ContentRepository.RepositoryStatus;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.common.params.ParamsUtils;

public abstract class BaseTourRepositoryExt {

	protected PagingContentRepositoryListModel listModel;
		
	@Inject
	protected ContentRepositoryService contentRepositoryService; 
	
	@Inject
	protected BrandDAO brandDao;
	
	protected abstract RepositoryStatus[] getRepositoryStatus();
	public abstract String generateLink(ContentRepository selected);
	
	protected String user;
	protected String brandCode;
	protected Brand brand;
	protected FunctionType function;
	
	private boolean hasPermissionToViewOldList=false;
	private boolean hasPermissionToViewNewList=false;
	
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
	
	protected void initialize()
	{	
		Map<String,String> params=Maps.newHashMap();
		 if (StringUtils.isNotBlank(Executions.getCurrent().getParameter("param"))) {
				params = ParamsUtils.getInstance().decodeAllParam(Executions.getCurrent().getParameter("param"));
		 }
		 brandCode= params.get("brandCode");
		 if (StringUtils.isBlank(brandCode)) {
				throw new CH2Exception("Request has invalid brandCode:" + brandCode);
		 }
		 String functionStr= params.get("function");
		 if (StringUtils.isBlank(functionStr)) {
			 throw new CH2Exception("Request has invalid pathType:" + functionStr);
		 }		 
		 function=FunctionType.valueOf(functionStr);				 
		 brand=brandDao.findByBrandCode(brandCode);
		 if(brand==null){
				throw new CH2Exception("Cant find brand by code:" + brandCode);		
		 }

		if(function==FunctionType.CR_VIEW_V3)
			{			
			hasPermissionToViewNewList=com.ttc.ch2.common.AuthorityHelper.checkUserHasFunctionPermition(com.ttc.ch2.common.SecurityHelper.getUserCCAPIPrincipal().getUserDb(), com.ttc.ch2.common.FunctionType.CR_VIEW_V3);
			hasPermissionToViewOldList=false;
			}
			else
			{
			hasPermissionToViewOldList=com.ttc.ch2.common.AuthorityHelper.checkUserHasFunctionPermition(com.ttc.ch2.common.SecurityHelper.getUserCCAPIPrincipal().getUserDb(), com.ttc.ch2.common.FunctionType.CR_VIEW_V1);
			hasPermissionToViewNewList=false;
			}	
		 
		QueryCondition condition=new QueryCondition(0, PagingContentRepositoryListModel.PAGE_SIZE,new SortCondition("tourCode", Direction.ASC));
		SessionHelper.getSession().setAttribute(UserContextStaticName.SORT_CONDITION_CR, condition);
		listModel=new PagingContentRepositoryListModel(contentRepositoryService,condition,getDefaultFilter(function),getRepositoryStatus());
		this.user=SecurityHelper.getUserCCAPIPrincipal().getUserDb().getUsername();
	}

	public PagingContentRepositoryListModel getListModel() {
		return listModel;
	}


	public void setListModel(PagingContentRepositoryListModel listModel) {
		this.listModel = listModel;
	}	
	
	protected ContentRepository getDefaultFilter(FunctionType functionType)
	{
		ContentRepository filter=new ContentRepository();
		filter.getBrands().add(brand);		
		UserCCAPI user=SecurityHelper.getUserCCAPIPrincipal().getUserDb();		
		filter.getSellingCompanies().addAll(AuthorityHelper.transformAuthorityforUserCcapi(user).get(functionType));		
		return filter;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public boolean isHasPermissionToViewOldList() {
		return hasPermissionToViewOldList;
	}
	public void setHasPermissionToViewOldList(boolean hasPermissionToViewOldList) {
		this.hasPermissionToViewOldList = hasPermissionToViewOldList;
	}
	public boolean isHasPermissionToViewNewList() {
		return hasPermissionToViewNewList;
	}
	public void setHasPermissionToViewNewList(boolean hasPermissionToViewNewList) {
		this.hasPermissionToViewNewList = hasPermissionToViewNewList;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
