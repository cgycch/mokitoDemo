package com.cch;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public abstract class BaseMockito {

	@Before
	public void initMocks() {
		System.out.println("initMocks...");
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void test() {
		System.out.println("BaseMockito::test()");
	}
}
