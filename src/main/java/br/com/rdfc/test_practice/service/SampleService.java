package br.com.rdfc.test_practice.service;

import br.com.rdfc.test_practice.controller.vo.SampleVo;
import br.com.rdfc.test_practice.dto.SampleDto;
import br.com.rdfc.test_practice.entity.FollowUp;
import br.com.rdfc.test_practice.entity.Sample;
import br.com.rdfc.test_practice.repository.SampleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SampleService {

    private final SampleRepository repository;
    private final FollowUpService followUpService;

    public SampleService(SampleRepository repository, FollowUpService followUpService) {
        this.repository = repository;
        this.followUpService = followUpService;
    }

    public void save(SampleDto sampleDto) {
        Sample sample = sampleDto.toSample();

        if (sample.getDate() == null) {
            sample.setDate(LocalDateTime.now());
        }

        FollowUp followUp = followUpService.findByExternalId(sampleDto.followUpExternalId());
        sample.setFollowUp(followUp);
        repository.save(sample);
    }

    public SampleVo findById(UUID id) {
        final Sample sample = repository.findById(id).orElseThrow(() -> new RuntimeException("Amostra não encontrada!"));
        return SampleVo.fromSample(sample);
    }

    public List<SampleVo> findAll() {
        final List<Sample> samples = (List<Sample>) repository.findAll();
        return SampleVo.fromSampleList(samples);
    }
}
