package com.devicematching.service;

import com.devicematching.model.Device;
import com.devicematching.model.UserAgent;

import java.util.Optional;
import java.util.UUID;

public class UserAgentServiceImpl implements  UserAgentService {

    @Override
    public Optional<Device> matchDevice(UserAgent userAgent){
        // Example mock data for matching
        // TODO: Change this mocked device to get the real userAgent string
        Device mockDevice = new Device(UUID.randomUUID(), 1,"Windows", "10.0", "Chrome", "1.0.0.1");

        if (mockDevice.getOsName().equals(userAgent.getOsName()) &&
                mockDevice.getOsVersion().equals(userAgent.getOsVersion()) &&
                mockDevice.getBrowserName().equals(userAgent.getBrowserName()) &&
                mockDevice.getBrowserVersion().equals(userAgent.getBrowserVersion())) {
            return Optional.of(mockDevice);
        }

        //TODO: Handle empty as error for observability purposes
        return Optional.empty(); // No match found
    }
}
