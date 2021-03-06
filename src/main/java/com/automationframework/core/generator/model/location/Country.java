package com.automationframework.core.generator.model.location;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public class Country {

    private String name;
    private String prefix;
    private String postalFormat;
    private boolean postalFixed;

    public Country(String name, String prefix, String postalFormat, boolean postalFixed) {
        this.name = name;
        this.prefix = prefix;
        this.postalFormat = postalFormat;
        this.postalFixed = postalFixed;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostalFormat() {
        return postalFormat;
    }

    public boolean isPostalFixed() {
        return postalFixed;
    }
}