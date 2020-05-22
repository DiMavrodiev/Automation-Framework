package com.automationframework.core.generator.data;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public class StringMapper implements AssetMapper<String> {
    @Override
    public String map(String element) {
        return element;
    }
}