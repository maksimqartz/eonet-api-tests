package org.qapower.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Event(
    List<Source> sources,
    String link,
    String description,
    Instant closed,
    List<Geometry> geometry,
    String id,
    List<Category> categories,
    String title
) {
}
