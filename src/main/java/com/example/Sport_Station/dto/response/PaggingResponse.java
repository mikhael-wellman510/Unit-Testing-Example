package com.example.Sport_Station.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class PaggingResponse {

    private List<?>data;
    private int page;
    private int size;
    private int totalPages;
    private long totalData;
    private boolean hasNext;
    private boolean hasPrev;
}

