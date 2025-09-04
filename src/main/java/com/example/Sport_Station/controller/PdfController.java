package com.example.Sport_Station.controller;

import com.example.Sport_Station.service.impl.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController {

	private final PdfService pdfService;

	@GetMapping("/getPdf")
	public ResponseEntity<?>getPdf(){
		Resource rs = pdfService.getPdf();


		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"contoh.pdf\"")
				.body(rs);
	}
}
