package me.lbenavides.firebasenotification.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class MessagingConfig {

    @Value("classpath:service-account.json")
    Resource credentialsFile;


    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {

        GoogleCredentials  credentials= GoogleCredentials.fromStream(credentialsFile.getInputStream());

        FirebaseOptions build = FirebaseOptions.builder()
                                               .setCredentials(credentials)
                                               .build();


        FirebaseApp firebaseApp = FirebaseApp.initializeApp(build, "Notification-demo");
        return FirebaseMessaging.getInstance(firebaseApp);
    }

}
