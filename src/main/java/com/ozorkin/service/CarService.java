package com.ozorkin.service;

import com.ozorkin.model.Car;
import com.ozorkin.model.Color;

import java.util.Random;

public class CarService {
    Random random = new Random();
    public  Car  create() {
        Car car = new Car();
        car.setManufacturer(createString());
        car.setEngine(createString());
        car.setColor(getRandomColor());
        car.setPrice(random.nextInt(0,10000));
        car.setCount(1);
        return  car;
    }

    private Color getRandomColor() {
        final Color[] values = Color.values();
        final int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }


    private String createString() {
        StringBuilder sb = new StringBuilder();
        int stringLength  = random.nextInt(1,10);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < stringLength; i++) {
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public  void print(Car car) {

        System.out.println( "{manufacturer: " + car.getManufacturer() +
                  ", engine: " + car.getEngine() +
                   ", color: " + car.getColor() +
                   ", count: " + car.getCount() +
                   ", price: " + car.getPrice() + "}");
    }

}
