package com.example.Sport_Station.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ColorRequest {

    private Long id;
    private String colorName;
    private String hexCode;
}
