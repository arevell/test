package com.ttc.ch2.bl.message;

import java.util.List;

import com.ttc.ch2.dao.comment.TypeComment;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.domain.common.QueryCondition;

public interface CommentsService {
	
	List<Comment> getCommentList(QueryCondition conditions, Comment filter,Long grCode,TypeComment type) throws CommentsServiceException;	
	int getCommentCount(Comment filter,Long grCode,TypeComment type) throws CommentsServiceException;	
	Comment getCommentById(Long id, TypeComment type)throws CommentsServiceException;
}
