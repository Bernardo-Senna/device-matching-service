package com.devicematching.utils;

import com.devicematching.model.UserAgent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserAgentParserTest {

    @InjectMocks
    private UserAgentParser userAgentParser;

    @Test
    public void testParseValidUserAgent() {
        // Given
        String userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) Chrome/1.0.0.1 Safari/537.36";

        // When
        UserAgent userAgent = userAgentParser.parse(userAgentString);

        // Then
        assertThat(userAgent.getOsName()).isEqualTo("Windows NT");
        assertThat(userAgent.getOsVersion()).isEqualTo("10.0");
        assertThat(userAgent.getBrowserName()).isEqualTo("Mozilla");
        assertThat(userAgent.getBrowserVersion()).isEqualTo("5.0");
    }

    @Test
    public void testParseInvalidUserAgent() {
        String invalidUserAgentString = "invalid_user_agent_input";
        assertThrows(IllegalArgumentException.class, () -> {
            userAgentParser.parse(invalidUserAgentString);
        });
    }
}