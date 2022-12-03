package com.ozorkin;

import com.ozorkin.model.*;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.service.CarService;


public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());
          carService.createCustomCar(Type.CAR,"TT",(new Engine(100,"Q")),Color.GREEN,"12345");
          carService.createCustomCar(Type.TRUCK,"TT",(new Engine(100,"Q")),Color.GREEN,"12345");
          carService.createCustomCar(Type.TRUCK,"TT",(new Engine(100,"Q")),Color.GREEN,"12345");
          carService.createCustomCar(Type.CAR,"ANOTHER",(new Engine(100,"Q")),Color.GREEN,"12345");

          final Car[] cars = carService.getAll();
          carService.print(cars[0]);
          carService.print(cars[1]);
          carService.print(cars[2]);
          carService.print(cars[3]);

          System.out.println(carService.carEquals(cars[0], cars[1]));
          System.out.println(carService.carEquals(cars[1], cars[2]));
          System.out.println(carService.carEquals(cars[0], cars[3]));

          System.out.println(carService.createCar(Type.TRUCK));

          carService.printAll();


    }
}
