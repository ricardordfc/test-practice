package br.com.rdfc.test_practice.service;

import br.com.rdfc.test_practice.controller.vo.PatientVo;
import br.com.rdfc.test_practice.dto.PatientDto;
import br.com.rdfc.test_practice.entity.FollowUp;
import br.com.rdfc.test_practice.entity.Patient;
import br.com.rdfc.test_practice.entity.Sample;
import br.com.rdfc.test_practice.repository.PatientRepository;
import br.com.rdfc.test_practice.types.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository repository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;
    private PatientDto patientDto;

    @BeforeEach
    void setUp() {
        FollowUp followUp = new FollowUp("001-1", null, LocalDateTime.now());
        Sample sample = new Sample("001-1-1", Type.BLOOD, "L1", LocalDateTime.now());
        patient = new Patient("001", LocalDateTime.now());
        patient.setId(UUID.randomUUID());
        followUp.setPatient(patient);
        followUp.setSamples(List.of(sample));
        sample.setFollowUp(followUp);
        patient.setFollowUps(List.of(followUp));

        patientDto = new PatientDto("001", LocalDateTime.now());
    }

    @Test
    void testSave() {
        when(repository.save(any(Patient.class))).thenReturn(patient);

        patientService.save(patientDto);

        verify(repository, times(1)).save(any(Patient.class));
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(patient));

        List<PatientVo> result = patientService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testFindById() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(patient));

        PatientVo result = patientService.findById(patient.getId());

        assertNotNull(result);
        assertEquals(patient.getExternalId(), result.externalId());
    }

    @Test
    void testFindById_should_throw_RuntimeException() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> patientService.findById(patient.getId()));
        assertEquals("Paciente não encontrado!", exception.getMessage());
    }

    @Test
    void testFindByExternalId() {
        when(repository.findByExternalId(anyString())).thenReturn(Optional.of(patient));

        Patient result = patientService.findByExternalId("001");

        assertNotNull(result);
        assertEquals(patient.getExternalId(), result.getExternalId());
    }

    @Test
    void testFindByExternalId_should_throw_RuntimeException() {
        when(repository.findByExternalId(anyString())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> patientService.findByExternalId("001"));
        assertEquals("Paciente não encontrado!", exception.getMessage());
    }
}