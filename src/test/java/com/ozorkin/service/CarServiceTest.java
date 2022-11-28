package com.ozorkin.service;

import com.ozorkin.model.Car;
import com.ozorkin.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CarServiceTest {
    private CarService target;
    private CarRepository repository;

    @BeforeEach
    void setUp () {
        repository = Mockito.mock(CarRepository.class);
        target=new CarService(repository);
    }

    @Test
    void create() {
        final Car car = target.create();
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(car.getId());
    }

    @Test
    void print() {
        final Car car = target.create();
        Assertions.assertDoesNotThrow(() -> target.print(car));
    }

    @Test
    void printAll() {
        Assertions.assertDoesNotThrow(() -> target.printAll());
    }

    @Test
    void findIdIncorrectNullId() {
        // initialize
        String id = null;

        // action
        final Car car = target.find(id);

        // checks
        Assertions.assertNull(car);
    }

    @Test
    void findIdIncorrectEmptyId() {
        // initialize
        String id = "";

        // action
        final Car car = target.find(id);

        // checks
        Assertions.assertNull(car);
    }

    @Test
    void findNotFound() {
        // initialize
        String id = "123";
        Mockito.when(repository.getById("123")).thenReturn(null);

        // action
        final Car car = target.find(id);

        // checks
        Assertions.assertNull(car);
    }

    @Test
    void find() {
        // initialize
        final Car expected = new Car();
        String id = "123";
        Mockito.when(repository.getById("123")).thenReturn(expected);

        // action
        final Car actual = target.find(id);

        // checks
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        Assertions.assertDoesNotThrow(() -> target.getAll());
    }

    @Test
    void insert() {
        final Car car = target.create();
        final int index = 2;
        Assertions.assertDoesNotThrow(() -> target.insert(index,car));
    }

    @Test
    void insertOutOfBounds() {
        final Car car = target.create();
        final int index = -2;
        Assertions.assertDoesNotThrow(() -> target.insert(index, car));
    }

    @Test
    void insertNull() {
        final Car car =null;
        final int index = 0;
        Assertions.assertDoesNotThrow(() -> target.insert(index, car));
    }

    @Test
    void delete() {
        String id =  "0000-1111";
        Assertions.assertDoesNotThrow(() -> target.delete(id));
    }


    @Test
    void changeRandomColor() {
        String id = "333-22-11";
        Assertions.assertDoesNotThrow(() -> target.changeRandomColor(id));
    }

    @Test
    void changeRandomColorNullId() {
        String id = null;

        // action

        final Car car = target.find(id);
        // checks
        Assertions.assertNull(car);
    }

    @Test
    void check() {
        final Car car = target.create();
        Assertions.assertDoesNotThrow(() -> CarService.check(car));
    }


}