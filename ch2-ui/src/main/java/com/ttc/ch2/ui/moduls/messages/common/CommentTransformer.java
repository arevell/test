package com.ttc.ch2.ui.moduls.messages.common;

import com.google.common.base.Function;
import com.ttc.ch2.dao.comment.TypeComment;
import com.ttc.ch2.domain.comment.CREComment;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.comment.TIComment;

public 	class CommentTransformer implements Function<Comment, CommentDec>
{
	@Override
	public CommentDec apply(Comment input) {

		CommentDec c=new CommentDec();
		c.setComment(input);
		if (input instanceof TIComment) {
			c.setTypeComment(TypeComment.TIComment);
			TIComment srcComment=(TIComment) input;
			if(srcComment.getUploadTourInfoFile()!=null)
				c.setGrCode(srcComment.getUploadTourInfoFile().getId());			
		}
		else if (input instanceof TDComment) {
			c.setTypeComment(TypeComment.TDComment);
			TDComment srcComment=(TDComment) input;
			if(srcComment.getTourDepartureHistory()!=null)
				c.setGrCode(srcComment.getTourDepartureHistory().getId());
		}
		else if (input instanceof CREComment) {
			c.setTypeComment(TypeComment.CREComment);
			CREComment srcComment=(CREComment) input;
			if(srcComment.getContentRepositoryExport()!=null)
				c.setGrCode(srcComment.getContentRepositoryExport().getId());			
		}
		else if (input instanceof QHComment) {
			c.setTypeComment(TypeComment.QHComment);
			QHComment srcComment=(QHComment) input;
			if(srcComment.getQuartzJobHistory()!=null)
				c.setGrCode(srcComment.getQuartzJobHistory().getId());
		}
		return c;
	}		
}