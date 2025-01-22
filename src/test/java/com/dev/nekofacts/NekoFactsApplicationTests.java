package com.dev.nekofacts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

/**
 * Unit tests for {@link NekoFactsApplication}.
 */
@ExtendWith(MockitoExtension.class)
class NekoFactsApplicationTests {

    @Test
    void main_ShouldStartSpringApplication() {
        // Using try-with-resources to automatically close the static mock
        try (MockedStatic<SpringApplication> springApplicationMock = Mockito.mockStatic(SpringApplication.class)) {
            // Arrange & Act
            String[] args = new String[]{};
            NekoFactsApplication.main(args);

            // Assert
            springApplicationMock.verify(() ->
                            SpringApplication.run(eq(NekoFactsApplication.class), any(String[].class)),
                    Mockito.times(1)
            );
        }
    }

    @Test
    void main_ShouldHandleArgumentsProperly() {
        try (MockedStatic<SpringApplication> springApplicationMock = Mockito.mockStatic(SpringApplication.class)) {
            // Arrange & Act
            String[] args = new String[]{"--server.port=8081"};
            NekoFactsApplication.main(args);

            // Assert
            springApplicationMock.verify(() ->
                            SpringApplication.run(eq(NekoFactsApplication.class), eq(args)),
                    Mockito.times(1)
            );
        }
    }

}
