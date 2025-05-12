package no.fintlabs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "adapter_contract")
public class AdapterContractEntity {

    @Id
    private String adapterId;

    private String orgId;
    private String userName;
    private int heartBeatIntervalInMinutes;
    private long time;

    @OneToMany(mappedBy = "adapterContract")
    private List<AdapterCapabilityEntity> capabilities;

}
