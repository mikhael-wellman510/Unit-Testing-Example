package com.example.Sport_Station.service.impl;

import com.example.Sport_Station.dto.projection.OrderView;
import com.example.Sport_Station.dto.response.OrderDto;
import com.example.Sport_Station.dto.response.PaggingResponse;
import com.example.Sport_Station.dto.store_procedure.OrderStoreProcedureDTO;
import com.example.Sport_Station.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<OrderStoreProcedureDTO>callProcedure(String search){
        List<Object[]> rawRes = orderRepository.callStoreProcedure(search);
        log.info("Result : {} " , rawRes);
        List<OrderStoreProcedureDTO> data = new ArrayList<>();
        for (Object[]res : rawRes){


           OrderStoreProcedureDTO order = OrderStoreProcedureDTO.builder()
                   .id(((Number)res[0]).longValue())
                   .itemName((String)res[1])
                   .orderDate(((Timestamp)res[2]).toLocalDateTime())
                   .price((Double)res[3])
                   .email((String)res[4])
                   .name(((String)res[5]))
                   .build();
           data.add(order);
        }

        log.info("Cek : {} " ,data.get(0));
        return data;
    }

}
