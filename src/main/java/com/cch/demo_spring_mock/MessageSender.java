package com.cch.demo_spring_mock;

import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	public boolean sendMessage(String env, String message) {
		if (!"PROD".equals(env)) {
			throw new RuntimeException("envirment not permit");
		}
		System.out.println("sendding message... " + message);
		return true;
	}

	public void close() {
		System.out.println("MessageSender is closing...");
	}
	public String getReply() {
		System.out.println("getReply...");
		return "received";
	}

}
