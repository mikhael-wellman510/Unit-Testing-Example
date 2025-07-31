package com.example.Sport_Station.FirebaseService.Entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class Mahasiswa {

    private String id;
    private String name;
    private String address;
    private String hobby;
    private Integer age;
}
