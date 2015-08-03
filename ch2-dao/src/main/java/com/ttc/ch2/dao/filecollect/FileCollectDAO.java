package com.ttc.ch2.dao.filecollect;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.filecollect.FileCollect;

public interface FileCollectDAO extends GenericDAO<FileCollect, Long> {

	FileCollect getFileCollect(String sellingCompanyCode);

	FileCollect getFileCollect(String brandCode, String sellingCompanyCode);

	List<FileCollect> getFileCollectList(String brandCode);
}
