package com.ttc.ch2.hibernate.comment;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.dao.comment.CRECommentDAO;
import com.ttc.ch2.domain.comment.CREComment;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.common.QueryCondition;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class CRECommentDAOImpl extends CommentDAO<CREComment, Long> implements CRECommentDAO {

	
	
	@Override
	public List<CREComment> getCRECommentList(QueryCondition conditions, CREComment filter) {
		Search search = new Search();
		setupSortCondition(search, conditions);					
		setupSearch(search, filter);	
		return search(search);
	}
	
	@Override
	public int getCRECommentCount(CREComment filter) {
		Search search = new Search();
		setupSearch(search, filter);	
		return count(search);
	}
	
	@Override
	protected void setupSearch(Search search, Comment filter) {	
		super.setupSearch(search, filter);
		
		CREComment localfilter=(CREComment) filter;		
		if(localfilter.getContentRepositoryExport()!=null) {
			search.addFilterEqual("contentRepositoryExport.id",localfilter.getContentRepositoryExport().getId());
		}			
	}
	
}
