package com.ozorkin;

import com.ozorkin.container.CarTree;
import com.ozorkin.model.*;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.service.CarService;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        CarTree<Car> carTree = new CarTree<Car>();
        CarService carService = new CarService(new CarRepository());
        carService.createCar(Type.CAR, 3);
        Car[] allCars = carService.getAll();
        allCars[0] = carService.createCustomCar(Type.CAR, "TEST", new Engine(100, "TYPE"), Color.GREEN, "20");
        allCars[1] = carService.createCustomCar(Type.CAR, "TEST2", new Engine(100, "TYPE"), Color.GREEN, "20");
        allCars[2] = carService.createCustomCar(Type.CAR, "TEST3", new Engine(100, "TYPE"), Color.GREEN, "20");
        System.out.println(Arrays.toString(allCars));
        allCars[0].setCount(12);
        carTree.insert(allCars[0]);
        carTree.insert(allCars[1]);
        carTree.preorder();
        carTree.postorder();
        System.out.println(carTree.summaryCount());


    }
}
