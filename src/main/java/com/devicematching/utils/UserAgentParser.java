package com.devicematching.utils;

import com.devicematching.model.UserAgent;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserAgentParser {

    private static final Pattern USER_AGENT_PATTERN = Pattern.compile(
            "(?<browser>\\w+)\\s*[/\\s](?<browserVersion>[\\d.]+).*\\((?<os>[^;\\d]+)\\s*;.*?(?<osVersion>[\\d.]+)");

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
