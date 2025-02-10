package me.lbenavides.firebasenotification.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FirebaseMessagingService {
    private final FirebaseMessaging firebaseMessaging;


    public String sendNotification(NotificationMessage notificationMessage) {
        Notification build = Notification.builder()
                                         .setTitle(notificationMessage.title())
                                         .setBody(notificationMessage.body())
                                         .setImage(notificationMessage.imageUrl())
                                         .build();

        Message message = Message.builder()
                                .setToken(notificationMessage.recipientToken())
                                .setNotification(build)
                                .putAllData(notificationMessage.data())
                                .build();

        try {
            firebaseMessaging.send(message);
            return "Success Sending Notification";
        }catch (FirebaseMessagingException e){
            log.error("We could not send the notification",e);
            return "Error Sending Notification";
        }


    }


}
