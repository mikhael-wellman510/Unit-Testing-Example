package com.example.Sport_Station.dto.store_procedure;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class OrderStoreProcedureDTO {

    private Long id;
    private String itemName;
    private LocalDateTime orderDate;
    private Double price;
    private String email;
    private String name;
}
