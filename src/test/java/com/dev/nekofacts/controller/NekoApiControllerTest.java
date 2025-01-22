package com.dev.nekofacts.controller;

import com.dev.nekofacts.model.NekoImage;
import com.dev.nekofacts.service.NekoApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link NekoApiController}.
 */
@ExtendWith(MockitoExtension.class)
class NekoApiControllerTest {

    @Mock
    private NekoApiService nekoApiService;

    @InjectMocks
    private NekoApiController nekoApiController;

    @Test
    void getRandomImages_WithDefaultLimit_ReturnsOneImage() {
        // Arrange
        NekoImage image = new NekoImage();
        image.setId("1234");
        image.setUrl("https://example.com/neko1.jpg");
        image.setTags(Arrays.asList("cute", "sleeping"));

        when(nekoApiService.getRandomImages(1)).thenReturn(new NekoImage[]{image});

        // Act
        NekoImage[] result = nekoApiController.getRandomImages(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.length);
        assertEquals(image.getId(), result[0].getId());
        assertEquals(image.getUrl(), result[0].getUrl());
        assertEquals(image.getTags(), result[0].getTags());

        verify(nekoApiService, times(1)).getRandomImages(1);
        verifyNoMoreInteractions(nekoApiService);
    }

    @Test
    void getRandomImages_WithCustomLimit_ReturnsMultipleImages() {
        // Arrange
        NekoImage image1 = new NekoImage();
        image1.setId("1234");
        image1.setUrl("https://example.com/neko1.jpg");
        image1.setTags(Arrays.asList("cute", "sleeping"));

        NekoImage image2 = new NekoImage();
        image2.setId("5678");
        image2.setUrl("https://example.com/neko2.jpg");
        image2.setTags(Arrays.asList("playing", "standing"));

        NekoImage[] mockImages = new NekoImage[]{image1, image2};
        when(nekoApiService.getRandomImages(2)).thenReturn(mockImages);

        // Act
        NekoImage[] result = nekoApiController.getRandomImages(2);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.length);

        // Verify first image
        assertEquals(image1.getId(), result[0].getId());
        assertEquals(image1.getUrl(), result[0].getUrl());
        assertEquals(image1.getTags(), result[0].getTags());

        // Verify second image
        assertEquals(image2.getId(), result[1].getId());
        assertEquals(image2.getUrl(), result[1].getUrl());
        assertEquals(image2.getTags(), result[1].getTags());

        verify(nekoApiService, times(1)).getRandomImages(2);
        verifyNoMoreInteractions(nekoApiService);
    }

    @Test
    void getRandomImageFile_ReturnsFileUrl() {
        // Arrange
        String expectedFileUrl = "https://example.com/neko.jpg";
        when(nekoApiService.getRandomImageFile()).thenReturn(expectedFileUrl);

        // Act
        String result = nekoApiController.getRandomImageFile();

        // Assert
        assertNotNull(result);
        assertEquals(expectedFileUrl, result);
        verify(nekoApiService, times(1)).getRandomImageFile();
        verifyNoMoreInteractions(nekoApiService);
    }
}