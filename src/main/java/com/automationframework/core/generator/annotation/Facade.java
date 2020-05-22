package com.automationframework.core.generator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Facade {

    String accessor();
}