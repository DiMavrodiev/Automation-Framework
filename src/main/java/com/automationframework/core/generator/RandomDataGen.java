package com.automationframework.core.generator;

import com.automationframework.core.generator.generators.basic.*;
import com.automationframework.core.generator.generators.location.*;
import com.automationframework.core.generator.generators.money.*;
import com.automationframework.core.generator.generators.person.*;
import com.automationframework.core.generator.generators.text.*;


/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public final class RandomDataGen {

    public RandomDataGen() {

    }

    public static BirthdayGenerator birthday() {
        return new BirthdayGenerator();
    }

    public static AltitudeGenerator altitude() {
        return new AltitudeGenerator();
    }

    public static CountryGenerator country() {
        return new CountryGenerator();
    }

    public static ParagraphGenerator paragraph() {
        return new ParagraphGenerator();
    }

    public static FirstnameGenerator firstName() {
        return new FirstnameGenerator();
    }

    public static BoolGenerator bool() {
        return new BoolGenerator();
    }

    public static StringGenerator string() {
        return new StringGenerator();
    }

    public static  GenderGenerator gender() {
        return new GenderGenerator();
    }

    public static CityGenerator city() {
        return new CityGenerator();
    }

    public static PrefixGenerator prefix() {
        return new PrefixGenerator();
    }

    public static LatitudeGenerator latitude() {
        return new LatitudeGenerator();
    }

    public static CardNumberGenerator cardNo() {
        return new CardNumberGenerator();
    }

    public static ExpireDateGenerator expiryDate() {
        return new ExpireDateGenerator();
    }

    public static LoremGenerator lorem() {
        return new LoremGenerator();
    }

    public static CharacterGenerator character() {
        return new CharacterGenerator();
    }

    public static StreetGenerator street() {
        return new StreetGenerator();
    }

    public static GeohashGenerator geohash() {
        return new GeohashGenerator();
    }

    public static IssueDateGenerator issueDate() {
        return new IssueDateGenerator();
    }

    public static LongitudeGenerator longitude() {
        return new LongitudeGenerator();
    }

    public static SentenceGenerator sentence() {
        return new SentenceGenerator();
    }

    public static CVVGenerator cvv() {
        return new CVVGenerator();
    }

    public static NaturalGenerator natural() {
        return new NaturalGenerator();
    }

    public static SyllableGenerator syllable() {
        return new SyllableGenerator();
    }

    public static CoordinatesGenerator coordinates() {
        return new CoordinatesGenerator();
    }

    public static PostcodeGenerator postcode() {
        return new PostcodeGenerator();
    }

    public static CardTypeGenerator cardType() {
        return new CardTypeGenerator();
    }

    public static DoubleGenerator dbl() {
        return new DoubleGenerator();
    }

    public static FloatGenerator flt() {
        return new FloatGenerator();
    }

    public static LastnameGenerator lastname() {
        return new LastnameGenerator();
    }

    public static DepthGenerator depth() {
        return new DepthGenerator();
    }

    public static NameGenerator name() {
        return new NameGenerator();
    }

    public static DecimalGenerator decimal() {
        return new DecimalGenerator();
    }

    public static WordGenerator word() {
        return new WordGenerator();
    }

    public static CardGenerator card() {
        return new CardGenerator();
    }

    public static AgeGenerator age() {
        return new AgeGenerator();
    }
}