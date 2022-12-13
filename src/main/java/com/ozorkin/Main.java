package com.ozorkin;

import com.ozorkin.model.*;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.service.CarService;
import com.ozorkin.util.AlgorithmUtil;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());
        carService.createCar(Type.CAR, 7);
        Car[] originalCarService = carService.getAll();
        Car forSearch = originalCarService[0];
        carService.printAll();

        AlgorithmUtil algorithmUtil = new AlgorithmUtil();
        Car[] sortedCarService = algorithmUtil.bubbleSort(originalCarService);
        System.out.println(Arrays.toString(sortedCarService));

        System.out.println("Car to search: " + forSearch);

        System.out.println(algorithmUtil.binarySearch(sortedCarService, forSearch, 0, sortedCarService.length - 1));


    }
}
