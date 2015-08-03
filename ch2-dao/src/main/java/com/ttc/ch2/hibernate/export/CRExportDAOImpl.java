package com.ttc.ch2.hibernate.export;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.export.CRExportDAO;
import com.ttc.ch2.domain.export.CRExport;

@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class CRExportDAOImpl extends BaseDao<CRExport, Long> implements CRExportDAO {

	

}
