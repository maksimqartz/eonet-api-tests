package org.qapower.dto;

import java.util.List;

public record Geometry(
	String date,
	Object magnitudeValue,
	List<Object> coordinates,
	String type,
	String magnitudeUnit
) {
}