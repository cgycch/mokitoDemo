package com.cch.example;

import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import com.cch.demo_spring_mock.BaseService;
import com.cch.demo_spring_mock.MessageSender;
import com.cch.example.baseutil.AopTargetUtils;
import com.cch.example.baseutil.BaseSpringUnit;

public class BaseServiceTest extends BaseSpringUnit {

	@Autowired
	private BaseService baseService;

	@Autowired
	private MessageSender messageSender;

	@Mock
	private MessageSender spyMessageSender;

	@Before
	public void initMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
		baseService.setEnv("PROD");
		ReflectionTestUtils.setField(AopTargetUtils.getTarget(baseService), "messageSender", spyMessageSender);
		// do some mock here
		when(spyMessageSender.sendMessage("PROD", "hello~~~")).thenReturn(true);
		// when(spyMessageSender.sendMessage("PROD", "hello~~~")).then(RETURNS_MOCKS);
		Mockito.doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				System.out.println("called getReply with arguments size: " + args.length);
				return "no received";
			}
		}).when(spyMessageSender).getReply();
		
		Mockito.doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				System.out.println("called close with arguments size: " + args.length);
				return null;
			}
		}).when(spyMessageSender).close();
	}

	@After
	public void clearMocks() throws Exception {
		baseService.setEnv(null);
		ReflectionTestUtils.setField(AopTargetUtils.getTarget(baseService), "messageSender", messageSender);
	}

	@Test
	public void test() {
		baseService.execute();
	}

}
