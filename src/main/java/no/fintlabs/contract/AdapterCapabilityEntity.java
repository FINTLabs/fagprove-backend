package no.fintlabs.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    @Column(nullable = true)
    private String domainName;

    @Column(nullable = true)
    private String packageName;

    @Column(nullable = true)
    private String resourceName;

    @Column(nullable = true)
    private int fullSyncIntervalInDays;

    @Column(nullable = true)
    private String deltaSyncInterval;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adapter_id", nullable = false)
    @JsonIgnore
    private AdapterContractEntity adapterContract;

}
