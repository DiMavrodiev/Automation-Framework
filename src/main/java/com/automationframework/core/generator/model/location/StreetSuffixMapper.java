package com.automationframework.core.generator.model.location;

import com.automationframework.core.generator.data.AssetMapper;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public class StreetSuffixMapper implements AssetMapper<StreetSuffix> {

    @Override
    public StreetSuffix map(String element) {
        String[] parts = element.split(":");
        return new StreetSuffix(parts[0], parts[1]);
    }}