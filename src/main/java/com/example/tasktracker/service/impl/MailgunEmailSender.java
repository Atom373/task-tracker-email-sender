package com.example.tasktracker.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.example.tasktracker.dto.EmailMessage;
import com.example.tasktracker.service.EmailSender;

import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.Mail;
import net.sargue.mailgun.Response;

@Component
@ConditionalOnProperty(name = "spring.email.provider", havingValue = "mailgun")
public class MailgunEmailSender extends EmailSender {
	 
	//private static final String EU_BASE_URL = "https://api.eu.mailgun.net/v3";

	@Override
	public Response send(EmailMessage messageDetails) throws Exception {
		Configuration configuration = new Configuration()
			    .domain(System.getenv("MG_DOMAIN"))
			    .apiKey(System.getenv("MG_API_KEY"))
			    .from("Task tracker", senderEmail);
		return Mail.using(configuration)
		    .to(messageDetails.getReceiver())
		    .subject(messageDetails.getSubject())
		    .text(messageDetails.getBody())
		    .build()
		    .send();
	}
}
