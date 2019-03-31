package com.cch.examples;

import static org.mockito.Mockito.*;
import com.cch.BaseMockito;

public class DoTest extends BaseMockito {
	@Override
	public void test() {
		MyTest mock = mock(MyTest.class, CALLS_REAL_METHODS);
		doReturn("1").when(mock).someMethod(anyString());
		// doReturn("2").when(mock).someMethod2(anyString()); //error
		doNothing().doThrow(new RuntimeException("exception mock")).when(mock).someMethod2(anyString());

		mock.someMethod("hello");
		try {
			mock.someMethod2("hello");
		} catch (Exception e) {
			System.out.println("error has happend 1");
		}
		try {
			mock.someMethod2("hello");
		} catch (Exception e) {
			System.out.println("error has happend 2");
		}
		String value = mock.toString();
		System.out.println(value);

		when(mock.toString()).thenReturn("fakeValue");

		// now fakeValue is returned
		value = mock.toString();
		System.out.println(value);


		doAnswer(CALLS_REAL_METHODS).when(mock).toString();
		value = mock.toString();
		System.out.println(value);
		
		doCallRealMethod().when(mock).hashCode();
		System.out.println(mock.hashCode());
		
	}

	private class MyTest {
		public String someMethod(String str) {
			System.out.println("someMethod:str==" + str);
			return "" + str;
		}

		public void someMethod2(String str) {
			System.out.println("someMethod2:str==" + str);
		}

		@Override
		public String toString() {
			return "MyTest [toString()=" + super.toString() + "]";
		}

	}
}
