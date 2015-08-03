package com.ttc.ch2.ui.moduls.tour.common;

import java.util.List;

import com.google.common.collect.Lists;
import com.ttc.ch2.bl.upload.UploadService;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.AbstractFilterPagingListModel;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;


public class PagingUploadFileListModel extends AbstractFilterPagingListModel<UploadTourInfoFile> {
	
	private static final long serialVersionUID = -1070933550377639125L;

	private static final int PAGE_SIZE=20;
	
	private UploadService uploadService;	
	private List<Brand> permissionBrands;
		
	public PagingUploadFileListModel(UploadService uploadTourService,QueryCondition condition,UploadTourInfoFile filter) {
		super(0, PAGE_SIZE,filter);
		this.uploadService=uploadTourService;	
		
		UserGui user=(UserGui) SessionHelper.getAttributeFromUserContext(UserContext.UserContextStaticName.LOGED_USER);
		this.permissionBrands=Lists.newArrayList(user.getBrands());
		loadData(condition);
	}
	
	public PagingUploadFileListModel(UploadService uploadTourInfoDAO,QueryCondition condition,UploadTourInfoFile filter,int activePage) {
		super(activePage, PAGE_SIZE,filter);
		this.uploadService=uploadTourInfoDAO;	
		UserGui user=(UserGui) SessionHelper.getAttributeFromUserContext(UserContext.UserContextStaticName.LOGED_USER);
		this.permissionBrands=Lists.newArrayList(user.getBrands());
		loadData(condition);
	}
	

	@Override
	public int getTotalSize() {
		return uploadService.getUploadTourInfoCount(filter,permissionBrands);
	}

	@Override
	protected List<UploadTourInfoFile> getPageData(QueryCondition conditions, UploadTourInfoFile filter) {
		return uploadService.getUploadTourInfoList(conditions, filter,permissionBrands);
	}

	@Override
	protected void storeSortCondition(QueryCondition condition) {
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_UPLOAD, condition);
		
	}
}
