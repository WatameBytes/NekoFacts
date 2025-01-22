package com.dev.nekofacts.service;

import com.dev.nekofacts.model.NekoImage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NekoApiService {

    private static final String BASE_URL = "https://api.nekosapi.com/v4";
    private final RestTemplate restTemplate;

    public NekoImage[] getRandomImages(int limit) {
        String url = String.format("%s/images/random?limit=%d", BASE_URL, limit);
        return restTemplate.getForObject(url, NekoImage[].class);
    }

    public String getRandomImageFile() {
        String url = String.format("%s/images/random/file", BASE_URL);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody(); // The direct URL of the image
    }
}
