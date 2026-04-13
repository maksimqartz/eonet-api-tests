package org.qapower.dto;

import java.util.List;

public record CategoriesResponse(
	String link,
	String description,
	List<Category> categories,
	String title
) {
}