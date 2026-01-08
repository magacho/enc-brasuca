package com.nanolink.backend.dto;

import java.util.Objects;

public class ShortenUrlRequest {
    private String url;

    public ShortenUrlRequest() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortenUrlRequest that = (ShortenUrlRequest) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "ShortenUrlRequest{" +
                "url='" + url + '\'' +
                '}';
    }
}
