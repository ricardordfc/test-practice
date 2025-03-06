package br.com.rdfc.test_practice.repository;

import br.com.rdfc.test_practice.entity.FollowUp;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface FollowUpRepository extends CrudRepository<FollowUp, UUID> {
    public Optional<FollowUp> findByExternalId(String externalId);
}
