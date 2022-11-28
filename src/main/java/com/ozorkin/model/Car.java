package com.ozorkin.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;
@Getter
@Setter
public class Car {
    private String manufacturer;
    private Engine  engine;
    private Color  color;
    private  int count;
    private  int price;
    private final String id;
    private final Random random = new Random();

    public Car(String manufacturer, Engine engine, Color color, String id) {
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
        this.id = UUID.randomUUID().toString();
        this.count = 1;
        this.price= random.nextInt(0,10000);
    }
    public Car(Color color) {
        this.color = color;
        this.id = UUID.randomUUID().toString();
    }
    public Car() {
        this.id = UUID.randomUUID().toString();

    }


    @Override
    public String toString() {
        return "Car{" +
                "manufacturer='" + manufacturer +
                ", engine=" + engine +
                ", color=" + color +
                ", count=" + count +
                ", price=" + price +
                ", id='" + id +
                '}' ;
    }
}
