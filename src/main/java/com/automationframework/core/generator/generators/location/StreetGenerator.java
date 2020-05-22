package com.automationframework.core.generator.generators.location;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.data.Assets;
import com.automationframework.core.generator.generators.basic.NaturalGenerator;
import com.automationframework.core.generator.model.location.StreetSuffix;
import com.automationframework.core.generator.Choose;
import com.automationframework.core.generator.Generator;
import com.automationframework.core.generator.generators.text.WordGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "street")
public class StreetGenerator extends Generator<String> {

    private WordGenerator wordGenerator;
    private NaturalGenerator nat;
    private String country;
    private boolean shortSuffix;
    private List<StreetSuffix> ukStreetSuffixes;
    private List<StreetSuffix> usStreetPrefixes;
    private List<StreetSuffix> all;
    private boolean houseNumber;

    public StreetGenerator() {
        this.wordGenerator = new WordGenerator();
        ukStreetSuffixes = Assets.UK_STREET_SUFFIXES.loadItems();
        usStreetPrefixes = Assets.US_STREET_SUFFIXES.loadItems();
        all = new ArrayList<>(ukStreetSuffixes);
        all.addAll(usStreetPrefixes);
        this.country = "all";
        this.nat = new NaturalGenerator();
    }

    /**
     * Use street suffixes from the United States
     *
     * @return The same data
     */
    public StreetGenerator us() {
        this.country = "us";
        return this;
    }


    /**
     * Use street suffixes from the United Kingdom
     *
     * @return The same data
     */
    public StreetGenerator uk() {
        this.country = "uk";
        return this;
    }

    /**
     * Set whether the street suffix to be appended is in
     * it's short version
     *
     * @param enable True for short suffix,
     *               False otherwise
     * @return The same data
     */
    public StreetGenerator shortSuffix(boolean enable) {
        this.shortSuffix = enable;
        return this;
    }

    /**
     * Set whether the street suffix to be appended is in
     * it's short version
     *
     * @return The same data
     */
    public StreetGenerator shortSuffix() {
        return shortSuffix(true);
    }

    /**
     * Append a house number to the front of the street
     *
     * @return The same data
     */
    public StreetGenerator houseNumber() {
        return houseNumber(true);
    }

    /**
     * Append a house number to the front of the street
     * @param enable True to append,
     *               False to not append
     * @return The same data
     */
    public StreetGenerator houseNumber(boolean enable) {
        this.houseNumber = enable;
        return this;
    }

    @Override
    public String gen() {
        String road = "";

        if (houseNumber) {
            road += nat.range(1,200).gen() + " ";
        }

        road += wordGenerator.capitalize().gen() + " ";
        StreetSuffix suffix;

        if (country.equals("uk")) {
            suffix = Choose.one(ukStreetSuffixes);
        } else if (country.equals("us")) {
            suffix = Choose.one(usStreetPrefixes);
        } else {
            suffix = Choose.one(all);
        }

        if (shortSuffix) {
            road += suffix.getShortVersion();
        } else {
            road += suffix.getLongVersion();
        }

        return road;
    }
}