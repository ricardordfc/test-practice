package br.com.rdfc.test_practice.repository;

import br.com.rdfc.test_practice.entity.Sample;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SampleRepository extends CrudRepository<Sample, UUID> {
}
