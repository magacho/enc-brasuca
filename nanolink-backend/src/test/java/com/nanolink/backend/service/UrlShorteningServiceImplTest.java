package com.nanolink.backend.service;

import com.nanolink.backend.exception.ShortCodeNotFoundException;
import com.nanolink.backend.model.UrlMapping;
import com.nanolink.backend.repository.UrlMappingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlShorteningServiceImplTest {

    @Mock
    private UrlMappingRepository urlMappingRepository;

    @InjectMocks
    private UrlShorteningServiceImpl urlShorteningService;

    @Test
    void whenNewUrl_createShortUrl_shouldCreateNewUrlMapping() {
        String originalUrl = "https://new-url.com";
        when(urlMappingRepository.findByOriginalUrl(originalUrl)).thenReturn(Optional.empty());
        when(urlMappingRepository.findByShortCode(anyString())).thenReturn(Optional.empty());

        String shortCode = urlShorteningService.createShortUrl(originalUrl);

        assertThat(shortCode).isNotNull();
        assertThat(shortCode.length()).isEqualTo(8);
        verify(urlMappingRepository, times(1)).save(any(UrlMapping.class));
    }

    @Test
    void whenExistingUrl_createShortUrl_shouldReturnExistingShortCode() {
        String originalUrl = "https://existing-url.com";
        UrlMapping existingMapping = new UrlMapping("existing", originalUrl);
        when(urlMappingRepository.findByOriginalUrl(originalUrl)).thenReturn(Optional.of(existingMapping));

        String shortCode = urlShorteningService.createShortUrl(originalUrl);

        assertThat(shortCode).isEqualTo("existing");
        verify(urlMappingRepository, never()).save(any(UrlMapping.class));
    }

    @Test
    void whenValidShortCode_getOriginalUrl_shouldReturnUrl() {
        String originalUrl = "https://example.com/page";
        UrlMapping urlMapping = new UrlMapping("validCode", originalUrl);
        when(urlMappingRepository.findByShortCode("validCode")).thenReturn(Optional.of(urlMapping));

        String result = urlShorteningService.getOriginalUrl("validCode");

        assertThat(result).isEqualTo(originalUrl);
    }

    @Test
    void whenInvalidShortCode_getOriginalUrl_shouldThrowException() {
        when(urlMappingRepository.findByShortCode("invalidCode")).thenReturn(Optional.empty());

        assertThrows(ShortCodeNotFoundException.class, () -> {
            urlShorteningService.getOriginalUrl("invalidCode");
        });
    }
}
