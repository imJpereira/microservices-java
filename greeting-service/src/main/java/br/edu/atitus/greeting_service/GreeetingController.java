package br.edu.atitus.greeting_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting-service")
public class GreeetingController {
	
	
	@GetMapping("/hello-world")
	public ResponseEntity<String> helloWorld() throws Exception {
		return ResponseEntity.ok("Hello World");
	}
	
}
