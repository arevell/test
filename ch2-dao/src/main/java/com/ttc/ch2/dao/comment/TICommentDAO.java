package com.ttc.ch2.dao.comment;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.common.QueryCondition;

public interface TICommentDAO extends GenericDAO<TIComment, Long> {
	
	public List<TIComment> getTICommentList(QueryCondition conditions, TIComment filter,List<Brand> brands);
	
	public int getTICommentCount(TIComment filter,List<Brand> brands); 
}
