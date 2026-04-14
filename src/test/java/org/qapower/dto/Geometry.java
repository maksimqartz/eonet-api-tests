package org.qapower.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public record Geometry(
	@JsonIgnoreProperties(ignoreUnknown = true)
	String date,
	Object magnitudeValue,
	List<Object> coordinates,
	String type,
	String magnitudeUnit
) {
}