package org.qapower.dto;

import java.util.List;

public record Event(
	List<Source> sources,
	String link,
	String description,
	String closed,
	List<Geometry> geometry,
	String id,
	List<Category> categories,
	String title
) {
}