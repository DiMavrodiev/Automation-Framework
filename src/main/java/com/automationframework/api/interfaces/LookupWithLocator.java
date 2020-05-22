package com.automationframework.api.interfaces;


/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/13/18
 *
 * Basic interface for finding objects by locator {@link String} of a generic type
 * @param <T> - object type to find
 */

public interface LookupWithLocator<T> {

    T find(String locator);

    T exist(String locator);
}