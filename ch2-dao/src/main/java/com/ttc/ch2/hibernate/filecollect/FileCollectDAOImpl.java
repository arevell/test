package com.ttc.ch2.hibernate.filecollect;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.filecollect.FileCollectDAO;
import com.ttc.ch2.domain.filecollect.FileCollect;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
public class FileCollectDAOImpl extends BaseDao<FileCollect, Long> implements FileCollectDAO {

	@Override
	public FileCollect getFileCollect(String sellingCompanyCode) {

		Search search = new Search();
		search.addFilterEqual("sellingCompany.code", sellingCompanyCode);

		return searchUnique(search);
	}

	@Override
	public FileCollect getFileCollect(String brandCode, String sellingCompanyCode) {

		Search search = new Search();
		search.addFilterEqual("brand.code", brandCode);
		search.addFilterEqual("sellingCompany.code", sellingCompanyCode);

		return searchUnique(search);
	}

	@Override
	public List<FileCollect> getFileCollectList(String brandCode) {

		Search search = new Search();
		search.addFilterEqual("brand.code", brandCode);

		return search(search);
	}
}
