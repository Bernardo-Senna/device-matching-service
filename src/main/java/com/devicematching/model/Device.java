package com.devicematching.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Device {
    private UUID id; //unique device identifier
    private int hitCount; //number of times this device has been seen
    private String osName; //Operating system name (e.g. macOS, Windows, etc.)
    private String osVersion; //Operating system version
    private String browserName; //Browser name (e.g. Chrome, Firefox, etc.)
    private String browserVersion; //Browser version
}
