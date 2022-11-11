package com.ozorkin;

import com.ozorkin.model.Car;
import com.ozorkin.model.Color;
import com.ozorkin.service.CarService;



public class Main {
    public static void main(String[] args) {
        CarService carService = new CarService();
        Car[] cars = new Car[]{
                carService.create(),
                carService.create(),
                carService.create(),
                new Car("Renault","mustang", Color.AQUA)
        };

        for (Car car: cars) {
            carService.print(car);
        }

    }
}
