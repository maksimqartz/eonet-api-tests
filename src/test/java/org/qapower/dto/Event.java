package org.qapower.dto;

import java.util.List;

public record Event(
	List<Source> sources,
	String link,
	Object description,
	Object closed,
	List<Geometry> geometry,
	String id,
	List<Category> categories,
	String title
) {
}