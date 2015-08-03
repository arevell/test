package com.ttc.ch2.hibernate.transfer;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.dao.transfer.TourInfoTransferDAO;
import com.ttc.ch2.domain.transfer.TourInfoTransfer;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class TourInfoTransferDAOImpl extends BaseDao<TourInfoTransfer, Long> implements TourInfoTransferDAO {

	@Override
	public boolean isUploadEnabled(String brandCode) {

		Search search = new Search();
		search.addFilterEqual("brand.code", brandCode);

		TourInfoTransfer tourInfoTransfer = searchUnique(search);

		return tourInfoTransfer != null ? tourInfoTransfer.getIsUploadEnabled() : false;
	}

	@Override
	public boolean isDownloadEnabled(String brandCode) {

		Search search = new Search();
		search.addFilterEqual("brand.code", brandCode);

		TourInfoTransfer tourInfoTransfer = searchUnique(search);

		return tourInfoTransfer != null ? tourInfoTransfer.getIsDownloadEnabled() : false;
	}

	@Override
	public TourInfoTransfer getTourInfoTransfer(String brandCode) {

		Search search = new Search();
		search.addFilterEqual("brand.code", brandCode);

		return searchUnique(search);
	}
}
