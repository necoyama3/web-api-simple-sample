package com.example;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/test")
public class MainController {

	@GetMapping(value = "/allHeaders")
	public ResponseEntity<String> listAllHeaders(@RequestHeader Map<String, String> headers, @RequestBody String access_token) {
		String json = "";
		try {
			json = new ObjectMapper().writeValueAsString(headers);
			headers.forEach((key, value) -> {
				System.out.println(String.format("Header '%s' = %s", key, value));
			});

			return new ResponseEntity<String>(json, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
