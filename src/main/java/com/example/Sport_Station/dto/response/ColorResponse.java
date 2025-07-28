package com.example.Sport_Station.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ColorResponse {
    private Long id;
    private String colorName;
    private String hexCode;
}
