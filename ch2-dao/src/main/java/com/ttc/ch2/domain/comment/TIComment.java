package com.ttc.ch2.domain.comment;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.ttc.ch2.domain.upload.UploadTourInfoFile;

@Entity
@DiscriminatorValue("TIComment")
public class TIComment extends Comment {
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="TIHISTORY_ID")
	UploadTourInfoFile uploadTourInfoFile; 
	
	public UploadTourInfoFile getUploadTourInfoFile() {
		return uploadTourInfoFile;
	}
	public void setUploadTourInfoFile(UploadTourInfoFile uploadTourInfoFile) {
		this.uploadTourInfoFile = uploadTourInfoFile;
	}
	

}
