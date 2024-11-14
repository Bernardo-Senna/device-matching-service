package com.devicematching.utils;

import com.devicematching.model.UserAgent;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserAgentParser {

    private static final Pattern USER_AGENT_PATTERN = Pattern.compile(
            // Capture browser name and version (ignores listing specific browsers)
            "(?<browser>\\b[a-zA-Z]+)/(?<browserVersion>[\\d.]+).*" +
            // Capture OS name and version inside parentheses
            "\\((?<os>[^;]+)\\s(?<osVersion>[\\d._]+)");

    public UserAgent parse(String userAgentInput) {
        Matcher matcher = USER_AGENT_PATTERN.matcher(userAgentInput);
        if (matcher.find()) {
            String browserName = matcher.group("browser");
            String browserVersion = matcher.group("browserVersion");
            String osName = matcher.group("os").trim();
            String osVersion = matcher.group("osVersion").trim();
            return new UserAgent(osName, osVersion, browserName, browserVersion);
        }
        throw new IllegalArgumentException("Invalid User-Agent string");
    }
}
