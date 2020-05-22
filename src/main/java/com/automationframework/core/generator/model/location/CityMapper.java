package com.automationframework.core.generator.model.location;

import com.automationframework.core.generator.data.AssetMapper;
import com.automationframework.core.generator.Tuple;
import com.automationframework.core.generator.data.IndexMapper;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public class CityMapper implements AssetMapper<City>, IndexMapper<City> {


    @Override
    public City map(String element) {
        String[] parts = element.split(",");

        return new City(parts[1], parts[0], parts[2], parts[3]);
    }


    @Override
    public Tuple<String, City> indexMap(City element) {
        return Tuple.from(element.getCountry(), element);
    }
}