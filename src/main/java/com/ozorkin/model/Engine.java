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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Engine engine = (Engine) o;

        if (power != engine.power) return false;
        return type.equals(engine.type);
    }

    @Override
    public int hashCode() {
        int result = power;
        result = 31 * result + type.hashCode();
        return result;
    }
}

