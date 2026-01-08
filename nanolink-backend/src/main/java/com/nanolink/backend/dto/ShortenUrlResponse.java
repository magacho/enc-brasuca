package com.nanolink.backend.dto;

import java.util.Objects;

public class ShortenUrlResponse {
    private String shortUrl;

    public ShortenUrlResponse() {
    }

    public ShortenUrlResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortenUrlResponse that = (ShortenUrlResponse) o;
        return Objects.equals(shortUrl, that.shortUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortUrl);
    }

    @Override
    public String toString() {
        return "ShortenUrlResponse{" +
                "shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
