package com.example.cryptotracker.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.ses.SesClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AWSSesClientUnitTest {

    @Mock
    private SesClient sesClient;  // Mocked SES client

    @BeforeEach
    void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSesClientInitialization() {
        assertNotNull(sesClient, "SES client should not be null");
    }
}
