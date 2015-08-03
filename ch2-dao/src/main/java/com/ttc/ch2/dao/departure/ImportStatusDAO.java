package com.ttc.ch2.dao.departure;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.departure.ImportStatus;

public interface ImportStatusDAO  extends GenericDAO<ImportStatus, Long>{

	public ImportStatus getImportStatusByBrandCode(String brandCode);
}
