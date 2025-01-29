package com.ashutosh.mockito.mockito_demo.business;

public class SomeBusinessImpl {
	private DataService dataService;
	
	public SomeBusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}

	public int findTheGretestFromAllData() {
		int[] data = dataService.retiveAllData();
		int gretestValue = Integer.MIN_VALUE;
		
		for(int value: data) {
			if(value > gretestValue)
				gretestValue = value;
		}
		return gretestValue;
	}

}


interface DataService{
	int[] retiveAllData();
}
