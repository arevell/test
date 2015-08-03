package com.ttc.ch2.ui.moduls.messages.common;

import java.util.Date;
import java.util.List;

import com.ttc.ch2.dao.comment.TypeComment;

public class CommentsForm {

	private List<TypeComment> chooseType;
	private TypeComment selectedType;
	private Long code;
	private String message;
	private Date modifiedTime;
	
	
	public List<TypeComment> getChooseType() {
		return chooseType;
	}
	public void setChooseType(List<TypeComment> chooseType) {
		this.chooseType = chooseType;
	}
	public TypeComment getSelectedType() {
		return selectedType;
	}
	public void setSelectedType(TypeComment selectedType) {
		this.selectedType = selectedType;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
}
