package com.automationframework.core.generator.generators.location;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.Generator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "coordinates")
public class CoordinatesGenerator extends Generator<String> {
    private int decimals;
    private LatitudeGenerator latitude;
    private LongitudeGenerator longitude;

    public CoordinatesGenerator() {
        latitude = new LatitudeGenerator();
        longitude = new LongitudeGenerator();
    }

    /**
     * Set the number of decimal digits to generate
     * @param noDecimals Number of decimal digits
     * @return The same data
     */
    public CoordinatesGenerator decimals(int noDecimals) {
        latitude.decimals(noDecimals);
        longitude.decimals(noDecimals);
        return this;
    }

    @Override
    public String gen() {
        return latitude.gen() + ", " + longitude.gen();
    }
}