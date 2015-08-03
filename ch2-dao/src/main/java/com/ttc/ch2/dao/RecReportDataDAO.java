package com.ttc.ch2.dao;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ttc.ch2.domain.RecReportData;

public interface RecReportDataDAO extends GenericDAO<RecReportData, Long> {

	public List<RecReportData> getList(RecReportData exemple);
}
