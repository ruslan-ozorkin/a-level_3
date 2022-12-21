package com.ozorkin.service;

import com.ozorkin.model.*;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.util.RandomGenerator;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class CarService {
    private final CarRepository carArrayRepository;
    private static CarService instance;

    public final RandomGenerator randomGenerator = new RandomGenerator();

    private final Random random = new Random();
    public int compareCar(final Car first, final Car second) {
        return first.getId().compareTo(second.getId());
    }
    public static CarService getInstance() {
        if (instance == null) {
            instance = new CarService(CarRepository.getInstance());
        }
        return instance;
    }
    public static CarService getInstance(final CarRepository repository) {
        if (instance == null) {
            instance = new CarService(repository);
        }
        return instance;
    }

    public CarService(final CarRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public void printInfo(final Car car) {
        final Optional<Car> optionalCar = Optional.ofNullable(car);
        optionalCar.ifPresentOrElse(car1 -> {
            print(car1);
        }, () -> {
            final Car newCar = createCar(Type.CAR);
            printInfo(newCar);
        });

    }

    public void printEngineInfo(Car car) {
        car = Optional.ofNullable(car).orElseGet(() -> createCar(Type.CAR));
        Optional.ofNullable(car).map(Car::getEngine).ifPresent(System.out::println);
    }

    public void checkCount(final Car car) {
        final Optional<Car> optionalCar = Optional.ofNullable(car);
        if (car != null) {

            optionalCar.ifPresent(newCar -> {
                try {
                    optionalCar.filter(someCar -> {
                        final boolean b = newCar.getCount() > 10;
                        if (b) {
                            System.out.println("Manufacturer: " + newCar.getManufacturer());
                            System.out.println("Count: " + newCar.getCount());
                        }
                        return b;
                    }).orElseThrow(() -> new UserInputException("Wrong count of cars: " + newCar.getCount()));
                } catch (UserInputException e) {
                    System.out.println("Need more cars!");
                }
            });
        }
    }

    public void printColor(final Car car) {
        final Car expectedCarOrNew = Optional.ofNullable(car).orElse(createCar(Type.CAR));
        System.out.println("Color  of car id: " + expectedCarOrNew.getId() + " " + expectedCarOrNew.getColor());
    }

    public void printManufacturerAndCount(final Car car) {
        final Optional<Car> optionalCar = Optional.ofNullable(car);
        optionalCar.ifPresent(someCar -> {
            System.out.println("Manufacturer: " + someCar.getManufacturer());
            System.out.println("Count: " + someCar.getCount());
        });
    }
    public void createCar(Type type,int count) {
        for (int i = 0; i < count; i++) {
            createCar(type);
        }
    }


    public Car createCar(Type type) {
        final Car car = createCarType(type);
        if (car == null) {
            return null;
        }
        car.setManufacturer(createString());
        car.setEngine(new Engine(random.nextInt(0, 1000), createString()));
        car.setColor(getRandomColor());
        car.setPrice(random.nextInt(0, 10000));
        car.setCount(1);
        car.setType(type);
        carArrayRepository.save(car);
        return car;
    }

    public Car createCustomCar(Type type, String manufacturer, Engine engine, Color color, String id) {
        final Car car = createCarType(type);
        if (car == null) {
            return null;
        }
        car.setManufacturer(manufacturer);
        car.setEngine(engine);
        car.setColor(color);
        car.setId(id);
        car.setCount(1);
        car.setType(type);
        car.setPrice(5000);
        carArrayRepository.save(car);
        return car;
    }

    private Car createCarType(Type type) {
        if (type.equals(Type.CAR)) {
            PassengerCar passengerCar = new PassengerCar();
            passengerCar.setPassengerCount(randomGenerator.generate());
            return passengerCar;
        } else if (type.equals(Type.TRUCK)) {
            Truck truck = new Truck();
            truck.setLoadCapacity(randomGenerator.generate());
            return truck;
        }
        return null;
    }

    public boolean carEquals(Car firstCar, Car secondCar) {
        if (firstCar.getType() == secondCar.getType()) {
            if (firstCar.hashCode() == secondCar.hashCode()) {
                return firstCar.equals(secondCar);
            }
        }
        return false;
    }

    public int createCar(Type type, RandomGenerator randomGenerator) {
        final int count = randomGenerator.generate();
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                Car car = createCar(type);
                print(car);
            }
            return count;
        }
        return -1;
    }

    public void insert(int index, final Car car) {
        carArrayRepository.insert(index, car);
    }


    private Color getRandomColor() {
        final Color[] values = Color.values();
        final int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }

    private String createString() {
        StringBuilder sb = new StringBuilder();
        int stringLength = random.nextInt(1, 10);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < stringLength; i++) {
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public void print(Car car) {
        System.out.println(car.getType() + "{manufacturer: " + car.getManufacturer() + ", engine: " + car.getEngine() + ", color: " + car.getColor() + ", count: " + car.getCount() + ", price: " + car.getPrice() + ", id: " + car.getId() + "}");
    }

    public static void check(Car car) {
        if (car.getCount() > 0 && car.getEngine().getPower() > 200) {
            System.out.println("Car is ready to sell");
        } else if (car.getCount() < 1 && car.getEngine().getPower() <= 200) {
            System.out.println("Car amount is less than 1 and low power");
        } else if (car.getEngine().getPower() <= 200) {
            System.out.println("LOW POWER");
        } else if (car.getCount() < 1) {
            System.out.println("AMOUNT IS LESS THAN 1");
        }
    }

    public void printAll() {
        final Car[] all = carArrayRepository.getAll();
        for (Car car : all) {
            print(car);
        }
    }

    public Car[] getAll() {
        return carArrayRepository.getAll();
    }

    public Car find(final String id) {
        return notNullNotEmpty(id) ? carArrayRepository.getById(id) : null;
    }

    public void delete(final String id) {
        if (notNullNotEmpty(id)) {
            carArrayRepository.delete(id);
        }
    }

    public void changeRandomColor(final String id) {
        if (notNullNotEmpty(id)) {
            final Car car = find(id);
            if (car == null) {
                return;
            }
            findAndChangeRandomColor(car);
        }
    }

    private boolean notNullNotEmpty(String id) {
        return !(id == null || id.isEmpty());
    }

    private void findAndChangeRandomColor(final Car car) {
        final Color color = car.getColor();
        Color randomColor;
        do {
            randomColor = getRandomColor();
        } while (randomColor == color);
        carArrayRepository.updateColor(car.getId(), randomColor);
    }


}
