package com.example.cryptotracker.repository;

import com.example.cryptotracker.model.CryptoSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CryptoSubscriptionRepository extends JpaRepository<CryptoSubscription,Long> {
    List<CryptoSubscription> findByCryptoName(String cryptoName);
}
