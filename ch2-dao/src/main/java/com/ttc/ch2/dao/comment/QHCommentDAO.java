package com.ttc.ch2.dao.comment;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.common.QueryCondition;

public interface QHCommentDAO extends GenericDAO<QHComment, Long> {


	public List<QHComment> getQHCommentList(QueryCondition conditions, QHComment filter,List<Brand> brands);
	
	public int getQHCommentCount(QHComment filter,List<Brand> brands); 
}
