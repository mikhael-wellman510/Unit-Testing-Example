package com.example.Sport_Station.PushNotifFirebase.Dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NotificationRequest {
    private String token;
    private String title;
    private String body;
}
