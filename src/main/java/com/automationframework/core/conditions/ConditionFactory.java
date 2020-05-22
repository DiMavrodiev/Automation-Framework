package com.automationframework.core.conditions;

import com.automationframework.api.interfaces.Conditions;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/4/18
 */

public class ConditionFactory {


    public ConditionFactory() {

    }

    public Conditions get() {

        return new FirstFound();
    }


}