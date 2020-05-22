package com.automationframework.core.generator.data;


import com.automationframework.core.generator.model.location.*;
import com.automationframework.core.generator.model.person.Prefix;
import com.automationframework.core.generator.model.person.PrefixMapper;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public class Assets {

    public static AssetDescriptor<City> CITIES = new AssetDescriptor<>("cities", new CityMapper(), null, new CityMapper());
    public static AssetDescriptor<Country> COUNTRIES = new AssetDescriptor<>("country", new CountryMapper(), new CountryMapper());
    public static AssetDescriptor<StreetSuffix> UK_STREET_SUFFIXES = new AssetDescriptor<>("uk/street_suffixes", new StreetSuffixMapper());
    public static AssetDescriptor<StreetSuffix> US_STREET_SUFFIXES = new AssetDescriptor<>("us/street_suffixes", new StreetSuffixMapper());
    public static AssetDescriptor<String> NEUTRAL_SURNAMES = new AssetDescriptor<>("neutral/surnames", new StringMapper());
    public static AssetDescriptor<String> MALE_FIRSTNAMES = new AssetDescriptor<>("male/firstnames", new StringMapper());
    public static AssetDescriptor<String> FEMALE_FIRSTNAMES = new AssetDescriptor<>("female/firstnames", new StringMapper());
    public static AssetDescriptor<Prefix> NEUTRAL_PREFIXES = new AssetDescriptor<>("neutral/prefixes", new PrefixMapper());
    public static AssetDescriptor<Prefix> MALE_PREFIXES = new AssetDescriptor<>("male/prefixes", new PrefixMapper());
    public static AssetDescriptor<Prefix> FEMALE_PREFIXES = new AssetDescriptor<>("female/prefixes", new PrefixMapper());
    public static AssetDescriptor<String> LOREM = new AssetDescriptor<>("lorem", new StringMapper());
}