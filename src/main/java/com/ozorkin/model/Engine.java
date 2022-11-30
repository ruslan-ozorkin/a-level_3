package com.ozorkin.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Engine {
    private int power;
    private String type;

    public Engine(int power, String type) {
        this.power = power;
        this.type = type;
    }

    public Engine() {
    }


    @Override
    public String toString() {
        return "[" +
                "power=" + power +
                ", type=" + type + "]";
    }
}

