package com.automationframework.core.url;

import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.core.conditions.ConditionFactory;
import com.automationframework.api.driver.Driver;
import com.automationframework.api.context.SearchStrategy;
import org.apache.xpath.operations.String;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 11/9/18
 *
 * Finding an URL or title with lookUp
 */

public class UrlAndTitleLookup {

    private final ElementFluentWait<Driver> fluentWait;
    private final ConditionFactory conditionFactory;

    public UrlAndTitleLookup(Driver driver) {

        this.fluentWait = new ElementFluentWait<>(driver, new SearchStrategy());
        this.conditionFactory = new ConditionFactory();
    }

    public String getUrl(String url) {

        String currentUrl;
        try {
            currentUrl = fluentWait.waitFor(conditionFactory.get().url(url));
        } catch (AssertionError ignoreUrl) {
            currentUrl = null;
        }
        if (currentUrl == null) {
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return null;
            }
        }
        return currentUrl;
    }

    public boolean switchTabByUrl(String URL) {

        boolean tab;
        try {
            tab = fluentWait.waitFor(conditionFactory.get().findAndSwitchTabByUrl(URL));
        } catch (AssertionError ignoreUrl) {
            tab = false;
        }
        if (!tab) {
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return false;
            }
        }
        return tab;
    }

    public boolean switchTabByTitle(String title) {

        boolean tab;
        try {
            tab = fluentWait.waitFor(conditionFactory.get().findAndSwitchTabByTitle(title));
        } catch (AssertionError ignoreTitle) {
            tab = false;
        }
        if (!tab) {
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return false;
            }
        }
        return tab;
    }

    public boolean switchByPartialUrl(String partialUrl) {

        boolean tab;
        try {
            tab = fluentWait.waitFor(conditionFactory.get().findAndSwitchTabByPartialUrl(partialUrl));
        } catch (AssertionError ignoreUrl) {
            tab = false;
        }
        if (!tab) {
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return false;
            }
        }
        return tab;
    }

    public boolean isPageLoaded(String url) {

        boolean loaded;
        try {
            loaded = fluentWait.waitFor(conditionFactory.get().pageLoaded(url));
        } catch (AssertionError ignored) {
            loaded = false;
        }
        if (!loaded) {
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return false;
            }
        }
        return loaded;
    }

    public String urlContains(String urlContains) {

        String currentUrl;
        try {
            currentUrl = fluentWait.waitFor(conditionFactory.get().urlContains(urlContains));
        } catch (AssertionError ignoreUrlContains) {
            currentUrl = null;
        }
        if (currentUrl == null) {
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return null;
            }
        }
        return currentUrl;
    }

    public String getTitle(String title) {

        String currentTitle;
        try {
            currentTitle = fluentWait.waitFor(conditionFactory.get().title(title));
        } catch (AssertionError ignoreTitle) {
            currentTitle = null;
        }
        if (currentTitle == null) {
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return null;
            }
        }
        return currentTitle;
    }

    public String titleContains(String partialTitle) {

        String currentTitle;
        try {
            currentTitle = fluentWait.waitFor(conditionFactory.get().titleContains(partialTitle));
        } catch (AssertionError ignoreTitleContains) {
            currentTitle = null;
        }
        if (currentTitle == null) {
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return null;
            }
        }
        return currentTitle;
    }

    public boolean isUrlChanged(String url1, String url2) {

        boolean currentUrl;
        try {
            currentUrl = fluentWait.waitFor(conditionFactory.get().isUrlChanged(url1, url2));
        } catch (AssertionError ignoreUrl) {
            currentUrl = false;
        }
        if (!currentUrl) {
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return false;
            }
        }
        return currentUrl;
    }

    public boolean isTitleChanged(String title1, String title2) {

        boolean currentTitle;
        try {
            currentTitle = fluentWait.waitFor(conditionFactory.get().isTitleChanged(title1, title2));
        } catch (AssertionError ignoreTitle) {
            currentTitle = false;
        }
        if (!currentTitle) {
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return false;
            }
        }
        return currentTitle;
    }
}