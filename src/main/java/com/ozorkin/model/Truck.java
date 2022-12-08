package com.ozorkin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Truck extends Car implements CountRestore {
    private int loadCapacity;

    public Truck() {

    }

    public Truck(String manufacturer, Engine engine, Color color, String id, int loadCapacity) {
        super(manufacturer, engine, color);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public int restore() {
        return this.loadCapacity = 50;
    }

    @Override
    public String toString() {
        return String.format("%s: {manufacturer =%s ; Engine =%s; Color = %s; LoadCapacity=%d; ID=%s)}",
                getType(), getManufacturer(), getEngine(), getColor(), getLoadCapacity(), getId());
    }
}
