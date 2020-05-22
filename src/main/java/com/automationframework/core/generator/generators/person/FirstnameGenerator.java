package com.automationframework.core.generator.generators.person;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.data.Assets;
import com.automationframework.core.generator.model.person.Gender;
import com.automationframework.core.generator.Choose;
import com.automationframework.core.generator.Generator;

import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "firstName")
public class FirstnameGenerator extends Generator<String> {
    private List<String> maleNames;
    private List<String> femaleNames;
    private Gender gender;
    private GenderGenerator genderGen;

    public FirstnameGenerator() {
        this.maleNames = Assets.MALE_FIRSTNAMES.loadItems();
        this.femaleNames = Assets.FEMALE_FIRSTNAMES.loadItems();
        this.genderGen = new GenderGenerator();
    }

    /**
     * Set the gender
     * @param gender The gender as a {@link Gender} type
     * @return The same data
     */
    public FirstnameGenerator gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    /**
     * Set the gender
     * @param gender The gender as a string,
     *               m for male, f for female
     * @return The same data
     */
    public FirstnameGenerator gender(String gender) {
        if (gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("male")) {
            this.gender = Gender.MALE;
        } else if (gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("female")) {
            this.gender = Gender.FEMALE;
        }
        return this;
    }

    @Override
    public String gen() {
        Gender _gender;
        if (gender == null){
            _gender = genderGen.genAsGender();
        } else {
            _gender = gender;
        }

        if (_gender == Gender.MALE) {
            return Choose.one(maleNames);
        } else {
            return Choose.one(femaleNames);
        }
    }
}