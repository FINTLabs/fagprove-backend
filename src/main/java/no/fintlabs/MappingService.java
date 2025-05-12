package no.fintlabs;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.adapter.models.AdapterCapability;
import no.fintlabs.adapter.models.AdapterContract;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public class MappingService {

    public AdapterContractEntity mapToEntity(AdapterContract adapterContract) {
        AdapterContractEntity entity = new AdapterContractEntity();
        entity.setAdapterId(adapterContract.getAdapterId());
        entity.setUserName(adapterContract.getUsername());
        entity.setOrgId(adapterContract.getOrgId());
        entity.setTime(adapterContract.getTime());
        entity.setCapabilities(mapCapabilities(entity,adapterContract.getCapabilities()));
        return entity;
    }

    private List<AdapterCapabilityEntity> mapCapabilities(AdapterContractEntity adapterContractEntity ,Set<AdapterCapability> capabilities) {
        List<AdapterCapabilityEntity> capabilitiesList = new ArrayList<>();
        for (AdapterCapability capability : capabilities) {
            AdapterCapabilityEntity entity = new AdapterCapabilityEntity();
            entity.setId(UUID.randomUUID().toString());
            entity.setAdapterContract(adapterContractEntity);
            entity.setDomainName(capability.getDomainName());
            entity.setPackageName(capability.getPackageName());
            entity.setResourceName(capability.getResourceName());
            entity.setDeltaSyncInterval(String.valueOf(capability.getDeltaSyncInterval()));
            capabilitiesList.add(entity);
        }
        return capabilitiesList;
    }

}
