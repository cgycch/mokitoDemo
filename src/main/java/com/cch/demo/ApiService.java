package com.cch.demo;

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
