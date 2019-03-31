package com.cch.examples;

//Let's import Mockito statically so that the code looks clearer
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

public class MockMethod {
	@Test
	public void test() {
		// mock creation
		List mockedList = mock(List.class);
		// using mock object
		mockedList.add("one");
		mockedList.clear();

		// verification
		verify(mockedList).add("one");
		verify(mockedList).clear();

	}

}
