package me.lbenavides.firebasenotification.firebase;

import lombok.Builder;

import java.util.Map;

@Builder
public record NotificationMessage(
        String recipientToken,
        String title,
        String body,
        String imageUrl,
        Map<String,String> data
) {
}
