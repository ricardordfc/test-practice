package br.com.rdfc.test_practice.controller.vo;

import br.com.rdfc.test_practice.entity.FollowUp;
import br.com.rdfc.test_practice.types.Label;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record FollowUpVo(UUID id, @JsonProperty("external_id") String externalId, Label label,
                         @JsonProperty("patient") String patientExternalId, String date,
                         List<SampleVo> samples) implements StandardVo {

    public static FollowUpVo fromFollowUp(FollowUp followUp) {
        return new FollowUpVo(followUp.getId(),
                followUp.getExternalId(),
                followUp.getLabel(),
                followUp.getPatient().getExternalId(),
                followUp.getDate().format(formatter),
                followUp.getSamples().stream().map(SampleVo::fromSample).toList());
    }

    public static List<FollowUpVo> fromFollowUpList(List<FollowUp> followUps) {
        return followUps.stream().map(FollowUpVo::fromFollowUp).toList();
    }
}
