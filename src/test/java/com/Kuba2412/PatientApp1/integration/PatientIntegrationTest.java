package com.Kuba2412.PatientApp1.integration;

import com.Kuba2412.PatientApp1.dto.VisitDTO;
import com.Kuba2412.PatientApp1.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PatientIntegrationTest {

    private static WireMockServer wireMockServer;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer(8083);
        WireMock.configureFor("localhost", 8083);
        wireMockServer.start();
    }

    @AfterAll
    public static void end(){
        wireMockServer.stop();
    }

    @Test
    void getAvailableVisitsByDoctorId_NotFound() {
        //404 Not Found
        WireMock.stubFor(get(urlPathEqualTo("/doctors/1/visits"))
                .willReturn(aResponse()
                        .withStatus(404)));

        // When & Then
        assertThrows(RuntimeException.class, () -> patientService.getAvailableVisitsByDoctorId(1L));
    }

    @Test
    void getAvailableVisitsByDoctorId_ServiceUnavailable() {
         //503 Service Unavailable
        WireMock.stubFor(get(urlPathEqualTo("/doctors/1/visits"))
                .willReturn(aResponse()
                        .withStatus(503)));

        // When & Then
        assertThrows(RuntimeException.class, () -> patientService.getAvailableVisitsByDoctorId(1L));
    }
}