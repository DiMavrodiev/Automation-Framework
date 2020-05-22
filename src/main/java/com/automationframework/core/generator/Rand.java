package com.automationframework.core.generator;

import java.util.Random;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public class Rand {

    private Random random = new Random();

    public float randFloat() {
        return random.nextFloat();
    }

    public double randDouble() {
        return random.nextDouble();
    }

    public int randInt() {
        return random.nextInt();
    }

    public long randLong() {
        return random.nextLong();
    }

    public boolean randBool() { return random.nextBoolean(); }

    public int randInt(int n) {
        return random.nextInt(n);
    }

}