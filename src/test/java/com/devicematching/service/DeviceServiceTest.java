package com.devicematching.service;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.QueryPolicy;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.Statement;
import com.devicematching.model.Device;
import com.devicematching.model.UserAgent;
import com.devicematching.utils.UserAgentParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

//    @Mock
//    private AerospikeClient aerospikeClient;
//
//    @Mock
//    private UserAgentParser userAgentParser;
//
//    @InjectMocks
//    private DeviceService deviceService;
//
//    private UserAgent mockUserAgent;
//    private Device mockDevice;
//    private Record mockRecord;
//    private static final String USER_AGENT_STRING = "Mozilla/5.0";
//    private static final String OS_NAME = "Windows";
//    private static final String OS_VERSION = "10";
//    private static final String BROWSER_NAME = "Chrome";
//    private static final String BROWSER_VERSION = "95.0";
//
//    @BeforeEach
//    void setUp() {
//        mockUserAgent = new UserAgent(OS_NAME, OS_VERSION, BROWSER_NAME, BROWSER_VERSION);
//
//        mockDevice = new Device();
//        mockDevice.setId(UUID.randomUUID());
//        mockDevice.setHitCount(1);
//        mockDevice.setOsName(OS_NAME);
//        mockDevice.setOsVersion(OS_VERSION);
//        mockDevice.setBrowserName(BROWSER_NAME);
//        mockDevice.setBrowserVersion(BROWSER_VERSION);
//
//        mockRecord = new Record(
//                Map.of(
//                        "id", mockDevice.getId().toString(),
//                        "hitCount", 1,
//                        "osName", OS_NAME,
//                        "osVersion", OS_VERSION,
//                        "browserName", BROWSER_NAME,
//                        "browserVersion", BROWSER_VERSION
//                ),
//                0,
//                0
//        );
//
//        // Mock Aerospike get call to return a mock record
//        when(aerospikeClient.get(any(Policy.class), any(Key.class))).thenReturn(mockRecord);
//
//        // Mock Aerospike put call (void method)
//        doNothing().when(aerospikeClient).put(any(WritePolicy.class), any(Key.class), any(Bin.class));
//
//        // Mock Aerospike delete call (void method)
//        doNothing().when(aerospikeClient).delete(any(WritePolicy.class), any(Key.class));
//    }
//
//    @Test
//    void testMatchDevice_DeviceExists() {
//        // Mock UserAgent parsing
//        when(userAgentParser.parse(USER_AGENT_STRING)).thenReturn(mockUserAgent);
//
//        // Mock Aerospike get call
//        when(aerospikeClient.get(any(), any(Key.class))).thenReturn(mockRecord);
//
//        // Test matchDevice method
//        Device result = deviceService.matchDevice(USER_AGENT_STRING);
//
//        // Verify results
//        assertNotNull(result);
//        assertEquals(mockDevice.getId(), result.getId());
//        assertEquals(2, result.getHitCount()); // Hit count should be incremented
//        verify(aerospikeClient).put(any(), any(Key.class), any(Bin.class));
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
//        assertNotNull(result);
//        assertEquals(1, result.getHitCount()); // Hit count should be 1 for new device
//        verify(aerospikeClient).put(any(), any(Key.class), any(Bin.class), any(Bin.class), any(Bin.class));
//    }
//
//    @Test
//    void testGetDeviceById_DeviceExists() {
//        // Mock Aerospike get call
//        when(aerospikeClient.get(any(), any(Key.class))).thenReturn(mockRecord);
//
//        // Test getDeviceById method
//        Device result = deviceService.getDeviceById(mockDevice.getId());
//
//        // Verify results
//        assertNotNull(result);
//        assertEquals(mockDevice.getId(), result.getId());
//    }
//
//    @Test
//    void testGetDeviceById_DeviceNotExists() {
//        // Mock Aerospike get call
//        when(aerospikeClient.get(any(), any(Key.class))).thenReturn(null);
//
//        // Test getDeviceById method
//        Device result = deviceService.getDeviceById(UUID.randomUUID());
//
//        // Verify results
//        assertNull(result);
//    }
//
//    @Test
//    void testDeleteDeviceById() {
//        // Test deleteDeviceById method
//        deviceService.deleteDeviceById(mockDevice.getId());
//
//        // Verify Aerospike delete call
//        verify(aerospikeClient).delete(any(), any(Key.class));
//    }

}
