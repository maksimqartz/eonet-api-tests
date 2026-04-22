package org.qapower.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Category(
    String link,
    String layers,
    String description,
    String id,
    String title
) {
}
