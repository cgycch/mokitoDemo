package com.cch.examples;
import static org.mockito.Mockito.*;

import java.util.List;

import org.mockito.InOrder;

import com.cch.BaseMockito;

public class VerifyInOrder extends BaseMockito{
	@Override
	public void test() {

		 // A. Single mock whose methods must be invoked in a particular order
		 List singleMock = mock(List.class);

		 //using a single mock
		 singleMock.add("was added first");
		 singleMock.add("was added second");

		 //create an inOrder verifier for a single mock
		 InOrder inOrder = inOrder(singleMock);

		 //following will make sure that add is first called with "was added first", then with "was added second"
		 inOrder.verify(singleMock).add("was added first");
		 inOrder.verify(singleMock).add("was added second");

		 // B. Multiple mocks that must be used in a particular order
		 List firstMock = mock(List.class);
		 List secondMock = mock(List.class);

		 //using mocks
		 firstMock.add("was called first");
		 secondMock.add("was called second");

		 //create inOrder object passing any mocks that need to be verified in order
		 InOrder inOrder2 = inOrder(firstMock, secondMock);

		 //following will make sure that firstMock was called before secondMock
		 inOrder2.verify(firstMock).add("was called first");
		 inOrder2.verify(secondMock).add("was called second");

		 // Oh, and A + B can be mixed together at will
		 
	}
}
