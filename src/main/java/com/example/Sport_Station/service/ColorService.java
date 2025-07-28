package com.example.Sport_Station.service;

import com.example.Sport_Station.dto.request.ColorRequest;
import com.example.Sport_Station.dto.response.ColorResponse;
import com.example.Sport_Station.dto.response.PaggingResponse;
import com.example.Sport_Station.entity.Color;
import org.springframework.data.domain.Page;

public interface ColorService {

    PaggingResponse filterAndPaggingColor(String colorName , Integer page , Integer size);
    ColorResponse addColor(ColorRequest colorRequest);
    ColorResponse findColorById(Long id);
    ColorResponse updatedColor(ColorRequest colorRequest);
    void deletedData(Long id);
    Page<Color>searchColor(String color , Integer page , Integer size);
}
