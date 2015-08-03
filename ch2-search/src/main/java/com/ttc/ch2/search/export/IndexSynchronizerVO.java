package com.ttc.ch2.search.export;

import java.util.ArrayList;
import java.util.List;

public class IndexSynchronizerVO {

	private List<String> documentIDsListNotAdded;
	private List<String> documentIDsListNotDeleted;
	private List<String> aggregatedIDsListNotAdded;
	private List<String> aggregatedIDsListNotDeleted;

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

		return  (documentIDsListNotAdded != null && documentIDsListNotAdded.size() > 0) ||
				(documentIDsListNotDeleted != null && documentIDsListNotDeleted.size() > 0) ||
				(aggregatedIDsListNotAdded != null && aggregatedIDsListNotAdded.size() > 0) ||
				(aggregatedIDsListNotDeleted != null && aggregatedIDsListNotDeleted.size() > 0);
	}
}
