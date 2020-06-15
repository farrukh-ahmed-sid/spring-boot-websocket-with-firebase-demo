package com.springbootwebsocketexample.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PushNotificationService {

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    private FirebaseApp firebaseApp;

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream()))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                this.firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                this.firebaseApp = FirebaseApp.getInstance();
            }
            subscribeToTopic();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void subscribeToTopic() {

        String token = "c2-7L6GYgxFpurDH1PcBon:APA91bG9t0w7WUu4U0W2Zuqa_vCuDTFJXqF77kEJBZPC-YJ6wxHsxqEOcsAiTEOTi6H7XuDTK61ToWDr5q4WiDfFzKCe4lJee_nLpTErthYiyNyOd8AH89CChz9tzHX6mBzC7x_p4e6v";

        List<String> tokens = new ArrayList<>();
        tokens.add(token);

        try {
            FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(tokens,
                    "user");
        } catch (FirebaseMessagingException e) {
            log.error("Firebase subscribe to topic fail", e);
        }
    }

    public void sendPnsToTopic() {
        Message message = Message.builder()
                .setTopic("user")
                .setNotification(new Notification("Bing!", "You got a message"))
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send firebase notification", e);
        }
    }
}
