package com.lazro.cloudstreamracecondition.binding;

import org.apache.kafka.streams.kstream.KStream;
import com.lazro.cloudstreamracecondition.model.DummyDataHolder;
import com.lazro.cloudstreamracecondition.model.WordHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class Bindings {


    @Bean
    Function<KStream<String, WordHolder>, KStream<String, DummyDataHolder>> wordsProcessor(){
        return stringWordHolderKStream -> stringWordHolderKStream
                .mapValues((readOnlyKey, value) -> new DummyDataHolder("dummyData" + readOnlyKey))
                ;
    }
}
