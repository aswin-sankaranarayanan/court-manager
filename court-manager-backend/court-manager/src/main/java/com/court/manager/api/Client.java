package com.court.manager.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class Client {

	@GetMapping
	public String getClient() {
		return "Hi";
	}
}
