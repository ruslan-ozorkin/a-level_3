package com.ozorkin.action;

import com.ozorkin.service.CarService;

public interface Action {

    CarService CAR_SERVICE = CarService.getInstance();

    void execute();
}