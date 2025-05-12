package no.fintlabs.contract;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "adapter_contract")
public class AdapterContractEntity {

    @Id
    @Column(name = "adapter_id")
    private String adapterId;

    @Column(nullable = true)
    private String orgId;

    @Column(nullable = true)
    private String userName;

    @Column(nullable = true)
    private int heartBeatIntervalInMinutes;

    @Column(nullable = true)
    private long time;

    @OneToMany(
            mappedBy = "adapterContract",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<AdapterCapabilityEntity> capabilities = new HashSet<>();

}
