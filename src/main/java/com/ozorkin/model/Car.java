package com.ozorkin.model;

import java.util.Random;
import java.util.UUID;

public class Car {
    private String manufacturer;
    private Engine  engine;
    private Color  color;
    private  int count;
    private  int price;
    private final String id;
    private final Random random = new Random();

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {

        this.engine = engine;
    }
    public String getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public void  setColor(Color color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



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
