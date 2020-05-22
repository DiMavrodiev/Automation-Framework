package com.automationframework.core.generator.generators.basic;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.Generator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "bool")
public class BoolGenerator extends Generator<Boolean> {

    private int likelihood;

    public BoolGenerator() {
        this.likelihood = 50;
    }

    /**
     * Sets the likelihood of generating a true value
     *
     * @param likelihood The likelihood as int between 0 and 100
     * @return The same data
     */
    public BoolGenerator likelihood(int likelihood) {
        this.likelihood = likelihood;
        return this;
    }

    @Override
    public Boolean gen() {
        float rand = random().randFloat();
        return rand <= ((float) likelihood / 100);
    }
}