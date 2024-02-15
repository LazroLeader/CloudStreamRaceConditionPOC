package com.lazro.cloudstreamracecondition.service;

import com.lazro.cloudstreamracecondition.model.WordHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class WordGenerator {
    public final Random random = new Random();
    public WordHolder generateRandomWord(int minSize, int maxSize){
        int wordSize = random.nextInt(minSize, maxSize);
        String word =  IntStream.range(0, wordSize)
                .boxed()
                .map(integer -> "" + (char)('a' + random.nextInt(0, 26)) )
                .reduce((s, s2) -> s + s2)
                .orElse("default")
                ;
        return new WordHolder(word, wordSize, System.currentTimeMillis());
    }

    public List<WordHolder> generateNWords(int wordCount){
        return IntStream.range(0, wordCount)
                .boxed()
                .map(integer -> generateRandomWord(5, 10))
                .toList();
    }
}
