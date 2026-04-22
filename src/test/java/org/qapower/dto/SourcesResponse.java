package org.qapower.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SourcesResponse(
    String link,
    String description,
    List<Source> sources,
    String title
) {
}
