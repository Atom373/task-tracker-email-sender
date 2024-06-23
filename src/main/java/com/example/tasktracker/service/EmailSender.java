package com.example.tasktracker.service;

import org.springframework.beans.factory.annotation.Value;

import com.example.tasktracker.dto.EmailMessage;

public abstract class EmailSender {

	@Value("${spring.email.box}")
	protected String senderEmail;
	
	public abstract Object send(EmailMessage messageDetails) throws Exception;
	
}
