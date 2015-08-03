package com.ttc.ch2.domain.tour;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name="XML_CONTENT_REPOSITORY")
public class XMLContentRepository extends EntityBase {

	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="CONTENT_REPOSITORY_ID")	
	private ContentRepository contentRepository;
	
	@Lob
	@Column(name="TOURINFO_XML")
	private String tourInfoXML;
	
	@Lob
	@Column(name="TOURDEPARTURE_XML")
	private String tourDepartureXML;
	
	@Lob
	@Column(name="OLD_TOURINFO_XML")
	private String oldTourInfoXML;
	
	@Lob
	@Column(name="OLD_TOURDEPARTURE_XML")
	private String oldTourDepartureXML;

	public String getTourInfoXML() {
		return tourInfoXML;
	}

	public void setTourInfoXML(String tourInfoXML) {
		this.tourInfoXML = tourInfoXML;
	}

	public String getTourDepartureXML() {
		return tourDepartureXML;
	}

	public void setTourDepartureXML(String tourDepartureXML) {
		this.tourDepartureXML = tourDepartureXML;
	}

	public String getOldTourInfoXML() {
		return oldTourInfoXML;
	}

	public void setOldTourInfoXML(String oldTourInfoXML) {
		this.oldTourInfoXML = oldTourInfoXML;
	}

	public String getOldTourDepartureXML() {
		return oldTourDepartureXML;
	}

	public void setOldTourDepartureXML(String oldTourDepartureXML) {
		this.oldTourDepartureXML = oldTourDepartureXML;
	}

	public ContentRepository getContentRepository() {
		return contentRepository;
	}

	public void setContentRepository(ContentRepository contentRepository) {
		this.contentRepository = contentRepository;
	}
	
}
