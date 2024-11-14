package com.devicematching.service;

import com.devicematching.model.Device;
import com.devicematching.model.UserAgent;

import java.util.Optional;

public interface UserAgentService {
    Optional<Device> getMatchingDevice(String userAgentInput);
}