package org.qapower.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Source(
	String id,
	String title,
	String source,
	String link,
	String url) {}
