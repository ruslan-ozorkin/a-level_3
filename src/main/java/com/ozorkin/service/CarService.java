package com.ozorkin.service;

import com.ozorkin.model.*;
import com.ozorkin.repository.CarRepository;
import com.ozorkin.util.RandomGenerator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.ozorkin.model.Type.CAR;

public class CarService {
    private final CarRepository carArrayRepository;
    private static CarService instance;

    public final RandomGenerator randomGenerator = new RandomGenerator();

    private final Random random = new Random();

    public void readFile (final String path) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream(path);
        if (input != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
            List<String> stringList = Collections.singletonList(bufferedReader.lines().toString());
            System.out.println(stringList);

        }

    }

    public Map <Color, Integer> innerList (List <Car> cars,int price) {
        return cars.stream()
                .sorted(Comparator.comparing(Car::getColor))
                .peek(System.out::println)
                .filter(car -> car.getPrice() >= price)
                .collect(Collectors.toMap(Car::getColor,Car::getCount));
    }

     Function<Map<String, Object>, Car> mapToObject = map -> {
        Type type = (Type) map.getOrDefault("type", CAR);
        if (type == CAR) {
            return createPassengerCar(map);
        } else {
            return createTruck(map);
        }
    };

    private PassengerCar createPassengerCar(final Map<String, Object> map) {
        final PassengerCar passengerCar = (PassengerCar) createCar(CAR, map);
        final int passengerCount = (int) map.getOrDefault("passengerCount", 10);
        passengerCar.setPassengerCount(passengerCount);
        return passengerCar;
    }

    private Truck createTruck(final Map<String, Object> map) {
        final Truck truck = (Truck) createCar(Type.TRUCK, map);
        final int loadCapacity = (int) map.getOrDefault("loadCapacity", 50);
        truck.setLoadCapacity(loadCapacity);
        return truck;
    }

    private Car createCar(final Type type, final Map<String, Object> map) {
        final Car car;
        if (type == CAR) {
            car = new PassengerCar();

        } else {
            car = new Truck();
        }
        final String manufacturer = (String) map.getOrDefault("manufacturer","ORIGINAL");
        car.setManufacturer(manufacturer);
        final Engine engine = (Engine) map.getOrDefault("engine", new Engine(100,"Skoda"));
        car.setEngine(engine);
        final Color color = (Color) map.getOrDefault("color",Color.AQUA);
        car.setColor(color);
        final int price = (int) map.getOrDefault("price",500);
        car.setPrice(price);
        final int count = (int) map.getOrDefault("count",1);
        car.setCount(count);
        return car;
    }


    public boolean priceCheck (List <Car> cars,int price) {
        final Predicate<Car> myPredicate = car -> car.getPrice() > price;
        return cars.stream()
                .allMatch(myPredicate);
    }

    public String statistic (List <Car> cars) {
       return  cars.stream()
                .mapToInt(Car::getPrice)
                .summaryStatistics()
                .toString();

    }
    public Map <String,Type> mapToMap(List<Car> cars) {
        return cars.stream()
                .sorted(Comparator.comparing(Car::getManufacturer))
                .distinct()
                .collect(Collectors.toMap(Car::getId, Car::getType, (a, b) -> b,LinkedHashMap::new));
    }

    public int countSum(List<Car> cars) {
        return cars.stream()
                .map(Car::getCount)
                .reduce(0, Integer::sum);
    }

    public void findManufacturerByPrice (List<Car> cars, int price) {
        cars.stream()
                .filter(car -> car.getPrice() > price)
                .map(Car::getManufacturer)
                .forEach(System.out::println);
    }

    public Map<String,Integer>  mappingListManufacturerAndCount (List<Car> cars) {
         Map <String,Integer>  map = new HashMap<>();
        for (Car car : cars) {
            map.put(car.getManufacturer(),car.getCount());
        }
        return map;
    }

    public Map<Engine,List<Car>>  mappingListEngineAndCar (List<Car> cars) {
        Map <Engine,List<Car>>  map = new HashMap<>();

        for (Car car : cars) {
            map.put(car.getEngine(),new ArrayList<>());
        }
        for (Car car : cars) {
            map.get(car.getEngine()).add(car);
        }

        return map;
    }


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
            final Car newCar = createCar(CAR);
            printInfo(newCar);
        });

    }

    public void printEngineInfo(Car car) {
        car = Optional.ofNullable(car).orElseGet(() -> createCar(CAR));
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
        final Car expectedCarOrNew = Optional.ofNullable(car).orElse(createCar(CAR));
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
        if (type.equals(CAR)) {
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
