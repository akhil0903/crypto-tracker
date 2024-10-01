package com.example.cryptotracker.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;


import java.util.logging.Logger;

@Service
public class EmailService {

    private final SesClient sesClient;
    private static final Logger logger = Logger.getLogger(EmailService.class.getName());


    public EmailService(SesClient sesClient) {
        this.sesClient = sesClient;
    }

    public void sendNotification(String recipientEmail, String subject, String body) {
        try {
            SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                    .destination(Destination.builder().toAddresses(recipientEmail).build())
                    .message(Message.builder()
                            .subject(Content.builder().data(subject).build())  // Correct placement
                            .body(Body.builder()
                                    .text(Content.builder().data(body).build())
                                    .build())
                            .build())
                    .source("your_verified_email@example.com")  // Must be your verified SES email
                    .build();

            sesClient.sendEmail(sendEmailRequest);
            logger.info("Email sent to " + recipientEmail);
        } catch (SesException e) {
            logger.severe("Failed to send email: " + e.awsErrorDetails().errorMessage());
        }
    }

}
