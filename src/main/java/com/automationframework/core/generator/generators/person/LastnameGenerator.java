package com.automationframework.core.generator.generators.person;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.data.Assets;
import com.automationframework.core.generator.Choose;
import com.automationframework.core.generator.Generator;

import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "lastname")
public class LastnameGenerator extends Generator<String> {
    private List<String> names;

    public LastnameGenerator() {
        this.names = Assets.NEUTRAL_SURNAMES.load().getItems();
    }

    @Override
    public String gen() {
        return Choose.one(names);
    }
}