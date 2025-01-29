package com.ashutosh.mockito.mockito_demo.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SomeBusinessImplTest {

	@Test
	void test() {
		DataServiceStub dataServiceStub = new DataServiceStub();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
	    int	result =businessImpl.findTheGretestFromAllData();
		assertEquals(25, result);
	}
	
	@Test
	void test1() {
		DataServiceStub dataServiceStub = new DataServiceStub();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
	    int	result =businessImpl.findTheGretestFromAllData();
		assertEquals(25, result);
	}

}

class DataServiceStub implements DataService{
	@Override
	public int[] retiveAllData() {
		return new int[] {25,15,5};
		
	}
}

class DataServiceStub1 implements DataService{
	@Override
	public int[] retiveAllData() {
		return new int[] {25};
		
	}
}