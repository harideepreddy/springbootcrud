package com.webservice.Restful.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	@GetMapping(path="/V1/getName")
	private Person1 getFirstPersonName() {
		return new Person1("Harideep Reddy");
	}
	
	@GetMapping(path="/V2/getName")
	private Person2 getSecondPersonName() {
		return new Person2(new Name("Harideep", "Reddy"));
	}
}
