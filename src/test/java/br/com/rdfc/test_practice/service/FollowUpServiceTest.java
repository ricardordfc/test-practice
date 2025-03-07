package br.com.rdfc.test_practice.service;

import br.com.rdfc.test_practice.controller.vo.FollowUpVo;
import br.com.rdfc.test_practice.dto.FollowUpDto;
import br.com.rdfc.test_practice.entity.FollowUp;
import br.com.rdfc.test_practice.entity.Patient;
import br.com.rdfc.test_practice.entity.Sample;
import br.com.rdfc.test_practice.repository.FollowUpRepository;
import br.com.rdfc.test_practice.types.Label;
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
class FollowUpServiceTest {

    @Mock
    private FollowUpRepository repository;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private FollowUpService followUpService;

    private FollowUp followUp;
    private FollowUpDto followUpDto;
    private Patient patient;

    @BeforeEach
    void setUp() {
        followUp = new FollowUp("001-1", null, LocalDateTime.now());
        followUp.setId(UUID.randomUUID());

        followUpDto = new FollowUpDto("001-1", Label.D0, "001", LocalDateTime.now());

        patient = new Patient("001", LocalDateTime.now());
        Sample sample = new Sample("001-1-1", Type.BLOOD, "L1", LocalDateTime.now());
        followUp.setPatient(patient);
        followUp.setSamples(List.of(sample));
    }

    @Test
    void testSave() {
        when(patientService.findByExternalId(anyString())).thenReturn(patient);
        when(repository.save(any(FollowUp.class))).thenReturn(followUp);

        followUpService.save(followUpDto);

        verify(repository, times(1)).save(any(FollowUp.class));
    }

    @Test
    void testFindByExternalId() {
        when(repository.findByExternalId(anyString())).thenReturn(Optional.of(followUp));

        FollowUp result = followUpService.findByExternalId("externalId");

        assertNotNull(result);
        assertEquals(followUp.getExternalId(), result.getExternalId());
    }

    @Test
    void testFindByExternalId_should_throw_RuntimeException() {
        when(repository.findByExternalId(anyString())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> followUpService.findByExternalId("externalId"));
        assertEquals("Follow-up não encontrado!", exception.getMessage());
    }

    @Test
    void testFindById() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(followUp));

        FollowUpVo result = followUpService.findById(followUp.getId());

        assertNotNull(result);
        assertEquals(followUp.getExternalId(), result.externalId());
    }

    @Test
    void testFindById_should_throw_RuntimeException() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> followUpService.findById(followUp.getId()));
        assertEquals("Follow-up não encontrado!", exception.getMessage());
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(followUp));

        List<FollowUpVo> result = followUpService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}