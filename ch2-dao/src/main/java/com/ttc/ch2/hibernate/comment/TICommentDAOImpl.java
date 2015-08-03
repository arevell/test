package com.ttc.ch2.hibernate.comment;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.dao.comment.TICommentDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.common.QueryCondition;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class TICommentDAOImpl extends CommentDAO<TIComment,  Long>  implements TICommentDAO {
	
	@Override
	public List<TIComment> getTICommentList(QueryCondition conditions, TIComment filter,List<Brand> brands) {
		Search search = new Search();
		setupSortCondition(search, conditions);		
		setupSearch(search, filter,brands);		
		return search(search);
	}
	
	@Override
	public int getTICommentCount(TIComment filter,List<Brand> brands) {
		Search search = new Search();		
		setupSearch(search, filter,brands);		
		return count(search);
	}
	
	protected void setupSearch(Search search, Comment filter,List<Brand> brands) {	
		super.setupSearch(search, filter);
		
		TIComment localfilter=(TIComment) filter;		
		if(localfilter.getUploadTourInfoFile()!=null) {
			search.addFilterEqual("uploadTourInfoFile.id",localfilter.getUploadTourInfoFile().getId());
		}		
		
		if(brands!=null && brands.size()>0){
			search.addFilterIn("uploadTourInfoFile.brand", brands);
		}
	}
}
