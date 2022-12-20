package com.ozorkin;

import com.ozorkin.model.*;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.service.CarService;




public class Main {
    public static void main(String[] args)  {
        final CarService carService = new CarService(new CarRepository());
        final Car expectedCar = carService.createCar(Type.CAR);

          carService.print(expectedCar);

          carService.printManufacturerAndCount(expectedCar);
          carService.printManufacturerAndCount(null);

          carService.printColor(expectedCar);
          carService.printColor(null);

          expectedCar.setCount(11);
          carService.checkCount(expectedCar);
          carService.checkCount(null);

          carService.printEngineInfo(expectedCar);
          carService.printEngineInfo(null);

           carService.printInfo(expectedCar);
           carService.printInfo(null);




    }
}
