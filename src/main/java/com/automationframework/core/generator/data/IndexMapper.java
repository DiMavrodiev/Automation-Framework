package com.automationframework.core.generator.data;

import com.automationframework.core.generator.Tuple;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public interface IndexMapper<T> {

    Tuple<String, T> indexMap(T element);
}
