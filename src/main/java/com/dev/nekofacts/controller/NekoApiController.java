package com.dev.nekofacts.controller;

import com.dev.nekofacts.model.NekoImage;
import com.dev.nekofacts.service.NekoApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NekoApiController {

    private final NekoApiService nekoApiService;

    @GetMapping("/nekos/random")
    public NekoImage[] getRandomImages(@RequestParam(defaultValue = "1") int limit) {
        return nekoApiService.getRandomImages(limit);
    }

    @GetMapping("/nekos/random/file")
    public String getRandomImageFile() {
        return nekoApiService.getRandomImageFile();
    }
}
