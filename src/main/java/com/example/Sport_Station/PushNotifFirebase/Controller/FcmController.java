package com.example.Sport_Station.PushNotifFirebase.Controller;

import com.example.Sport_Station.PushNotifFirebase.Dto.NotificationRequest;
import com.example.Sport_Station.PushNotifFirebase.Service.FcmService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notify")
public class FcmController {

    private final FcmService fcmService;

    @PostMapping("/sendNotif")
    public ResponseEntity<?>sendNotification(@RequestBody NotificationRequest notificationRequest) throws FirebaseMessagingException {
        String res = fcmService.sendPushNotification(notificationRequest.getToken() , notificationRequest.getTitle(), notificationRequest.getBody());

        return ResponseEntity.ok(res);

    }

}
