package com.example.Sport_Station.dto.projection;




public record OrderViewImpl(Long getId, String getItemName, String getOrderDate, Double getPrice, Long getCustomerId, String getEmail,
                            String getName) implements OrderView {
}
