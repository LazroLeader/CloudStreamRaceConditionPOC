package com.lazro.cloudstreamracecondition.producer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.ProducerRecord;
import com.lazro.cloudstreamracecondition.model.WordHolder;
import com.lazro.cloudstreamracecondition.service.WordGenerator;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class WordProducerKafka {

    private final AdminClient kafkaAdminClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaProducerPropertiesCustom kafkaProps;
    private final WordGenerator wordGenerator;

    @PostConstruct
    void createTopics() throws ExecutionException, InterruptedException {
        Set<String> topicsSet = kafkaAdminClient.listTopics().names().get();
        if (topicsSet.contains(kafkaProps.getWordTopicName()) || topicsSet.contains(kafkaProps.getDummyOutputTopic()))
            return;
        NewTopic wordTopic = new NewTopic(kafkaProps.getWordTopicName(), 10, (short) 1);
        NewTopic dummyOutputTopic = new NewTopic(kafkaProps.getDummyOutputTopic(), 1, (short) 1);
        Collection<NewTopic> topics = List.of(wordTopic, dummyOutputTopic);
        CreateTopicsResult topicCreationFuture = kafkaAdminClient.createTopics(topics);
        topicCreationFuture.all().get();
        log.info("Following topics were created: {}, {}", kafkaProps.getWordTopicName(), kafkaProps.getDummyOutputTopic());
    }

    public void produceRandomWords() {
        List<WordHolder> randomWords = wordGenerator.generateNWords(1000000);
        final Random random = new Random();
        randomWords
                .forEach(wordHolder -> {

                    ProducerRecord<String, Object> record = new ProducerRecord<>(
                            kafkaProps.getWordTopicName(),
                            random.nextInt(0, 10),
                            System.currentTimeMillis(),
                            "" + wordHolder.getSize(),
                            wordHolder
                    );

                    CompletableFuture<SendResult<String, Object>> sentWordFuture = kafkaTemplate.send(record);
                    SendResult<String, Object> sendResult;
                    try {
                        sendResult = sentWordFuture.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                });

    }

}
