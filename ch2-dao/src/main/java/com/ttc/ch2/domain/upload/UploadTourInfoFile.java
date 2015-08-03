package com.ttc.ch2.domain.upload;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.TIBlobData;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.common.EntityBase;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.domain.user.User;

@Entity
@Table(name="TOURINFO_HISTORY")
public class UploadTourInfoFile extends EntityBase {
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH}, mappedBy="uploadTourInfoFile")
	Set<ContentRepository> contentRepositories;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPLOAD", nullable=false)
	private Date dateUpload;
	
	@Column(name="NAME", length=60, nullable=false)	
	private String name;
		
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name="ZIP_DATA_ID")	
	private TIBlobData zipData;
	
	@Column(name="ZIP_ATTACHED")
	private Boolean zipAttached;
	
	@Enumerated(EnumType.STRING)
	@Column(name="FILE_STATUS", nullable=false)
	private UploadTourInfoFileStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(name="FILE_SOURCE", nullable=false)
	private UploadTourInfoFileSource sourceUploadFile;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "uploadTourInfoFile")
	@OrderBy("id")
//	@LazyCollection(LazyCollectionOption.FALSE)
	private List<TIComment> comments;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="BRAND_ID")
	private Brand brand; 
	
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,CascadeType.REFRESH })
	@JoinColumn(name = "USER_ID", nullable = true)
	private User user;
		
	public Set<ContentRepository> getContentRepositories() {
		if(contentRepositories == null)
			contentRepositories = new HashSet<ContentRepository>();
		return contentRepositories;
	}
	public void setContentRepositories(Set<ContentRepository> contentRepositories) {
		this.contentRepositories = contentRepositories;
	}
	public Date getDateUpload() {
		return dateUpload;
	}
	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UploadTourInfoFileStatus getStatus() {
		return status;
	}
	public void setStatus(UploadTourInfoFileStatus status) {
		this.status = status;
	}
	
	public UploadTourInfoFileSource getSourceUploadFile() {
		return sourceUploadFile;
	}
	public void setSourceUploadFile(UploadTourInfoFileSource sourceUploadFile) {
		this.sourceUploadFile = sourceUploadFile;
	}
	public List<TIComment> getComments() {
		if(comments == null)
			comments = new ArrayList<TIComment>();
		return comments;
	}
	public void setComments(List<TIComment> comments) {
		this.comments = comments;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityBase other = (EntityBase) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		if (getVersion() == null) {
			if (other.getVersion() != null)
				return false;
		} else if (!getVersion().equals(other.getVersion()))
			return false;
		return true;
	}
	
	public TIBlobData getZipData() {
		return zipData;
	}
	public void setZipData(TIBlobData zipData) {
		
		if(zipData!=null && zipData.getData().length>0)
			zipAttached=true;
		else
			zipAttached=false;		
		this.zipData = zipData;
	}
	public Boolean getZipAttached() {
		return zipAttached;
	}
	public void setZipAttached(Boolean zipAttached) {
		this.zipAttached = zipAttached;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
