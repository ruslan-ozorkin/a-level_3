package com.ozorkin.service;

import com.ozorkin.model.Car;
import com.ozorkin.model.PassengerCar;
import com.ozorkin.model.Type;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.util.RandomGenerator;
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
    void createCarWithRandomGenerator() {
        RandomGenerator randomGenerator = new RandomGenerator();
        int countGeneratedTrucks = target.createCar(Type.CAR,randomGenerator);
        Assertions.assertTrue(countGeneratedTrucks >0 && countGeneratedTrucks <=10);
    }

    @Test
    void createCar() {
        final Car car = target.createCar(Type.CAR);
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(car.getId());
    }

    @Test
    void printCar() {
        final Car car = target.createCar(Type.CAR);
        Assertions.assertDoesNotThrow(() -> target.print(car));
    }
    @Test
    void printTruck() {
        final Car car = target.createCar(Type.TRUCK);
        Assertions.assertDoesNotThrow(() -> target.print(car));
    }

    @Test
    void printAll() {
        Assertions.assertDoesNotThrow(() -> target.printAll());
    }

    @Test
    void findIdIncorrectNullId() {
        String id = null;
        final Car car = target.find(id);
        Assertions.assertNull(car);
    }

    @Test
    void findIdIncorrectEmptyId() {
        String id = "";
        final Car car = target.find(id);
        Assertions.assertNull(car);
    }

    @Test
    void findNotFound() {
        String id = "123";
        Mockito.when(repository.getById("123")).thenReturn(null);
        final Car car = target.find(id);
        Assertions.assertNull(car);
    }

    @Test
    void find() {
        final Car expected = new PassengerCar();
        String id = "123";
        Mockito.when(repository.getById("123")).thenReturn(expected);
        final Car actual = target.find(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        Assertions.assertDoesNotThrow(() -> target.getAll());
    }

    @Test
    void insert() {
        final Car car = target.createCar(Type.CAR);
        final int index = 2;
        Assertions.assertDoesNotThrow(() -> target.insert(index,car));
    }

    @Test
    void insertOutOfBounds() {
        final Car car = target.createCar(Type.CAR);
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
        final String id =  "0000-1111";
        target.delete(id);
        Mockito.verify(repository).delete(id);
    }
    @Test
    void deleteIdNull() {
        target.delete(null);
        Mockito.verify(repository, Mockito.never()).delete(Mockito.anyString());
    }

    @Test
    void deleteIdEmpty() {
        target.delete("");
        Mockito.verify(repository, Mockito.never()).delete(Mockito.anyString());
    }
    @Test
    void changeRandomColor() {
        String id = "333-22-11";
        Assertions.assertDoesNotThrow(() -> target.changeRandomColor(id));
    }

    @Test
    void changeRandomColorNullId() {
        String id = null;
        final Car car = target.find(id);
        Assertions.assertNull(car);
    }

    @Test
    void check() {
        final Car car = target.createCar(Type.CAR);
        Assertions.assertDoesNotThrow(() -> CarService.check(car));
    }

}