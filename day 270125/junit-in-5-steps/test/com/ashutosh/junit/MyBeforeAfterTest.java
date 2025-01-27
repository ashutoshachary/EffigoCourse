package com.ashutosh.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyBeforeAfterTest {
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("BeforeAll");
	}
	
	@BeforeEach
	void beforeEach() {
		System.out.println("BeforeEach");
		
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("AfterEach");
		
	}

	@Test
	void test1() {
		System.out.println("test1");
	}
	
	@Test
	void test2() {
		System.out.println("test2");
	}
	
	@Test
	void test3() {
		System.out.println("test3");
	}
	
	@Test
	void test4() {
		System.out.println("test4");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("After All");
	}

}
