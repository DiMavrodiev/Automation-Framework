package com.automationframework.core.element;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.api.driver.Driver;
import com.automationframework.api.interfaces.ElementShould;
import com.automationframework.api.interfaces.ElementWaitFor;
import com.automationframework.core.element.validator.VisibleShould;
import com.automationframework.core.element.waitfor.VisibleElementWaitFor;
import com.automationframework.api.context.SearchStrategy;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 *
 * Represents lookUpElement that is displayed for the user
 */

public class VisibleElement extends BaseElement {


    public VisibleElement(ElementData elementData) {

        super(elementData);
    }

    @Override
    public ElementShould should() {

        Driver driver = new Driver();
        return new VisibleShould(this, new ElementFluentWait<>(driver.getDriver(), new SearchStrategy()));
    }

    @Override
    public ElementWaitFor waitFor() {

        Driver driver = new Driver();
        return new VisibleElementWaitFor(this, new ElementFluentWait<>(driver.getDriver(), new SearchStrategy()));
    }
}