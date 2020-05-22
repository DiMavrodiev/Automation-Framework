package com.automationframework.api.wait;

import com.automationframework.api.context.SearchStrategy;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 9/27/18
 *
 * General fluent waiter handling anything as an input parameter
 */

public class ElementFluentWait<T> extends FluentWait<T> {

    //flag showing that null should be returned instead of failing
    private boolean nullOnFailure;

    public ElementFluentWait(T input, SearchStrategy strategy) {

        super(input);
        withTimeout(strategy.getFluentTimeout());
        pollingEvery(strategy.getFluentInterval());
        this.nullOnFailure = strategy.isNullOnFailure();
    }

    public <R> R waitFor(Function<? super T, R> condition) {

        try {
            return until(condition);
        } catch (Throwable ignoredAndContinue) {
            if (nullOnFailure) {
                //TODO add some logging here if necessary
                return null;
            } else {
                throw new AssertionError("Condition: " + condition.toString() + " failed!");
            }
        }
    }
}