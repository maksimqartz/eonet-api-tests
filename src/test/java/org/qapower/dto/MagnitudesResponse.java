package org.qapower.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MagnitudesResponse(
    String link,
    String description,
    String title,
    List<Magnitude> magnitudes
) {
}
