package com.example.tasktracker.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.tasktracker.dto.EmailMessage;
import com.example.tasktracker.service.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener {

	private final EmailSender sender;
	
	@RabbitListener(queues = "EMAIL_SENDING_TASKS")
	public void handleMessage(EmailMessage messageDetails) {
		try {
			sender.send(messageDetails);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}
}
