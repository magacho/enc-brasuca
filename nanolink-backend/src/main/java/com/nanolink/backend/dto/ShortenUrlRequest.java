package com.nanolink.backend.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import java.util.Objects;

public class ShortenUrlRequest {

    @NotBlank(message = "O campo de URL não pode estar vazio.")
    @URL(message = "Por favor, insira uma URL válida.")
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
