package no.fintlabs;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import no.fintlabs.contract.CapabilitesRepository;
import no.fintlabs.contract.ContractRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class Metrics {

    private final ContractRepository contracts;
    private final CapabilitesRepository capabilites;
    private AtomicLong contractGauge;
    private AtomicLong capabilitiGauge;


    public Metrics(ContractRepository contracts, MeterRegistry registry, CapabilitesRepository capabilites) {
        this.contracts = contracts;
        this.capabilites = capabilites;
        this.contractGauge = new AtomicLong(0);
        this.capabilitiGauge = new AtomicLong(0);


        Gauge.builder("db_contracts_total", contractGauge, AtomicLong::get)
                .description("Antall rader i adapter_contracts")
                .register(registry);

        Gauge.builder("db_capabilities_total", capabilitiGauge, AtomicLong::get)
                .description("Antall rader i adapter_capability")
                .register(registry);
    }

    @Scheduled(fixedRateString = "${metrics.capabilities.refresh.ms:15000}")
    public void refresh() {
        contractGauge.set(contracts.count());
        capabilitiGauge.set(capabilites.count());
    }

}
