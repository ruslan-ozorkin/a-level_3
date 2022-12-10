package com.ozorkin.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public abstract class Car implements CountRestore {
    private String manufacturer;
    private Engine engine;
    private Color color;
    private Type type;
    private int count;
    private int price;
    private  String id;
    private final Random random = new Random();

    public Car(String manufacturer, Engine engine, Color color) {
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
        this.id = UUID.randomUUID().toString();
        this.count = 1;
        this.price = random.nextInt(0, 10000);

    }

    public Type getType() {
        return type;
    }

    public Car(Color color) {
        this.color = color;
        this.id = UUID.randomUUID().toString();
    }

    public Car() {
        this.id = UUID.randomUUID().toString();

    }


    @Override
    public String toString() {
        return "Car{" +
                "manufacturer='" + manufacturer +
                ", engine=" + engine +
                ", color=" + color +
                ", count=" + count +
                ", price=" + price +
                ", id='" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!Objects.equals(manufacturer, car.manufacturer)) return false;
        if (!Objects.equals(engine, car.engine)) return false;
        if (color != car.color) return false;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        int result = manufacturer != null ? manufacturer.hashCode() : 0;
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
