package com.automationframework.core.generator.generators.basic;

import com.automationframework.core.generator.Generator;
import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.model.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "natural")
public class NaturalGenerator extends Generator<Integer>{

    private int min;
    private int max;

    public NaturalGenerator() {
        this.max = Integer.MAX_VALUE - 1;
        this.min = 0;
    }

    /**
     * Set the minimum value (inclusive)
     *
     * @param min The minimum value
     * @return The same data
     */
    public NaturalGenerator min(int min) {
        this.min = min;
        return this;
    }

    /**
     * Set the maximum value to return (inclusive)
     *
     * @param max The maximum value to return (inclusive)
     * @return The same data
     */
    public NaturalGenerator max(int max) {
        this.max = max;
        return this;
    }

    /**
     * Set a min/max range
     *
     * @param min Minimum value to be returned (inclusive)
     * @param max Maximum value to be returned (inclusive)
     * @return The same data
     */
    public NaturalGenerator range(int min, int max) {
        min(min);
        max(max);
        return this;
    }

    /**
     * Set a range starting from 0
     *
     * @param max Maximum value to be returned (inclusive)
     * @return The same data
     */
    public NaturalGenerator range(int max) {
        min(0);
        max(max - 1);
        return this;
    }

    public NaturalGenerator range(Range<Integer> rangeOption) {
        min(rangeOption.getMin());
        max(rangeOption.getMax());
        return this;
    }

    /**
     * Retrieve n uniform samples from population without replacement
     * @param sampleSize Number of samples to retrieve
     * @param population The population size
     * @return A list of integer samples
     */
    public List<Integer> sample(int sampleSize, int population) {
        int t = 0, m =0;
        List<Integer> samples = new ArrayList<>();

        while (m < sampleSize) {
            double u = random().randDouble();
            if ((population -t) * u >= sampleSize -m) {
                t++;
            } else {
                samples.add(t);
                t++; m++;
            }
        }
        return samples;
    }

    @Override
    public Integer gen() {
        return random().randInt((max - min) + 1) + min;
    }
}