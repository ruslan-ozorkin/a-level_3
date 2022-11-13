package com.ozorkin;

import com.ozorkin.model.Car;
import com.ozorkin.model.Color;
import com.ozorkin.model.Engine;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.service.CarService;



public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());
        final Car car1 = carService.create();
        Car car2= new Car("FORD",new Engine(100,"balance"),Color.DARK_BLUE,"PPPPPPP");

        carService.create(3);
        carService.printAll();
        carService.insert(3,car2);
        carService.printAll();

        final Car[] all = carService.getAll();
        for (Car cars: all ) {
            carService.print(cars);
            CarService.check(cars);
        }


    }
}
