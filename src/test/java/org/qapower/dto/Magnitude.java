package org.qapower.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Magnitude(
    String unit,
    String name,
    String link,
    String description,
    String id
) {
}
