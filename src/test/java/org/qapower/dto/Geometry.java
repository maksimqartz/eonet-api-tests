package org.qapower.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Geometry(
    Instant date,
    Double magnitudeValue,
    List<Object> coordinates,
    String type,
    String magnitudeUnit) {}
