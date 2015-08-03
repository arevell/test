package com.ttc.ch2.search.export;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IndexSynchronizerVO implements Serializable  {

	private static final long serialVersionUID = 4474175793918362120L;
			
	private Boolean indexingAll;
	private boolean unexpectedErrorOccurred;
	private long documentCount;
	private long aggregatedCount;
				
	private List<String> documentIDsListNotAdded;
	private List<String> documentIDsListNotDeleted;
	private List<String> aggregatedIDsListNotAdded;
	private List<String> aggregatedIDsListNotDeleted;
	
	public IndexSynchronizerVO(){
		
	}
	
	public IndexSynchronizerVO(boolean indexingAll){
		this.indexingAll=indexingAll;
	}
	
	

	public List<String> getDocumentIDsListNotAdded() {

		if (documentIDsListNotAdded == null) {
			documentIDsListNotAdded = new ArrayList<String>();
		}

		return documentIDsListNotAdded;
	}

	public List<String> getDocumentIDsListNotDeleted() {

		if (documentIDsListNotDeleted == null) {
			documentIDsListNotDeleted = new ArrayList<String>();
		}

		return documentIDsListNotDeleted;
	}

	public List<String> getAggregatedIDsListNotAdded() {

		if (aggregatedIDsListNotAdded == null) {
			aggregatedIDsListNotAdded = new ArrayList<String>();
		}

		return aggregatedIDsListNotAdded;
	}

	public List<String> getAggregatedIDsListNotDeleted() {

		if (aggregatedIDsListNotDeleted == null) {
			aggregatedIDsListNotDeleted = new ArrayList<String>();
		}

		return aggregatedIDsListNotDeleted;
	}

	public boolean hasErrors() {

		return   unexpectedErrorOccurred ||
				(documentIDsListNotAdded != null && documentIDsListNotAdded.size() > 0) ||
				(documentIDsListNotDeleted != null && documentIDsListNotDeleted.size() > 0) ||
				(aggregatedIDsListNotAdded != null && aggregatedIDsListNotAdded.size() > 0) ||
				(aggregatedIDsListNotDeleted != null && aggregatedIDsListNotDeleted.size() > 0);
	}



	public Boolean getIndexingAll() {
		return indexingAll;
	}

	public void setIndexingAll(Boolean indexingAll) {
		this.indexingAll = indexingAll;
	}
	
	public Boolean getUnexpectedErrorOccurred() {
		return unexpectedErrorOccurred;
	}

	public void setUnexpectedErrorOccurred(Boolean unexpectedErrorOccurred) {
		this.unexpectedErrorOccurred = unexpectedErrorOccurred;
	}


	public long getDocumentCount() {
		return documentCount;
	}

	public void setDocumentCount(long documentCount) {
		this.documentCount = documentCount;
	}

	public long getAggregatedCount() {
		return aggregatedCount;
	}

	public void setAggregatedCount(long aggregatedCount) {
		this.aggregatedCount = aggregatedCount;
	}	
}
