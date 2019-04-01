package com.cch.examples;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.cch.BaseMockito;

public class SpyTest extends BaseMockito {
	@Override
	public void test() {
		List list = new LinkedList();
		List spy = spy(list);

		// optionally, you can stub out some methods:
		when(spy.size()).thenReturn(100);

		// using the spy calls *real* methods
		spy.add("one");
		spy.add("two");
		spy.add("three");

		// prints "one" - the first element of a list
		System.out.println(spy.get(0));

		// size() method was stubbed - 100 is printed
		System.out.println(spy.size());

		// optionally, you can verify
		verify(spy).add("one");
		verify(spy).add("two");
		// verify(spy).add("four");

		// Impossible: real method is called so spy.get(3) throws
		// IndexOutOfBoundsException
		// when(spy.get(3)).thenReturn("foo");

		// You have to use doReturn() for stubbing
		doReturn("foo").when(spy).get(3);
		System.out.println(spy.get(3));

	}

	@Test
	public void mMyServiceTest() {
		// MyService spyService = mock(MyService.class, RETURNS_DEEP_STUBS);
		// when(spyService.getProList().isEmpty()).thenReturn(false);
		MyService spyService = spy(new MyService());
		// doNothing().when(spyService).callVoid();
		// doCallRealMethod().when(spyService).callVoid();
		// doAnswer(CALLS_REAL_METHODS).when(spyService).callVoid();
		// doReturn(true).when(spyService).getBool();

		// List<String> list = new ArrayList<>();
		// list.add("hello");
		// doReturn(list).when(spyService).getProList();
		
		doAnswer(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("invocation...");
				System.out.println("method name: "+invocation.getMethod().getName());
				System.out.println("getArguments length: "+invocation.getArguments().length);
				return true;
			}
		}).when(spyService).getBool();

		spyService.callVoid();
		System.out.println(spyService.getBool());
		System.out.println(spyService.getProList().size());
	}

	private class MyService {
		private List<String> proList = new ArrayList<>();

		public MyService() {
			System.out.println("MyService is initialize...");
		}

		public void callVoid() {
			System.out.println("MyService::callVoid()...");
		}

		public boolean getBool() {
			System.out.println("MyService::getBool()...");
			return false;
		}

		public List<String> getProList() {
			System.out.println("MyService::getProList()...");
			return proList;
		}

	}
}
