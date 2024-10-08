package com.example.cryptotracker.unit;

import com.example.cryptotracker.service.CryptoPriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class CryptoPriceServiceUnitTest {
    @InjectMocks
    private CryptoPriceService cryptoPriceService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCryptoPrice(){
        //Arrange
        String cryptoName = "bitcoin";
        //String url ="https://api.coingecko.com/api/v3/simple/price?ids=" + cryptoName + "&vs_currencies=usd";


        Map<String, Map<String,Double>> responseBody = new HashMap<>();
        Map<String, Double> priceMap = new HashMap<>();
        priceMap.put("usd",63306.0);
        responseBody.put(cryptoName,priceMap);

        // Mock the response from RestTemplate
        ResponseEntity<Map<String, Map<String, Double>>>responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
        when(restTemplate.exchange(anyString(),eq(HttpMethod.GET),isNull(),any(ParameterizedTypeReference.class))).thenReturn(responseEntity);

        //Act
        double result = cryptoPriceService.getCryptoPrice(cryptoName);

        //Assert
        assertEquals(63306.0,result);
    }

    @Test
    void testGetCryptoPriceFailure(){
        String cryptoName = "invalidrypto";
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=" + cryptoName + "&vs_currencies=usd";

        ResponseEntity<Map<String, Map<String, Double>>> responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);

        assertThrows(RuntimeException.class, () -> {
            cryptoPriceService.getCryptoPrice(cryptoName);
        });
    }
}
