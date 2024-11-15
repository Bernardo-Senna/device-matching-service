package com.devicematching.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private String id; //unique device identifier based on user-agent info
    private int hitCount; //number of times this device has been seen
    private String osName; //Operating system name (e.g. macOS, Windows, etc.)
    private String osVersion; //Operating system version
    private String browserName; //Browser name (e.g. Chrome, Firefox, etc.)
    private String browserVersion; //Browser version
}
