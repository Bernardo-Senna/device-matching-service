package com.devicematching.service;

import com.devicematching.model.Device;
import com.devicematching.model.UserAgent;
import com.devicematching.utils.UserAgentParser;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserAgentServiceImpl implements  UserAgentService {

    private final UserAgentParser userAgentParser;

    public UserAgentServiceImpl(UserAgentParser userAgentParser) {
        this.userAgentParser = userAgentParser;
    }

    @Override
    public Optional<Device> getMatchingDevice(String userAgentString) {
        try {
            UserAgent userAgent = userAgentParser.parse(userAgentString);
            return matchDevice(userAgent);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<Device> matchDevice(UserAgent userAgent){
        // Example mock data for matching
        // TODO: Change this mocked device to get the real userAgent string
        Device mockDevice = new Device(UUID.randomUUID(), 1,"Windows NT", "10.0", "Mozilla", "5.0");

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
