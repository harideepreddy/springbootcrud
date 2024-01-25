package com.webservice.Restful.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	@GetMapping("/getValues")
	public MappingJacksonValue filter() {
		
		FilterBean filterBean =	new FilterBean("value1", "value2", "value3");
		MappingJacksonValue mapingJacksonValue = new MappingJacksonValue(filterBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1","value3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", filter );
		mapingJacksonValue.setFilters(filters);
		return mapingJacksonValue;
	}
	
	@GetMapping("/getFilterList")
	public List<FilterBean> filterList() {
		
		return Arrays.asList(new FilterBean("value1","value2","value3"), 
				new FilterBean("value4","value5","value6"));
		
	}
}
