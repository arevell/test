package com.ttc.ch2.hibernate.comment;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.comment.TDCommentDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class TDCommentDAOImpl extends CommentDAO<TDComment, Long> implements TDCommentDAO {

	@Override
	public List<TDComment> getTDCommentList(QueryCondition conditions, TDComment filter,List<Brand> brands) {
		Search search = new Search();
		setupSortCondition(search, conditions);		
		setupSearch(search, filter,brands);			
		return search(search);
	}
	
	@Override
	public int getTDCommentCount(TDComment filter,List<Brand> brands) {
		Search search = new Search();
		setupSearch(search, filter,brands);	
		return count(search);
	}


	protected void setupSearch(Search search, Comment filter,List<Brand> brands) {	
		super.setupSearch(search, filter);
		
		TDComment localfilter=(TDComment) filter;		
		if(localfilter.getTourDepartureHistory()!=null) {
			search.addFilterEqual("tourDepartureHistory.id",localfilter.getTourDepartureHistory().getId());
		}	
		
		if(brands!=null && brands.size()>0){
			search.addFilterIn("tourDepartureHistory.brand", brands);
		}
	}
	
}
