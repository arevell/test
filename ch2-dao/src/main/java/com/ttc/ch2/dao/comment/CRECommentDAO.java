package com.ttc.ch2.dao.comment;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.comment.CREComment;
import com.ttc.ch2.domain.common.QueryCondition;

public interface CRECommentDAO extends GenericDAO<CREComment, Long> {
	
	public List<CREComment> getCRECommentList(QueryCondition conditions, CREComment filter);
	
	public int getCRECommentCount(CREComment filter);
	
}
