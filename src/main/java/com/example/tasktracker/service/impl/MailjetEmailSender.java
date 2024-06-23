package com.example.tasktracker.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.example.tasktracker.dto.EmailMessage;
import com.example.tasktracker.service.EmailSender;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;

@Component
@ConditionalOnProperty(name = "spring.email.provider", havingValue = "mailjet")
public class MailjetEmailSender extends EmailSender {
	
	@Override
	public MailjetResponse send(EmailMessage messageDetails) throws MailjetException, MailjetSocketTimeoutException {
		MailjetClient client = getClient();
		MailjetRequest request = createRequest(messageDetails);
		
		return client.post(request);
	}

	private MailjetClient getClient() {
		MailjetClient client = new MailjetClient(
				System.getenv("MJ_API_KEY"),
				System.getenv("MJ_SECRET_KEY"),
				new ClientOptions("v3.1")
		);
		return client;
	}
	
	private MailjetRequest createRequest(EmailMessage messageDetails) {
		MailjetRequest request;
		
		JSONArray messages = new JSONArray();
		
		JSONObject from = new JSONObject()
								.put("Email", senderEmail)
								.put("Name", "Task tracker email sending system");
		JSONArray to = new JSONArray()
								.put(new JSONObject()
										.put("Email", messageDetails.getReceiver())
								);
		JSONObject message = new JSONObject()
									.put(Emailv31.Message.FROM, from)
									.put(Emailv31.Message.TO, to)
									.put(Emailv31.Message.SUBJECT, messageDetails.getSubject())
				                    .put(Emailv31.Message.TEXTPART, messageDetails.getBody());
		messages.put(message);
		
		request = new MailjetRequest(Emailv31.resource)
						.property(Emailv31.MESSAGES, messages);
		
		return request;
	}
}
