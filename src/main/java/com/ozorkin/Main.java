package com.ozorkin;

import com.ozorkin.model.Car;
import com.ozorkin.service.CarService;



public class Main {
    public static void main(String[] args) {
        CarService carService = new CarService();
        Car[] cars = new Car[]{
                new CarService().create(),
                new CarService().create(),
                new CarService().create(),
                new Car("Renault","mustang","orange")
        };

        for (Car car: cars) {
            carService.print(car);
        }

    }
}
