package org.qapower.dto;

import java.util.List;

public record MagnitudesResponse(
	String link,
	String description,
	String title,
	List<Magnitudes> magnitudes
) {
}