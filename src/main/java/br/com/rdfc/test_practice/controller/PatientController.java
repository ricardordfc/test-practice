package br.com.rdfc.test_practice.controller;

import br.com.rdfc.test_practice.controller.vo.PatientVo;
import br.com.rdfc.test_practice.dto.PatientDto;
import br.com.rdfc.test_practice.service.PatientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public List<PatientVo> list() {
        final List<PatientVo> patientVos = service.findAll();
        return patientVos;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PatientVo getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void save(@RequestBody PatientDto patient) {
        service.save(patient);
    }
}
