package org.qapower.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EventsResponse(
    String link,
    String description,
    String title,
    List<Event> events
) {
}
