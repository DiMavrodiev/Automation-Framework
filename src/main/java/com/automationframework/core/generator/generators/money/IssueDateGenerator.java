package com.automationframework.core.generator.generators.money;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.generators.basic.NaturalGenerator;
import com.automationframework.core.generator.Generator;
import org.joda.time.DateTime;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "issueDate")
public class IssueDateGenerator extends Generator<String> {
    private boolean longVersion;
    private NaturalGenerator nat;

    public IssueDateGenerator() {
        this.nat = new NaturalGenerator();
        longVersion = false;
    }

    /**
     * Return the calendarClickDatesPick as MM/YYYY
     * @return The same data
     */
    public IssueDateGenerator longVersion() {
        return longVersion(true);
    }

    /**
     * Return the calendarClickDatesPick as MM/YYYY
     * @param longVersion True for MM/YYYY,
     *                    False for MM/YY
     * @return The same data
     */
    public IssueDateGenerator longVersion(boolean longVersion) {
        this.longVersion = longVersion;
        return this;
    }

    @Override
    public String gen() {
        DateTime now = new DateTime();
        now = now.minusYears(nat.range(1, 10).gen());
        now = now.minusMonths(nat.range(0, 12).gen());
        return ExpireDateGenerator.formatDate(now, longVersion);
    }
}