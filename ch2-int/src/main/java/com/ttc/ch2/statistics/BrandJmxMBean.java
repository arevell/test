package com.ttc.ch2.statistics;

import java.util.Date;

public interface BrandJmxMBean {

	public abstract Date getTiOperationStart();
	public abstract Date getTiOperationStop();
	public abstract Date getTdOperationStart();
	public abstract Date getTdOperationStop();
	public abstract long getTdImportTime();
	public abstract Date getExtendedTdOperationStart();
	public abstract Date getExtendedTdOperationStop();
	public abstract long getExtendedTdImportTime();
	public abstract long getTiImportTime();
	public abstract long getShortIndexationTime();
	public abstract long getLongIndexationTime();
	public abstract long getToursCount();
	public abstract long getTourPairsCount();
	public abstract long getDeparturesCount();
	public abstract long getAllTDXMLSize();
	public abstract long getAllTIXMLSize();
}
