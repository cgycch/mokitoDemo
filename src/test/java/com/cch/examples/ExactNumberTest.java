package com.cch.examples;

import static org.mockito.Mockito.*;

import java.util.LinkedList;

import com.cch.BaseMockito;

public class ExactNumberTest extends BaseMockito {
	
	@Override
	public void test() {
		// You can mock concrete classes, not just interfaces
		LinkedList mockedList = mock(LinkedList.class);
	
		// using mock
		mockedList.add("once");

		mockedList.add("twice");
		mockedList.add("twice");

		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");


		// following two verifications work exactly the same - times(1) is used by
		// default
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");

		// exact number of invocations verification
		verify(mockedList, times(2)).add("twice");
		verify(mockedList, times(3)).add("three times");

		// verification using never(). never() is an alias to times(0)
		verify(mockedList, never()).add("never happened");

		// verification using atLeast()/atMost()
		verify(mockedList, atLeastOnce()).add("three times");
		verify(mockedList, atLeast(2)).add("three times");
		verify(mockedList, atMost(5)).add("three times");
		
		
		//mockedList.add("test");
		//verify(mockedList).add("test");
		//verifyNoMoreInteractions(mockedList);
		
		LinkedList mockTwo = mock(LinkedList.class);
		LinkedList mockThree = mock(LinkedList.class);
		//mockTwo.add("test");
		//verify(mockTwo, atLeastOnce()).add("test");
		verifyZeroInteractions(mockTwo, mockThree);
		System.out.println("in the end ");
	}
}
