package br.com.rdfc.test_practice.repository;

import br.com.rdfc.test_practice.entity.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends CrudRepository<Patient, UUID> {
    public Optional<Patient> findByExternalId(String externalId);
}
