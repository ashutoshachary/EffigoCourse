package com.ashutosh.rest.webservices.restful_web_services.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.engine.spi.Mapping;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		// dynamic filtering 
		SomeBean someBean = new SomeBean("value1","value2","value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue; 
	}
	
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {
		List<SomeBean> list= Arrays.asList(
				new SomeBean("Value1", "Value2", "Value3"),
				new SomeBean("Test1", "Test2", "Test3"),
				new SomeBean("Group1", "Group2", "Group3")
				);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		
SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}

}
