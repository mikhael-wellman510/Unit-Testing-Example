package com.example.Sport_Station.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {

    public String utils(String name){

        return "Hallo " +name;
    }
}
