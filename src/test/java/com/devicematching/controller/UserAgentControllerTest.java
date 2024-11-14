package com.devicematching.controller;

import com.devicematching.model.Device;
import com.devicematching.service.UserAgentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAgentControllerTest {

    @Mock
    private UserAgentServiceImpl userAgentService;

    @InjectMocks
    private UserAgentController userAgentController;

    @Test
    public void testMatchDeviceFound() {
        // Given
        Device device = new Device(UUID.randomUUID(), 1,"Windows NT", "10.0", "Mozilla", "5.0");
        when(userAgentService.getMatchingDevice(anyString())).thenReturn(Optional.of(device));

        // When
        ResponseEntity<Device> response = userAgentController.matchDevice("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/1.0.0.1 Safari/537.36");

        // Then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(device);
    }

    @Test
    public void testMatchDeviceNotFound() {
        // Given
        when(userAgentService.getMatchingDevice(anyString())).thenReturn(Optional.empty());

        // When
        ResponseEntity<Device> response = userAgentController.matchDevice("invalid_user_agent_input");

        // Then
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }
}