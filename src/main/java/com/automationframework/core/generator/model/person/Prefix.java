package com.automationframework.core.generator.model.person;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public class Prefix {

    private String abbreviation;
    private String full;
    private String gender;

    public Prefix(String abbreviation, String full, String gender) {
        this.abbreviation = abbreviation;
        this.full = full;
        this.gender = gender;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getFull() {
        return full;
    }

    public String getGender() {
        return gender;
    }
}