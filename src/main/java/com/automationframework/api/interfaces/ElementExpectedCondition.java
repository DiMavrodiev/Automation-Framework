package com.automationframework.api.interfaces;

import com.automationframework.api.driver.Driver;
import com.google.common.base.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 1/18/19
 */

public interface ElementExpectedCondition<T> extends Function<Driver, T> {
}