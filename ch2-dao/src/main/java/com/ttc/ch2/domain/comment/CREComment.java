package com.ttc.ch2.domain.comment;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.ttc.ch2.domain.export.CRExport;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;

@Entity
@DiscriminatorValue("CREComment")
public class CREComment extends Comment {
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="CREXPORT_ID")
	CRExport contentRepositoryExport;

	public CRExport getContentRepositoryExport() {
		return contentRepositoryExport;
	}

	public void setContentRepositoryExport(CRExport contentRepositoryExport) {
		this.contentRepositoryExport = contentRepositoryExport;
	} 
}
