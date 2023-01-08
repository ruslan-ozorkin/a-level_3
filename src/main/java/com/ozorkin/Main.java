package com.ozorkin;

import com.ozorkin.container.CarTree;
import com.ozorkin.model.*;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.service.CarService;

import java.util.Arrays;
import java.util.function.Predicate;


public class Main {
    public static void main(String[] args) {
        CarService carService = new CarService(new CarRepository());
        carService.readFile("hw18.xml");


    }
}