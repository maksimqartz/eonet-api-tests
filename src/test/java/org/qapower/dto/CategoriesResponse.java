package org.qapower.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CategoriesResponse(
    String link,
    String description,
    List<Category> categories,
    String title
) {
}
