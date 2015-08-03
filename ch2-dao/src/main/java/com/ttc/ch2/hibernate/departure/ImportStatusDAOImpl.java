package com.ttc.ch2.hibernate.departure;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.departure.ImportStatusDAO;
import com.ttc.ch2.domain.departure.ImportStatus;


@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class ImportStatusDAOImpl extends BaseDao<ImportStatus,  Long>  implements ImportStatusDAO{

	public ImportStatus getImportStatusByBrandCode(String brandCode){
		Search search = new Search();
		if(StringUtils.isNotEmpty(brandCode))
		search.addFilterEqual("brandCode", brandCode);
		ImportStatus result=searchUnique(search);		
		return result;
	}
}
