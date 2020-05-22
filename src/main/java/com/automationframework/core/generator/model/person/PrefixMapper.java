package com.automationframework.core.generator.model.person;

import com.automationframework.core.generator.data.AssetMapper;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public class PrefixMapper implements AssetMapper<Prefix> {
    @Override
    public Prefix map(String element) {
        String[] parts = element.split(";");
        return new Prefix(parts[0], parts[1], parts[2]);
    }
}