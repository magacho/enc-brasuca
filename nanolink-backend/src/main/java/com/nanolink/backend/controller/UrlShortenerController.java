package com.nanolink.backend.controller;

import com.nanolink.backend.dto.ShortenUrlRequest;
import com.nanolink.backend.dto.ShortenUrlResponse;
import com.nanolink.backend.service.UrlShorteningService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UrlShortenerController {

    private final UrlShorteningService urlShorteningService;

    public UrlShortenerController(UrlShorteningService urlShorteningService) {
        this.urlShorteningService = urlShorteningService;
    }

    @PostMapping("/api/v1/s")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@Valid @RequestBody ShortenUrlRequest request) {
        String shortCode = urlShorteningService.createShortUrl(request.getUrl());
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{shortCode}")
                .buildAndExpand(shortCode)
                .toUri();
        return ResponseEntity.created(location).body(new ShortenUrlResponse(location.toString()));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode) {
        String originalUrl = urlShorteningService.getOriginalUrl(shortCode);
        return ResponseEntity.status(301).location(URI.create(originalUrl)).build();
    }
}
