package com.devicematching.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAgent {
    private String osName;
    private String osVersion;
    private String browserName;
    private String browserVersion;
}