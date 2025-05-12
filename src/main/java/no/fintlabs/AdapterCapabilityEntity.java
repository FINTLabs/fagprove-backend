package no.fintlabs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "adapter_capabilities")
public class AdapterCapabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String domainName;
    private String packageName;
    private String resourceName;
    private int fullSyncIntervalInDays;
    private String deltaSyncInterval;

    @ManyToOne()
    @JoinColumn(name = "adapter_id")
    private AdapterContractEntity adapterContract;

}
