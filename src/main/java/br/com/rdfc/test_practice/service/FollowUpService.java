package br.com.rdfc.test_practice.service;

import br.com.rdfc.test_practice.controller.vo.FollowUpVo;
import br.com.rdfc.test_practice.dto.FollowUpDto;
import br.com.rdfc.test_practice.entity.FollowUp;
import br.com.rdfc.test_practice.entity.Patient;
import br.com.rdfc.test_practice.repository.FollowUpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FollowUpService {

    private final FollowUpRepository repository;
    private final PatientService patientService;

    public FollowUpService(FollowUpRepository repository, PatientService patientService) {
        this.repository = repository;
        this.patientService = patientService;
    }

    public void save(FollowUpDto followUpDto) {
        FollowUp followUp = new FollowUp();

        if (followUpDto.date() == null) {
            followUp.setDate(LocalDateTime.now());
        } else {
            followUp.setDate(followUpDto.date());
        }

        Patient patient = patientService.findByExternalId(followUpDto.patientExternalId());
        followUp.setExternalId(followUpDto.externalId());
        followUp.setLabel(followUpDto.label());
        followUp.setPatient(patient);
        repository.save(followUp);
    }

    public FollowUp findByExternalId(String externalId) {
        return repository.findByExternalId(externalId).orElseThrow(() -> new RuntimeException("Follow-up não encontrado!"));
    }

    public FollowUpVo findById(UUID id) {
        var followUp = repository.findById(id).orElseThrow(() -> new RuntimeException("Follow-up não encontrado!"));
        return FollowUpVo.fromFollowUp(followUp);
    }

    public List<FollowUpVo> findAll() {
        return FollowUpVo.fromFollowUpList((List<FollowUp>) repository.findAll());
    }
}
