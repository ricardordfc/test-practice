package br.com.rdfc.test_practice.controller.vo;

import br.com.rdfc.test_practice.entity.Patient;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record PatientVo(UUID id, String date, @JsonProperty("external_id") String externalId,
                        @JsonProperty("follow_ups") List<FollowUpVo> followUps) implements StandardVo {

    public static PatientVo fromPatient(Patient patient) {
        return new PatientVo(patient.getId(),
                patient.getCreationDate().format(formatter),
                patient.getExternalId(),
                patient.getFollowUps().stream().map(FollowUpVo::fromFollowUp).toList());
    }

    public static List<PatientVo> fromPatientList(List<Patient> patients) {
        return patients.stream().map(PatientVo::fromPatient).toList();
    }
}
