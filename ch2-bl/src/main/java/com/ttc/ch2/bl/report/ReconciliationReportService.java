package com.ttc.ch2.bl.report;

import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.search.export.IndexSynchronizerVO;

public interface ReconciliationReportService {

	public void createReconciliationReport(ProcessName processName, String brandCode, IndexSynchronizerVO indexSynchronizerVO, FileCollectVO fileCollectVO) throws ReconciliationReportServiceException;
}
