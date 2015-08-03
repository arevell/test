package com.ttc.ch2.domain.filecollect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.ttc.ch2.domain.common.EntityBase;

@Entity
@Table(name = "ZIP_FILE_COLLECT")
public class ZIPFileCollect extends EntityBase {

	@Lob
	@Column(name = "FILE_COLLECT_ZIP_V1", nullable = false)
	private byte[] fileCollectZIPV1;

	@Lob
	@Column(name = "FILE_COLLECT_ZIP_V3", nullable = false)
	private byte[] fileCollectZIPV3;

	public byte[] getFileCollectZIPV1() {
		return fileCollectZIPV1;
	}

	public void setFileCollectZIPV1(byte[] fileCollectZIPV1) {
		this.fileCollectZIPV1 = fileCollectZIPV1;
	}

	public byte[] getFileCollectZIPV3() {
		return fileCollectZIPV3;
	}

	public void setFileCollectZIPV3(byte[] fileCollectZIPV3) {
		this.fileCollectZIPV3 = fileCollectZIPV3;
	}
}
