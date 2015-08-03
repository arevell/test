package com.ttc.ch2.api.ccapi.v3;

import javax.xml.bind.annotation.XmlTransient;

public class SearchResultsExtended extends SearchResults {

	@XmlTransient
	protected boolean onlineBookable;

	public boolean isOnlineBookable() {
		return onlineBookable;
	}

	public void setOnlineBookable(boolean onlineBookable) {
		this.onlineBookable = onlineBookable;
	}
}
