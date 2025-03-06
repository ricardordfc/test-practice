package br.com.rdfc.test_practice.dto;

import br.com.rdfc.test_practice.types.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record SampleDto(@JsonProperty("external_id") String externalId,
                        @JsonProperty("follow_up") String followUpExternalId, Type type, String storage,
                        LocalDateTime date) {
}
