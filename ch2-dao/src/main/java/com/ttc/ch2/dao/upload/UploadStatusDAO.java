package com.ttc.ch2.dao.upload;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.upload.UploadStatus;

public interface UploadStatusDAO  extends GenericDAO<UploadStatus, Long>{

	
	
	public UploadStatus getUploadStatusByBrandCode(String brandCode);
}
