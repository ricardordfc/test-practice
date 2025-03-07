package br.com.rdfc.test_practice.service;

import br.com.rdfc.test_practice.controller.vo.PatientVo;
import br.com.rdfc.test_practice.dto.PatientDto;
import br.com.rdfc.test_practice.entity.Patient;
import br.com.rdfc.test_practice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public void save(PatientDto patientDto) {
        Patient patient = patientDto.toPatient();
        if (patientDto.date() == null) {
            patient.setCreationDate(LocalDateTime.now());
        }
        repository.save(patient);
    }

    public List<PatientVo> findAll() {
        final var patients = (List<Patient>) repository.findAll();
        return PatientVo.fromPatientList(patients);
    }

    public PatientVo findById(UUID id) {
        final Patient patient = repository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
        return PatientVo.fromPatient(patient);
    }

    public Patient findByExternalId(String externalId) {
        return repository.findByExternalId(externalId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
    }
}
