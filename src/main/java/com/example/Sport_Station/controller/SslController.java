package com.example.Sport_Station.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class SslController {

	@GetMapping("/testSsl")
	public ResponseEntity<?> testSsl(@RequestParam(name = "name" , defaultValue = "")String name){
		log.info("Cek  :{} " , name);
		Map<String , Object> data = new HashMap<>();

		data.put("name" , "Mikhael");
		data.put("address" , "Bogor");

		return ResponseEntity.ok(data);
	}
}
