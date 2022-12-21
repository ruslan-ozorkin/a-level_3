package com.ozorkin;

import com.ozorkin.container.GenericContainer;
import com.ozorkin.model.*;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.service.CarService;


public class Main {
    public static void main(String[] args) {
        CarService carService = new CarService(new CarRepository());
        GenericContainer <Car> genericContainer = new GenericContainer<>(carService.createCar(Type.CAR));
        genericContainer.print();
        genericContainer.increaseCount(2.5);
        genericContainer.print();
        genericContainer.increaseCount();
        genericContainer.print();




    }
}
