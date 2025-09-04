package com.example.Sport_Station.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ProfileRespons {

    private String id;
    private String name;
    private String address;
    private Integer age;
    private Integer weight;
    private String hobby;
    private String gender;

}
