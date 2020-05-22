package com.automationframework.core.generator.model.person;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public enum PersonType {
    CHILD(1, 12),
    TEEN(13, 17),
    ADULT(18, 40),
    SENIOR(41, 120),
    GENERIC(1, 120);

    private int min;

    private int max;

    PersonType(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}