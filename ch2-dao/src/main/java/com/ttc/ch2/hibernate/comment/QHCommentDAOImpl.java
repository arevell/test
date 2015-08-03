package com.ttc.ch2.hibernate.comment;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.dao.comment.QHCommentDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.common.QueryCondition;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class QHCommentDAOImpl extends CommentDAO<QHComment, Long> implements QHCommentDAO {

	
	
	@Override
	public List<QHComment> getQHCommentList(QueryCondition conditions, QHComment filter,List<Brand> brands) {
		Search search = new Search();
		setupSortCondition(search, conditions);		
		setupSearch(search, filter,brands);			
		return search(search);
	}
	
	@Override
	public int getQHCommentCount(QHComment filter,List<Brand> brands) {
		Search search = new Search();
		setupSearch(search, filter,brands);	
		return count(search);
	}
	

	protected void setupSearch(Search search, Comment filter,List<Brand> brands) {	
		super.setupSearch(search, filter);
		
		QHComment localfilter=(QHComment) filter;		
		if(localfilter.getQuartzJobHistory()!=null) {
			search.addFilterEqual("quartzJobHistory.id",localfilter.getQuartzJobHistory().getId());
		}	
		
		if(brands!=null && brands.size()>0){
			search.addFilterIn("quartzJobHistory.brand", brands);
		}
	}
	
}
