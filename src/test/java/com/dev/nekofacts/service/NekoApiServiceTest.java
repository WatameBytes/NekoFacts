package com.dev.nekofacts.service;

import com.dev.nekofacts.model.NekoImage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link NekoApiService}.
 */
@ExtendWith(MockitoExtension.class)
class NekoApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NekoApiService nekoApiService;

    private static final String BASE_URL = "https://api.nekosapi.com/v4";

    @Test
    void getRandomImages_WithLimit_ReturnsArrayOfNekoImages() {
        // Arrange
        int limit = 2;
        String expectedUrl = String.format("%s/images/random?limit=%d", BASE_URL, limit);

        NekoImage image1 = new NekoImage();
        image1.setId("123");
        image1.setUrl("https://example.com/neko1.jpg");
        image1.setTags(Arrays.asList("cute", "sleeping"));

        NekoImage image2 = new NekoImage();
        image2.setId("456");
        image2.setUrl("https://example.com/neko2.jpg");
        image2.setTags(Arrays.asList("playing", "standing"));

        NekoImage[] expectedImages = new NekoImage[]{image1, image2};

        when(restTemplate.getForObject(expectedUrl, NekoImage[].class))
                .thenReturn(expectedImages);

        // Act
        NekoImage[] result = nekoApiService.getRandomImages(limit);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(image1.getId(), result[0].getId());
        assertEquals(image1.getUrl(), result[0].getUrl());
        assertEquals(image1.getTags(), result[0].getTags());
        assertEquals(image2.getId(), result[1].getId());
        assertEquals(image2.getUrl(), result[1].getUrl());
        assertEquals(image2.getTags(), result[1].getTags());

        verify(restTemplate, times(1)).getForObject(expectedUrl, NekoImage[].class);
        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    void getRandomImages_WithSingleLimit_ReturnsSingleNekoImage() {
        // Arrange
        int limit = 1;
        String expectedUrl = String.format("%s/images/random?limit=%d", BASE_URL, limit);

        NekoImage image = new NekoImage();
        image.setId("123");
        image.setUrl("https://example.com/neko1.jpg");
        image.setTags(Arrays.asList("cute", "sleeping"));

        NekoImage[] expectedImages = new NekoImage[]{image};

        when(restTemplate.getForObject(expectedUrl, NekoImage[].class))
                .thenReturn(expectedImages);

        // Act
        NekoImage[] result = nekoApiService.getRandomImages(limit);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.length);
        assertEquals(image.getId(), result[0].getId());
        assertEquals(image.getUrl(), result[0].getUrl());
        assertEquals(image.getTags(), result[0].getTags());

        verify(restTemplate, times(1)).getForObject(expectedUrl, NekoImage[].class);
        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    void getRandomImageFile_ReturnsImageUrl() {
        // Arrange
        String expectedUrl = BASE_URL + "/images/random/file";
        String expectedImageUrl = "https://example.com/neko.jpg";
        ResponseEntity<String> mockResponse = ResponseEntity.ok(expectedImageUrl);

        when(restTemplate.getForEntity(expectedUrl, String.class))
                .thenReturn(mockResponse);

        // Act
        String result = nekoApiService.getRandomImageFile();

        // Assert
        assertNotNull(result);
        assertEquals(expectedImageUrl, result);

        verify(restTemplate, times(1)).getForEntity(expectedUrl, String.class);
        verifyNoMoreInteractions(restTemplate);
    }
}