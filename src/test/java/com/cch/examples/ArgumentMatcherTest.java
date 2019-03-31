package com.cch.examples;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;

import com.cch.BaseMockito;

public class ArgumentMatcherTest extends BaseMockito {

	@Mock
	MyService myService;

	@SuppressWarnings("unchecked")
	@Override
	public void test() {
		// You can mock concrete classes, not just interfaces
		LinkedList mockedList = mock(LinkedList.class);
		// stubbing using built-in anyInt() argument matcher
		when(mockedList.get(anyInt())).thenReturn("element");

		// stubbing using custom matcher (let's say isValid() returns your own matcher
		// implementation):
		// when(mockedList.contains(argThat(isSize2()))).thenReturn(true);
		when(mockedList.contains(argThat(isContainHello("hello1")))).thenReturn(true);
		// when(mockedList.contains(argThat(isContainHello()))).thenReturn(true);

		// following prints "element"
		System.out.println("999:" + mockedList.get(999));
		// System.out.println("list:" + mockedList.contains(new ArrayList<>()));
		System.out.println("hello:" + mockedList.contains("hello"));

		myService.test(1, "2", true);

		// you can also verify using an argument matcher
		verify(mockedList).get(anyInt());
		// verify(mockedList).contains(anyList());
		verify(mockedList).contains(anyString());
        // When using matchers, all arguments have to be provided by matchers.
		verify(myService).test(anyInt(), anyString(), eq(true));

	}

	class MyService {
		public void test(int x, String y, boolean b) {
			System.out.println("MyService::x  " + x);
			System.out.println("MyService::y  " + y);
			System.out.println("MyService::b  " + b);
		}
	}

	protected Matcher isSize2() {
		Matcher matcher = new ArgumentMatcher<List>() {

			@Override
			public boolean matches(Object list) {
				boolean isMatch = false;
				if (list != null && ((List) list).size() == 2) {
					isMatch = true;
				}
				return isMatch;
			}
		};
		return matcher;
	}

	protected Matcher<String> isContainHello(String str) {
		Matcher<String> matcher = new IsEqual<String>(str) {
			@Override
			public boolean matches(Object str2) {
				System.out.println("str: " + str);
				System.out.println("str2: " + str2);
				boolean isMatch = false;
				if (str2 != null && str2.equals("hello")) {
					isMatch = true;
				}
				return isMatch;
			}
		};
		return matcher;
	}

	protected Matcher<String> isContainHello() {
		Matcher<String> matcher = new IsEqual<String>(null) {
			@Override
			public boolean matches(Object str) {
				boolean isMatch = false;
				if (str != null && str.equals("hello")) {
					isMatch = true;
				}
				return isMatch;
			}
		};
		return matcher;
	}

}
