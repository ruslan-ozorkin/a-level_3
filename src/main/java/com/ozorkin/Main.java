package com.ozorkin;

import com.ozorkin.container.CarTree;
import com.ozorkin.model.*;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.service.CarService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;


public class Main {
    public static void main(String[] args) throws IOException {
        CarService carService = new CarService(new CarRepository());

        Map<String, Object> jsonMap = carService.mapFromFile("Car.json");
        Map<String, Object> xmlMap = carService.mapFromFile("Car.xml");


        System.out.println(carService.mapToObject.apply(jsonMap).toString());
        System.out.println(carService.mapToObject.apply(xmlMap).toString());


    }
}