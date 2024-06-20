package com.example.tasktracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mailjet.client.MailjetClient;

@Configuration
public class MailJetConfig {

	@Bean
	@SuppressWarnings("deprecation")
	public MailjetClient client() {
		MailjetClient client = new MailjetClient(
	    		System.getenv("MJ_API_KEY"), 
	    		System.getenv("MJ_SECRET_KEY")
	    );
	    return client;
	}
}
