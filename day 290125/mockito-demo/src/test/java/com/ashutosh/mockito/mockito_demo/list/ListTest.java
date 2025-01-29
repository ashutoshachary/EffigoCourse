package com.ashutosh.mockito.mockito_demo.list;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListTest {

	@Test
	void test() {
		List listMock = mock(List.class);
		
		when(listMock.size()).thenReturn(3);
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
		
	}
	
	@Test
	void test1() {
		List listMock = mock(List.class);
		
		when(listMock.size()).thenReturn(3).thenReturn(2).thenReturn(1);
		assertEquals(3, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(1, listMock.size());
		
	}
	
	@Test
	void test2() {
		List listMock = mock(List.class);
		
		when(listMock.get(0)).thenReturn("Somestring").thenReturn(null);
		assertEquals("Somestring", listMock.get(0));
		assertEquals(null, listMock.get(1));
		
		
	}
	
	@Test
	void test3() {
		List listMock = mock(List.class);
		
		when(listMock.get(Mockito.anyInt())).thenReturn("SomeOtherString");
		assertEquals("SomeOtherString", listMock.get(0));
		assertEquals("SomeOtherString", listMock.get(1));
		assertEquals("SomeOtherString", listMock.get(2));
		
		
	}

}
