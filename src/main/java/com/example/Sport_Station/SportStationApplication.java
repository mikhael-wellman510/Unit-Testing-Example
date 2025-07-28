package com.example.Sport_Station;


import com.example.MikhaelLib.utils.HelloUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SportStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportStationApplication.class, args);

		String tes = HelloUtils.hello("Cek");
		log.info("TEs : {} " , tes);

	}

}
