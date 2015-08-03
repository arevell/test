package com.ttc.ch2.ui.moduls.messages.common;

import java.util.List;

import org.elasticsearch.common.collect.Lists;

import com.google.common.collect.Iterables;
import com.ttc.ch2.bl.message.CommentsService;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.ui.common.AbstractFilterPagingListModel;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;


public class PagingCommentsModel extends AbstractFilterPagingListModel<CommentDec> {
	
	private static final long serialVersionUID = 1266540936158676578L;

	public static final int PAGE_SIZE=20;
	
	private CommentsService service;
		
	public PagingCommentsModel(CommentsService service,QueryCondition condition,CommentDec filter) {
		super(0, PAGE_SIZE,filter);
		this.service=service;		
		loadData(condition);
	}
	
	public PagingCommentsModel(CommentsService userGuiDAO,QueryCondition condition,CommentDec filter,int activePage) {
		super(activePage, PAGE_SIZE,filter);
		this.service=userGuiDAO;	
		loadData(condition);
	}
	
	@Override
	public int getTotalSize() {
		return service.getCommentCount(filter.getCorrectComment(),filter.getGrCode(),filter.getTypeComment());
	}

	@Override
	protected List<CommentDec> getPageData(QueryCondition conditions, CommentDec filter) {		
		List<Comment> comments=service.getCommentList(conditions, filter.getCorrectComment(),filter.getGrCode(), filter.getTypeComment());		
		return Lists.newArrayList(Iterables.transform(comments, new CommentTransformer()));
	}

	@Override
	protected void storeSortCondition(QueryCondition condition) {
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_COMMENTS, condition);		
	}
}
