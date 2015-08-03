package com.ttc.ch2.bl.message;

import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.Brand;

public interface MailSenderService {

	static final String SMTP_ERROR = "Email message has not been sent";
	
	public void sendMessage(ProcessName processName, String messageSubject, String messageBody, String fromAddress, boolean isHtml, Brand brand, String... toAddress) throws MailSenderServiceException;
}
