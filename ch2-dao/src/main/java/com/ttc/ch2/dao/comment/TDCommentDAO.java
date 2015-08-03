package com.ttc.ch2.dao.comment;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.common.QueryCondition;

public interface TDCommentDAO extends GenericDAO<TDComment, Long> {

	public List<TDComment> getTDCommentList(QueryCondition conditions, TDComment filter,List<Brand> brands);
	
	public int getTDCommentCount(TDComment filter,List<Brand> brands);
}
