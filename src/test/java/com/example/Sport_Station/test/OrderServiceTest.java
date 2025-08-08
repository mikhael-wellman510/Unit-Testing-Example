package com.example.Sport_Station.test;

import com.example.Sport_Station.dto.projection.OrderView;
import com.example.Sport_Station.dto.projection.OrderViewImpl;
import com.example.Sport_Station.dto.response.PaggingResponse;
import com.example.Sport_Station.dto.store_procedure.OrderStoreProcedureDTO;
import com.example.Sport_Station.repository.OrderRepository;
import com.example.Sport_Station.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;


    @BeforeEach
    public void setup(){

    }

    @Test
    public void searchOrder(){
        Integer page = 0 ;
        Integer size = 5 ;
        String customerName = "baki";
        String itemName = "";

        List<OrderView> data = List.of(
                new OrderViewImpl(1L ,"Illum Sport", "2025-07-16 22:30:31.0" , 790054.27 ,148L,"karya37@yahoo.com" , "Bakianto Halim"),
                new OrderViewImpl(2L ,"Illum Sport2", "2025-07-16 22:30:31.0" , 790054.28 ,149L,"karya38@yahoo.com" , "Bakianto Halim2")
        );

        Mockito.when(orderRepository.searchOrder(page,size,customerName,itemName)).thenReturn(data);
        Mockito.when(orderRepository.countDataOrder(customerName,itemName)).thenReturn(2L);

        PaggingResponse order = orderServiceImpl.orderRes(page,size,customerName,itemName);
        OrderView ov = (OrderView) order.getData().get(0);
        Assertions.assertNotNull(order);
        Assertions.assertEquals("Bakianto Halim" ,ov.getName());
        Assertions.assertEquals("Illum Sport" , ov.getItemName());
        Assertions.assertEquals("karya37@yahoo.com" , ov.getEmail());
//        Assertions.assertEquals(0, );
    }

    @Test
    public void callProcedureTest(){
        String search = "am";
        List<Object[]> data = new ArrayList<>();
        Object[] row = new Object[]{
                1L,                        // id
                "Item A",                  // itemName
                Timestamp.valueOf(LocalDateTime.of(2023, 8, 1, 10, 0)), // orderDate
                2000.50,                   // price
                "customer@example.com",    // email
                "John Doe"                 // name
        };

        data.add(row);

        Mockito.when(orderRepository.callStoreProcedure(search)).thenReturn(data);

        List<OrderStoreProcedureDTO> res = orderServiceImpl.callProcedure(search);

        Assertions.assertNotNull(res);
        Assertions.assertEquals("Item A" , res.get(0).getItemName());
        Assertions.assertEquals("John Doe", res.get(0).getName());
        verify(orderRepository,times(1)).callStoreProcedure(search);
    }

}
