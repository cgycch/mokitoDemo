package com.cch.example;

import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import com.cch.demo.ApiService;
import com.cch.demo.TestApiService;
import com.cch.example.baseutil.AopTargetUtils;
import com.cch.example.baseutil.BaseSpringUnit;

public class SpringMockitoTest extends BaseSpringUnit {

	@Autowired
	private ApiService apiService;

	@Autowired
	private TestApiService testApiService;

	@Mock
	private TestApiService spyTestApiService;

	@Before
	public void initMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(AopTargetUtils.getTarget(apiService), "testApiService", spyTestApiService);
		when(spyTestApiService.connect()).thenReturn("successful");
	}

	@After
	public void clearMocks() throws Exception {
		ReflectionTestUtils.setField(AopTargetUtils.getTarget(apiService), "testApiService", testApiService);
	}

	@Test
	public void should_success_when_testApiService() {
		String result = apiService.connectStatus();
		Assert.assertEquals("connection is successful", result);
	}
}
