package com.ttc.ch2.domain.upload;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.domain.common.EntityBase;


@Entity
@Table(name="UPLOAD_STATUS")
public class UploadStatus extends EntityBase{

	public static enum ProcessLevel{
		PREPARE(0),
		PARSE(1),
		CH1_TI_DOWNLOAD_FOR_TD(4000),
		INDEXING(8000),
		OUTGOING_ARCHIVE(12000),
		INTENERARY_RAPORT(16000),
		RECONCILIATION(20000),
		CH1_TI_DOWNLOAD(24000),
		FINISH(25000);
		
	int level;
	private ProcessLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}	
	
	};
	
	
	
	@Column(name="COUNT_FILES")	
	private int count;
	
	@Column(name="CURRENT_FILE_NR")
	private int value;
	
	@Enumerated(EnumType.STRING)
	private TypeMsg typeMsg;
		
	@Column(name="MESSAGE", length=3000)
	private String messages;
	
	@Column(name="FILE_NAME_DESC", length=50)	
	private String fileNameDesc;
	
	@Column(name="BRAND_CODE", length=30)
	private String brandCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATE", nullable=false)
	private Date dateUpdate;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public TypeMsg getTypeMsg() {
		return typeMsg;
	}

	public void setTypeMsg(TypeMsg typeMsg) {
		this.typeMsg = typeMsg;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getFileNameDesc() {
		return fileNameDesc;
	}

	public void setFileNameDesc(String fileName) {
		this.fileNameDesc = fileName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}	
}
