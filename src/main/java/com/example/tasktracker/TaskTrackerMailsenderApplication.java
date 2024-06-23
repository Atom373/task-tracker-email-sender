package com.example.tasktracker;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class TaskTrackerMailsenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskTrackerMailsenderApplication.class, args);
	}

}
