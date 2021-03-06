package com.automationframework.core.generator.generators.location;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.generators.basic.DecimalGenerator;
import com.automationframework.core.generator.model.Range;
import com.automationframework.core.generator.Generator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "longitude")
public class LongitudeGenerator extends Generator<String> {
    private Range<Double> range;
    private int decimals;
    private DecimalGenerator decimal;

    public LongitudeGenerator() {
        this.decimals = 5;
        this.decimal = new DecimalGenerator();
        this.decimal.range(Range.from(-180.0, 180.0));
    }

    /**
     * Set the number of decimals to return
     * @param noOfDecimals The number of decimals to return
     * @return The same data
     */
    public LongitudeGenerator decimals(int noOfDecimals) {
        this.decimal.digits(noOfDecimals);
        return this;
    }

    /**
     * Set the minimum, and maximum longitude
     * @param min Minimum latitude (inclusive)
     * @param max Maximum latitude (inclusive)
     * @return The same data
     */
    public LongitudeGenerator range(double min, double max) {
        this.decimal.range(min, max);
        return this;
    }

    @Override
    public String gen() {
        return decimal.gen();
    }
}