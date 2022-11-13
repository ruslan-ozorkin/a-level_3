package com.ozorkin.service;

import com.ozorkin.model.Car;
import com.ozorkin.model.Color;
import com.ozorkin.model.Engine;
import com.ozorkin.repository.CarRepository;

import java.util.Arrays;
import java.util.Random;

public class CarService {
    private final CarRepository carArrayRepository;

    private final Random random = new Random();

    public CarService(final CarRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public void create(final int count) {
        for (int i = 0; i < count; i++) {
            create();
        }
    }
    public void insert(int index, final Car car) {

        carArrayRepository.insert(index, car);
    }

    public Car create() {
        final Car car = new Car();
        car.setManufacturer(createString());
        car.setEngine(new Engine(random.nextInt(0,1000),createString()));
        car.setColor(getRandomColor());
        car.setPrice(random.nextInt(0,10000));
        car.setCount(1);
        carArrayRepository.save(car);
        return car;
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
                   ", price: " + car.getPrice() +
                   ", id: " + car.getId() +"}");
    }
    public static void check(Car car) {
        if (car.getCount() > 0 && car.getEngine().getPower() > 200) {
            System.out.println("Car is ready to sell");
        } else if (car.getCount() < 1 && car.getEngine().getPower() <= 200) {
            System.out.println("Car amount is less than 1 and low power");
        } else if (car.getEngine().getPower() <= 200) {
            System.out.println("LOW POWER");
        } else if (car.getCount() < 1){
            System.out.println("AMOUNT IS LESS THAN 1");
        }

    }

    public void printAll() {
        final Car[] all = carArrayRepository.getAll();
        System.out.println(Arrays.toString(all));
    }


    public Car[] getAll() {
        return carArrayRepository.getAll();
    }

    public Car find(final String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return carArrayRepository.getById(id);
    }

    public void delete(final String id) {
        if (id == null || id.isEmpty()) {
            return;
        }
        carArrayRepository.delete(id);
    }

    public void changeRandomColor(final String id) {
        if (id == null || id.isEmpty()) {
            return;
        }
        final Car car = find(id);
        if (car == null) {
            return;
        }
        findAndChangeRandomColor(car);
    }

    private void findAndChangeRandomColor(final Car car) {
        final Color color = car.getColor();
        Color randomColor;
        do {
            randomColor = getRandomColor();
        } while (randomColor == color);
        carArrayRepository.updateColor(car.getId(), randomColor);
    }

}
