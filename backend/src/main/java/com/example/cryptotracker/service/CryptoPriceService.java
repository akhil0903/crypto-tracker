package com.example.cryptotracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CryptoPriceService {

    @Autowired
    private RestTemplate restTemplate;

    public double getCryptoPrice(String cryptoName){
        String url ="https://api.coingecko.com/api/v3/simple/price?ids=" + cryptoName + "&vs_currencies=usd";
        ResponseEntity<Map<String, Map<String,Double>>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return response.getBody().get(cryptoName).get("usd");
        }else {
            throw new RuntimeException("Failed to fetch price for "+cryptoName);
        }
    }
}
