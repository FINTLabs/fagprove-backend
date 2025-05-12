package no.fintlabs.contract;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.adapter.models.AdapterCapability;
import no.fintlabs.adapter.models.AdapterContract;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class MappingService {

    public AdapterContractEntity mapTOEntity(AdapterContract adapterContract) {
        AdapterContractEntity adapterContractEntity = new AdapterContractEntity();
        adapterContractEntity.setAdapterId(adapterContract.getAdapterId().replace("/", "&"));
        adapterContractEntity.setOrgId(adapterContract.getOrgId());
        adapterContractEntity.setUserName(adapterContract.getUsername());
        adapterContractEntity.setTime(adapterContract.getTime());

        Set<AdapterCapabilityEntity> capabilities = new HashSet<>();
        adapterContract.getCapabilities().forEach(capability -> {
            capabilities.add(mapCapabilities(adapterContractEntity, capability));
        });
        adapterContractEntity.setCapabilities(capabilities);
        adapterContractEntity.setHeartBeatIntervalInMinutes(adapterContract.getHeartbeatIntervalInMinutes());
        return adapterContractEntity;
    }

    private AdapterCapabilityEntity mapCapabilities(AdapterContractEntity adapterContract, AdapterCapability capability) {
        AdapterCapabilityEntity adapterCapabilityEntity = new AdapterCapabilityEntity();
        adapterCapabilityEntity.setDomainName(capability.getDomainName());
        adapterCapabilityEntity.setPackageName(capability.getPackageName());
        adapterCapabilityEntity.setResourceName(capability.getResourceName());
        adapterCapabilityEntity.setAdapterContract(adapterContract);
        adapterCapabilityEntity.setDeltaSyncInterval(String.valueOf(capability.getDeltaSyncInterval()));
        adapterCapabilityEntity.setFullSyncIntervalInDays(capability.getFullSyncIntervalInDays());
        return adapterCapabilityEntity;
    }

}
