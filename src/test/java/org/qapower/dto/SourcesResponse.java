package org.qapower.dto;

import java.util.List;

public record SourcesResponse(
	String link,
	String description,
	List<Source> sources,
	String title
) {
}