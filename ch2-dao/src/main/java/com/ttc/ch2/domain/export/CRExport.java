package com.ttc.ch2.domain.export;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.ttc.ch2.domain.comment.CREComment;
import com.ttc.ch2.domain.common.EntityBase;
import com.ttc.ch2.domain.tour.ContentRepository;

@Entity
public class CRExport extends EntityBase{
	public enum ContentRepositoryExportStatus {
		SUCCESS,
		FAIL
	}
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(name = "CREXPORT_CONTENT", joinColumns = { 
			@JoinColumn(name = "CREXPORT_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "CONTENT_ID",nullable = false, updatable = false) })
	Set<ContentRepository> contentRepositories;
		
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_EXPORT", nullable=false)
	private Date dateExport;
	
	@Column(name="NAME", length=60, nullable=false)
	private String name;
	
	@Column(name="MODIFIED_BY", length=60, nullable=false)
	private String modifiedBy;
	
	@Enumerated(EnumType.STRING)
	@Column(name="FILE_STATUS", nullable=false)
	private ContentRepositoryExportStatus status;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contentRepositoryExport")
	@OrderBy("id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CREComment> comments;

	public Set<ContentRepository> getContentRepositories() {
		return contentRepositories;
	}

	public void setContentRepositories(Set<ContentRepository> contentRepositories) {
		this.contentRepositories = contentRepositories;
	}

	public Date getDateExport() {
		return dateExport;
	}

	public void setDateExport(Date dateExport) {
		this.dateExport = dateExport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ContentRepositoryExportStatus getStatus() {
		return status;
	}

	public void setStatus(ContentRepositoryExportStatus status) {
		this.status = status;
	}

	public List<CREComment> getComments() {
		return comments;
	}

	public void setComments(List<CREComment> comments) {
		this.comments = comments;
	}

	
}
