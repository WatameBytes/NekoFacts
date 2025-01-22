package com.dev.nekofacts.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link AppConfig}.
 */
class AppConfigTest {

    private final AppConfig appConfig = new AppConfig();

    @Test
    void restTemplate_WhenCalled_ReturnsRestTemplateInstance() {
        // Act
        RestTemplate restTemplate = appConfig.restTemplate();

        // Assert
        assertNotNull(restTemplate);
        assertEquals(RestTemplate.class, restTemplate.getClass());
    }
}