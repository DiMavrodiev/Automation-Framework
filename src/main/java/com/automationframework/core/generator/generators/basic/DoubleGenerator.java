package com.automationframework.core.generator.generators.basic;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.model.Range;
import com.automationframework.core.generator.Generator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "dbl")
public class DoubleGenerator extends Generator<Double> {

    private double min;
    private double max;

    public DoubleGenerator() {
        this.min = Double.MIN_VALUE;
        this.max = Double.MAX_VALUE;
    }

    /**
     * Sets the maximum value (inclusive)
     *
     * @param max The maximum value
     * @return The same data
     */
    public DoubleGenerator max(double max) {
        this.max = max;
        return this;
    }

    /**
     * Set the minimum value (inclusive)
     *
     * @param min The minimum value
     * @return The same data
     */
    public DoubleGenerator min(double min) {
        this.min = min;
        if (this.max < min) {
            this.max = min * 2;
        }
        return this;
    }

    /**
     * Set a min/max range
     *
     * @param min Minimum value to be returned (inclusive)
     * @param max Maximum value to be returned (inclusive)
     * @return The same data
     */
    public DoubleGenerator range(double min, double max) {
        this.max = max;
        this.min = min;
        return this;
    }

    public DoubleGenerator range(Range<Double> range) {
        this.max = range.getMax();
        this.min = range.getMin();
        return this;
    }

    @Override
    public Double gen() {
        double rand = random().randDouble();
        return min + (rand * (max - min));
    }
}
