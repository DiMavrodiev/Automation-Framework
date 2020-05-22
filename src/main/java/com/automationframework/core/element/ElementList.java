package com.automationframework.core.element;

import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.api.driver.Driver;
import com.automationframework.api.interfaces.ListShould;
import com.automationframework.core.element.validator.EmptyListShould;
import com.automationframework.core.element.validator.GeneralListShould;
import com.automationframework.api.context.SearchStrategy;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/8/18
 *
 * Represents list of Elements
 * Basically decorates List adding elements-related behavior
 */

public class ElementList implements List<WebElement> {

    private final List<WebElement> elements;
    private final String  locator;

    public ElementList(List<WebElement> elements, String locator) {
        this.elements = elements;
        this.locator = locator;
    }

    public ElementList(String locator) {

        this(null, locator);
    }

    public ListShould should() {

        return should(new SearchStrategy());
    }

    public ListShould should(SearchStrategy strategy) {
        ListShould should;
        Driver driver = new Driver();
        if (this.size() == 0) {
            should = new EmptyListShould(this, new ElementFluentWait<>(driver.getDriver(), strategy));
        } else {
            should = new GeneralListShould(this, new ElementFluentWait<>(driver.getDriver(), strategy));
        }
        return should;
    }

    public String  getLocator() {

        return locator;
    }

    @Override
    public int size() {

        return elements.size();
    }

    @Override
    public boolean isEmpty() {

        return elements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {

        return elements.contains(o);
    }

    @Override
    public Iterator<WebElement> iterator() {

        return elements.iterator();
    }

    @Override
    public Object[] toArray() {

        return elements.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {

        return elements.toArray(a);
    }

    @Override
    public boolean add(WebElement element) {

        return elements.add(element);
    }

    @Override
    public boolean remove(Object o) {

        return elements.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends WebElement> c) {

        return elements.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends WebElement> c) {

        return elements.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        return elements.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        return elements.retainAll(c);
    }

    @Override
    public void clear() {

        elements.clear();
    }

    @Override
    public WebElement get(int index) {

        return elements.get(index);
    }

    @Override
    public WebElement set(int index, WebElement element) {

        return elements.set(index, element);
    }

    @Override
    public void add(int index, WebElement element) {

        elements.add(index, element);
    }

    @Override
    public WebElement remove(int index) {

        return elements.remove(index);
    }

    @Override
    public int indexOf(Object o) {

        return elements.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {

        return elements.lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<WebElement> listIterator() {

        return elements.listIterator();
    }

    @NotNull
    @Override
    public ListIterator<WebElement> listIterator(int index) {

        return elements.listIterator(index);
    }

    @NotNull
    @Override
    public List<WebElement> subList(int fromIndex, int toIndex) {

        return elements.subList(fromIndex, toIndex);
    }
}