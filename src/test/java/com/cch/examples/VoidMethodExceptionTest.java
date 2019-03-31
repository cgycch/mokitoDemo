package com.cch.examples;

import static org.mockito.Mockito.*;

import java.util.LinkedList;

import com.cch.BaseMockito;

public class VoidMethodExceptionTest extends BaseMockito {
	@Override
	public void test() {
		LinkedList mockedList = mock(LinkedList.class);
		doThrow(new RuntimeException()).when(mockedList).clear();

		// following throws RuntimeException:
		mockedList.clear();
		System.out.println("in the end");
	}

}
