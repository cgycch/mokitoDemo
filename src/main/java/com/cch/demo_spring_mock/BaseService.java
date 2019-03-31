package com.cch.demo_spring_mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseService {
	
	@Autowired
	private MessageSender messageSender;
	
	
	private String env;
	
	public BaseService() {
		System.out.println("##############BaseService init");
	}
	public BaseService(String env) {
		super();
		this.env = env;
		System.out.println("##############BaseService(String) init");
	}


	public String getEnv() {
		return env;
	}


	public void setEnv(String env) {
		this.env = env;
	}


	public void execute() {
		if(env==null) {
			throw new RuntimeException("invalid params");
		}else if(env.equals("PROD")) {
			System.out.println("execute() is running...");
			//1 get data list
			List<String> datas = getDataSources();
			//2 generate files
			List<String> files = generateFiles(datas);
			System.out.println("generate File size is "+files.size());
			//3 sent message
			boolean status = messageSender.sendMessage(env, "hello~~~");
			System.out.println("message has been sent: "+ status);
			String reply = messageSender.getReply();
			System.out.println("reply: " + reply);
			messageSender.close();
			//4 update status
			updateStatus();
		}else {
			throw new RuntimeException("invalid params");
		}
	}
	
	private List<String> generateFiles(List<String> datas) {
		System.out.println("generateFiles....");
		List<String> list = new ArrayList<>();
		list.add("file1");
		list.add("file2");
		return list;
	}
	protected void updateStatus() {
		System.out.println("updateStatus....");
	}
	
	protected List<String> getDataSources(){
		if(!env.equals("PROD")) {
			throw new RuntimeException("you could not access method getDataSources().");
		}
		System.out.println("getDataSources....");
		List<String> list = new ArrayList<>();
		list.add("str1");
		list.add("str2");
		list.add("str3");
		return list;
	}
	

}
