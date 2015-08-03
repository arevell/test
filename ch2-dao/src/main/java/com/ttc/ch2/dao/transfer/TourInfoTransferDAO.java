package com.ttc.ch2.dao.transfer;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.transfer.TourInfoTransfer;

public interface TourInfoTransferDAO extends GenericDAO<TourInfoTransfer, Long> {

	boolean isUploadEnabled(String brandCode);

	boolean isDownloadEnabled(String brandCode);

	TourInfoTransfer getTourInfoTransfer(String brandCode);
}
