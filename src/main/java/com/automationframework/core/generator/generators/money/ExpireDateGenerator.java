package com.automationframework.core.generator.generators.money;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.generators.basic.NaturalGenerator;
import com.automationframework.core.generator.Generator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "expireDate")
public class ExpireDateGenerator extends Generator<String> {
    private boolean longVersion;
    private boolean expired;
    private boolean canExpire;

    private NaturalGenerator nat;

    public ExpireDateGenerator() {
        this.nat = new NaturalGenerator();
        longVersion = false;
        expired = false;
        canExpire = false;
    }


    /**
     * Return the expiry calendarClickDatesPick as MM/YYYY
     * @return The same data
     */
    public ExpireDateGenerator longVersion() {
        return longVersion(true);
    }

    /**
     * Return the expiry calendarClickDatesPick as MM/YYYY
     * @param longVersion True for MM/YYYY,
     *                    False for MM/YY
     * @return The same data
     */
    public ExpireDateGenerator longVersion(boolean longVersion) {
        this.longVersion = longVersion;
        return this;
    }

    /**
     * Set whether this can generate a past expiry calendarClickDatesPick card
     * @param enabled True for enabled,
     *                False otherwise
     * @return The same data
     */
    public ExpireDateGenerator canExpire(boolean enabled) {
        this.canExpire = enabled;
        return this;
    }

    /**
     * Generate an expired card
     * @return The same data
     */
    public ExpireDateGenerator expired() {
        return expired(true);
    }

    /**
     * Generate an expired card
     * @param enabled True for enabled,
     *                False otherwise
     * @return The same data
     */
    public ExpireDateGenerator expired(boolean enabled) {
        this.expired = enabled;
        return this;
    }

    @Override
    public String gen() {
        DateTime now = new DateTime();
        if (expired) {
            now = now.minusYears(nat.range(1, 2).gen());
        } else if (canExpire) {
            now = now.minusYears(nat.range(-5,5).gen());
        } else {
            now = now.plusYears(nat.range(1,5).gen());
        }
        now = now.minusMonths(nat.range(0,11).gen());
        return formatDate(now, longVersion);
    }

    protected static String formatDate(DateTime now, boolean longVersion) {
        if (longVersion) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/yyyy");
            return formatter.print(now);
        } else {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/yy");
            return formatter.print(now);
        }
    }
}