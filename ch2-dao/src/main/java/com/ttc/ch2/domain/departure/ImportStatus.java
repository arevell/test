package com.ttc.ch2.domain.departure;

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
@Table(name="IMPORT_STATUS")
public class ImportStatus extends EntityBase{
	
	public static enum ProcessLevel{
		PREPARE(0),
		IMPORT(1),
		DBLEVEL(2),
		PERSIST(3),
		CH1_TI_DOWNLOAD(4),CH1_TI_PERSIST(5),INDEXING(6),OUTGOING_ARCHIVE(7),RECONCILIATION(8),FINALIZE(9);
		
	int level;

	private ProcessLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}	
	
	};
		
	@Enumerated(EnumType.STRING)
	private TypeMsg typeMsg;
	
	@Enumerated(EnumType.STRING)
	private ProcessLevel processLevel;
		
	@Column(name="MESSAGE", length=300)
	private String messages;
		
	@Column(name="BRAND_CODE", length=30)
	private String brandCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATE", nullable=false)
	private Date dateUpdate;
	
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

	public ProcessLevel getProcessLevel() {
		return processLevel;
	}

	public void setProcessLevel(ProcessLevel processLevel) {
		this.processLevel = processLevel;
	}


}
