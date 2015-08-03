package com.ttc.ch2.bl.message;

import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.dao.comment.CRECommentDAO;
import com.ttc.ch2.dao.comment.QHCommentDAO;
import com.ttc.ch2.dao.comment.TDCommentDAO;
import com.ttc.ch2.dao.comment.TICommentDAO;
import com.ttc.ch2.dao.comment.TypeComment;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.domain.comment.CREComment;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.departure.TourDepartureHistory;
import com.ttc.ch2.domain.export.CRExport;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.user.UserGui;

@Service
public class CommentsServiceImpl implements CommentsService {

	private static final Logger logger = LoggerFactory.getLogger(CommentsServiceImpl.class);
	
	@Inject
	private TICommentDAO tiCommentDAO;
	@Inject
	private TDCommentDAO tdCommentDAO;
	@Inject
	private CRECommentDAO creCommentDAO;
	@Inject
	private QHCommentDAO qhCommentDAO;
	

	@Override
	public List<Comment> getCommentList(QueryCondition conditions,Comment filter,Long grCode,TypeComment type) throws CommentsServiceException {
		logger.trace("CommentsServiceImpl:getCommentList-start");
		List<Comment> comments=Lists.newArrayList();
		try {
			Preconditions.checkArgument(type!=null, "CommentsServiceImpl->type is empty");
			
			List<Brand> brands = Lists.newArrayList();
			if(SecurityHelper.hasAuthority(Role.BRAND)){
				UserGui userGui=SecurityHelper.getUserGuiPrincipal().getUserDb();
				brands.addAll(userGui.getBrands());
			}
			
			switch (type) {
			case TIComment:				
				setupExtraFilter(filter, grCode, type);			
				comments.addAll(tiCommentDAO.getTICommentList(conditions, (TIComment) filter,brands));
				break;
			case TDComment:
				setupExtraFilter(filter, grCode, type);
				comments.addAll(tdCommentDAO.getTDCommentList(conditions, (TDComment) filter,brands));
				break;
			case CREComment:
				setupExtraFilter(filter, grCode, type);
				comments.addAll(creCommentDAO.getCRECommentList(conditions, (CREComment) filter));
				break;
			case QHComment:
				setupExtraFilter(filter, grCode, type);
				comments.addAll(qhCommentDAO.getQHCommentList(conditions, (QHComment) filter,brands));
				break;
				
			default:
				break;
			}
		}
		catch (Exception e) {
			logger.trace("CommentsServiceImpl:getCommentList-end");	
			throw new CommentsServiceException(e);
		}		
		logger.trace("CommentsServiceImpl:getCommentList-end");		
		return comments;
	}
	
	
	private void setupExtraFilter(Comment filter,Long grCode,TypeComment type)
	{		
		if(grCode==null)
		return;
		
		switch (type) {
		case TIComment:				
			TIComment localFilterUpload=(TIComment) filter;
			UploadTourInfoFile parentEntity1=new UploadTourInfoFile();
			parentEntity1.setId(grCode);
			localFilterUpload.setUploadTourInfoFile(parentEntity1);
			break;
		case TDComment:			
			TDComment localFilterImport=(TDComment) filter;
			TourDepartureHistory parentEntity2=new TourDepartureHistory();
			parentEntity2.setId(grCode);
			localFilterImport.setTourDepartureHistory(parentEntity2);
			break;
		case CREComment:
			CREComment localFilterExport=(CREComment) filter;
			CRExport parentEntity3=new CRExport();
			parentEntity3.setId(grCode);
			localFilterExport.setContentRepositoryExport(parentEntity3);			
			break;
		case QHComment:
			QHComment localFilterJob=(QHComment) filter;
			QuartzJobHistory parentEntity4=new QuartzJobHistory();
			parentEntity4.setId(grCode);
			localFilterJob.setQuartzJobHistory(parentEntity4);
			break;			
		default:
			break;
		}
	}
	
	

	@Override
	public int getCommentCount(Comment filter,Long grCode,TypeComment type) throws CommentsServiceException {
		logger.trace("CommentsServiceImpl:getCommentCount-start");
		int count=0;
		try {
			Preconditions.checkArgument(type!=null, "CommentsServiceImpl->type is empty");
			
			List<Brand> brands = Lists.newArrayList();
			if(SecurityHelper.hasAuthority(Role.BRAND)){
				UserGui userGui=SecurityHelper.getUserGuiPrincipal().getUserDb();
				brands.addAll(userGui.getBrands());
			}
			switch (type) {
			case TIComment:
				setupExtraFilter(filter, grCode, type);
				count=tiCommentDAO.getTICommentCount((TIComment) filter,brands);
				break;
			case TDComment:
				setupExtraFilter(filter, grCode, type);
				count=tdCommentDAO.getTDCommentCount((TDComment) filter,brands);
				break;
			case CREComment:
				setupExtraFilter(filter, grCode, type);
				count=creCommentDAO.getCRECommentCount((CREComment) filter);
				break;
			case QHComment:
				setupExtraFilter(filter, grCode, type);
				count=qhCommentDAO.getQHCommentCount((QHComment) filter,brands);
				break;
				
			default:
				break;
			}
			
		} catch (Exception e) {
			logger.trace("CommentsServiceImpl:getCommentCount-end");
			throw new CommentsServiceException(e);
		}
		
		logger.trace("CommentsServiceImpl:getCommentCount-end");
		return count;
	}

	@Override
	public Comment getCommentById(Long id,TypeComment type) throws CommentsServiceException{
		logger.trace("CommentsServiceImpl:getCommentById-start");
		Comment result=null;
		try {
			Preconditions.checkArgument(type!=null, "CommentsServiceImpl->type is empty");			
			switch (type) {
			case TIComment:
				result=tiCommentDAO.find(id);
				break;
			case TDComment:
				result=tdCommentDAO.find(id);
				break;
			case CREComment:
				result=creCommentDAO.find(id);
				break;
			case QHComment:
				result=qhCommentDAO.find(id);
				break;
				
			default:
				break;
			}
			
		} catch (Exception e) {
			logger.trace("CommentsServiceImpl:getCommentById-end");
			throw new CommentsServiceException(e);
		}
		logger.trace("CommentsServiceImpl:getCommentById-end");
		return result;
	}

}
