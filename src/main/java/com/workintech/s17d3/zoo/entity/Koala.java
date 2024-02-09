package com.workintech.s17d3.zoo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Koala {
    private Integer id;
    private String name;
    private double weight;
    private double sleepHour;
    private String gender;
}
