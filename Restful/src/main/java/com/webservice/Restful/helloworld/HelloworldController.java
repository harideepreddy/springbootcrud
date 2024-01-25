package com.webservice.Restful.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {

private MessageSource messageSource;
	
	private HelloworldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@GetMapping(path="/helloworld")
			public String helloworld() {
		return "Hello There";
	}
	
	@GetMapping(path="/helloworld-bean")
	public HelloworldBean helloworldbean() {
		return new HelloworldBean("Hello Bean");
	}

	@GetMapping(path="/helloworld/pathvarible/{name}")
	public HelloworldBean pathVarible(@PathVariable String name) {
		return new HelloworldBean(String.format("Hello %s", name));
	}
	
	@GetMapping(path="/helloworld-i18n")
	public String helloworldI18n() {
		Locale Locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", Locale );
}
	
}
