package com.example.Sport_Station.service.impl;

import com.example.Sport_Station.dto.response.ProfileRespons;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncSimulation {


    public ProfileRespons profile1() throws InterruptedException {

        Thread.sleep(3000);

        return ProfileRespons.builder()
                .name("tugas 1")
                .gender("Male")
                .build();
    }

    public ProfileRespons profile2() throws InterruptedException {
        Thread.sleep(7000);

        return ProfileRespons.builder()
                .name("Tugas 2")
                .gender("Male")
                .build();
    }
}
