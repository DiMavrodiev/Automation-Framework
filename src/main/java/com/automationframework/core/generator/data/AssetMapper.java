package com.automationframework.core.generator.data;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public interface AssetMapper<T> {

    T map(String element);
}