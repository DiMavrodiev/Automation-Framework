package com.automationframework.core.generator.generators.location;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.data.Assets;
import com.automationframework.core.generator.model.location.Country;
import com.automationframework.core.generator.Choose;
import com.automationframework.core.generator.Generator;

import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "country")
public class CountryGenerator extends Generator<String> {

    private List<Country> countries;
    private boolean prefix;

    public CountryGenerator() {
        this.countries = Assets.COUNTRIES.loadItems();
        this.prefix = false;
    }

    /**
     * Return a country prefix
     * @return The same data
     */
    public CountryGenerator prefix() {
        return prefix(true);
    }


    /**
     * Return a country prefix
     * @param enabled True to return prefix,
     *                False otherwise
     * @return The same data
     */
    public CountryGenerator prefix(boolean enabled) {
        this.prefix = enabled;
        return this;
    }

    /**
     * Generate as a {@link Country} object
     * @return The country object
     */
    public Country genAsCountry(){
        return Choose.one(countries);
    }

    @Override
    public String gen() {
        Country country = Choose.one(countries);
        if (prefix) {
            return country.getPrefix();
        } else {
            return country.getName();
        }
    }
}