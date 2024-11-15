package com.devicematching.service;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.devicematching.model.Device;
import com.devicematching.model.UserAgent;
import com.devicematching.utils.UserAgentParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    private AerospikeClient aerospikeClient;

    @Mock
    private UserAgentParser userAgentParser;

    @InjectMocks
    private DeviceService deviceService;

    private Device device;
    private UserAgent userAgent;

    private static final String USER_AGENT_STRING = "Mozilla/5.0 (Windows NP 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/1.0.0.1 Safari/537.36";
    private static final String DEVICE_KEY = "Windows NT_10.0_Mozilla_5.0";

//
    @BeforeEach
    void setUp() {
        device = new Device();
        device.setId("Windows NT_10.0_Chrome_1.0.0.1");
        device.setHitCount(1);
        device.setOsName("Windows NT");
        device.setOsVersion("10.0");
        device.setBrowserName("Mozilla");
        device.setBrowserVersion("5.0");

        userAgent = new UserAgent();
        userAgent.setOsName("Windows NT");
        userAgent.setOsVersion("10.0");
        userAgent.setBrowserName("Mozilla");
        userAgent.setBrowserVersion("5.0");
    }

//    @Test
//    void testMatchDevice_DeviceExists() {
//        // Mock UserAgent parsing
//        when(userAgentParser.parse(USER_AGENT_STRING)).thenReturn(userAgent);
//
//        // Create a mock Record
//        Map<String, Object> recordBins = new HashMap<>();
//        recordBins.put("id", "Windows NT_10.0_Chrome_1.0.0.1");
//        recordBins.put("hitCount", 5);
//        recordBins.put("osName", "Windows NT");
//        recordBins.put("osVersion", "10.0");
//        recordBins.put("browserName", "Mozilla");
//        recordBins.put("browserVersion", "5.0");
//        Record record = new Record(recordBins, 0, 0);
//
//        // Create the Key for the existing device
//        Key key = new Key("test", "devices", DEVICE_KEY);
//        when(aerospikeClient.get(any(), eq(key))).thenReturn(record);
//
//        // Test matchDevice method
//        Device result = deviceService.matchDevice(USER_AGENT_STRING);
//
//        // Verify results
//        assertThat(result).isNotNull();
//        assertThat(result.getId()).isEqualTo("Windows NT_10.0_Chrome_1.0.0.1");
//        assertThat(result.getHitCount()).isEqualTo(6); // Hit count should be incremented
//        verify(aerospikeClient).put(any(), eq(key), any(Bin.class));
//    }
//
//    @Test
//    void testMatchDevice_DeviceNotExists() {
//        // Mock UserAgent parsing
//        when(userAgentParser.parse(USER_AGENT_STRING)).thenReturn(mockUserAgent);
//
//        // Mock Aerospike get call
//        when(aerospikeClient.get(any(), any(Key.class))).thenReturn(null);
//
//        // Test matchDevice method
//        Device result = deviceService.matchDevice(USER_AGENT_STRING);
//
//        // Verify results
//        assertThat(result).isNotNull();
//        assertThat(result.getHitCount()).isEqualTo(1); // Hit count should be 1 for new device
//        verify(aerospikeClient).put(any(), any(Key.class), any(Bin.class), any(Bin.class), any(Bin.class));
//    }
//
//    @Test
//    void testGetDeviceById_DeviceExists() {
//        // Mock Aerospike get call
//        Map<String, Object> recordBins = new HashMap<>();
//        recordBins.put("id", "Windows NT_10.0_Chrome_1.0.0.1");
//        recordBins.put("hitCount", 5);
//        recordBins.put("osName", "Windows NT");
//        recordBins.put("osVersion", "10.0");
//        recordBins.put("browserName", "Mozilla");
//        recordBins.put("browserVersion", "5.0");
//        Record record = new Record(recordBins, 0, 0);
//        when(aerospikeClient.get(any(), any(Key.class))).thenReturn(record);
//
//        // Test getDeviceById method
//        Device result = deviceService.getDeviceById(DEVICE_KEY);
//
//        // Verify results
//        assertThat(result).isNotNull();
//        assertThat(result.getId()).isEqualTo(device.getId());
//    }
//
//    @Test
//    void testGetDeviceById_DeviceNotExists() {
//        // Mock Aerospike get call
//        when(aerospikeClient.get(any(), any(Key.class))).thenReturn(null);
//
//        // Test getDeviceById method
//        Device result = deviceService.getDeviceById(DEVICE_KEY);
//
//        // Verify results
//        assertThat(result).isNull();
//    }
//
//    @Test
//    void testDeleteDeviceById() {
//        deviceService.deleteDevicesByIds(List.of(DEVICE_KEY));
//
//        // Verify Aerospike delete call
//        verify(aerospikeClient).delete(any(), any(Key.class));
//    }
}
