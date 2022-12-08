package com.ozorkin;

import com.ozorkin.model.*;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.service.CarService;


public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());
        carService.createPassengerCar();
        carService.createTruck();
        carService.printAll();

        for (Car car : carService.getAll()) {
            car.restore();
        }
        carService.printAll();

//        carService.createPassengerCar(carService.randomGenerator);
//        carService.createTruck(carService.randomGenerator);
//        final Car[] all = carService.getAll();
//        for (Car cars: all ) {
//            carService.print(cars);
//            CarService.check(cars);
//        }


    }
}
