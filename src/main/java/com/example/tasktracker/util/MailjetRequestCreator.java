package com.example.tasktracker.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import com.example.tasktracker.dto.EmailMessage;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.resource.Emailv31;

public class MailjetRequestCreator {

	@Value("${spring.mail.sender-email}")
	private String senderEmail;
	
	public MailjetRequest createFrom(EmailMessage messageDetails) {
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
