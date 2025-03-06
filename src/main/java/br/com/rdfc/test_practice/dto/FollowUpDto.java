package br.com.rdfc.test_practice.dto;

import br.com.rdfc.test_practice.types.Label;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record FollowUpDto(@JsonProperty("external_id") String externalId, Label label,
                          @JsonProperty("patient") String patientExternalId, LocalDateTime date) {
}
