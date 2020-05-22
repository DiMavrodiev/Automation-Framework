package com.automationframework.core.generator.generators.money;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.generators.basic.NaturalGenerator;
import com.automationframework.core.generator.Generator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "cvv")
public class CVVGenerator extends Generator<String> {
    private NaturalGenerator nat;
    private boolean amex;

    public CVVGenerator() {
        nat = new NaturalGenerator();
        amex = false;
    }

    /**
     * Return a 4-digit American Express CVV
     *
     * @param enable Set to true to enable this
     *               setting
     * @return The same data
     */
    public CVVGenerator amex(boolean enable) {
        this.amex = enable;
        return this;
    }

    /**
     * Return a 4-digit American Express CVV
     *
     * @return The same data
     */
    public CVVGenerator amex() {
        return amex(true);
    }

    @Override
    public String gen() {
        StringBuilder cvv = new StringBuilder(3);
        cvv.append(nat.range(1,9).gen());
        cvv.append(nat.range(0,9).gen());
        cvv.append(nat.range(0,9).gen());
        if (amex) {
            cvv.append(nat.range(0,9).gen());
        }
        // nat.range(100,999)?
        return cvv.toString();
    }
}