package com.devicematching.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAgent {
    private String osName;
    private String osVersion;
    private String browserName;
    private String browserVersion;
}