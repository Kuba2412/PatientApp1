package com.Kuba2412.PatientApp1.integration;

import com.Kuba2412.PatientApp1.dto.VisitDTO;
import com.Kuba2412.PatientApp1.service.PatientService;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWireMock(port = 8083)
public class PatientIntegrationTest {

    @Autowired
    private PatientService patientService;

    @Test
    void getAvailableVisitsByDoctorId_Success() throws Exception {
        //200 OK
        WireMock.stubFor(get(urlPathEqualTo("/doctors/1/visits"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\":1,\"startVisit\":\"2025-11-15T10:00:00\",\"endVisit\":\"2025-11-15T11:00:00\",\"patientId\":1,\"doctorId\":1,\"specialization\":\"Cardiology\"}]")));

        // When
        List<VisitDTO> result = patientService.getAvailableVisitsByDoctorId(1L);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
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