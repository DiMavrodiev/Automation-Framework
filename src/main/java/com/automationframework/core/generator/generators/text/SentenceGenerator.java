package com.automationframework.core.generator.generators.text;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.generators.basic.NaturalGenerator;
import com.automationframework.core.generator.CharUtils;
import com.automationframework.core.generator.Choose;
import com.automationframework.core.generator.Constants;
import com.automationframework.core.generator.Generator;

import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "sentence")
public class SentenceGenerator extends Generator<String> {
    private WordGenerator wordGen;
    private NaturalGenerator nat;
    private int wordsMin;
    private int wordsMax;
    private boolean punctuation;

    public SentenceGenerator() {
        this.wordGen = new WordGenerator();
        this.nat = new NaturalGenerator();
        this.wordsMin = new NaturalGenerator().range(12,18).gen();
        this.wordsMax = -1;
    }

    /**
     * Generate a sentence with this amount of words
     *
     * @param words Amount of words in the sentence
     * @return The same data
     */
    public SentenceGenerator words(int words) {
        this.wordsMin = words;
        this.wordsMax = -1;
        return this;
    }

    /**
     * Set the minimum/maximum words in a sentence
     *
     * @param min Minimum number of words
     * @param max Maximum number of words
     * @return The same data
     */
    public SentenceGenerator words(int min, int max) {
        this.wordsMin = min;
        this.wordsMax = max;
        return this;
    }


    /**
     * Enable extended punctuation (include ?,!,'s,') in sentence
     * @param punctuation true for enabled
     * @return The same data
     */
    public SentenceGenerator punctuation(boolean punctuation) {
        this.punctuation = punctuation;
        return this;
    }

    /**
     * Enable extended punctuation (include ?,!,'s,') in sentence
     * @return The same data
     */
    public SentenceGenerator punctuation() {
        return punctuation(true);
    }


    @Override
    public String gen() {
        int toGenerate;
        // If a range is set (wordsMax != -1)
        // we generate a random range of words
        if (wordsMax == -1){
            toGenerate = this.wordsMin;
        } else {
            toGenerate = nat.range(wordsMin, wordsMax).gen();
        }

        List<String> words = wordGen.genMany(toGenerate);

        // Capitalize the first word
        words.set(0, CharUtils.capitalize(words.get(0)));

        // If extended punctuation is set, we step in and add
        // punc. at various points
        if (punctuation) {
            int size = words.size();
            StringBuilder sbr = new StringBuilder(size * 2);

            for (int i = 1; i <= size; i++) {
                // Append a word
                sbr.append(words.get(i - 1));

                // Randomly at punc. at the end of it
                if (i != size && nat.range(1,6).gen() == 1){
                    sbr.append(Choose.one(Constants.midPunc));
                }

                if (i != size){
                    sbr.append(" ");
                }
            }

            // One in five times we change from full-stop to something else
            if (nat.range(1,5).gen() == 1){
                sbr.append(Choose.one(Constants.endPunc));
            } else {
                sbr.append(".");
            }

            return sbr.toString();
        } else {

            return CharUtils.join(words, " ", ".");
        }

    }
}