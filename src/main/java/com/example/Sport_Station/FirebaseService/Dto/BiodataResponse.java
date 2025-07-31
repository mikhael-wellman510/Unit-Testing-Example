package com.example.Sport_Station.FirebaseService.Dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class BiodataResponse {

    private String id;
    private String name;
    private String address;
    private Integer age;
    private String hobby;
    private Long height;
    private Date createdAt;
    private Date updatedAt;
}
