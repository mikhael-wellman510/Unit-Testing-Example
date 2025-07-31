package com.example.Sport_Station.FirebaseService.Dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class BiodataRequest {
    private String id;
    private String name;
    private String address;
    private Integer age;
    private String hobby;
    private Long height;
}
