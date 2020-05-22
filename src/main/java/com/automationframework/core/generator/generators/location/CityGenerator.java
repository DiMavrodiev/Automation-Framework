package com.automationframework.core.generator.generators.location;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.data.Assets;
import com.automationframework.core.generator.model.location.City;
import com.automationframework.core.generator.Choose;
import com.automationframework.core.generator.Generator;

import java.util.List;
import java.util.Map;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "city")
public class CityGenerator extends Generator<String> {
    private final List<City> cities;
    private final Map<String, List<City>> citiesMap;
    private String country;


    public CityGenerator() {
        this.cities = Assets.CITIES.loadItems();
        this.citiesMap = Assets.CITIES.load().getGroupingIndex();
    }

    /**
     * Return a city from the given country
     * @param country The given country
     * @return The same data
     */
    public CityGenerator country(String country) {
        this.country = country;
        return this;
    }

    @Override
    public String gen() {
        if (country != null && citiesMap.get(country) != null) {
            List<City> list = citiesMap.get(country);
            return Choose.one(list).getName();
        }
        return Choose.one(cities).getName();
    }
}