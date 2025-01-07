package br.com.rdfc.test_practice.controller;

import br.com.rdfc.test_practice.entity.Patient;
import br.com.rdfc.test_practice.repository.PatientRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class Controller {

    private final PatientRepository repository;

    public Controller(PatientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public List<Patient> list() {
        return (List<Patient>) repository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Patient getById(@PathVariable UUID id) {
        Optional<Patient> optPatient = repository.findById(id);
        if(optPatient.isPresent()) {
            return optPatient.get();
        }
//        LocalDateTime ldt = LocalDateTime.now();
//        return new Patient(UUID.randomUUID(), "Teste", "11122233344", LocalDate.of(1987, 5, 29), ldt, ldt);
        throw new RuntimeException("Paciente não encontrado!");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void save(@RequestBody Patient patient) {
        repository.save(patient);
    }
}
