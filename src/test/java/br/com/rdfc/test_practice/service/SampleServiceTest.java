package br.com.rdfc.test_practice.service;

import br.com.rdfc.test_practice.controller.vo.SampleVo;
import br.com.rdfc.test_practice.dto.SampleDto;
import br.com.rdfc.test_practice.entity.FollowUp;
import br.com.rdfc.test_practice.entity.Sample;
import br.com.rdfc.test_practice.repository.SampleRepository;
import br.com.rdfc.test_practice.types.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SampleServiceTest {

    @Mock
    private SampleRepository repository;

    @Mock
    private FollowUpService followUpService;

    @InjectMocks
    private SampleService sampleService;

    private Sample sample;
    private SampleDto sampleDto;
    private FollowUp followUp;

    @BeforeEach
    void setUp() {
        followUp = new FollowUp("001-1", null, LocalDateTime.now());
        followUp.setId(UUID.randomUUID());

        sample = new Sample("001-1-1", Type.BLOOD, "L1", LocalDateTime.now());
        sample.setId(UUID.randomUUID());
        sample.setFollowUp(followUp);

        sampleDto = new SampleDto("001-1-1", "001-1", Type.BLOOD, "L1", LocalDateTime.now());
    }

    @Test
    void testSave() {
        when(followUpService.findByExternalId(anyString())).thenReturn(followUp);
        when(repository.save(any(Sample.class))).thenReturn(sample);

        sampleService.save(sampleDto);

        verify(repository, times(1)).save(any(Sample.class));
    }

    @Test
    void testFindById() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(sample));

        SampleVo result = sampleService.findById(sample.getId());

        assertNotNull(result);
        assertEquals(sample.getExternalId(), result.externalId());
    }

    @Test
    void testFindById_should_throw_RuntimeException() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> sampleService.findById(sample.getId()));
        assertEquals("Amostra não encontrada!", exception.getMessage());
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(sample));

        List<SampleVo> result = sampleService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}