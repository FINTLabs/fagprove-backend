package no.fintlabs;


import lombok.extern.slf4j.Slf4j;
import no.fintlabs.adapter.models.AdapterContract;
import no.fintlabs.kafka.common.topic.pattern.FormattedTopicComponentPattern;
import no.fintlabs.kafka.common.topic.pattern.ValidatedTopicComponentPattern;
import no.fintlabs.kafka.event.EventConsumerConfiguration;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicNamePatternParameters;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContractConsumer {

    private final EventConsumerFactoryService eventConsumerFactoryService;

    public ContractConsumer(EventConsumerFactoryService eventConsumerFactoryService) {
        this.eventConsumerFactoryService = eventConsumerFactoryService;
    }


    @Bean
    public ConcurrentMessageListenerContainer<String, AdapterContract> consumeContracts(EventConsumerFactoryService eventConsumerFactoryService) {
        return eventConsumerFactoryService.createFactory(
                AdapterContract.class,
                this::processEvent,
                EventConsumerConfiguration.builder()
                        .seekingOffsetResetOnAssignment(true)
                        .build()
        ).createContainer(
                EventTopicNamePatternParameters.builder()
                        .orgId(FormattedTopicComponentPattern.any())
                        .domainContext(FormattedTopicComponentPattern.containing("fint-core"))
                        .eventName(ValidatedTopicComponentPattern.endingWith("adapter-register"))
                        .build()
        );
    }

    private void processEvent(ConsumerRecord<String, AdapterContract> consumerRecord) {
        log.info("Consumer record: {}", consumerRecord.value());
    }

}
