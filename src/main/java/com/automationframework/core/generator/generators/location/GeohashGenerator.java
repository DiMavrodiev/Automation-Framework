package com.automationframework.core.generator.generators.location;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.generators.basic.StringGenerator;
import com.automationframework.core.generator.Generator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "geohash")
public class GeohashGenerator extends Generator<String> {
    private int length;
    private StringGenerator string;

    public GeohashGenerator() {
        this.length = 7;
        this.string = new StringGenerator();
    }


    /**
     * Set the length of the geohash
     * @param length The length of the geohash
     * @return The same data
     */
    public GeohashGenerator length(int length) {
        this.length = length;
        return this;
    }


    @Override
    public String gen() {
        return string.pool("0123456789bcdefghjkmnpqrstuvwxyz").length(this.length).gen();
    }
}