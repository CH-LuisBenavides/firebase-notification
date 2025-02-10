package me.lbenavides.firebasenotification.firebase;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final FirebaseMessagingService firebaseMessagingService;


    @PostMapping
    public String notify(@RequestBody NotificationMessage message) {
        return firebaseMessagingService.sendNotification(message);
    }

}
