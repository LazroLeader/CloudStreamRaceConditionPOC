package com.lazro.cloudstreamracecondition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WordHolder {
    String word;
    int size;
    long generatedTime;
}
