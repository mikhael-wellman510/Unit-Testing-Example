package com.example.Sport_Station.dto.projection;

public interface OrderView {
    Long getId();
    String getItemName();
    String getOrderDate();
    Double getPrice();
    Long getCustomerId();
    String getEmail();
    String getName();
}
