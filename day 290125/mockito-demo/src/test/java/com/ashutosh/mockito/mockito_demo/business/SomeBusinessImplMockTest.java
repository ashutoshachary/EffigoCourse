package com.ashutosh.mockito.mockito_demo.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class SomeBusinessImplMockTest {
	
	@Mock
	private DataService dataServiceMock;
	
	@InjectMocks
	private SomeBusinessImpl businessImpl;

	
	
	@Test
	void test1() {
//		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retiveAllData()).thenReturn(new int[] {25,15,5});
//		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
//	    int	result =businessImpl.findTheGretestFromAllData();
//		assertEquals(25, result);
		assertEquals(25, businessImpl.findTheGretestFromAllData());
	}
	
	@Test
	void test2() {
//		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retiveAllData()).thenReturn(new int[] {25});
//		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
//	    int	result =businessImpl.findTheGretestFromAllData();
//		assertEquals(25, result);
		assertEquals(25, businessImpl.findTheGretestFromAllData());
	}

}
