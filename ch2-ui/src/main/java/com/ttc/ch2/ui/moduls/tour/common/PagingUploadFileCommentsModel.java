package com.ttc.ch2.ui.moduls.tour.common;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.ttc.ch2.bl.upload.UploadService;
import com.ttc.ch2.bl.upload.common.TourInfoMessages;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.ui.common.AbstractFilterPagingListModel;


public class PagingUploadFileCommentsModel extends AbstractFilterPagingListModel<TIComment> {
	
	private static final long serialVersionUID = -1070933550377639125L;

	public static final int PAGE_SIZE=5;
	
	private UploadTourInfoFile uploadTourInfoFile;
	private UploadService uploadService;
		
	public PagingUploadFileCommentsModel(UploadService uploadService, UploadTourInfoFile uploadTourInfoFile) {
		super(0, PAGE_SIZE, null);
		this.uploadTourInfoFile=uploadTourInfoFile;	
		this.uploadService=uploadService;
		loadData();
	}
	
	public PagingUploadFileCommentsModel(UploadService uploadService,UploadTourInfoFile uploadTourInfoFile,int activePage) {
		super(activePage, PAGE_SIZE,null);
		this.uploadTourInfoFile=uploadTourInfoFile;	
		this.uploadService=uploadService;
		loadData();
	}
	

	@Override
	public int getTotalSize() {		
		return uploadService.getCommensForTourInfo(uploadTourInfoFile).size();
	}

	@Override
	protected List<TIComment> getPageData(QueryCondition conditions, TIComment filter) {
		
//		Preconditions.checkArgument(uploadTourInfoFile.getName()!=null,"PagingUploadFileCommentsModel.getPageData QueryCondition is null");		
		int count=0;
		List<TIComment> selected=Lists.newArrayList();		
		for (List<TIComment> part : Lists.newArrayList(Iterables.partition(uploadService.getCommensForTourInfo(uploadTourInfoFile), PAGE_SIZE))) {
			if(count==activePage)
			{
				selected=part;
				break;
			}	
			count++;
		}
		
		List<TIComment> reuslt=Lists.newArrayList(selected);
		Collections.sort(reuslt, new SortTIComments());
		
		return reuslt;
	}

	@Override
	protected void storeSortCondition(QueryCondition condition) {
		
	}
	
	
	class SortTIComments extends Ordering<TIComment> {

		@Override
		public int compare(TIComment left, TIComment right) {

			if(left.getMessageCode().equals(TourInfoMessages.SUCCESS_INFO.getCode()) || left.getMessageCode().equals(TourInfoMessages.FAIL_INFO.getCode()))
				return -1000;
			
			if(left.getMessageCode().equals(TourInfoMessages.UPLOAD_STATUS_ERROR.getCode()))
				return 1000;
			if(left.getMessageCode().equals(TourInfoMessages.UPLOAD_STATUS_INFO.getCode()))
				return 1000;
			
			return left.getMessageCode().compareTo(right.getMessageCode());
		}
		
	}
	
	
}
