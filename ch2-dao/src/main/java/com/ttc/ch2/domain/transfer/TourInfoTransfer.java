package com.ttc.ch2.domain.transfer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name = "TOURINFO_TRANSFER")
public class TourInfoTransfer extends EntityBase {

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "BRAND_ID", nullable = false)
	private Brand brand;

	@Column(name = "IS_UPLOAD_ENABLED", nullable = false)
	private Boolean isUploadEnabled = false;

	@Column(name = "IS_DOWNLOAD_ENABLED", nullable = false)
	private Boolean isDownloadEnabled = false;

	public Boolean getIsUploadEnabled() {
		return isUploadEnabled;
	}

	public void setIsUploadEnabled(Boolean isUploadEnabled) {
		this.isUploadEnabled = isUploadEnabled;
	}

	public Boolean getIsDownloadEnabled() {
		return isDownloadEnabled;
	}

	public void setIsDownloadEnabled(Boolean isDownloadEnabled) {
		this.isDownloadEnabled = isDownloadEnabled;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
