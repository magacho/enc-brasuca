package com.nanolink.backend.service;

public interface UrlShorteningService {

    String createShortUrl(String originalUrl);

    String getOriginalUrl(String shortCode);
}
