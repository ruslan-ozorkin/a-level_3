package com.ozorkin.util;

import java.util.Random;

public class RandomGenerator {
    private final Random random = new Random();

    public int generate() {

        return random.nextInt(10);
    }
}
