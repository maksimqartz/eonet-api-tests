package org.qapower.dto;

import java.util.List;

public record EventsResponse(
	String link,
	String description,
	String title,
	List<Event> events
) {
}