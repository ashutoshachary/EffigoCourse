package com.ashutosh.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class MyAssertTest {
	
	List<String> todos = Arrays.asList("Aws","Azure", "DevOps");

	@Test
	void test() {
		
		boolean test = todos.contains("Aws");
		boolean test1 = todos.contains("CG");
		assertEquals(true, test);
		assertFalse(test1);
		assertArrayEquals(new int[] {1,2}, new int[] {2});
		assertEquals(3, todos.size());
	}

}
