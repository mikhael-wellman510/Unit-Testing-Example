package com.example.Sport_Station.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SampleApi {

	public String tt2(){
		return "s";
	}

	public String tt(){

		return "";
	}

	@GetMapping("/test1")
	public String test1C() throws InterruptedException {

		Map<String,Object> data = new HashMap<>();

		data.put("nama" , "mike");
		Thread.sleep(3000);
		return "mike";
	}

	@GetMapping("/test2")
	public String test2c() throws InterruptedException {
		Thread.sleep(5000);
		return "bogor";
	}

	@GetMapping("/test3")
	public String test3c() throws InterruptedException {
		Thread.sleep(1000);
		return "backend";
	}
}
