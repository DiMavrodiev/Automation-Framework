package com.automationframework.core.generator.generators.location;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.generators.basic.DecimalGenerator;
import com.automationframework.core.generator.model.Range;
import com.automationframework.core.generator.Generator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "depth")
public class DepthGenerator extends Generator<String> {
    private DecimalGenerator decimal;
    private int noDecimals;

    public DepthGenerator() {
        this.noDecimals = 5;
        this.decimal = new DecimalGenerator().range(Range.from(-10994.0, 0.0)).digits(noDecimals);
    }

    /**
     * Set the number of digits after the decimal place
     * @param noDecimals Number
     * @return The same data
     */
    public DepthGenerator decimals(int noDecimals) {
        decimal.digits(noDecimals);
        return this;
    }

    /**
     * Set the minimum depth
     * @param min The minimum depth
     * @return The same data
     */
    public DepthGenerator min(double min) {
        decimal.min(min);
        return this;
    }

    @Override
    public String gen() {
        return decimal.gen();
    }
}