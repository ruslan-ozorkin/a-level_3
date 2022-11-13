package com.ozorkin.model;

import java.util.Random;

public class Engine {
    private int power;
    private String type;

    public Engine(int power, String type) {
        this.power = power;
        this.type = type;
    }

    public Engine() {
    }


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" +
                "power=" + power +
                ", type=" + type + "]";
    }
}

