package no.fintlabs.contract;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.adapter.models.AdapterContract;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final MappingService mappingService;

    public ContractService(ContractRepository contractRepository, MappingService mappingService) {
        this.contractRepository = contractRepository;
        this.mappingService = mappingService;
    }

    public void handleAndSave(AdapterContract contract) {
        AdapterContractEntity adapterContractEntity = mappingService.mapTOEntity(contract);
        contractRepository.save(adapterContractEntity);
    }

    public List<AdapterContractEntity> getContracts() {
        return contractRepository.findAll();
    }

}
