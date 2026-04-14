package org.qapower.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record Source(
	@JsonIgnoreProperties(ignoreUnknown = true)
	String id,
	String title,
	String source,
	String link,
	String url
) {
}
