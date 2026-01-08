package com.nanolink.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nanolink.backend.dto.ShortenUrlRequest;
import com.nanolink.backend.service.UrlShorteningService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UrlShortenerController.class)
class UrlShortenerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UrlShorteningService urlShorteningService;

    @Test
    void whenValidUrl_thenShortenUrl_shouldReturn201() throws Exception {
        ShortenUrlRequest request = new ShortenUrlRequest();
        request.setUrl("https://example.com");

        when(urlShorteningService.createShortUrl(anyString())).thenReturn("aBcDeF12");

        mockMvc.perform(post("/api/v1/s")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.shortUrl").value("http://localhost/aBcDeF12"));
    }

    @Test
    void whenInvalidUrl_thenShortenUrl_shouldReturn400() throws Exception {
        ShortenUrlRequest request = new ShortenUrlRequest();
        request.setUrl("not-a-valid-url");

        mockMvc.perform(post("/api/v1/s")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Por favor, insira uma URL válida."));
    }
    
    @Test
    void whenEmptyUrl_thenShortenUrl_shouldReturn400() throws Exception {
        ShortenUrlRequest request = new ShortenUrlRequest();
        request.setUrl("");

        mockMvc.perform(post("/api/v1/s")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("O campo de URL não pode estar vazio."));
    }

    @Test
    void whenValidShortCode_thenRedirect_shouldReturn301() throws Exception {
        when(urlShorteningService.getOriginalUrl("aBcDeF12")).thenReturn("https://example.com");

        mockMvc.perform(get("/aBcDeF12"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", "https://example.com"));
    }
}
