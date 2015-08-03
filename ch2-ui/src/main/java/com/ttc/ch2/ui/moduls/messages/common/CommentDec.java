package com.ttc.ch2.ui.moduls.messages.common;

import org.apache.commons.lang.StringUtils;

import com.ttc.ch2.dao.comment.TypeComment;
import com.ttc.ch2.domain.comment.CREComment;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.comment.TIComment;

public class CommentDec {

	private Long grCode;
	private Comment comment;
	private TypeComment typeComment;
	private boolean isDetailExist;
	
	public CommentDec() {
		comment = new Comment();
		typeComment=TypeComment.TIComment;
	}
	
	public Comment getComment() {	
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
		isDetailExist = comment!=null && (StringUtils.isNotBlank(comment.getContent()) || StringUtils.isNotBlank(comment.getStackTrace()));
	}
	public TypeComment getTypeComment() {
		return typeComment;
	}
	public void setTypeComment(TypeComment typeComment) {
		this.typeComment = typeComment;
	}
	
	public Comment getCorrectComment()
	{		
		Comment result=null;
		switch (typeComment) {
		case TIComment:
			TIComment c1=new TIComment();			
			result=mapping(c1);
			break;
		case TDComment:
			TDComment c2=new TDComment();					
			result=mapping(c2);
			break;
		case CREComment:
			CREComment c3=new CREComment();			
			result=mapping(c3);
			break;			
		case QHComment:
			QHComment c4=new QHComment();
			result=mapping(c4);
			break;			
		default:
			break;
		}
		return result;
	}
	
	private Comment mapping(Comment tempComment)
	{
		tempComment.setMessage(comment.getMessage());
		tempComment.setMessageCode(comment.getMessageCode());
		tempComment.setModifiedTime(comment.getModifiedTime());		
		return tempComment;
	}

	public boolean isDetailExist() {
		return isDetailExist;
	}

	public void setDetailExist(boolean isDetailExist) {
		this.isDetailExist = isDetailExist;
	}

	public Long getGrCode() {
		return grCode;
	}

	public void setGrCode(Long grCode) {
		this.grCode = grCode;
	}
}
