package com.ttc.ch2.bl.departure;

import com.ttc.ch2.common.TypeMsg;
import com.ttc.ch2.domain.departure.ImportStatus.ProcessLevel;

public interface ImportStatusService {

	void setupMessage(String message, String brandCode, TypeMsg typeMsg,ProcessLevel processLevel);

	void clearStatus();

	void clearStatus(String brandCode);

}