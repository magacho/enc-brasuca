package com.nanolink.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShortenUrlRequest {
    private String url;
}
