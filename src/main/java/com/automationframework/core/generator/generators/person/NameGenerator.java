package com.automationframework.core.generator.generators.person;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.model.person.Gender;
import com.automationframework.core.generator.Generator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "name")
public class NameGenerator extends Generator<String> {

    private LastnameGenerator last;
    private FirstnameGenerator first;
    private PrefixGenerator prefix;

    private boolean reverseOrder;
    private boolean withMiddleName;
    private boolean withPrefix;
    private boolean asCardName;

    private Gender gender;

    private String separator = " ";

    public NameGenerator() {
        this.last = new LastnameGenerator();
        this.first = new FirstnameGenerator();
        this.prefix = new PrefixGenerator();
    }

    /**
     * Set an option to print lastname first then the surname
     * @param enabled True for reverse order,
     *                False otherwise
     * @return The same data
     */
    public NameGenerator reverseOrder(boolean enabled) {
        this.reverseOrder = enabled;
        return this;
    }

    /**
     * Set an option to print lastname first then the surname
     * @return The same data
     */
    public NameGenerator reverseOrder() {
        return reverseOrder(true);
    }

    /**
     * Set an option to add a middlename
     * @param enabled True for middle name,
     *                False otherwise
     * @return The same data
     */
    public NameGenerator withMiddleName(boolean enabled) {
        this.withMiddleName = enabled;
        return this;
    }

    /**
     * Set an option to add a middlename
     * @return The same data
     */
    public NameGenerator withMiddleName() {
        return withMiddleName(true);
    }

    /**
     * Set the separator joining the name and the surname
     *
     * @param sep The separator to use as a string
     * @return The same data
     */
    public NameGenerator separator(String sep) {
        this.separator = sep;
        return this;
    }

    /**
     * Formats the name as the uppercase shortened names
     * typically found on cards
     * @param enabled True for a cardname,
     *                False otherwise
     * @return The same data
     */
    public NameGenerator cardName(boolean enabled) {
        this.asCardName = true;
        return this;
    }

    /**
     * Formats the name as the uppercase shortened names
     * typically found on cards
     *
     * @return The same data
     */
    public NameGenerator cardName() {
        return cardName(true);
    }

    /**
     * Add a prefix to the name (eg. Mr)
     * @param enabled True for prefix,
     *                False otherwise
     * @return The same data
     */
    public NameGenerator withPrefix(boolean enabled) {
        this.withPrefix = enabled;
        return this;
    }

    /**
     * Set the gender
     * @param gender The gender as a string,
     *               m for male, f for female
     * @return The same data
     */
    public NameGenerator gender(String gender) {
        this.first.gender(gender);
        this.prefix.gender(gender);
        return this;
    }

    /**
     * Set the gender
     * @param gender The gender as a {@link Gender} type
     * @return The same data
     */
    public NameGenerator gender(Gender gender) {
        this.first.gender(gender);
        this.prefix.gender(gender);
        return this;
    }


    /**
     * Add a prefix to the name (eg. Mr)
     * @return The same data
     */
    public NameGenerator withPrefix() {
        return withPrefix(true);
    }

    @Override
    public String gen() {
        String lastName = last.gen();
        String firstName = first.gen();
        String name;

        if (withMiddleName) {
            if (reverseOrder) {
                name = firstName + separator + last.gen() + separator + lastName;
            } else {
                name = lastName + separator + last.gen() + separator + firstName;
            }
        } else {
            if (reverseOrder) {
                name = lastName + separator + firstName;
            } else {
                name = firstName + separator + lastName;
            }
        }

        if (withPrefix) {
            name = prefix.gen() + separator + name;
        }

        if (asCardName) {
            name = formatAsCardName(name);
        }

        return name;

    }

    private String formatAsCardName(String name) {
        StringBuilder cardName = new StringBuilder(32);
        name = name.toUpperCase().replaceAll("[^A-Z]","");
        String[] parts = name.split(" ");
        int i = 0;
        for (String part : parts) {
            if ((part.length() > 6 || name.length() > 16) && i != parts.length - 1) {
                cardName.append(part.substring(0, 1));
                cardName.append(".");
            } else {
                cardName.append(part);
            }
            cardName.append(" ");
            i++;
        }

        name = cardName.toString();
        return name;
    }
}