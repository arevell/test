package com.ttc.ch2.bl.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.messages.EmailHistory;
import com.ttc.ch2.domain.messages.EmailHistory.EmailStatus;

@Service
public class MailSenderServiceImpl implements MailSenderService {

	private final static String ERROR_NO_RECIPIENT_DEFINED = "Error during sending a message: no recipient has been defined.";

	private static final String UNDEFINED = "UNDEFINED";

	@Inject
	private JavaMailSender mailSender;

	@Inject
	private MessagesService messagesService;

	@Override
	public void sendMessage(ProcessName processName, String messageSubject, String messageBody, String fromAddress, boolean isHtml, Brand brand, String... toAddress) throws MailSenderServiceException {

		EmailHistory emailHistory = new EmailHistory();

		emailHistory.setProccessName(processName);
		emailHistory.setSubject(StringUtils.defaultIfBlank(messageSubject, UNDEFINED));
		emailHistory.setMessage(StringUtils.defaultIfBlank(messageBody, UNDEFINED));
		emailHistory.setFrom(StringUtils.defaultIfBlank(fromAddress, UNDEFINED));
		emailHistory.setTo(toAddress != null && StringUtils.isNotBlank(StringUtils.join(toAddress)) ? StringUtils.join(toAddress, ";") : UNDEFINED);
		emailHistory.setMessageDeliveryTime(new Date());
		emailHistory.setBrand(brand);

		try {

			MimeMessage message = mailSender.createMimeMessage();

			message.setSubject(messageSubject);
			message.setFrom(new InternetAddress(fromAddress));

			if (isHtml) {
				message.setContent(messageBody, "text/html");
			} else {
				message.setText(messageBody);
			}

			if (toAddress != null && toAddress.length == 1) {

				message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress[0]));

			} else if (toAddress != null && toAddress.length > 1) {

				List<Address> addressesList = new ArrayList<Address>();

				for (String address : toAddress) {
					addressesList.add(new InternetAddress(address));
				}

				message.setRecipients(Message.RecipientType.TO, addressesList.toArray(new Address[addressesList.size()]));

			} else {
				throw new MailSenderServiceException(ERROR_NO_RECIPIENT_DEFINED);
			}

			mailSender.send(message);

			emailHistory.setStatus(EmailStatus.Success);

		} catch (Exception e) {

			emailHistory.setStatus(EmailStatus.ServerFail);

			throw new MailSenderServiceException(e);

		} finally {
			messagesService.saveEmailHistory(emailHistory);
		}
	}
}
