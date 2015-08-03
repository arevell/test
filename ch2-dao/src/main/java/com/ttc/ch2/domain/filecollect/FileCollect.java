package com.ttc.ch2.domain.filecollect;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name = "FILE_COLLECT")
public class FileCollect extends EntityBase {

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "BRAND_ID", nullable = false)
	private Brand brand;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "SELLING_COMPANY_ID", nullable = false)
	private SellingCompany sellingCompany;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "ZIP_FILE_COLLECT", nullable = false)
	private ZIPFileCollect fileZip;

	@Column(name = "FILE_NAME", length = 255, nullable = false)
	private String fileName;

	@Column(name = "FILE_MD5_V1", length = 32, nullable = false)
	private String fileMD5V1;

	@Column(name = "FILE_MD5_V3", length = 32, nullable = false)
	private String fileMD5V3;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FILE_MODIFIED", nullable = false)
	private Date fileModified;

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public SellingCompany getSellingCompany() {
		return sellingCompany;
	}

	public void setSellingCompany(SellingCompany sellingCompany) {
		this.sellingCompany = sellingCompany;
	}

	public ZIPFileCollect getFileZip() {
		return fileZip;
	}

	public void setFileZip(ZIPFileCollect fileZip) {
		this.fileZip = fileZip;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileMD5V1() {
		return fileMD5V1;
	}

	public void setFileMD5V1(String fileMD5V1) {
		this.fileMD5V1 = fileMD5V1;
	}

	public String getFileMD5V3() {
		return fileMD5V3;
	}

	public void setFileMD5V3(String fileMD5V3) {
		this.fileMD5V3 = fileMD5V3;
	}

	public Date getFileModified() {
		return fileModified;
	}

	public void setFileModified(Date fileModified) {
		this.fileModified = fileModified;
	}
}
