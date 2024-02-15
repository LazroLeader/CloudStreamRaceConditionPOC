package com.lazro.cloudstreamracecondition;

import com.lazro.cloudstreamracecondition.producer.WordProducerKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerCustom implements CommandLineRunner {

    private final WordProducerKafka wordProducerKafka;

    @Override
    public void run(String... args) {
        wordProducerKafka.produceRandomWords();
    }
}
