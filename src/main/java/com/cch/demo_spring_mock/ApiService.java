package com.cch.demo_spring_mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiService {
 
    @Autowired
    private TestApiService testApiService;
 
    public String connectStatus() {
        return "connection is "+ testApiService.connect();
    }
}
