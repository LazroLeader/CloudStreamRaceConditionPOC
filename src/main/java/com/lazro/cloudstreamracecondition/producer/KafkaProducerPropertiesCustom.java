package com.lazro.cloudstreamracecondition.producer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties("kafka")
public class KafkaProducerPropertiesCustom {
    private String brokers;
    private String wordTopicName;
    private String dummyOutputTopic;
}
