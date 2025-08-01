package com.example.Sport_Station.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ProfileRespons {

    private String name;
    private String gender;
}
