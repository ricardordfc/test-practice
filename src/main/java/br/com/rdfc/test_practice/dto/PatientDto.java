package br.com.rdfc.test_practice.dto;

import br.com.rdfc.test_practice.entity.Patient;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record PatientDto(@JsonProperty("external_id") String externalId, LocalDateTime date) {
    public Patient toPatient() {
        return new Patient(externalId, date);
    }
}
