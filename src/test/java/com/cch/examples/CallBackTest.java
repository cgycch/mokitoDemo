package com.cch.examples;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.cch.BaseMockito;

public class CallBackTest extends BaseMockito {
	@Override
	public void test() {
		MyTest mock = mock(MyTest.class);
		when(mock.someMethod(anyString())).thenAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				Object mock = invocation.getMock();
				System.out.println("in hashcode:"+mock.hashCode());
				return "called with arguments: " + Arrays.toString(args);
			}
		});

		// Following prints "called with arguments: [foo]"
		System.out.println("out hashcode:"+mock.hashCode());
		System.out.println(mock.someMethod("foo"));
	}

	private class MyTest {
		public String someMethod(String str) {
			System.out.println("someMethod:str==" + str);
			return "" + str;
		}
	}
}
