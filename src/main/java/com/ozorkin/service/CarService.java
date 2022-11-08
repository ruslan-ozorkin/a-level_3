package com.ozorkin.service;

import com.ozorkin.model.Car;

import java.util.Arrays;
import java.util.Random;

public class CarService {
    private final String[] mColors = {
            "light blue",
            "dark blue",
            "mauve",
            "red",
            "orange",
            "lavender",
            "purple",
            "aqua",
            "green",
            "mustard",
            "dark gray",
            "pink",
            "light gray",
    };
    Random random = new Random();
    public  Car  create() {
        Car car = new Car();
        StringBuilder sb = new StringBuilder();
        int stringLength  = random.nextInt(1,10);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < stringLength; i++) {
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
            sb.append(randomChar);
        }
        car.setManufacturer(sb.toString());

        sb.delete(0,sb.length());

        int engineStringLenght = random.nextInt(alphabet.length());
        for (int i = 0; i < engineStringLenght; i++) {
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
            sb.append(randomChar);
        }
        car.setEngine(sb.toString());

        int randomColor = random.nextInt(mColors.length);
        car.setColor(mColors[randomColor]);
        car.setPrice(random.nextInt(0,10000));
        car.setCount(1);

        return  car;
    }

    public  void print(Car car) {

        System.out.println( "{manufacturer: " + car.getManufacturer() +
                  ", engine: " + car.getEngine() +
                   ", color: " + car.getColor() +
                   ", count: " + car.getCount() +
                   ", price: " + car.getPrice() + "}");
    }

}
