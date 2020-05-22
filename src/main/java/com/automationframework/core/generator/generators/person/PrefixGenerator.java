package com.automationframework.core.generator.generators.person;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.data.Assets;
import com.automationframework.core.generator.model.person.Gender;
import com.automationframework.core.generator.model.person.Prefix;
import com.automationframework.core.generator.Choose;
import com.automationframework.core.generator.Generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "prefix")
public class PrefixGenerator extends Generator<String> {
    private List<Prefix> prefixPool;
    private List<Prefix> malePrefixes;
    private List<Prefix> femalePrefixes;
    private List<Prefix> neutralPrefixes;

    private boolean isLong;
    private boolean all;
    private boolean withDot;


    public PrefixGenerator() {
        this.prefixPool = new ArrayList<>();
        malePrefixes = Assets.MALE_PREFIXES.load().getItems();
        femalePrefixes = Assets.FEMALE_PREFIXES.loadItems();
        neutralPrefixes = Assets.NEUTRAL_PREFIXES.loadItems();
        prefixPool.addAll(malePrefixes);
        prefixPool.addAll(femalePrefixes);
    }

    /**
     * Set whether to return prefixes are returned in a non-abreviated form
     * For example Misses instead of Mrs
     * @param isLong True for long,
     *               False otherwise
     * @return The same data
     */
    public PrefixGenerator full(boolean isLong) {
        this.isLong = isLong;
        return this;
    }

    /**
     * Set whether to return prefixes are returned in a non-abreviated form
     * For example Misses instead of Mrs
     * @return The same data
     */
    public PrefixGenerator full() {
        return full(true);
    }

    /**
     * Include all male, female and neutral genders
     * @return The same data
     */
    public PrefixGenerator all() {
        return all(true);
    }

    /**
     * Add a dot at the end of the prefix
     * @param enabled True for enabled,
     *                False otherwise
     * @return The same data
     */
    public PrefixGenerator withDot(boolean enabled) {
        this.withDot = enabled;
        return this;
    }

    /**
     * Add a dot at the end of the prefix
     * @return The same data
     */
    public PrefixGenerator withDot() {
        return withDot(true);
    }

    /**
     * Include all male, female and neutral genders
     * @param enabled True for enabled,
     *                False otherwise
     * @return The same data
     */
    public PrefixGenerator all(boolean enabled) {
        if (enabled){
            prefixPool.clear();
            prefixPool.addAll(malePrefixes);
            prefixPool.addAll(femalePrefixes);
            prefixPool.addAll(neutralPrefixes);
        } else {
            prefixPool.removeAll(neutralPrefixes);
        }
        return this;
    }

    /**
     * Set the gender of the prefixes to be returned in.
     *
     * @param gender Gender to generate
     * @return The same data
     */
    public PrefixGenerator gender(Gender gender) {
        prefixPool.clear();
        if (gender == Gender.MALE) {
            prefixPool.addAll(malePrefixes);
        } else if (gender == Gender.FEMALE) {
            prefixPool.addAll(femalePrefixes);
        } else if (gender == Gender.NEUTRAL) {
            prefixPool.addAll(neutralPrefixes);
        } else {
            prefixPool.addAll(malePrefixes);
            prefixPool.addAll(femalePrefixes);
        }
        return this;
    }


    /**
     * Set the gender of the prefixes to be returned in.
     * "male" or "m" for male, "female" or "f" for female,
     * "n" for neutral, "all" for all, anything else to include only
     * male and female
     *
     * @param gender Set to "m"/"male" for male and "f"/"female" for female
     * @return The same data
     */
    public PrefixGenerator gender(String gender) {
        prefixPool.clear();
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) {
            prefixPool.addAll(malePrefixes);
        } else if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) {
            prefixPool.addAll(femalePrefixes);
        } else if (gender.equalsIgnoreCase("neutral") || gender.equalsIgnoreCase("n")) {
            prefixPool.addAll(neutralPrefixes);
        } else if (gender.equalsIgnoreCase("all")) {
            prefixPool.addAll(malePrefixes);
            prefixPool.addAll(femalePrefixes);
            prefixPool.addAll(neutralPrefixes);
        } else {
            prefixPool.addAll(malePrefixes);
            prefixPool.addAll(femalePrefixes);
        }
        return this;
    }

    public Prefix genAsPrefix() {
        return Choose.one(prefixPool);
    }

    public String gen() {
        Prefix prefix = Choose.one(prefixPool);
        String _prefix = isLong ? prefix.getFull() : prefix.getAbbreviation();
        if (withDot) {
            _prefix += '.';
        }

        return _prefix;
    }
}