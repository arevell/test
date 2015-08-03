package com.ttc.ch2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name="TIBLOB_DATA")
public class TIBlobData extends EntityBase{
	
	@Lob
	@Column(name="DATA")
	private byte[] data;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}	
}
