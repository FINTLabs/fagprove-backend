package no.fintlabs.contract;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/contract")
public class ContractController {

    private ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdapterContractEntity> getContract(@PathVariable String id) {
        log.info("");
        if (contractService.exists(id)) {
            return ResponseEntity.ok(contractService.getContract(id));
        }
        else return ResponseEntity.notFound().build();
    }


    @GetMapping
    public ResponseEntity<List<AdapterContractEntity>> getContracts() {
        return ResponseEntity.ok(contractService.getContracts());
    }

}
