package br.com.rdfc.test_practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record PatientDto(@JsonProperty("external_id") String externalId, LocalDateTime date) {
}
