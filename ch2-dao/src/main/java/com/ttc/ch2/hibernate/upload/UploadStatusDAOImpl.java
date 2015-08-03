package com.ttc.ch2.hibernate.upload;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.upload.UploadStatusDAO;
import com.ttc.ch2.domain.upload.UploadStatus;


@Repository
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class UploadStatusDAOImpl extends BaseDao<UploadStatus,  Long>  implements UploadStatusDAO{


	public UploadStatus getUploadStatusByBrandCode(String brandCode){
		Search search = new Search();
		search.addFilterEqual("brandCode", brandCode);
		return searchUnique(search);
	}
}
