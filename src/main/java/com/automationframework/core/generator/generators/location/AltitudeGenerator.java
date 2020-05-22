package com.automationframework.core.generator.generators.location;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.generators.basic.DecimalGenerator;
import com.automationframework.core.generator.Generator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "altitude")
public class AltitudeGenerator extends Generator<String> {
    DecimalGenerator decimal;

    public AltitudeGenerator() {
        this.decimal = new DecimalGenerator();
        this.decimal.max(8848);
        this.decimal.digits(5);
    }

    /**
     * Set the maximum altitude
     * @param max The maximum altitude
     * @return The same data
     */
    public AltitudeGenerator max(double max) {
        decimal.max(max);
        return this;
    }

    /**
     * By default 5 digits of accuracy are returned.
     * Use this function to override.
     *
     * @param digits Number of digits to return
     * @return The same data
     */
    public AltitudeGenerator digits(int digits) {
        decimal.digits(digits);
        return this;
    }

    @Override
    public String gen() {
        return decimal.gen();
    }
}