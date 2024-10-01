package com.example.cryptotracker.service;

import com.example.cryptotracker.model.CryptoSubscription;
import com.example.cryptotracker.repository.CryptoSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoTrackerService {
    @Autowired
    private CryptoPriceService cryptoPriceService;

    @Autowired
    private CryptoSubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 30000) // Runs every 5 minutes
    public void trackPrices(){
        List<CryptoSubscription> subscriptions = subscriptionRepository.findAll();

        for (CryptoSubscription subscription : subscriptions){
            double currentPrice = cryptoPriceService.getCryptoPrice(subscription.getCryptoName());

            if (currentPrice >= subscription.getThresholdPrice() ){

                String subject = "Price Alert!! - " + subscription.getCryptoName();
                String body = "The price of " + subscription.getCryptoName() + " has reached $" + currentPrice;

                emailService.sendNotification(subscription.getEmail(),subject,body);

            }
        }

    }


}
