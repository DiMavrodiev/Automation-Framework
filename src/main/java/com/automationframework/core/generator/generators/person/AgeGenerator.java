package com.automationframework.core.generator.generators.person;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.generators.basic.NaturalGenerator;
import com.automationframework.core.generator.model.person.PersonType;
import com.automationframework.core.generator.Generator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "age")
public class AgeGenerator extends Generator<Integer> {

    private PersonType personType;
    private NaturalGenerator natGen;


    public AgeGenerator() {
        this.natGen = new NaturalGenerator();
        this.personType = PersonType.GENERIC;
    }

    /**
     * Return the age of a child
     * @return The same data
     */
    public AgeGenerator child() {
        this.personType = PersonType.CHILD;
        return this;
    }

    /**
     * Return the age of an adult
     * @return The same data
     */
    public AgeGenerator adult() {
        this.personType = PersonType.ADULT;
        return this;
    }

    /**
     * Return the age of a teen
     * @return The same data
     */
    public AgeGenerator teen() {
        this.personType = PersonType.TEEN;
        return this;
    }

    /**
     * Return the age of a senior
     * @return The same data
     */
    public AgeGenerator senior() {
        this.personType = PersonType.SENIOR;
        return this;
    }

    /**
     * Return the age of given person type
     * @param type The person type to generate an age for
     * @return The same data
     */
    public AgeGenerator personType(PersonType type) {
        this.personType = type;
        return this;
    }

    @Override
    public Integer gen() {
        return natGen.range(this.personType.getMin(), this.personType.getMax()).gen();
    }
}