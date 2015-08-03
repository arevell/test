package com.ttc.ch2.domain.tour;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.common.EntityBase;
import com.ttc.ch2.domain.departure.TourDepartureHistory;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;


@Entity
@Table(name="CONTENT_REPOSITORY")
@org.hibernate.annotations.Table(appliesTo = "CONTENT_REPOSITORY", indexes =
	{
		@Index(name = "CR_IDX_RepositoryStatus", columnNames = { "REPOSITORY_STATUS" }),
		@Index(name = "CR_IDX_Status", columnNames = { "STATUS" })
	}
)
public class ContentRepository extends EntityBase{
	public enum RepositoryStatus {
		Initial,
		TourInfoOnly,
		TourDepartureOnly,
		TIandTD,
		Empty
	};
	/*
	 * Gives an information about last operation on CONTENT_REPOSITORY table 
	 * 
	 */
	public enum Status {
		Initial,
		TourInfoUpload,
		TourDepartureUpload,
		Complete
	};

	public enum DataSource {
		CH1,
		CH2
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contentRepository",cascade = {CascadeType.ALL})
	private List<XMLContentRepository> xmlContentRepository;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(name = "TOURINFO_CONTENT", joinColumns = { 
			@JoinColumn(name = "CONTENT_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "TOURINFO_HISTORY_ID",nullable = false, updatable = false) })
	private Set<UploadTourInfoFile> uploadTourInfoFile;
	
		
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(name = "TOURDEPARTURE_CONTENT", joinColumns = { 
			@JoinColumn(name = "CONTENT_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "TOURDEPARTURE_HISTORY_ID",nullable = false, updatable = false) })
	private Set<TourDepartureHistory> tourDepartureHistory;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(name = "SELLINGCOMPANY_CONTENT", joinColumns = { 
			@JoinColumn(name = "CONTENT_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "SELLING_COMPANY_ID",nullable = false, updatable = false) })
	private Set<SellingCompany> sellingCompanies;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(name = "BRAND_CONTENT", 		
	joinColumns = { 
			@JoinColumn(name = "CONTENT_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "BRAND_ID",nullable = false, updatable = false) }
	)
	private Set<Brand> brands;

	@Enumerated(EnumType.STRING)
	@Column(name = "TOURINFO_SOURCE", nullable = false)
	private DataSource tourInfoSource = DataSource.CH2;

	@Column(name="TOURINFO_XML_SIZE")
	private Long tourInfoXMLSize;
	
	@Column(name="TOURDEPARTURE_XML_SIZE")
	private Long tourDepartureXMLSize;
	
	@Column(name="OLD_TOURINFO_XML_SIZE")
	private Long oldTourInfoXMLSize;
	
	@Column(name="OLD_TOURDEPARTURE_XML_SIZE")
	private Long oldTourDepartureXMLSize;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TOURINFO_MODIFIED")
	private Date tourInfoModified;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TOURDEPARTURE_MODIFIED")
	private Date tourDepartureModified;
	
	@Enumerated(EnumType.STRING)
	@Column(name="REPOSITORY_STATUS", nullable=false)
	private RepositoryStatus repositoryStatus=RepositoryStatus.Initial;
	
	@Column(name="IS_TOURINFO_AVAILABLE", nullable=false)
	private Boolean isTourInfoAvailable = false;
	
	@Column(name="IS_TOURDEPARTURE_AVAILABLE", nullable=false)
	private Boolean isTourDepartureAvailable = false;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", nullable=false)
	private Status status = Status.Initial;
	
	@Column(name="TI_FILENAME", length=255)
	private String tiFileName;
	
	@Column(name="TD_FILENAME", length=255)
	private String tdFileName;
	
	@Column(name="TOUR_CODE", length=10)
	private String tourCode;
	
	@Column(name="CATALOGUED_TOUR_CODE", length=10)
	private String cataloguedTourCode;
	
	@Column(name="TOURDEPARTURE_XML_MD5", length=32)
	private String tourDepartureXMLMD5;
	
	@Column(name="TOURINFO_XML_MD5", length=32)
	private String tourInfoXMLMD5;
	
	public String getTourDepartureXMLMD5() {
		return tourDepartureXMLMD5;
	}
	public void setTourDepartureXMLMD5(String tourDepartureXMLMD5) {
		this.tourDepartureXMLMD5 = tourDepartureXMLMD5;
	}
	public String getTourInfoXMLMD5() {
		return tourInfoXMLMD5;
	}
	public void setTourInfoXMLMD5(String tourInfoXMLMD5) {
		this.tourInfoXMLMD5 = tourInfoXMLMD5;
	}
	public Set<Brand> getBrands() {
		if(brands == null)
			brands = new HashSet<Brand>();
		return brands;
	}
	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}
	public String getTourCode() {
		return tourCode;
	}
	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}
	
	public Date getTourInfoModified() {
		return tourInfoModified;
	}
	public void setTourInfoModified(Date tourInfoModified) {
		this.tourInfoModified = tourInfoModified;
	}
	public Date getTourDepartureModified() {
		return tourDepartureModified;
	}
	public void setTourDepartureModified(Date tourDepartureModified) {
		this.tourDepartureModified = tourDepartureModified;
	}
	
	public RepositoryStatus getRepositoryStatus() {
		return repositoryStatus;
	}
	public void setRepositoryStatus(RepositoryStatus repositoryStatus) {
		this.repositoryStatus = repositoryStatus;
		switch(repositoryStatus) {
			case TourInfoOnly:
				setIsTourDepartureAvailable(false);
				setIsTourInfoAvailable(true);
				break;
			case TourDepartureOnly:
				setIsTourDepartureAvailable(true);
				setIsTourInfoAvailable(false);
				break;
			case TIandTD:
				setIsTourDepartureAvailable(true);
				setIsTourInfoAvailable(true);
				break;
			case Empty:
				setIsTourDepartureAvailable(false);
				setIsTourInfoAvailable(false);
				break;
			default:
				break;
		}
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Set<UploadTourInfoFile> getUploadTourInfoFile() {
		if(uploadTourInfoFile == null)
			uploadTourInfoFile = new HashSet<UploadTourInfoFile>();
		return uploadTourInfoFile;
	}
	public void setUploadTourInfoFile(Set<UploadTourInfoFile> uploadTourInfoFile) {
		this.uploadTourInfoFile = uploadTourInfoFile;
	}
	public Set<TourDepartureHistory> getTourDepartureHistory() {
		if(tourDepartureHistory == null)
			tourDepartureHistory = new HashSet<TourDepartureHistory>();
		return tourDepartureHistory;
	}
	public void setTourDepartureHistory(
			Set<TourDepartureHistory> tourDepartureHistory) {
		this.tourDepartureHistory = tourDepartureHistory;
	}
	public Set<SellingCompany> getSellingCompanies() {
		if(sellingCompanies == null)
			sellingCompanies = new HashSet<SellingCompany>();
		return sellingCompanies;
	}
	public void setSellingCompanies(Set<SellingCompany> sellingCompanies) {
		this.sellingCompanies = sellingCompanies;
	}
	public String getTiFileName() {
		return tiFileName;
	}
	public void setTiFileName(String tiFileName) {
		this.tiFileName = tiFileName;
	}
	public String getTdFileName() {
		return tdFileName;
	}
	public void setTdFileName(String tdFileName) {
		this.tdFileName = tdFileName;
	}
	
	public void setOldTourDepartureXMLSize(Long oldTourDepartureXMLSize) {
		this.oldTourDepartureXMLSize = oldTourDepartureXMLSize;
	}
	public Boolean getIsTourInfoAvailable() {
		return isTourInfoAvailable;
	}
	public void setIsTourInfoAvailable(Boolean isTourInfoAvailable) {
		this.isTourInfoAvailable = isTourInfoAvailable;
	}
	public Boolean getIsTourDepartureAvailable() {
		return isTourDepartureAvailable;
	}
	public void setIsTourDepartureAvailable(Boolean isTourDepartureAvailable) {
		this.isTourDepartureAvailable = isTourDepartureAvailable;
	}
	public DataSource getTourInfoSource() {
		return tourInfoSource;
	}
	public void setTourInfoSource(DataSource tourInfoSource) {
		this.tourInfoSource = tourInfoSource;
	}
	public Long getTourInfoXMLSize() {
		return tourInfoXMLSize;
	}
	public void setTourInfoXMLSize(Long tourInfoXMLSize) {
		this.tourInfoXMLSize = tourInfoXMLSize;
	}
	public Long getTourDepartureXMLSize() {
		return tourDepartureXMLSize;
	}
	public void setTourDepartureXMLSize(Long tourDepartureXMLSize) {
		this.tourDepartureXMLSize = tourDepartureXMLSize;
	}
	public Long getOldTourInfoXMLSize() {
		return oldTourInfoXMLSize;
	}
	public void setOldTourInfoXMLSize(Long oldTourInfoXMLSize) {
		this.oldTourInfoXMLSize = oldTourInfoXMLSize;
	}
	public Long getOldTourDepartureXMLSize() {
		return oldTourDepartureXMLSize;
	}
	
	public String getCataloguedTourCode() {
		return cataloguedTourCode;
	}
	public void setCataloguedTourCode(String cataloguedTourCode) {
		this.cataloguedTourCode = cataloguedTourCode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tourCode == null) ? 0 : tourCode.hashCode());
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
		ContentRepository other = (ContentRepository) obj;
		if (tourCode == null) {
			if (other.tourCode != null)
				return false;
		} else if (!tourCode.equals(other.tourCode))
			return false;
		return true;
	}
	
	public List<XMLContentRepository> getXmlContentRepository() {
		return xmlContentRepository;
	}
	
	public void setXmlContentRepository(
			List<XMLContentRepository> xmlContentRepository) {
		this.xmlContentRepository = xmlContentRepository;
	}
}
