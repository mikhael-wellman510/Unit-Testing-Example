package com.example.Sport_Station.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdfService {

		public Resource getPdf(){

			return new ClassPathResource("Mikhael Wellman.pdf");
		}
}
