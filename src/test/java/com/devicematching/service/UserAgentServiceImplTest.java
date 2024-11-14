package com.devicematching.service;

import com.devicematching.model.Device;
import com.devicematching.model.UserAgent;
import com.devicematching.utils.UserAgentParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAgentServiceImplTest {

    @Mock
    private UserAgentParser userAgentParser;

    @InjectMocks
    private UserAgentServiceImpl userAgentService;

    @Test
    public void testMatchDevice() {
        // Given
        UserAgent userAgent = new UserAgent("Windows NT", "10.0", "Mozilla", "5.0");
        when(userAgentParser.parse(anyString())).thenReturn(userAgent);

        Device expectedMatchingDevice = new Device(UUID.randomUUID(), 1, "Windows NT", "10.0", "Mozilla", "5.0");

        // When
        Optional<Device> matchedDevice = userAgentService.getMatchingDevice("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/1.0.0.1 Safari/537.36");

        // Then
        assertThat(matchedDevice).isPresent(); // Check that the Optional is present
        assertThat(matchedDevice.get().getOsName()).isEqualTo(expectedMatchingDevice.getOsName());
        assertThat(matchedDevice.get().getOsVersion()).isEqualTo(expectedMatchingDevice.getOsVersion());
        assertThat(matchedDevice.get().getBrowserName()).isEqualTo(expectedMatchingDevice.getBrowserName());
        assertThat(matchedDevice.get().getBrowserVersion()).isEqualTo(expectedMatchingDevice.getBrowserVersion());
    }

    @Test
    public void testHandleUserAgentStringInvalid() {
        // Given
        when(userAgentParser.parse(anyString())).thenThrow(new IllegalArgumentException("Invalid User-Agent string"));

        // When
        Optional<Device> matchedDevice = userAgentService.getMatchingDevice("invalid_user_agent_input");

        // Then
        assertThat(matchedDevice).isEmpty();
    }
}