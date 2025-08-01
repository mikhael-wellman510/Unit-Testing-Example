package com.example.Sport_Station.controller;

import com.example.Sport_Station.service.impl.AsyncSimulation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AsyncSimulationController {

    private final AsyncSimulation asyncSimulation;

    @GetMapping("/getAsync1")
    public ResponseEntity<?>getProfileAsync1() throws InterruptedException {

        var data = asyncSimulation.profile1();
        log.info("Data 1 : {} " , data);
        return ResponseEntity.ok(data);

    }

    @GetMapping("/getAsync2")
    public ResponseEntity<?>getProfileAsync2() throws InterruptedException {

        var data = asyncSimulation.profile2();
        log.info("Data 2 : {} " , data);
        return ResponseEntity.ok(data);

    }
}
