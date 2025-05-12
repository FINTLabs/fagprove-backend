package no.fintlabs.contract;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/contract")
@RestController
public class ContractController {

    private ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }


    @GetMapping
    public ResponseEntity<List<AdapterContractEntity>> getContracts() {
        return ResponseEntity.ok(contractService.getContracts());
    }

}
