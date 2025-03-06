package br.com.rdfc.test_practice.controller.vo;

import br.com.rdfc.test_practice.entity.Sample;
import br.com.rdfc.test_practice.types.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record SampleVo(UUID id, @JsonProperty("external_id") String externalId, String date,
                       @JsonProperty("follow_up") String followUp, Type type, String storage) implements StandardVo {
    public static SampleVo fromSample(Sample sample) {
        return new SampleVo(sample.getId(),
                sample.getExternalId(),
                sample.getDate().format(formatter),
                sample.getExternalId(),
                sample.getType(),
                sample.getStorage());
    }

    public static List<SampleVo> fromSampleList(List<Sample> samples) {
        return samples.stream().map(SampleVo::fromSample).toList();
    }
}
