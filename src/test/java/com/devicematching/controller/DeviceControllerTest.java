package com.devicematching.controller;

import com.devicematching.model.Device;
import com.devicematching.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeviceControllerTest {

    @Mock
    DeviceService deviceService;

    @InjectMocks
    DeviceController deviceController;

    private Device device;

    @BeforeEach
    public void setUp() {
        device = new Device();
        device.setId("Windows NT_10.0_Chrome_1.0.0.1");
        device.setHitCount(1);
        device.setOsName("Windows NT");
        device.setOsVersion("10.0");
        device.setBrowserName("Mozilla");
        device.setBrowserVersion("5.0");
    }

    @Test
    public void testMatchDevice() {
        String userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36";

        // Mock the service response
        when(deviceService.matchDevice(anyString())).thenReturn(device);

        // Call the controller method
        ResponseEntity<Device> response = deviceController.matchDevice(userAgentString);

        // Verify the response
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(device);
        verify(deviceService, times(1)).matchDevice(userAgentString);
    }

    @Test
    public void testGetDeviceById() {
        String deviceId = "Windows NT_10.0_Mozilla_5.0";
        // Mock the service response
        when(deviceService.getDeviceById(deviceId)).thenReturn(device);

        // Call the controller method
        ResponseEntity<Device> response = deviceController.getDeviceById(deviceId);

        // Verify the response
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(device);
        verify(deviceService, times(1)).getDeviceById(deviceId);
    }

    @Test
    public void testGetDeviceById_NotFound() {
        String deviceId = "Windows NT_10.0_Mozilla_5.0";
        // Mock the service response
        when(deviceService.getDeviceById(deviceId)).thenReturn(null);

        // Call the controller method
        ResponseEntity<Device> response = deviceController.getDeviceById(deviceId);

        // Verify the response
        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody()).isNull();
        verify(deviceService, times(1)).getDeviceById(deviceId);
    }

    @Test
    public void testGetDevicesByOSName() {
        String osName = "Windows NT";
        // Mock the service response
        when(deviceService.getDevicesByOSName(osName)).thenReturn(List.of(device));

        // Call the controller method
        ResponseEntity<List<Device>> response = deviceController.getDevicesByOSName(osName);

        // Verify the response
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(List.of(device));
        verify(deviceService, times(1)).getDevicesByOSName(osName);
    }

    @Test
    public void testGetDevicesByOSName_NotFound() {
        String osName = "Windows NT";
        // Mock the service response
        when(deviceService.getDevicesByOSName(osName)).thenReturn(new ArrayList<>());

        // Call the controller method
        ResponseEntity<List<Device>> response = deviceController.getDevicesByOSName(osName);

        // Verify the response
        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody()).isNull();
        verify(deviceService, times(1)).getDevicesByOSName(osName);
    }

    @Test
    public void testDeleteDevicesByIds() {
        String deviceId = "Windows NT_10.0_Mozilla_5.0";

        // Call the controller method
        ResponseEntity<Void> response = deviceController.deleteDevicesByIds(List.of(deviceId));

        // Verify the response
        assertThat(response.getStatusCode().value()).isEqualTo(204);
        assertThat(response.getBody()).isNull();
        verify(deviceService, times(1)).deleteDevicesByIds(List.of(deviceId));
    }
}
