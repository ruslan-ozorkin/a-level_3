package com.ozorkin.model;

import java.util.Random;

public class Car {
    private String manufacturer;
    private String  engine;
    private Color  color;
    private  int count;
    private  int price;

    private final Random random = new Random();

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
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

    public Car() {

    }

    public Car(String manufacturer, String engine, Color color) {
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
        this.count = 1;
        this.price= random.nextInt(0,10000);
    }
}
