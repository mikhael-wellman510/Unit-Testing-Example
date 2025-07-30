package com.example.Sport_Station.service.impl;

import com.example.Sport_Station.dto.projection.OrderView;
import com.example.Sport_Station.dto.response.OrderDto;
import com.example.Sport_Station.dto.response.PaggingResponse;
import com.example.Sport_Station.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl {

    private final OrderRepository orderRepository;

    public PaggingResponse orderRes(Integer page , Integer size , String customerName , String itemName){

        int offset = page * size;
        List<OrderView> res = orderRepository.searchOrder(offset, size, customerName, itemName);
        Long total = orderRepository.countDataOrder(customerName, itemName);
        int totalPages = (int) Math.ceil((double) total / size);

        boolean hasNext = page < totalPages - 1;
        boolean hasPrev = page > 0;


        return PaggingResponse.builder()
                .data(res)
                .totalData(total)
                .page(page)
                .size(size)
                .totalPages(totalPages)
                .hasNext(hasNext)
                .hasPrev(hasPrev)
                .build();
    }


}
