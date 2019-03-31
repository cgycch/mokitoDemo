package com.cch.examples;

import static org.mockito.Mockito.*;

import com.cch.BaseMockito;

public class IterationStyleTest extends BaseMockito {
	@Override
	public void test() {
		MyTest mock = mock(MyTest.class);
		//when(mock.someMethod("some arg")).thenThrow(new RuntimeException()).thenReturn("foo");
		when(mock.someMethod("some arg"))
		   .thenReturn("one", "two", "three");

		// First call: throws runtime exception:
		try {
			System.out.println(mock.someMethod("some arg"));
		} catch (Exception e) {
			System.out.println("error has happend on first call");
		}

		// Second call: prints "foo"
		System.out.println(mock.someMethod("some arg"));

		// Any consecutive call: prints "foo" as well (last stubbing wins).
		System.out.println(mock.someMethod("some arg"));
		System.out.println(mock.someMethod("some arg1"));
		

	}

	private class MyTest {
		public String someMethod(String str) {
			System.out.println("someMethod:str==" + str);
			return "" + str;
		}
	}
}
