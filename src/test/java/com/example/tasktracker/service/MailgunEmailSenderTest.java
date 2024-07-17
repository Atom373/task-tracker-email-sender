package com.example.tasktracker.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tasktracker.dto.EmailMessage;
import com.example.tasktracker.service.impl.MailgunEmailSender;
import com.mailgun.api.v3.MailgunDomainsApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.domains.DomainListResponse;

import lombok.extern.slf4j.Slf4j;
import net.sargue.mailgun.Response;

@Slf4j
@SpringBootTest
public class MailgunEmailSenderTest {

	private static final String reciver = "pvana7065@gmail.com";
	private static final String subject = "Test message";
	private static final String body = "Some text for testing...";
	
	@Autowired
	private MailgunEmailSender sender;
	
	//@Test
	public void emailSendingTest() {
		EmailMessage msg = new EmailMessage(reciver, subject, body);
		Response response = null;
		try {
			response = sender.send(msg);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		assertNotNull(response);
		assertEquals(200, response.responseCode());
	}
}
