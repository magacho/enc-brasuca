package com.nanolink.backend.service;

import com.nanolink.backend.exception.ShortCodeNotFoundException;
import com.nanolink.backend.model.UrlMapping;
import com.nanolink.backend.repository.UrlMappingRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class UrlShorteningServiceImpl implements UrlShorteningService {

    private static final int SHORT_CODE_LENGTH = 8;

    private final UrlMappingRepository urlMappingRepository;

    public UrlShorteningServiceImpl(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    @Override
    public String createShortUrl(String originalUrl) {
        String shortCode;
        do {
            shortCode = RandomStringUtils.randomAlphanumeric(SHORT_CODE_LENGTH);
        } while (urlMappingRepository.findByShortCode(shortCode).isPresent());

        UrlMapping urlMapping = new UrlMapping(shortCode, originalUrl);
        urlMappingRepository.save(urlMapping);
        return shortCode;
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        UrlMapping urlMapping = urlMappingRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortCodeNotFoundException(shortCode));
        return urlMapping.getOriginalUrl();
    }
}
