package com.ttc.ch2.search.export;

import com.ttc.ch2.domain.export.CRExport;

public class IndexSynchronizerServiceException extends Exception {
	
	private CRExport crexport;
	
	
	public IndexSynchronizerServiceException() {
		super();
	}
	
	public IndexSynchronizerServiceException(Exception e) {
		super(e);
	}

	public IndexSynchronizerServiceException(String message) {
		super(message);
	}

	public IndexSynchronizerServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public CRExport getCrexport() {
		return crexport;
	}

	public void setCrexport(CRExport crexport) {
		this.crexport = crexport;
	}
}
