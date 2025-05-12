package no.fintlabs.contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilitesRepository extends JpaRepository<AdapterCapabilityEntity, Long> {
}
