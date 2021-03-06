package com.automationframework.core.generator.generators.text;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.generators.basic.NaturalGenerator;
import com.automationframework.core.generator.CharUtils;
import com.automationframework.core.generator.Generator;


/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "word")
public class WordGenerator extends Generator<String> {

    private NaturalGenerator nat;
    private SyllableGenerator syl;
    private boolean capitalize;
    private int syllablesMin;
    private int syllablesMax;
    private int length;

    public WordGenerator() {
        this.syllablesMin = 1;
        this.syllablesMax = 3;
        this.length = -1;
        this.capitalize = false;
        this.syl = new SyllableGenerator();
        this.nat = new NaturalGenerator();
    }

    public WordGenerator setSyl(SyllableGenerator syl) {
        this.syl = syl;
        return this;
    }

    /**
     * Set the length of the word to return
     *
     * @param length The length of the word to return
     * @return The same data
     */
    public WordGenerator length(int length) {
        this.length = length;
        return this;
    }

    /**
     * Set the number of syllables to return
     *
     * @param syllables Number of syllables to return
     * @return The same data
     */
    public WordGenerator syllables(int syllables) {
        this.syllablesMin = syllables;
        this.syllablesMax = -1;
        return this;
    }

    /**
     * Set the number of syllables to return
     *
     * @param min Minimum number of syllables to return
     * @param max Maximum number of syllables to return
     * @return The same data
     */
    public WordGenerator syllables(int min, int max) {
        this.syllablesMin = min;
        this.syllablesMax = max;
        return this;
    }

    /**
     * Capitalize the first letter of the word
     * @return The same data
     */
    public WordGenerator capitalize() {
        return capitalize(true);
    }

    /**
     * Capitalize the first letter of the word
     * @param enable True to capitalize
     *               False otherwise
     * @return The same data
     */
    public WordGenerator capitalize(boolean enable) {
        this.capitalize = enable;
        return this;
    }

    @Override
    public String gen() {
        StringBuilder sbr = new StringBuilder(8);
        String result;

        if (length > 0) {
            while (sbr.length() < length) {
                sbr.append(syl.gen());
            }
            result = sbr.substring(0, length);
        } else {
            int syllables;
            if (syllablesMax == -1) {
                syllables = syllablesMin;
            } else {
                syllables = nat.range(syllablesMin, syllablesMax).gen();
            }
            for (int i = 0; i < syllables; i++) {
                sbr.append(syl.gen());
            }

            result = sbr.toString();
        }

        if (capitalize) {
            return CharUtils.capitalize(result);
        }

        return result;

    }
}